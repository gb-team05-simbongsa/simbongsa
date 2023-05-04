package com.app.simbongsa.repository.user;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserQueryDsl {
}
