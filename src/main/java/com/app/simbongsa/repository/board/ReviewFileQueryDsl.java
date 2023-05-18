package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.file.ReviewFile;

import java.util.List;

public interface ReviewFileQueryDsl {
    // 파일 가져오기
    public List<ReviewFile> findByReviewId(Long id);
}
