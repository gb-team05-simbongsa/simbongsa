package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.repository.volunteer.VolunteerWorkFileRepository;
import com.app.simbongsa.repository.volunteer.VolunteerWorkRepository;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import com.app.simbongsa.type.FileRepresentationalType;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
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
@Qualifier("volunteerWork") @Primary
@Slf4j
public class VolunteerWorkServiceImpl implements VolunteerWorkService {
    private final VolunteerWorkRepository volunteerWorkRepository;
    private final VolunteerWorkFileRepository volunteerWorkFileRepository;

    /*메인페이지 - 봉사 활동 8개 띄우기*/
    @Override
    public List<VolunteerWorkDTO> getVolunteerList() {

        List<VolunteerWork> volunteerWorkList = volunteerWorkRepository.findVolunteerWorkList();

        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkList.stream()
                .map(volunteerWork -> {
                    VolunteerWorkDTO volunteerWorkDTO = toVolunteerWorkDTO(volunteerWork);
                    VolunteerWorkFile volunteerWorkFile = volunteerWork.getVolunteerWorkFile();
                    FileDTO fileDTO = toFileDTO(volunteerWorkFile);
                    volunteerWorkDTO.setFileDTO(fileDTO);
                    return volunteerWorkDTO;
                }).collect(Collectors.toList());

//        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkList.stream().map(this::toVolunteerWorkDTO).collect(Collectors.toList());
        volunteerWorkList.stream().map(VolunteerWork::getVolunteerWorkFile).forEach(v -> log.info(v.toString()));
        volunteerWorkDTOS.stream().map(VolunteerWorkDTO::toString).forEach(v -> log.info(v + "여기야!!!!!!!!!!!!!!!!!!!!!!=================="));
        return volunteerWorkDTOS;
    }


    // 봉사 목록페이지 검색, 무한스크롤
    @Override
    public VolunteerWork getCurrentSequence() {
        return volunteerWorkRepository.getCurrentSequence_QueryDSL();
    }


    @Override
    public Page<VolunteerWorkDTO> getVolunteerWork(Integer page, AdminVolunteerSearch adminVolunteerSearch) {
        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository.findAllWithPaging(adminVolunteerSearch, PageRequest.of(page, 5));
        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorks.getContent().stream().map(this::toVolunteerWorkDTO).collect(Collectors.toList());
        return new PageImpl<>(volunteerWorkDTOS, volunteerWorks.getPageable(), volunteerWorks.getTotalElements());
    }

    @Override
    public void saveVolunteerWork(VolunteerWorkDTO volunteerWorkDTO) {
        FileDTO fileDTO = volunteerWorkDTO.getFileDTO();

        volunteerWorkRepository.save(toVolunteerWorkEntity(volunteerWorkDTO));

        fileDTO.setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
        fileDTO.setVolunteerWork(getCurrentSequence());
        volunteerWorkFileRepository.save(toVolunteerWorkFileEntity(fileDTO));
    }

    @Override
    public VolunteerWorkDTO getVolunteerWorkDetail(Long id) {
        log.info("========================= 들어오나2222222 ==================================");
        return toVolunteerWorkDTO(volunteerWorkRepository.findById_QueryDSL(id).get());
    }

    @Override
    public void deleteVolunteerWorkByIds(List<Long> ids) {
        volunteerWorkRepository.deleteAllById(ids);
    }

    @Override
    @Transactional
    public void updateVolunteerWork(VolunteerWorkDTO volunteerWorkDTO) {
        FileDTO fileDTO = volunteerWorkDTO.getFileDTO();

        volunteerWorkRepository.updateVolunteerWork(toVolunteerWorkEntity(volunteerWorkDTO));
        volunteerWorkFileRepository.updateVolunteerWorkFile(toVolunteerWorkFileEntity(fileDTO));
    }

    @Override
    public Page<VolunteerWorkDTO> pagingVolunteerWork(String keyword, Integer page, String volunteerWorkCategoryType) {
        log.info(keyword + "keyword  ====  ====  ===   ==================");
        log.info(page + "=======================");
        log.info(volunteerWorkCategoryType + "=============");
        VolunteerWorkCategoryType categoryType = null;

        switch (volunteerWorkCategoryType){
            case "전체":
                categoryType = null;
                break;
            case "시설봉사":
                categoryType = VolunteerWorkCategoryType.시설봉사;
            break;
            case "재가봉사":
                categoryType = VolunteerWorkCategoryType.재가봉사;
            break;
            case "전문봉사":
                categoryType = VolunteerWorkCategoryType.전문봉사;
            break;
            case "지역사회봉사":
                categoryType = VolunteerWorkCategoryType.지역사회봉사;
            break;
            case "금물품봉사":
                categoryType = VolunteerWorkCategoryType.금물품봉사;
            break;
            case "해외봉사":
                categoryType = VolunteerWorkCategoryType.해외봉사;
            break;
            case "헌혈":
                categoryType = VolunteerWorkCategoryType.헌혈;
            break;
            case "재능기부봉사":
                categoryType = VolunteerWorkCategoryType.재능기부봉사;
            break;
            default:
                categoryType= null;
                break;
        }
        log.info(categoryType + "===============");

        log.info(keyword + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + "키워드");
        log.info(keyword + "keyword  ====  ====  ===   ==================");


        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository.findAllPagingAndSearch(keyword, PageRequest.of(page, 8), categoryType);
        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorks.getContent().stream().map(this::toVolunteerWorkDTO).collect(Collectors.toList());
        return new PageImpl<>(volunteerWorkDTOS, volunteerWorks.getPageable(), volunteerWorks.getTotalElements());
    }

    @Override
    public Page<VolunteerWorkDTO> pagingVolunteerWorkCategory(VolunteerWorkCategoryType volunteerWorkCategoryType, Integer page) {
        Page<VolunteerWork> volunteerWorks = volunteerWorkRepository.findAllByCategory_QueryDSL(volunteerWorkCategoryType, PageRequest.of(page, 8));
        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorks.getContent().stream().map(this::toVolunteerWorkDTO).collect(Collectors.toList());
        return new PageImpl<>(volunteerWorkDTOS, volunteerWorks.getPageable(), volunteerWorks.getTotalElements());
    }


}




