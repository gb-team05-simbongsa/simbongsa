package com.app.simbongsa.summernote;

import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.repository.support.SupportRepository;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupportRequestFileService {
    private final SupportRequestFileRepository supportRequestFileRepository;
    private final SupportRequestRepository supportRequestRepository;
    // 이미지 파일 저장 기능 구현
    public SupportRequestFile saveImage(MultipartFile imageFile) {
        log.info("이미지 저장 요청 받음");

        try {
            // 이미지 파일을 저장하는 로직 구현
            String fileName = imageFile.getOriginalFilename();
            String fileUuid = UUID.randomUUID().toString();
            String filePath = "/Users/hyun/uploads/" + fileUuid; /*/uploads/*/
            FileRepresentationalType fileRepresentationalType = FileRepresentationalType.NORMAL;

            Path savePath = Paths.get("/Users/hyun/uploads/" + fileUuid); /*C:/uploads/*/
            try (InputStream inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // SupportRequestFile 엔티티 생성 및 저장
            SupportRequestFile supportRequestFile = new SupportRequestFile(fileName, fileUuid, filePath, fileRepresentationalType, supportRequestRepository.findById(144L).get());

            return supportRequestFileRepository.save(supportRequestFile);
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            throw new RuntimeException("Failed to save image file.");
        }
    }

    // 이미지 파일 불러오기 기능 구현
    public byte[] loadImage(Long fileId) {
        // 파일 ID를 기반으로 이미지 파일을 불러오는 로직 구현
        SupportRequestFile supportRequestFile = supportRequestFileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found."));
        String filePath = supportRequestFile.getFilePath();

        try {
//            Path imagePath = Paths.get("/Users/hyun" + filePath); /*"C:*/
            Path imagePath = Paths.get(filePath);
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            throw new RuntimeException("Failed to load image file.");
        }
    }

    // 써머노트에서 이미지 파일 저장 기능 구현
    public String saveSummernoteImage(MultipartFile imageFile) {
        log.info("써머노트 이미지 저장 요청 받음");
        try {
            // 이미지 파일을 저장하는 로직 구현
            String fileName = imageFile.getOriginalFilename();
            String fileUuid = UUID.randomUUID().toString();
            String filePath = "/Users/hyun/uploads/" + fileUuid; /*/uploads/*/

            Path savePath = Paths.get("/Users/hyun/uploads/" + fileUuid);/*C:uploads/*/
            try (InputStream inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);
            }

            return filePath;
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            throw new RuntimeException("Failed to save Summernote image file.");
        }
    }

    public SupportRequestFileDTO toDTO(SupportRequestFile supportRequestFile) {
        return SupportRequestFileDTO.builder()
                .fileName(supportRequestFile.getFileName())
                .fileUuid(supportRequestFile.getFileUuid())
                .filePath(supportRequestFile.getFilePath())
                .fileRepresentationalType(supportRequestFile.getFileRepresentationalType())
                .supportRequest(supportRequestFile.getSupportRequest())
                .build();
    }

    public SupportRequestFile toEntity(SupportRequestFileDTO supportRequestFileDTO){
        return SupportRequestFile.builder()
                .fileName(supportRequestFileDTO.getFileName())
                .filePath(supportRequestFileDTO.getFilePath())
                .fileRepresentationalType(supportRequestFileDTO.getFileRepresentationalType())
                .fileUuid(supportRequestFileDTO.getFileUuid())
                .supportRequest(supportRequestFileDTO.getSupportRequest())
                .build();
    }
}
