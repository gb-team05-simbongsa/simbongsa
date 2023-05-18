package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.search.admin.AdminSupportRequestSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("supportRequest") @Primary
@Slf4j
public class SupportRequestServiceImpl implements SupportRequestService {
    private final SupportRequestRepository supportRequestRepository;

    @Override
    public Page<SupportRequestDTO> getSupportRequest(Integer page, AdminSupportRequestSearch adminSupportRequestSearch) {
        Page<SupportRequest> supportRequests = supportRequestRepository.findAllWithPaging(adminSupportRequestSearch, PageRequest.of(page, 5));
        log.info("서울사이버 대학을 다니고");
        List<SupportRequestDTO> noticeDTOS = supportRequests.getContent().stream().map(this::toSupportRequestDTO).collect(Collectors.toList());
        log.info("내인생이 달라졌다");
        return new PageImpl<>(noticeDTOS, supportRequests.getPageable(), supportRequests.getTotalElements());
    }

    @Override
    public SupportRequestDTO getSupportRequestDetail(Long id) {
        return toSupportRequestDTO(supportRequestRepository.findById(id).get());
    }

    @Override
    public void deleteSupportRequest(List<Long> ids) {
        supportRequestRepository.deleteAllById(ids);
    }

    @Override
    public void updateWaitToAccess(List<Long> ids) {
        supportRequestRepository.updateWaitToAccessByIds(ids);
    }

    @Override
    public Page<SupportRequestDTO> getSupportRequestAllWithPaging(Integer page, String keyword) {
        Page<SupportRequest> supportRequests = supportRequestRepository.findAllWithPagingSearch(keyword, PageRequest.of(page, 5));
        List<SupportRequestDTO> noticeDTOS = supportRequests.getContent().stream().map(this::toSupportRequestDTO).collect(Collectors.toList());
        return new PageImpl<>(noticeDTOS, supportRequests.getPageable(), supportRequests.getTotalElements());
    }

    /* 내 후원요청목록 페이징처리해서 불러오기 */
    @Override
    public Page<SupportRequestDTO> getMySupportRequest(Integer page, UserDetail userDetail) {
        Page<SupportRequest> mySupportRequest = supportRequestRepository.findByMemberId(PageRequest.of(page, 5), userDetail);
        List<SupportRequestDTO> mySupportRequestDTOS = mySupportRequest.getContent().stream().map(this::toSupportRequestDTO).collect(Collectors.toList());
        return new PageImpl<>(mySupportRequestDTOS, mySupportRequest.getPageable(), mySupportRequest.getTotalElements());
    }
}
