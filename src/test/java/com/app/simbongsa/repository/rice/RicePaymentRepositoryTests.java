package com.app.simbongsa.repository.rice;

import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.repository.user.UserRepository;
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

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class RicePaymentRepositoryTests {
    @Autowired
    private RicePaymentRepository ricePaymentRepository;

    @Autowired
    private UserRepository userRepository;

//    더미데이터 넣기
    @Test
    public void saveTest() {
        for(int i = 1; i <= 10; i++) {
            RicePayment ricePayment = new RicePayment(300 + i, RicePaymentType.사용, "국민", "123412-1234123" + i, userRepository.findById(5L).get());
            ricePaymentRepository.save(ricePayment);
        }
    }

//    공양미 충전내역 전체 조회
    @Test
    public void findAllWithPaging() {
        Page<RicePayment> foundRicePayment = ricePaymentRepository.findByPaymentStatusWithPaging(PageRequest.of(0, 5));
        foundRicePayment.stream().map(RicePayment::toString).forEach(log::info);
        log.info("=========================" + foundRicePayment.getTotalElements());
    }

//    금일 결제 수 조회
    @Test
    public void findByCreateDateToday() {
        log.info("=========================" + ricePaymentRepository.findByCreateDateToday());
    }

//    결제 총 금액 조회

//    공양미 내역 삭제
    @Test
    public void deleteTest() {
        Long[] ids = {142L, 143L};
        ricePaymentRepository.deleteAllById(Arrays.asList(ids));
    }
}
