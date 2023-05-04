package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerWorkRepository extends JpaRepository<VolunteerWork, Long>, VolunteerWorkQueryDsl {
}
