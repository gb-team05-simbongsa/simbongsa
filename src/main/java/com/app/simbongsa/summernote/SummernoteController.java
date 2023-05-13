package com.app.simbongsa.summernote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/summernote")
public class SummernoteController {
    private final SupportRequestFileService supportRequestFileService;

    @Autowired
    public SummernoteController(SupportRequestFileService supportRequestFileService) {
        this.supportRequestFileService = supportRequestFileService;
    }

    @PostMapping("/image")
    public String saveSummernoteImage(@RequestParam("file") MultipartFile imageFile) {
        return supportRequestFileService.saveSummernoteImage(imageFile);
    }

    @GetMapping(value = "/image/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] loadSummernoteImage(@PathVariable Long fileId) {
        return supportRequestFileService.loadImage(fileId);
    }

}
