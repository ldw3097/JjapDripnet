package ldw3097.JjapDripnet.repository;

import jakarta.persistence.EntityManager;
import ldw3097.JjapDripnet.domain.Comment;
import ldw3097.JjapDripnet.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    @Transactional
    public void save(Comment comment){
        em.persist(comment);
    }

    public List<Comment> getComments(Post post){
        List<Comment> comments = post.getComment();
        return comments;
    }


}
