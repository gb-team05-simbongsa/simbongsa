package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
//        for(int i = 0; i < 50; i++){
//            VolunteerWork volunteerWork = new VolunteerWork(
//                    LocalDateTime.of(2023,4,12,12,00)
//                    ,LocalDateTime.of(2023,5,12,12,00)
//                    ,1+i
//                    ,LocalDate.of(2023,04,12)
//                    ,LocalDate.of(2023,04,17)
//                    ,1+i
//                    ,"서울"
//                    ,"서울");
//            volunteerWorkRepository.save(volunteerWork);
//        }
        for(int i = 0; i < 20; i++){
            VolunteerWork volunteerWork = new VolunteerWork(
                    LocalDateTime.of(2023,4,12,12,00)
                    ,LocalDateTime.of(2023,5,12,12,00)
                    ,1+i
                    ,LocalDate.of(2023,04,12)
                    ,LocalDate.of(2023,04,17)
                    ,1+i
                    ,"전주"
                    ,"전주");
            volunteerWorkRepository.save(volunteerWork);
        }
    }

    @Test
    public void findVolunteerWorkListTest(){
        volunteerWorkRepository.findVolunteerWorkList().stream().map(VolunteerWork::toString).forEach(log::info);
    }

    //    봉사 전체조회(페이징)
    @Test
    public void findAllWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository.findAllWithPaging(pageRequest);
        volunteerWorks.stream().map(VolunteerWork::toString).forEach(log::info);
        log.info("=======================" + volunteerWorks.getTotalElements());
    }
    //     봉사 전체조회(검색+페이징)
    @Test
    public void findAllWithPagingAndSearchTest(){
        PageRequest pageRequest = PageRequest.of(0, 5);
        String keyword = "경기도";
        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository.findAllWithPagingAndSearch(keyword, pageRequest);
        volunteerWorks.stream().map(VolunteerWork::toString).forEach(log::info);

    }
    @Test
    public void testFindAllWithPagingAndMultipleKeywordSearch() {
        String placeKeyword = "서";
        String agencyKeyword = "경기";
        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository
                .findAllWithPagingAndMultipleKeywordSearch(placeKeyword, agencyKeyword, pageRequest);

        volunteerWorks.stream().map(VolunteerWork::toString).forEach(log::info);
        log.info("=======================" + volunteerWorks.getTotalElements());
    }

}
