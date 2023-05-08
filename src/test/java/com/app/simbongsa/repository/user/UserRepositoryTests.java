package com.app.simbongsa.repository.user;

import com.app.simbongsa.domain.search.admin.AdminUserSearch;
import com.app.simbongsa.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

//    user 더미데이터 넣기
    @Test
    public void saveTest() {
        for (int i = 1; i <= 100; i++) {
            User user = new User("이름" + i, "email" + i + "@naver.com",
                    "123" + i, "역삼로" + i, 10 + i, "봉사",i+1, 100 * i);
            userRepository.save(user);
        }
    }

//    회원 전체조회(페이징)
    @Test
    public void findAllWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        AdminUserSearch adminUserSearch = new AdminUserSearch();
//        adminUserSearch.setUserEmail("email33");
        adminUserSearch.setUserAddress("삼");
        Page<User> users = userRepository.findAllWithPaging(adminUserSearch, pageRequest);
        users.stream().map(User::toString).forEach(log::info);
        log.info("=======================" + users.getTotalElements());
    }

//    회원 정보 조회(상세보기)
    @Test
    public void findByIdTest() {
        userRepository.findById(1L).stream().map(User::toString).forEach(log::info);
    }

//    회원 정보 수정(관리자 페이지)
    @Test
    public void updateTest() {
        userRepository.updateUser(100L, "수정 이름", "수정 주소", "심봉사", 3);
    }

//    회원 삭제
    @Test
    public void deleteTest() {
//        userRepository.delete(userRepository.findById(1L).get());
        userRepository.deleteAllById(Arrays.asList(1L, 2L, 3L));
    }

//    봉사시간 순 랭킹 조회 (메인 페이지)
    @Test
    public void findUserWithVolunteerTime(){
        userRepository.findUserWithVolunteerTime().stream().map(User::toString).forEach(log::info);
    }

//    유저 공양미 조회(후원 상세페이지 공양미 조회용)
    @Test
    public void findUserPaymentById(){
        Optional<User> goyangmi = userRepository.findById(715L);
        goyangmi.ifPresent(user -> log.info("====================================" + user.getUserRice()+ "====================="));
    }
}
