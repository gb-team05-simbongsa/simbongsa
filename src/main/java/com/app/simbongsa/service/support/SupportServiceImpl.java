package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.repository.support.SupportRepository;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("support") @Primary
@Slf4j
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;
    private final MemberRepository memberRepository;
    private final SupportRequestRepository supportRequestRepository;
//    @Override
//    public Page<SupportDTO> getAllSupportAttendWithMember_QueryDSL(Integer page, Long id) {
//        Page<Support> getAttendMember = supportRepository.findAllSupportAttendWithMember_QueryDSL(PageRequest.of(page,5),id);
//        List<SupportDTO> supportDTOS = getAttendMember.getContent().stream().map(this::toSupportDTO).collect(Collectors.toList());
//        return new PageImpl<>(supportDTOS, getAttendMember.getPageable(), getAttendMember.getTotalElements());
//    }
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

    @Override
    public void saveSupport(SupportDTO supportDTO, Long id) {
        supportRepository.save(toSupportEntity(supportDTO));
//        memberRepository.updateChargeRiceByMemberId(id, -supportDTO.getSupportPrice());
    }

    @Override
    @Transactional
    public void updateRice(int price, Long id) {
        memberRepository.updateChargeRiceByMemberId(id, price);
    }

    @Override
    public Page<SupportDTO> getSupportById(Integer page, Long id) {
        Page<Support> foundSupport = supportRepository.findByMemberId(PageRequest.of(page, 5), id);
        List<SupportDTO> supportDTOS = foundSupport.getContent().stream().map(this::toSupportDTO).collect(Collectors.toList());
        return new PageImpl<>(supportDTOS, foundSupport.getPageable(), foundSupport.getTotalElements());
    }

    @Override
    public void updateSupportGongyangmi(SupportDTO supportDTO) {
        supportRepository.updateSupportRequestCash(toSupportEntity(supportDTO));
    }


//    @Override
//    public Page<SupportDTO> getSupportListWithPaging(Integer page, Long id) {
//        Page<Support> getAttendMember = supportRepository.findAllSupportAttendWithMember_QueryDSL(PageRequest.of(page,5), id);
//        List<SupportDTO> supportDTOS = getAttendMember.getContent().stream().map(this::toSupportDTO).collect(Collectors.toList());
//        return new PageImpl<>(supportDTOS, getAttendMember.getPageable(), getAttendMember.getTotalElements());
//    }


}
