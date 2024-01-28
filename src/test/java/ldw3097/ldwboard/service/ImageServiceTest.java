package ldw3097.ldwboard.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ImageServiceTest {


    @Test
    public void newFileNameTest() throws Exception {
        // Given
        ImageService imageService = new ImageService();

        // When
        String originalFileName = "testing.png";


        // Then
        Assertions.assertThat(imageService.extractExt(originalFileName)).isEqualTo("png");
        System.out.println(imageService.createStoreFileName("png"));
        System.out.println(imageService.getFullPath("asdf.png"));
    }
}