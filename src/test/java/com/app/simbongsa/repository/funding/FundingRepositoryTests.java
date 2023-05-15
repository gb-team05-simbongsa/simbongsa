package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.file.FundingFile;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingItem;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import com.app.simbongsa.type.FundingCategoryType;
import com.app.simbongsa.type.RequestType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FundingRepositoryTests {
    @Autowired
    private FundingRepository fundingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FundingFileRepository fundingFileRepository;

    @Autowired
    private  FundingGiftRepository fundingGiftRepository;

    @Test
    public void saveTest() {

        for (int i = 1; i <= 30; i++) {
            FundingCreator fundingCreator = new FundingCreator("test" + i,"test Introduce" + i,"test","test");
//            Funding funding = new Funding(FundingCategoryType.승인
//                    , "큰제목"
//                    , "소제목"
//                    , "안녕하세요"
//                    , 10000 + 100 * i
//                    , 3000 + 100 * i
//                    , LocalDateTime.now()
//                    , LocalDateTime.now()
//                    ,"어서오세요"
//                    ,"안녕하십니까"
//                    ,"하이하이"
//                    ,"헬로헬로"
//                    ,fundingCreator);
//                    ,memberRepository.findById(1L).get());

//            fundingRepository.save(funding);
        }

    }
    //    메인페이지 달성률로 인기펀딩 목록 조회
    @Test
    public void findAllWithPopularTest(){
        fundingRepository.findAllWithPopular().stream()
                .map(Funding::toString)
                .forEach(log::info);
    }

    //    펀딩 전체 조회(페이징)
    @Test
    public void findAllWithPagingTest() {
        AdminFundingSearch adminFundingSearch = new AdminFundingSearch();
//        adminFundingSearch.setCreatorNickName("5");
        adminFundingSearch.setFundingTitle("큰제목");
        Page<Funding> foundFunding = fundingRepository.findAllWithPaging(adminFundingSearch, PageRequest.of(0, 5));
        foundFunding.stream().map(Funding::toString).forEach(log::info);
        log.info("==========================" + foundFunding.getTotalElements());
    }

    //  펀딩 상세보기
    @Test
    public void findByIdTest() {
        fundingRepository.findByIdForDetail(143L).ifPresent(funding -> {
            log.info(funding.toString());
            funding.getFundingFile().stream().map(FundingFile::toString).forEach(log::info);
            funding.getFundingGifts().stream().map(FundingGift::toString).forEach(log::info);
        });
    }


    /* 내 펀딩 내역 조회(페이징처리) */
    @Test
    public void findByMemberIdTest(){
        PageRequest pageRequest = PageRequest.of(0,9);
        Page<Funding> myFunding = fundingRepository.findByMemberId_QueryDSL(pageRequest,555L);
        myFunding.stream().map(Funding::toString).forEach(log::info);
        log.info("====================유저 아이디 555의 내 펀딩 목록 수=================" + myFunding.getTotalElements());
    }

    @Test
    public void findFileAll() {
        log.info(fundingFileRepository.findAll() + "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
    }

    //    펀딩 삭제(fk도 같이 삭제)
    @Test
    public void deleteTest() {
        fundingRepository.delete(fundingRepository.findById(141L).get());
    }

    //    펀딩 승인, 펀딩 대기 수 구하기
    @Test
    public void findCountAcceptOrWaitTest() {
        log.info("==========================" + fundingRepository.findCountAcceptOrWait(RequestType.승인));
        log.info("==========================" + fundingRepository.findCountAcceptOrWait(RequestType.대기));
    }

    //    펀딩 대기를 승인으로 변경
    @Test
    public void updateWaitToAcceptByIdsTest() {
        fundingRepository.updateWaitToAcceptByIds(Arrays.asList(143L, 144L, 145L));
    }

    //    펀딩 수정
    @Test
    public void updateTest() {

    }

    // 펀딩 후원하기
    @Test
    public void fundingSupportTest(){
    log.info("======="+fundingRepository.findByIdsupport(1L).toString());

    }

}