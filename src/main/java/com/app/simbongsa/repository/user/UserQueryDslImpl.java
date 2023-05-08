package com.app.simbongsa.repository.user;

import com.app.simbongsa.domain.search.admin.AdminUserSearch;
import com.app.simbongsa.entity.user.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.user.QUser.user;

@RequiredArgsConstructor
public class UserQueryDslImpl implements UserQueryDsl {
    private final JPAQueryFactory query;

//    회원 전체 조회(페이징)
//    검색 포함(관리자)
    @Override
    public Page<User> findAllWithPaging(AdminUserSearch adminUserSearch, Pageable pageable) {
        BooleanExpression userEmailLike = adminUserSearch.getUserEmail() == null ? null : user.userEmail.like("%" + adminUserSearch.getUserEmail() + "%");
        BooleanExpression userAddressLike = adminUserSearch.getUserAddress() == null ? null : user.userAddress.like("%" + adminUserSearch.getUserAddress() + "%");

        List<User> foundUser = query.select(user)
                .from(user)
                .where(userEmailLike, userAddressLike)
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
//    @Modifying(clearAutomatically = true)
//    ??
    public void updateUser(Long id, String userName, String userAddress, String userInterest, int userVolunteerTime) {
        query.update(user)
                .set(user.userName, userName)
                .set(user.userAddress, userAddress)
                .set(user.userInterest, userInterest)
                .set(user.userVolunteerTime, userVolunteerTime)
                .where(user.id.eq(id))
                .execute();
    }

    @Override
    public List<User> findUserWithVolunteerTime() {
        return query.select(user).from(user).orderBy(user.userVolunteerTime.desc()).limit(8).fetch();
    }

    //    비밀 번호 찾기
    @Override
    public Optional<User> findByUserEmailForPassword(String userEmail) {
        return Optional.ofNullable(query.select(user).from(user).where(user.userEmail.eq(userEmail)).fetchOne());
    }

    //    비밀 번호 변경
    @Override
    public void updatePassword(Long id, String userPassword) {
        query.update(user).set(user.userPassword, userPassword).where(user.id.eq(id)).execute();

    }
}
