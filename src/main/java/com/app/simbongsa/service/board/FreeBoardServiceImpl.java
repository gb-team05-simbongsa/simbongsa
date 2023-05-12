package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.repository.board.FreeBoardRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<FreeBoardDTO> getFreeBoard(Integer page, AdminBoardSearch adminBoardSearch) {
        Page<FreeBoard> freeBoards = freeBoardRepository.findAllWithPaging(adminBoardSearch, PageRequest.of(page, 5));
        List<FreeBoardDTO> freeBoardDTOS = freeBoards.getContent().stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new PageImpl<>(freeBoardDTOS, freeBoards.getPageable(), freeBoards.getTotalElements());
    }

    @Override
    public FreeBoardDTO getFreeBoardDetail(Long id) {
        return toFreeBoardDTO(freeBoardRepository.findById(id).get());
    }

    @Override
    public void deleteFreeBoardByIds(List<Long> ids) {
        freeBoardRepository.deleteAllById(ids);
    }

}
