package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VolunteerWorkRepository extends JpaRepository<VolunteerWork, Long>, VolunteerWorkQueryDsl {
}
