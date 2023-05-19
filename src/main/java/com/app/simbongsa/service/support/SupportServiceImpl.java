package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.support.SupportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("support") @Primary
@Slf4j
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;
    @Override
    public Page<SupportDTO> getAllSupportAttendWithMember_QueryDSL(Integer page, Long id) {
        Page<Support> getAttendMember = supportRepository.findAllSupportAttendWithMember_QueryDSL(PageRequest.of(page,5), id);
        List<SupportDTO> supportDTOS = getAttendMember.getContent().stream().map(this::toSupportDTO).collect(Collectors.toList());
        return new PageImpl<>(supportDTOS, getAttendMember.getPageable(), getAttendMember.getTotalElements());
    }

    @Override
    public Long getAllSupportAttend_QueryDSL(Long id) {

        return supportRepository.findAllSupportAttend_QueryDSL(id);
    }

//    @Override
//    public List<SupportDTO> getSupportListWithPaging(Long id) {
//        return supportRepository.findAllSupportAttend_QueryDSL(id).stream().map(this::toSupportDTO).collect(Collectors.toList());
//    }


}
