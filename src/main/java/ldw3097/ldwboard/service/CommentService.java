package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.Comment;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.CommentRepository;
import ldw3097.ldwboard.web.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveComment(CommentForm commentForm, Post post, User user) {
        Comment comment = new Comment();
        comment.setBody(commentForm.getCommentBody());
        comment.setPost(post);
        comment.setCreateTime(LocalDateTime.now());
        comment.setWriter(user);
        commentRepository.save(comment);
    }

    public Comment findOne(Long commentId){
        return commentRepository.findOne(commentId);
    }

    public void deleteComment(Comment comment){
        commentRepository.delete(comment);
    }

    public void updateComment(Comment comment, String newBody){
        comment.setBody(newBody);
    }
}
