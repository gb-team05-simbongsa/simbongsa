package com.app.simbongsa.summernote;

import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.support.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportRequestFileRepository extends JpaRepository<SupportRequestFile, Long> {
    List<SupportRequestFile> findBySupportRequest(SupportRequest supportRequest);
}
