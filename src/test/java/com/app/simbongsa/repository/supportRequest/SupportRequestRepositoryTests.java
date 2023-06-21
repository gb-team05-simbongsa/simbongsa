package com.app.simbongsa.repository.supportRequest;

import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminSupportRequestSearch;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.type.RequestType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class SupportRequestRepositoryTests {
    @Autowired
    private SupportRequestRepository supportRequestRepository;

    @Autowired
    private MemberRepository memberRepository;

//    /* 후원요청목록 save */
//    @Test
//    public void saveTest(){
//        for (int i = 1; i <= 23; i++) {
//            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.대기, memberRepository.findById(1264L).get());
//            supportRequestRepository.save(supportRequest);
//        }
//        /*for (int i = 1; i <= 3; i++) {
//            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.승인, memberRepository.findById(144L).get());
//            supportRequestRepository.save(supportRequest);
//        }
//        for (int i = 1; i <= 3; i++) {
//
//            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.대기, memberRepository.findById(145L).get());
//            SupportRequest supportRequest1 = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.승인, memberRepository.findById(80L).get());
//            supportRequestRepository.save(supportRequest);
//        }
//        for (int i = 1; i <= 3; i++) {
//            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.승인, memberRepository.findById(79L).get());
//            supportRequestRepository.save(supportRequest);
//        }
//        for (int i = 1; i <= 3; i++) {
//            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.대기, memberRepository.findById(81L).get());
//            supportRequestRepository.save(supportRequest);
//        }*/
//
//    }
//
//    /* 유저아이디로 후원요청목록 페이징처리해서 불러오기 */
//    /*@Test
//    public void findByMemberIdTest(){
//        PageRequest pageRequest = PageRequest.of(0,3);
//        UserDetail userDetail = new UserDetail();
////        Page<SupportRequest> supportRequests = supportRequestRepository.findByMemberId(pageRequest, userDetail);
////        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
////        log.info("====================유저 아이디 6의 후원요청목록수=================" + supportRequests.getTotalElements());
//    }
//    @Test
//    public void findAllSupportRequestTest(){
//        // 페이지 요청 설정
//        PageRequest pageRequest = PageRequest.of(0, 3);
//        // findAllSupportRequest() 메서드 호출
//        Slice<SupportRequest> supportRequests = supportRequestRepository.findAllSupportRequest(pageRequest);
//        // 조회된 데이터 출력
//        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
//
//        // 전체 후원 요청 목록 수 출력
////        Slice 객체에는 현재 페이지의 요소 목록과 다음 페이지가 있는지 여부를 확인할 수 있는 hasNext() 있는데,
////        전체 요소 수를 얻기 위해서는 Slice 객체를 List로 변환한 후에 List의 크기를 확인.
//        List<SupportRequest> supportRequestList = supportRequests.getContent();
//        long totalCount = supportRequests.hasNext() ? (pageRequest.getPageNumber() + 1) * pageRequest.getPageSize() : supportRequestList.size();
//        log.info("==================== 전체 후원 요청 목록 수 ====================" + totalCount);
//        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
//
//    }
//    //    후원 요청 목록 전체 조회(페이징)
//    @Test
//    public void findAllWithPagingTest() {
//        AdminSupportRequestSearch adminSupportRequestSearch = new AdminSupportRequestSearch();
////        adminSupportRequestSearch.setRequestType(RequestType.대기);
//        adminSupportRequestSearch.setMemberEmail("6");
//        Page<SupportRequest> foundSupportRequest = supportRequestRepository.findAllWithPaging(adminSupportRequestSearch, PageRequest.of(0, 5));
//        foundSupportRequest.stream().map(SupportRequest::toString).forEach(log::info);
//        log.info("=======================" + foundSupportRequest.getTotalElements());
//    }
//
//    @Test
//    public void findSupportRequestDetail_QueryDSLTest(){
////        log.info("======="+supportRequestRepository.findSupportRequestDetail_QueryDSL(122L).toString());
//        supportRequestRepository.findSupportRequestDetail_QueryDSL(122L).ifPresent(supportRequest -> log.info(supportRequest.getSupportRequestFiles().toString()));
//    }
//
////    후원요청 대기에서 승인으로
//    @Test
//    public void updateWaitToAccessByIdsTest() {
////        supportRequestRepository.updateWaitToAccessByIds(Arrays.asList(130L, 131L));
//    }
//
//
//    *//* 후원 상세페이지 모달 정보 *//*
//    @Test
//    public void findByIdWithSupportRequestInfo_QueryDslTest(){
//        log.info("===========" + supportRequestRepository.findByIdWithSupportRequestInfo_QueryDsl(129L).toString());
//    }
//
//
//    *//* 후원 최신순, 후원 많은순, 후원 적은순 *//*
//    @Test
//    public void findByIdWithOrderTest(){
//        PageRequest pageRequest = PageRequest.of(0, 3);
//        Slice<SupportRequest> supportRequests = supportRequestRepository.findByIdWithOrder("후원 적은순", pageRequest);
//        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
//
//        List<SupportRequest> supportRequestList = supportRequests.getContent();
//        long totalCount = supportRequests.hasNext() ? (pageRequest.getPageNumber() + 1) * pageRequest.getPageSize() : supportRequestList.size();
//        log.info("==================== 전체 후원 요청 목록 수 ====================" + totalCount);
//        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
//    }
    @Test
    public void findAllWithPagingSearchTest(){
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<SupportRequest> supportRequests = supportRequestRepository.findAllWithPagingSearch("참여 적은순", pageRequest);
        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);

        List<SupportRequest> supportRequestList = supportRequests.getContent();
        long totalCount = supportRequests.hasNext() ? (pageRequest.getPageNumber() + 1) * pageRequest.getPageSize() : supportRequestList.size();
        log.info("==================== 전체 후원 요청 목록 수 ====================" + totalCount);
        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
    }
//
////    후원요청 삭제
//    @Test
//    public void deleteTest() {
//        supportRequestRepository.delete(supportRequestRepository.findById(121L).get());
//    }
//
////    @Test
////    public void findAllTest(){
////        PageRequest pageRequest = PageRequest.of(0, 40);
////        Page<SupportRequest> supportRequests = supportRequestRepository.findAllTest(pageRequest);
////        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
////        List<SupportRequest> supportRequestList = supportRequests.getContent();
////        long totalCount = supportRequests.hasNext() ? (pageRequest.getPageNumber() + 1) * pageRequest.getPageSize() : supportRequestList.size();
////        log.info("==================== 전체 후원 요청 목록 수 ====================" + totalCount);
////    }*/
//
}
