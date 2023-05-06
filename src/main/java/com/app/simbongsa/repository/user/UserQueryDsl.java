package com.app.simbongsa.repository.user;

import com.app.simbongsa.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryDsl {

//    회원 전체 조회(페이징)
    public Page<User> findAllWithPaging(Pageable pageable);

//    회원 정보 수정
    public void updateUser(Long id, String userName, String userAddress, String userInterest, int userVolunteerTime);
}
