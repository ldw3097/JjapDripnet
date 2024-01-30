package ldw3097.ldwboard.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageServiceS3 {

    public String extractExt(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }

    public String createStoreFileName(String ext) {
        return UUID.randomUUID().toString() + "." + ext;
    }


    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) return null;
        String storeFileName = createStoreFileName(extractExt(Objects.requireNonNull(multipartFile.getOriginalFilename())));

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());
        s3Client.putObject(new PutObjectRequest(bucketName, storeFileName, multipartFile.getInputStream(), metadata));
        return storeFileName;
    }

    public Resource downloadFile(String fileName){
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        return new InputStreamResource(inputStream);
    }


}
