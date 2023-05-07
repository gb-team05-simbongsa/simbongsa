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

//    공양미 충전 내역 전체 조회(상태에 따름)
    @Test
    public void findByPaymentStatusWithPaging() {
        Page<RicePayment> foundRicePayment = ricePaymentRepository.findByPaymentStatusWithPaging(PageRequest.of(0, 5), RicePaymentType.충전);
        foundRicePayment.stream().map(RicePayment::toString).forEach(log::info);
        log.info("=========================" + foundRicePayment.getTotalElements());
    }

//    공양미 충전 내역 상세보기(충전한 사람 포함)
    @Test
    public void findByIdTest() {
        ricePaymentRepository.findById(149L).ifPresent(ricePayment -> log.info(ricePayment.getUser().getUserName()));
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
    public void updatePaymentStatusToAccessByIdTest() {
        ricePaymentRepository.updatePaymentStatusToAccessById(142L);
    }
//    공양미 조회
    @Test
    public void findByIdWithPayment(){
        userRepository.findById(715L).ifPresent(user -> log.info(String.valueOf(user.getUserRice())));
    }
//    공양미 후원
//    @Test
//    public void updatePaymentByUserIdTest(){
//         ricePaymentRepository.updatePaymentByUserId(716L,3000);
//
//         ricePaymentRepository.findById(716L).ifPresent(ricePayment -> log.info("================== 공양미 사용량 "+ricePayment.getRicePaymentUsed() + "==========="));
//         ricePaymentRepository.findById(716L).ifPresent(ricePayment -> log.info("================== 공양미 남은 "+ricePayment.getUser().getUserRice() + " =========="));
//    }
}
