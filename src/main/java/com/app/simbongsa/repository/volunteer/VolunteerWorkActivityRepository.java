package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerWorkActivityRepository extends JpaRepository<VolunteerWorkActivity, Long>, VolunteerWorkActivityQueryDsl {
}
