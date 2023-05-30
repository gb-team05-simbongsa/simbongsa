package com.app.simbongsa.service.rice;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.RicePaymentDTO;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.repository.rice.RicePaymentRepository;
import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.type.RicePaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("ricePayment") @Primary
@Slf4j
public class RicePaymentServiceImpl implements RicePaymentService {
    private final RicePaymentRepository ricePaymentRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<RicePaymentDTO> getRicePayment(Integer page, AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentType) {
        Page<RicePayment> ricePayments = ricePaymentRepository.findByPaymentStatusWithPaging(adminPaymentSearch, ricePaymentType, PageRequest.of(page, 5));
        List<RicePaymentDTO> ricePaymentDTOS = ricePayments.getContent().stream().map(this::toRicePaymentDTO).collect(Collectors.toList());
        return new PageImpl<>(ricePaymentDTOS, ricePayments.getPageable(), ricePayments.getTotalElements());
    }

    @Override
    public Page<RicePaymentDTO> getRicePayment(Integer page, AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentFirstType, RicePaymentType ricePaymentSecondType) {
        Page<RicePayment> ricePayments = ricePaymentRepository.findByPaymentStatusWithPaging(adminPaymentSearch, ricePaymentFirstType, ricePaymentSecondType, PageRequest.of(page, 5));
        List<RicePaymentDTO> ricePaymentDTOS = ricePayments.getContent().stream().map(this::toRicePaymentDTO).collect(Collectors.toList());
        return new PageImpl<>(ricePaymentDTOS, ricePayments.getPageable(), ricePayments.getTotalElements());
    }

    @Override
    public RicePaymentDTO getRicePaymentDetail(Long id) {
        return toRicePaymentDTO(ricePaymentRepository.findById(id).get());
    }

    @Override
    public void deleteRicePaymentByIds(List<Long> ids) {
        ricePaymentRepository.deleteAllById(ids);
    }

    @Override
    @Transactional
    public void updatePaymentStatusToAccessByIds(List<Long> ids) {
        ricePaymentRepository.updatePaymentStatusToAccessByIds(ids);
    }

    @Override
    public List<Long> countStatusWaitAccessDenied() {
        return Arrays.asList(ricePaymentRepository.countStatusWaitAccessDenied(RicePaymentType.환전승인),
                ricePaymentRepository.countStatusWaitAccessDenied(RicePaymentType.환전대기));
    }

    @Override
    public List<Long> getPaymentPriceAndPaymentCount() {
        return Arrays.asList(ricePaymentRepository.countTodayPayment(), ricePaymentRepository.getAllPaymentPrice() * 110);
    }


    /* 내 공양미 조회(페이징) */
    @Override
    public Page<RicePaymentDTO> getMyRicePayment(Integer page, MemberDTO memberDTO) {
        Page<RicePayment> myRicePayments = ricePaymentRepository.findByMemberId(PageRequest.of(page,5), memberDTO.getId());
        List<RicePaymentDTO> ricePaymentDTOS = myRicePayments.getContent().stream().map(this::toRicePaymentDTO).collect(Collectors.toList());
        return new PageImpl<>(ricePaymentDTOS, myRicePayments.getPageable(),myRicePayments.getTotalElements());
    }

    @Override
    @Transactional
    public void insertRicePayment(Integer ricePaymentUsed, MemberDTO memberDTO) {
        RicePaymentDTO ricePaymentDTO = new RicePaymentDTO();
        ricePaymentDTO.setRicePaymentUsed(ricePaymentUsed);
        ricePaymentDTO.setRicePaymentStatus(RicePaymentType.충전);
        ricePaymentDTO.setMemberDTO(memberDTO);
        ricePaymentRepository.save(toRicePaymentEntity(ricePaymentDTO));
        memberRepository.updateChargeRiceByMemberId(memberDTO.getId(), ricePaymentUsed);
    }

    @Override
    @Transactional
    public void insertSupportRicePayment(Integer ricePaymentUsed, MemberDTO memberDTO, RicePaymentType ricePaymentType) {
        RicePaymentDTO ricePaymentDTO = new RicePaymentDTO();
        ricePaymentDTO.setRicePaymentUsed(ricePaymentUsed);
        ricePaymentDTO.setRicePaymentStatus(ricePaymentType);
        ricePaymentDTO.setMemberDTO(memberDTO);
        ricePaymentRepository.save(toRicePaymentEntity(ricePaymentDTO));
    }

    @Override
    @Transactional
    public void insertExchangeRequest(RicePaymentDTO ricePaymentDTO, MemberDTO memberDTO) {
        int ricePaymentUsed = -ricePaymentDTO.getRicePaymentUsed();

        ricePaymentRepository.save(toRicePaymentEntity(ricePaymentDTO));
        memberRepository.updateChargeRiceByMemberId(memberDTO.getId(), ricePaymentUsed);
    }

    /*회원 환전 가능 공양미(후원받은 공양미) 가져오기*/
    @Override
    public int findEnableRiceById(Long id) {
        Integer supportedRice = ricePaymentRepository.findSupportedRiceById(id);
        Integer waitRice = ricePaymentRepository.findWaitRiceById(id);

        log.info("supportedRice: " + supportedRice);
        log.info("waitRice: " + waitRice);

        int supportedRiceValue = supportedRice != null ? supportedRice : 0;
        int waitRiceValue = waitRice != null ? waitRice : 0;

        int rice = supportedRiceValue - waitRiceValue;
        log.info("supportedRiceValue: " + supportedRiceValue);
        log.info("waitRiceValue: " + waitRiceValue);
        log.info("rice: " + rice);
        return rice >= 0 ? rice : 0;
    }

}
