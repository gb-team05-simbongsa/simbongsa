package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.repository.volunteer.VolunteerWorkRepository;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Qualifier("volunteerWork") @Primary
@Slf4j
public class VolunteerWorkServiceImpl implements VolunteerWorkService {
    private final VolunteerWorkRepository volunteerWorkRepository;

    /*메인페이지 - 봉사 활동 8개 띄우기*/
    @Override
    public List<VolunteerWorkDTO> getVolunteerList() {
//        List<VolunteerWork> volunteerWorks = volunteerWorkRepository.findVolunteerWorkList();
//        List<VolunteerWorkDTO> volunteerWorkDTOS = new ArrayList<>();
//        for (VolunteerWork volunteerWork : volunteerWorks) {
//            VolunteerWorkDTO volunteerWorkDTO = toVolunteerWorkDTO(volunteerWork);
//            volunteerWorkDTOS.add(volunteerWorkDTO);
//        }
//        return volunteerWorkDTOS;
        List<VolunteerWork> volunteerWorkList = volunteerWorkRepository.findVolunteerWorkList();
        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkList.stream()
                .map(volunteerWork -> {
                    VolunteerWorkDTO volunteerWorkDTO = toVolunteerWorkDTO(volunteerWork);
                    List<VolunteerWorkFile> volunteerWorkFiles = volunteerWork.getVolunteerWorkFiles();
                    List<FileDTO> fileDTOS = FileToDTO(volunteerWorkFiles);
                    volunteerWorkDTO.setFileDTOs(fileDTOS);
                    return volunteerWorkDTO;
                }).collect(Collectors.toList());
        volunteerWorkDTOS.stream().map(VolunteerWorkDTO::toString).forEach(v -> log.info(v + "여기야!!!!!!!!!!!!!!!!!!!!!!=================="));
        return volunteerWorkDTOS;
    }


    @Override
    public Page<VolunteerWorkDTO> getVolunteerWork(Integer page, AdminVolunteerSearch adminVolunteerSearch) {
        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository.findAllWithPaging(adminVolunteerSearch, PageRequest.of(page, 5));
        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorks.getContent().stream().map(this::toVolunteerWorkDTO).collect(Collectors.toList());
        return new PageImpl<>(volunteerWorkDTOS, volunteerWorks.getPageable(), volunteerWorks.getTotalElements());
    }

    @Override
    public VolunteerWorkDTO getVolunteerWorkDetail(Long id) {
        return toVolunteerWorkDTO(volunteerWorkRepository.findById_QueryDSL(id).get());
    }

    @Override
    public void deleteVolunteerWorkByIds(List<Long> ids) {
        volunteerWorkRepository.deleteAllById(ids);
    }
}

