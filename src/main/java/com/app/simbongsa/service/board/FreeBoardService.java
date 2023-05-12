package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FreeBoardService {
    // 저장
    public void register(FreeBoardDTO freeBoardDTO, Long memberId);

    // 최신순 목록
    public Slice<FreeBoardDTO> getNewList(Pageable pageable);

    // 인기순 목록
    public Slice<FreeBoardDTO> getLikesList(Pageable pageable);

    // 상세 보기
    public FreeBoardDTO getDetail(Long memberId);

    // 작성
    public void write(FreeBoard freeBoard);

}
