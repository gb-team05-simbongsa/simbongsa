package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.inquiry.InquiryRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.type.InquiryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("inquiry") @Primary
@Slf4j
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    @Override
    public void saveInquiry(InquiryDTO inquiryDTO) {
        inquiryRepository.save(toInquiryEntity(inquiryDTO));
    }

    @Override
    public Page<InquiryDTO> getInquiry(Integer page, AdminBoardSearch adminBoardSearch) {
        Page<Inquiry> inquiries = inquiryRepository.findAllWithPaging(adminBoardSearch, PageRequest.of(page, 5));
        List<InquiryDTO> inquiryDTOS = inquiries.getContent().stream().map(this::toInquiryDTO).collect(Collectors.toList());
        return new PageImpl<>(inquiryDTOS, inquiries.getPageable(), inquiries.getTotalElements());
    }

    @Override
    public InquiryDTO getInquiryDetail(Long id) {
        Inquiry inquiry = inquiryRepository.findInquiryAndAnswerById(id);
        return toInquiryDTO(inquiry);
    }

    @Override
    public void deleteInquiry(List<Long> ids) {
        inquiryRepository.deleteAllById(ids);
    }

    /* 유저아이디로 문의 페이징처리해서 불러오기 */
    @Override
    public Page<InquiryDTO> getMyInquiry(Integer page, UserDetail userDetail) {
        Page<Inquiry> myInquiries = inquiryRepository.findByMemberId(PageRequest.of(page,5),userDetail);
        List<InquiryDTO> inquiryDTOS = myInquiries.getContent().stream().map(this::toInquiryDTO).collect(Collectors.toList());
        return new PageImpl<>(inquiryDTOS, myInquiries.getPageable(), myInquiries.getTotalElements());
    }

    /* 문의수정 */
    @Override
    public void setInquiry(InquiryDTO inquiryDTO) {
        Inquiry inquiry = toInquiryEntity(inquiryDTO);
        updateInquiry(inquiry, inquiry.getInquiryTitle(),inquiry.getInquiryContent());
        inquiryRepository.save(inquiry);
    }

    /* 문의 삭제 */
    @Override
    public void deleteByInquiryId(Long id) {
        inquiryRepository.deleteByInquiryId(id);
    }

    @Override
    public void updateStatusById(Long id) {
        inquiryRepository.updateInquiryStatus(id);
    }

    @Override
    public List<Long> countStatusWaitAndComplete() {
        return Arrays.asList(inquiryRepository.findInquiryStatusCount(InquiryType.답변대기), inquiryRepository.findInquiryStatusCount(InquiryType.답변완료));
    }
}
