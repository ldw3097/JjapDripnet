package ldw3097.ldwboard.web;


import ldw3097.ldwboard.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
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

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam final MultipartFile image){
        if (image.isEmpty()){
            return ResponseEntity.badRequest().body("이미지가 없습니다.");
        }
        try{
            String filename = imageService.storeFile(image);
            return ResponseEntity.ok(filename);
        }catch (IOException e){
            return ResponseEntity.internalServerError().body("이미지 저장에 실패했습니다.");
        }

    }

    @GetMapping("/attach")
    public ResponseEntity<Resource> getImage(@RequestParam String filename) throws MalformedURLException{
        Path filePath = Paths.get(imageService.getFullPath(filename));
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() && resource.isReadable()){
            return ResponseEntity.ok().body(resource);

        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
