package com.app.simbongsa.repository.rice;

import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.type.RicePaymentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class RicePaymentRepositoryTests {
    @Autowired
    private RicePaymentRepository ricePaymentRepository;

    @Autowired
    private MemberRepository memberRepository;

//    더미데이터 넣기
    @Test
    public void saveTest() {
        for(int i = 1; i <= 2; i++) {
            RicePayment ricePayment = new RicePayment(300 + i, RicePaymentType.충전, "국민", "123412-1234123" + i, memberRepository.findById(595L).get());
            ricePaymentRepository.save(ricePayment);
        }
        for(int i = 1; i <= 2; i++) {
            RicePayment ricePayment = new RicePayment(300 + i, RicePaymentType.환전승인, "국민", "123412-1234123" + i, memberRepository.findById(595L).get());
            ricePaymentRepository.save(ricePayment);
        }
        for(int i = 1; i <= 10; i++) {
            RicePayment ricePayment = new RicePayment(300 + i, RicePaymentType.사용, "국민", "123412-1234123" + i, memberRepository.findById(595L).get());
            ricePaymentRepository.save(ricePayment);
        }
    }

//    공양미 충전 내역 전체 조회(상태에 따름)
    @Test
    public void findByPaymentStatusWithPaging() {
        AdminPaymentSearch adminPaymentSearch = new AdminPaymentSearch();
//        adminPaymentSearch.setRicePaymentUsed(301);
        adminPaymentSearch.setMemberEmail("5");
        Page<RicePayment> foundRicePayment = ricePaymentRepository.findByPaymentStatusWithPaging(adminPaymentSearch, RicePaymentType.충전, PageRequest.of(0, 5));
        foundRicePayment.stream().map(RicePayment::toString).forEach(log::info);
        log.info("=========================" + foundRicePayment.getTotalElements());
    }

//    공양미 충전 내역 상세보기(충전한 사람 포함)
    @Test
    public void findByIdTest() {
        ricePaymentRepository.findById(149L).ifPresent(ricePayment -> log.info(ricePayment.getMember().getMemberName()));
    }

//    금일 결제 수 조회
    @Test
    public void findByCreateDateToday() {
        log.info("=========================" + ricePaymentRepository.findByCreateDateToday());
    }

//    결제 총 금액 조회
    @Test
    public void findAllPaymentTypeChargeTest() {
        log.info("=========================" + ricePaymentRepository.findAllPaymentTypeCharge().stream().map(RicePayment::getRicePaymentUsed).mapToInt(used -> used).sum());
    }

//    공양미 내역 삭제(요청 내역 삭제)
    @Test
    public void deleteTest() {
        Long[] ids = {142L, 143L};
        ricePaymentRepository.deleteAllById(Arrays.asList(ids));
    }

//    환전 요청 상태 승인으로 변경
    @Test
    public void updatePaymentStatusToAccessByIdsTest() {
        ricePaymentRepository.updatePaymentStatusToAccessByIds(Arrays.asList(405L, 406L));
    }
//    공양미 조회
    @Test
    public void findByIdWithPayment(){
        memberRepository.findById(715L).ifPresent(member -> log.info(String.valueOf(member.getMemberRice())));
    }
//    공양미 후원
//    @Test
//    public void updatePaymentByMemberIdTest(){
//         ricePaymentRepository.updatePaymentByMemberId(716L,3000);
//
//         ricePaymentRepository.findById(716L).ifPresent(ricePayment -> log.info("================== 공양미 사용량 "+ricePayment.getRicePaymentUsed() + "==========="));
//         ricePaymentRepository.findById(716L).ifPresent(ricePayment -> log.info("================== 공양미 남은 "+ricePayment.getMember().getMemberRice() + " =========="));
//    }



//    @Test
//    public void updatePaymentByMemberIdAndSupportGongyangTest(){
//        ricePaymentRepository.updatePaymentByMemberIdAndSupportGongyang(716L, 3000);
//
//    }

    /* 세션에 담긴 id 값 받아와서 내 공양미 조회(페이징) */
/*      @Test
      public void findByMemberId(){
          PageRequest pageRequest = PageRequest.of(0,3);
          UserDetail userDetail = new UserDetail();
          userDetail.setId(595L);
          Page<RicePayment> ricePayments = ricePaymentRepository.findByMemberId(pageRequest, userDetail);
          ricePayments.stream().map(RicePayment::toString).forEach(log::info);
          ricePayments.stream().map(RicePayment::getMember).forEach(v -> log.info(v.toString() + "놔아아아아아아아라라라라라라라라라라ㅏ라"));
          log.info("====================유저 아이디 565의 내 공양미 내역 수=================" + ricePayments.getTotalElements());
      }*/

}
