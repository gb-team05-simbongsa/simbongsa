package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.FundingCategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FundingRepositoryTests {
    @Autowired
    private FundingRepository fundingRepository;

/*    @Test
    public void saveTest() {
        for (int i = 1; i <= 30; i++) {
            Funding funding = new Funding(FundingCategoryType.승인, "큰제목", "소제목",
                    "안녕하세요", 10000, 3000, LocalDateTime.now(), LocalDateTime.now(), "어서오세요", "안녕하십니까",
                    "하이하이",  "헬로헬로",  "창작자");
            fundingRepository.save(funding);
        }

    }*/
}
