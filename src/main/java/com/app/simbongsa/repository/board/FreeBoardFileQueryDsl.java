package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.file.FreeBoardFile;

import java.util.List;

public interface FreeBoardFileQueryDsl {
    // 파일 가져오기
    public List<FreeBoardFile> findByFreeBoardId(Long id);
}
