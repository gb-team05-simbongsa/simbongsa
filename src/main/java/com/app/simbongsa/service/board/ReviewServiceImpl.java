package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.ReviewDTO;
import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.repository.board.ReviewFileRepository;
import com.app.simbongsa.repository.board.ReviewReplyRepository;
import com.app.simbongsa.repository.board.ReviewRepository;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("review") @Primary
@Slf4j
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ReviewReplyRepository reviewReplyRepository;
    private final ReviewFileRepository reviewFileRepository;

    @Override
    public void register(ReviewDTO reviewDTO, Long memberId) {
        List<FileDTO> fileDTOS = reviewDTO.getFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> reviewDTO.setMemberDTO(toMemberDTO(member))
        );
    }

    /*상세*/
    @Override
    public ReviewDTO getReview(Long reviewId) {
        Optional<Review> review = reviewRepository.findByIdForDetail_QueryDsl(reviewId);
        return toReviewDTO(review.get());
    }

    /*작성*/
    @Override
    public void write(Review review){
        reviewRepository.save(review);
    }


    /*최신순*/
    @Override
    public Slice<ReviewDTO> getNewReviewList(Pageable pageable) {
        Slice<Review> reviews =
                reviewRepository.findAllByIdReviewPaging_QueryDSL(PageRequest.of(0,10));
        List<ReviewDTO> collect = reviews.get().map(review -> toReviewDTO(review)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, reviews.hasNext());
    }

    /*인기순*/
    @Override
    public Slice<ReviewDTO> getLikesReviewList(Pageable pageable) {
        Slice<Review> reviews =
                reviewRepository.findAllByLikeCountReviewPaging_QueryDSL(PageRequest.of(0,10));
        List<ReviewDTO> collect = reviews.get().map(review -> toReviewDTO(review)).collect(Collectors.toList());
        return new SliceImpl<>(collect, pageable, reviews.hasNext());
    }



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
