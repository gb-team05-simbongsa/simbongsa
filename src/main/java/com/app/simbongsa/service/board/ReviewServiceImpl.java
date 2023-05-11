package com.app.simbongsa.service.board;

import com.app.simbongsa.repository.board.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("review") @Primary
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
}
