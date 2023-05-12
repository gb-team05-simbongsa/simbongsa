package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.entity.support.SupportRequest;
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
        List<SupportRequestDTO> noticeDTOS = supportRequests.getContent().stream().map(this::toSupportRequestDTO).collect(Collectors.toList());
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
}
