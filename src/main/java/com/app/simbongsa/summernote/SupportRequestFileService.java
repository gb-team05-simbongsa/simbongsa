package com.app.simbongsa.summernote;

import com.app.simbongsa.entity.file.File;
import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.type.FileRepresentationalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class SupportRequestFileService {
    private final SupportRequestFileRepository supportRequestFileRepository;

    @Autowired
    public SupportRequestFileService(SupportRequestFileRepository supportRequestFileRepository) {
        this.supportRequestFileRepository = supportRequestFileRepository;
    }

    // 이미지 파일 저장 기능 구현
    public SupportRequestFile saveImage(MultipartFile imageFile, SupportRequest supportRequest) {
        try {
            // 이미지 파일을 저장하는 로직 구현
            String fileName = imageFile.getOriginalFilename();
            String fileUuid = UUID.randomUUID().toString();
            String filePath = "/uploads/" + fileUuid;
            FileRepresentationalType fileRepresentationalType = FileRepresentationalType.NORMAL;


            Path savePath = Paths.get("C:/uploads/" + fileUuid);
            try (InputStream inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, savePath, StandardCopyOption.REPLACE_EXISTING);
            }

            // SupportRequestFile 엔티티 생성 및 저장
            SupportRequestFile supportRequestFile = new SupportRequestFile(fileName, fileUuid, filePath, fileRepresentationalType, supportRequest);
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

            Path imagePath = Paths.get("C:" + filePath);
            byte[] fileBytes = Files.readAllBytes(imagePath);
            return fileBytes;
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            throw new RuntimeException("Failed to load image file.");
        }
    }

    // 써머노트에서 이미지 파일 저장 기능 구현
    public String saveSummernoteImage(MultipartFile imageFile) {
        try {
            // 이미지 파일을 저장하는 로직 구현
            String fileName = imageFile.getOriginalFilename();
            String fileUuid = UUID.randomUUID().toString();
            String filePath = "/uploads/" + fileUuid;


            Path savePath = Paths.get("C:/uploads/" + fileUuid);
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
}
