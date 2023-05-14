package com.app.simbongsa.summernote;

import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/summernote")
@RequiredArgsConstructor
@Slf4j
public class SummernoteController {
    private final SupportRequestFileService supportRequestFileService;
    private final SupportRequestRepository supportRequestRepository;


//    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String saveSummernoteImage(@RequestParam("file") MultipartFile imageFile) {
//        log.info("들어와");
//        return supportRequestFileService.saveSummernoteImage(imageFile);
//    }

    @GetMapping(value = "/image/{fileId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] loadSummernoteImage(@PathVariable Long fileId) {
        return supportRequestFileService.loadImage(fileId);
    }

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SupportRequestFileDTO saveImage(@RequestParam("file") MultipartFile imageFile) {
        log.info("이미지 저장 요청 받음");
        SupportRequestFile savedFile = supportRequestFileService.saveImage(imageFile);
        return supportRequestFileService.toDTO(savedFile);
    }
}
