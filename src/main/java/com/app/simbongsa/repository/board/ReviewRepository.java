package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
}
