package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/file/*")
@RequiredArgsConstructor
public class FileRestController {

    @PostMapping("upload")
    public Map<String, Object> upload(@RequestParam("file") List<MultipartFile> multipartFiles) throws IOException {
        Map<String, Object> map = new HashMap<>();

        List<String> uuids = new ArrayList<>();
        List<String> filePaths = new ArrayList<>();
        String path = "/C:/upload/" + getPath();
        String filePath = "";
        File file = new File(path);
        if(!file.exists()) {file.mkdirs();}


        for(int i=0; i<multipartFiles.size(); i++){
            uuids.add(UUID.randomUUID().toString());
            filePath = uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename();
            multipartFiles.get(i).transferTo(new File(path, filePath));

            InputStream inputStream = new FileInputStream("/C:/upload/" + getPath() + "/" + uuids.get(i)+ "_" + multipartFiles.get(i).getOriginalFilename());

            if(multipartFiles.get(i).getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(path, "t_" + uuids.get(i) + "_" + multipartFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(inputStream, out, 400, 400);
                out.close();
            }

             filePaths.add(getPath() + "/" + filePath);
        }

        map.put("uuids", uuids);
        map.put("paths", filePaths);

        return map;
    }

    //    파일 불러오기
    @GetMapping("display")
    public byte[] businessDisplay(String fileName) throws Exception {
        try {
            return fileName.contentEquals("null") || fileName.isBlank() ? null : FileCopyUtils.copyToByteArray(new File("/C:/upload", fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //    현재 날짜 경로 구하기
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
