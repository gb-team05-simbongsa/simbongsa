package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerWorkFileRepository extends JpaRepository<VolunteerWorkFile, Long>, VolunteerWorkFileQueryDsl {
}
