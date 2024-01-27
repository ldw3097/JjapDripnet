package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.Comment;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.CommentRepository;
import ldw3097.ldwboard.web.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
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
    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
        Post targetPost = comment.getPost();
        targetPost.decCommentCnt();
    }

    @Transactional
    public void updateComment(Long commentId, User user, String newBody){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (comment.getWriter().getId().equals(user.getId()) ){
            comment.setBody(newBody);
        }
    }
}
