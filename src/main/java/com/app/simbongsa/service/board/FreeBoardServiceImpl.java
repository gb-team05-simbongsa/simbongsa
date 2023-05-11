package com.app.simbongsa.service.board;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.repository.board.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("freeBoard") @Primary
public class FreeBoardServiceImpl implements FreeBoardService{
    private final FreeBoardRepository freeBoardRepository;

    /*최신순 무한스크롤 전체 목록*/
    @Override
    public Slice<FreeBoard> getAllNewFreeBoards(Pageable pageable) {
        return freeBoardRepository.findAllByIdDescWithPaging_QueryDSL(pageable);
    }

    /*인기순 무한스크롤 전체 목록*/
    @Override
    public Slice<FreeBoard> getAllLikeFreeBoards(Pageable pageable) {
        return freeBoardRepository.findAllByLikeCountDescWithPaging_QueryDSL(pageable);
    }

}
