package ldw3097.ldwboard.web;


import ldw3097.ldwboard.service.ImageServiceLocal;
import ldw3097.ldwboard.service.ImageServiceS3;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageServiceS3 imageServiceS3;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam final MultipartFile image){
        if (image.isEmpty()){
            return ResponseEntity.badRequest().body("이미지가 없습니다.");
        }
        try{
            String filename = imageServiceS3.storeFile(image);
            return ResponseEntity.ok(filename);
        }catch (IOException e){
            return ResponseEntity.internalServerError().body("이미지 저장에 실패했습니다.");
        }
    }

    @GetMapping("/attach")
    public ResponseEntity<Resource> getImage(@RequestParam String filename){

        Resource resource = imageServiceS3.downloadFile(filename);
        if (resource.exists() && resource.isReadable()){
            return ResponseEntity.ok().body(resource);

        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
