package ldw3097.ldwboard.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class ImageServiceLocalTest {


    @Test
    public void newFileNameTest() throws Exception {
        // Given
        ImageServiceLocal imageServiceLocal = new ImageServiceLocal();

        // When
        String originalFileName = "testing.png";


        // Then
        Assertions.assertThat(imageServiceLocal.extractExt(originalFileName)).isEqualTo("png");
        System.out.println(imageServiceLocal.createStoreFileName("png"));
        System.out.println(imageServiceLocal.getFullPath("asdf.png"));
    }
}