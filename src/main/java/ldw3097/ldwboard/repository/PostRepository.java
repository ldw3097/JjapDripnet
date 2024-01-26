package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByBoardId(String boardId, Pageable pageable);
}
