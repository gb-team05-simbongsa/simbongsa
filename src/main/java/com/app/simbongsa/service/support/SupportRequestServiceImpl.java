package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.search.admin.AdminSupportRequestSearch;
import com.app.simbongsa.summernote.SupportRequestFileRepository;
import com.app.simbongsa.type.FileRepresentationalType;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.RequestType;
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
@Qualifier("supportRequest") @Primary
@Slf4j
public class SupportRequestServiceImpl implements SupportRequestService {
    private final SupportRequestRepository supportRequestRepository;
    private final MemberRepository memberRepository;
    private final SupportRequestFileRepository supportRequestFileRepository;

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
    @Transactional
    public void updateWaitToAccessOrDenied(List<Long> ids, RequestType requestType) {
        supportRequestRepository.updateWaitToAccessByIds(ids, requestType);
    }

    @Override
    public Page<SupportRequestDTO> getSupportRequestAllWithPaging(Integer page, String keyword) {
        Page<SupportRequest> supportRequests = supportRequestRepository.findAllWithPagingSearch(keyword, PageRequest.of(page, 5));
        List<SupportRequestDTO> noticeDTOS = supportRequests.getContent().stream().map(this::toSupportRequestDTO).collect(Collectors.toList());
        return new PageImpl<>(noticeDTOS, supportRequests.getPageable(), supportRequests.getTotalElements());
    }

    /* 내 후원요청목록 페이징처리해서 불러오기 */
    @Override
    public Page<SupportRequestDTO> getMySupportRequest(Integer page, MemberDTO memberDTO) {
        Page<SupportRequest> mySupportRequest = supportRequestRepository.findByMemberId(PageRequest.of(page, 5), memberDTO);
        List<SupportRequestDTO> mySupportRequestDTOS = mySupportRequest.getContent().stream().map(this::toSupportRequestDTO).collect(Collectors.toList());
        return new PageImpl<>(mySupportRequestDTOS, mySupportRequest.getPageable(), mySupportRequest.getTotalElements());
    }

    @Override
    public List<Long> countStatusWaitAccessDenied() {
        return Arrays.asList(supportRequestRepository.countStatusWaitAccessDenied(RequestType.승인),
                supportRequestRepository.countStatusWaitAccessDenied(RequestType.대기),
                supportRequestRepository.countStatusWaitAccessDenied(RequestType.반려));
    }

    @Override
    public void saveSupportRequest(SupportRequestDTO supportRequestDTO) {
        supportRequestRepository.save(toSupportRequestEntity(supportRequestDTO));
    }

    @Override @Transactional
    public void register(SupportRequestDTO supportRequestDTO, Long memberId) {
        List<FileDTO> fileDTOS = supportRequestDTO.getFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> supportRequestDTO.setMemberDTO(toMemberDTO(member))
        );
        supportRequestRepository.save(toSupportRequestEntity(supportRequestDTO));
        if(fileDTOS != null){
            for(int i = 0; i< fileDTOS.size(); i++){
                if(i==0){
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
                }else{
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.NORMAL);
                }
                fileDTOS.get(i).setSupportRequest(getCurrentSequence());
                supportRequestFileRepository.save(toSupportRequestFileEntity(fileDTOS.get(i)));
            }
        }
    }

    @Override
    public SupportRequest getCurrentSequence() {
        return supportRequestRepository.getCurrentSequence_QueryDsl();
    }
}
