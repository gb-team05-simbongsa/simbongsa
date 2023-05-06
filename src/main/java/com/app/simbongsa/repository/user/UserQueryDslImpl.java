package com.app.simbongsa.repository.user;

import com.app.simbongsa.entity.user.QUser;
import com.app.simbongsa.entity.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.user.QUser.user;

@RequiredArgsConstructor
public class UserQueryDslImpl implements UserQueryDsl {
    private final JPAQueryFactory query;

//    회원 전체 조회(페이징)
    @Override
    public Page<User> findAllWithPaging(Pageable pageable) {
        List<User> foundUser = query.select(user)
                .from(user)
                .orderBy(user.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(user.count())
                .from(user)
                .fetchOne();

        return new PageImpl<>(foundUser, pageable, count);
    }

    @Override
    public void updateUser(Long id, String userName, String userAddress, String userInterest, int userVolunteerTime) {
        query.update(user)
                .set(user.userName, userName)
                .set(user.userAddress, userAddress)
                .set(user.userInterest, userInterest)
                .set(user.userVolunteerTime, userVolunteerTime)
                .where(user.id.eq(id))
                .execute();
    }

}
