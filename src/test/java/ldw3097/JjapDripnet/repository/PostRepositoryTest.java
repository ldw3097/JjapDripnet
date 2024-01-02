package ldw3097.JjapDripnet.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

//    @Test
//    public void PostRepositoryTest() throws Exception {
//        // Given
//
//        // When
//        Long count = postRepository.countPage();
//
//        // Then
//        Assertions.assertThat(count).isEqualTo(150);
//
//    }
}