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
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

        List<VolunteerWork> volunteerWorkList = volunteerWorkRepository.findVolunteerWorkList();

        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkList.stream()
                .map(volunteerWork -> {
                    VolunteerWorkDTO volunteerWorkDTO = toVolunteerWorkDTO(volunteerWork);
                    List<VolunteerWorkFile> volunteerWorkFiles = volunteerWork.getVolunteerWorkFiles();
                    List<FileDTO> fileDTOS = FileToDTO(volunteerWorkFiles);
                    volunteerWorkDTO.setFileDTOs(fileDTOS);
                    return volunteerWorkDTO;
                }).collect(Collectors.toList());

//        List<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkList.stream().map(this::toVolunteerWorkDTO).collect(Collectors.toList());
        volunteerWorkList.stream().map(VolunteerWork::getVolunteerWorkFiles).forEach(v -> log.info(v.toString()));
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

    @Override
    public Map<String, Object> uploadFile(List<MultipartFile> multipartFiles) throws IOException {
        Map<String, Object> map = new HashMap<>();

        List<String> uuids = new ArrayList<>();
        List<String> filePaths = new ArrayList<>();
        String path = "/C:/upload" + "/" + getPath();
        String filePath = "";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
//
        for (int i = 0; i < multipartFiles.size(); i++) {
            uuids.add(UUID.randomUUID().toString());
            filePath = uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename();
            /* multipartFiles로 가져온 파일을 path, uuid, fileOriginalName 을 File 객체로 만들어 저장 */
            multipartFiles.get(i).transferTo(new File(path, uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));

            /* 해당 파일이 이미지인 경우 썸네일도 저장 */
            if (multipartFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(multipartFiles.get(i).getInputStream(), out, 150, 150);
                out.close();
                filePath = "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename();
            }

            filePaths.add(getPath() + "/" + filePath);
        }
//
        map.put("uuids", uuids);
        map.put("paths", filePaths);
        return map;
    }

//    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}

