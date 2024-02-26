package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.Comment;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.CommentRepository;
import ldw3097.ldwboard.web.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    @CacheEvict(value = "postCache", key="#post.id")
    public void saveComment(String commentBody, Post post, User user) {
        Comment comment = new Comment();
        comment.setBody(commentBody);
        comment.setPost(post);
        comment.setCreateTime(LocalDateTime.now());
        comment.setWriter(user);
        post.incCommentCnt();
        commentRepository.save(comment);
    }

    public Optional<Comment> findOne(Long commentId){
        return commentRepository.findById(commentId);
    }

    @Transactional
    @CacheEvict(value = "postCache", key="#comment.post.id")
    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
        Post targetPost = comment.getPost();
        targetPost.decCommentCnt();
    }

    @Transactional
    @CacheEvict(value = "postCache", key="#comment.post.id")
    public void updateComment(Comment comment, User user, String newBody){
        if (comment.getWriter().getId().equals(user.getId()) ){
            comment.setBody(newBody);
        }
    }
}
