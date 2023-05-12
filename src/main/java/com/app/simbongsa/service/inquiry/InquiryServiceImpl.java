package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.AnswerDTO;
import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.repository.inquiry.InquiryRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
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
@Qualifier("inquiry") @Primary
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    @Override
    public Page<InquiryDTO> getInquiry(Integer page, AdminBoardSearch adminBoardSearch) {
        Page<Inquiry> inquiries = inquiryRepository.findAllWithPaging(adminBoardSearch, PageRequest.of(page, 5));
        List<InquiryDTO> inquiryDTOS = inquiries.getContent().stream().map(this::toInquiryDTO).collect(Collectors.toList());
        return new PageImpl<>(inquiryDTOS, inquiries.getPageable(), inquiries.getTotalElements());
    }

    @Override
    public InquiryDTO getInquiryDetail(Long id) {
        return toInquiryDTO(inquiryRepository.findById(id).get());
    }

    @Override
    public void deleteInquiry(Long id) {
        inquiryRepository.deleteById(id);
    }
}
