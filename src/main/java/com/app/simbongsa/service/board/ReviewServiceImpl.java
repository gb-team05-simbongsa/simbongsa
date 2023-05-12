package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.ReviewDTO;
import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.repository.board.ReviewRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("review") @Primary
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewDTO> getReview(Integer page, AdminBoardSearch adminBoardSearch) {
        Page<Review> reviews = reviewRepository.findAllWithPaging(adminBoardSearch, PageRequest.of(page, 5));
        List<ReviewDTO> reviewDTOS = reviews.getContent().stream().map(this::toReviewDTO).collect(Collectors.toList());
        return new PageImpl<>(reviewDTOS, reviews.getPageable(), reviews.getTotalElements());
    }

    @Override
    public ReviewDTO getReviewDetail(Long id) {
        return toReviewDTO(reviewRepository.findById(id).get());
    }

    @Override
    public void deleteReviewByIds(List<Long> ids) {
        reviewRepository.deleteAllById(ids);
    }
}
