package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.repository.user.UserRepository;
import com.app.simbongsa.type.FundingCategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

//import static com.app.simbongsa.entity.funding.QFundingCreator.fundingCreator;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FundingRepositoryTests {
    @Autowired
    private FundingRepository fundingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FundingFileRepository fundingFileRepository;

    @Test
    public void saveTest() {

        for (int i = 1; i <= 30; i++) {
            FundingCreator fundingCreator = new FundingCreator("test" + i,"test Introduce" + i,"test","test");
            Funding funding = new Funding(FundingCategoryType.승인
                    , "큰제목"
                    , "소제목"
                    , "안녕하세요"
                    , 10000 + 100 * i
                    , 3000 + 100 * i
                    , LocalDateTime.now()
                    , LocalDateTime.now()
                    ,"어서오세요"
                    ,"안녕하십니까"
                    ,"하이하이"
                    ,"헬로헬로"
                    ,fundingCreator
                    ,userRepository.findById(555L).get());


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
        Page<Funding> foundFunding = fundingRepository.findAllWithPaging(PageRequest.of(0, 5));
        foundFunding.stream().map(Funding::toString).forEach(log::info);
        log.info("==========================" + foundFunding.getTotalElements());
    }

    //  펀딩 상세보기
    @Test
    public void findByIdTest() {
        fundingRepository.findById(555L).ifPresent(funding -> log.info(funding.toString()));
    }


    /* 내 펀딩 내역 조회(페이징처리) */
    @Test
    public void findByUserIdTest(){
        PageRequest pageRequest = PageRequest.of(0,9);
        Page<Funding> myFunding = fundingRepository.findByUserId_QueryDSL(pageRequest,555L);
        myFunding.stream().map(Funding::toString).forEach(log::info);
        log.info("====================유저 아이디 555의 내 펀딩 목록 수=================" + myFunding.getTotalElements());
    }

    @Test
    public void findFileAll() {
        log.info(fundingFileRepository.findAll() + "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
    }

}
