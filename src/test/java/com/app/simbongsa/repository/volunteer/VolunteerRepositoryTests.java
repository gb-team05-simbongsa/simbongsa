package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class VolunteerRepositoryTests {
    @Autowired
    private VolunteerWorkRepository volunteerWorkRepository;

    @Autowired
    private VolunteerWorkActivityRepository volunteerWorkActivityRepository;

    @Autowired
    private VolunteerWorkFileRepository volunteerWorkFileRepository;

    @Autowired
    private MemberRepository memberRepository;

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
//        for(int i = 0; i < 20; i++){
//            VolunteerWork volunteerWork = new VolunteerWork(
//                    LocalDateTime.of(2023,4,12,12,00)
//                    ,LocalDateTime.of(2023,5,12,12,00)
//                    ,1+i
//                    ,LocalDate.of(2023,04,12)
//                    ,LocalDate.of(2023,04,17)
//                    ,1+i
//                    ,"전주"
//                    ,"전주"));
//            volunteerWorkRepository.save(volunteerWork);
//        }

    }

    @Test
    public void findVolunteerWorkListTest(){
        volunteerWorkRepository.findVolunteerWorkList().stream().map(VolunteerWork::toString).forEach(log::info);
    }


//    봉사 목록 전체 조회(페이징)
    @Test
    public void findAllWithPagingTest() {
        Page<VolunteerWork> foundVolunteerWork = volunteerWorkRepository.findAllWithPaging(PageRequest.of(0, 5));
        foundVolunteerWork.stream().map(VolunteerWork::toString).forEach(log::info);
        log.info("======================" + foundVolunteerWork.getTotalElements());
    }

//    신청 명단 넣기
    @Test
    public void applySaveTest() {
        for(int i = 0; i < 5; i++) {
            VolunteerWorkActivity volunteerWorkActivity = new VolunteerWorkActivity(LocalDate.of(2023,04,04),memberRepository.findById(Long.valueOf(143+i)).get(),
                    volunteerWorkRepository.findById(401L).get());
            volunteerWorkActivityRepository.save(volunteerWorkActivity);
        }
    }

//    신청 명단 조회
    @Test
    public void findApplyByVolunteerWorkIdTest() {
        volunteerWorkActivityRepository.findApplyByVolunteerWorkId(401L).stream().map(VolunteerWorkActivity::toString).forEach(log::info);
    }

//    봉사활동 삭제
    @Test
    public void deleteTest() {
        Long[] ids = {291L, 292L};
        volunteerWorkRepository.deleteAllById(Arrays.asList(ids));
    }


    //     봉사 전체조회(검색+페이징)
    @Test
    public void findAllWithPagingAndSearchTest(){
        PageRequest pageRequest = PageRequest.of(0, 5);
        String keyword = "경기도";
        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository.findAllPagingAndSearch(keyword, pageRequest);
        volunteerWorks.stream().map(VolunteerWork::toString).forEach(log::info);

    }
    @Test
    public void findAllWithPagingAndMultipleKeywordSearchTest() {
        String placeKeyword = null;
        String agencyKeyword = "기";
        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository
                .findPagingAndSearch(placeKeyword, agencyKeyword, pageRequest);

        volunteerWorks.stream().map(VolunteerWork::toString).forEach(log::info);
        log.info("=======================" + volunteerWorks.getTotalElements());
    }
    /* */
    @Test
    public void findById_QueryDSLTest(){
        log.info(volunteerWorkRepository.findById_QueryDSL(40L).toString());
        volunteerWorkRepository.findById_QueryDSL(41L).ifPresent(volunteerWork -> log.info(volunteerWork.toString()));
        volunteerWorkRepository.findById_QueryDSL(42L).stream().map(VolunteerWork::toString);
    }
    @Test
    public void findByNextIdOrPrevIdTest(){

        Optional<VolunteerWork> volunteerWork =volunteerWorkRepository.findByNextIdOrPrevId("이전글",194L);
        volunteerWork.map(VolunteerWork::toString);
        volunteerWork.ifPresent(volunteerWork1 -> log.info(volunteerWork1.toString()));

    }

}
