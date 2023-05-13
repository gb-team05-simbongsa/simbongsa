package com.app.simbongsa.service.rice;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.RicePaymentDTO;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.repository.rice.RicePaymentRepository;
import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.type.RicePaymentType;
import lombok.RequiredArgsConstructor;
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
@Qualifier("ricePayment") @Primary
public class RicePaymentServiceImpl implements RicePaymentService {
    private final RicePaymentRepository ricePaymentRepository;

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
    public void updatePaymentStatusToAccessByIds(List<Long> ids) {
        ricePaymentRepository.updatePaymentStatusToAccessByIds(ids);
    }
}
