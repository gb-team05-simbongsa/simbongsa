package com.app.simbongsa.service.member;

import com.app.simbongsa.domain.MailDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.search.admin.AdminMemberSearch;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("member") @Primary
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.MEMBER);
        memberRepository.save(toMemberEntity(memberDTO));
    }

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return new UserDetail(member);
    }

//    메인페이지 유저랭킹 목록
    @Override
    public List<MemberDTO> getMemberRankingList() {
            List<Member> members = memberRepository.findMemberWithVolunteerTime();
            List<MemberDTO> memberDTOS = new ArrayList<>();
            for(Member member : members){
                MemberDTO memberDTO = toMemberDTO(member);
                memberDTOS.add(memberDTO);
            }
        return memberDTOS;
    }

    @Override
    public Page<MemberDTO> getMembers(Integer page, AdminMemberSearch adminMemberSearch) {
        Page<Member> members = memberRepository.findAllWithPaging(adminMemberSearch, PageRequest.of(page, 5));
        List<MemberDTO> memberDTOS = members.getContent().stream().map(this::toMemberDTO).collect(Collectors.toList());
        return new PageImpl<>(memberDTOS, members.getPageable(), members.getTotalElements());
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        return toMemberDTO(memberRepository.findById(id).get());
    }

    @Override
    public MemberDTO getMemberByEmail(String memberEmail) {
        Optional<Member> member = memberRepository.findByMemberEmail(memberEmail);
        return member.map(this::toMemberDTO).orElse(null);
    }

    @Override
    @Transactional
    public void updateStatusByIds(List<Long> ids) {
        ids.forEach(id -> {
            MemberStatus memberStatus;
            memberRepository.findById(id).get().getMemberStatus();
            memberStatus = memberRepository.findById(id).get().getMemberStatus() == MemberStatus.가입 ? MemberStatus.탈퇴 : MemberStatus.가입;
            memberRepository.updateMemberStatus(id, memberStatus);
        });
    }

    /* 카카오 토큰 접근 */
    @Override
    public List<Member> getSupportList(Long id) {
        return memberRepository.findSupportByRequestId(id);
    }

    public String getKaKaoAccessToken(String code, String type){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=7d1b8d2a934aea81ad94f34bb0bee10c"); // TODO REST_API_KEY 입력

//            회원가입에서 접근했을 때
            if(type.equals("join")) {
                sb.append("&redirect_uri=http://localhost:10000/member/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            } else if (type.equals("login")) {
//            로그인에서 접근했을 때
                log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~로그인이 맞워요~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                sb.append("&redirect_uri=http://localhost:10000/member/kakao-login"); // TODO 인가코드 받은 redirect_uri 입력
            }
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);
            log.info("refresh_token : " + refresh_Token);
            log.info("sb : " + sb);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }


    /* 카카오 사용자 정보 불러오기 */
    public MemberDTO getKakaoInfo(String token) throws Exception {

        MemberDTO kakaoInfo = new MemberDTO();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String userEmail = "";
            if(hasEmail){
                userEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            log.info("id : " + id);
            log.info("email : " + userEmail);
            kakaoInfo.setMemberEmail(userEmail);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return kakaoInfo;
    }

    /*이메일 중복 검사*/
    @Override
    public Long overlapByMemberEmail(String memberEmail) {
        return memberRepository.overlapByMemberEmail(memberEmail);
    }

    /* 랜덤키생성 */
    @Override
    public String randomKey() {
        Random random = new Random();
        String randomNum = "";

        for(int i = 0; i < 6; i++) {
            String number = Integer.toString(random.nextInt(10));
            randomNum += number;
        }

        return randomNum;
    }

    /* 랜덤키 변경 */
    @Override
    public void updateRandomKey(String memberEmail, String randomKey) {
        memberRepository.updateRandomKey(memberEmail,randomKey);
    }

    /* 메일보내기 */
    @Override
    public void sendMail(MailDTO mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom("simbongsa300@gmail.com");
//        from 값을 설정하지 않으면 application.yml의 username값이 설정됩니다.
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());

        mailSender.send(message);
    }

    /* 비밀번호변경 */
    @Override
    public void updatePassword(String memberEmail, String memberPassword) {
        log.info(memberPassword + "===============================");
        log.info(memberEmail + "===================================");
       memberRepository.updatePassword(memberEmail, memberPassword);
    }
}
