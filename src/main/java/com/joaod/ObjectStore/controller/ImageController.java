package com.joaod.ObjectStore.controller;

import com.joaod.ObjectStore.service.UploadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    UploadImageService uploadImageService;

    @PostMapping("/upload-image")
    public void uploadImage(@RequestParam MultipartFile file) throws Exception {
        uploadImageService.uploadFile(file);
    }

    @GetMapping(value = "/{ibjectId}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImageByObjectId(@PathVariable("ibjectId") String objectId) throws Exception {
        return uploadImageService.getImage(objectId);
    }

}
