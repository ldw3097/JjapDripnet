package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
