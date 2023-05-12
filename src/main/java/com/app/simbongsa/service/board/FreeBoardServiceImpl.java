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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("freeBoard") @Primary
public class FreeBoardServiceImpl implements FreeBoardService{
    private final FreeBoardRepository freeBoardRepository;

    /*저장*/
    @Override
    public void register(FreeBoardDTO freeBoardDTO, Long memberId) {

    }

    /*최신순 무한스크롤 전체 목록*/
    @Override
    public Slice<FreeBoardDTO> getNewList(Pageable pageable) {
        return null;
    }

    /*인기순 무한스크롤 전체 목록*/
    @Override
    public Slice<FreeBoardDTO> getLikesList(Pageable pageable) {
        return null;
    }

    /*상세*/
    @Override
    public FreeBoardDTO getDetail(Long memberId) {
        return null;
    }

    /*작성*/
    @Override
    public void write(FreeBoard freeBoard) {
        freeBoardRepository.save(freeBoard);
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

    @Override
    public List<FreeBoardDTO> findAllWithPopularFreeBoard() {
        List<FreeBoard> freeBoards = freeBoardRepository.findAllWithPopularFreeBoard();
        List<FreeBoardDTO> freeBoardDTOS = new ArrayList<>();
        for (FreeBoard freeBoard : freeBoards){
            FreeBoardDTO freeBoardDTO = toFreeBoardDTO(freeBoard);
            freeBoardDTOS.add(freeBoardDTO);

        }
        return freeBoardDTOS;
    }

}
