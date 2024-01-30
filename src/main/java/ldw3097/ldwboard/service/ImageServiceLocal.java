package ldw3097.ldwboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageServiceLocal {

    @Value("${file.dir}")
    String fileDir;

    public String extractExt(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }


    public String createStoreFileName(String ext) {
        return UUID.randomUUID().toString() + "." + ext;
    }

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) return null;
        String storeFileName = createStoreFileName(extractExt(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return storeFileName;
    }


}
