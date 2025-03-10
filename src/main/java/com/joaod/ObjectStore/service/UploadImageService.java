package com.joaod.ObjectStore.service;

import com.joaod.ObjectStore.model.ImageModel;
import com.joaod.ObjectStore.repository.ImageRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UploadImageService {
    @Autowired
    MinioClient minioClient;

    @Value("${application.config.salvaimagembanco}")
    private boolean isSalvaImageNoBanco;
    @Autowired
    private ImageRepository imageRepository;

    public void uploadFile(MultipartFile file) throws Exception {
        var inputStream = file.getInputStream();
        String imageId = UUID.randomUUID().toString();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("images").object(imageId).stream(inputStream, inputStream.available(), -1)
                        .contentType("image/png")
                        .build()
        );
        if (isSalvaImageNoBanco) {
            ImageModel imageModel = new ImageModel();
            imageModel.setUuid(imageId);
            imageRepository.save(imageModel);
        }
    }

    public byte[] getImage(String imageId) throws Exception{
        var stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket("images")
                        .object(imageId)
                        .build());

        return IOUtils.toByteArray(stream);
    }


}
