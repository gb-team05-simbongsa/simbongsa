package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class VolunteerRepositoryTests {
    @Autowired
    private VolunteerWorkRepository volunteerWorkRepository;

    @Test
    public void saveTest(){
        for(int i = 0; i < 50; i++){
            VolunteerWork volunteerWork = new VolunteerWork(
                    LocalDateTime.of(2023,4,12,12,00)
                    ,LocalDateTime.of(2023,5,12,12,00)
                    ,1+i
                    ,LocalDate.of(2023,04,12)
                    ,LocalDate.of(2023,04,17)
                    ,1+i
                    ,"서울"
                    ,"서울");
            volunteerWorkRepository.save(volunteerWork);
        }
    }

    @Test
    public void findVolunteerWorkListTest(){
        volunteerWorkRepository.findVolunteerWorkList().stream().map(VolunteerWork::toString).forEach(log::info);
    }

}
