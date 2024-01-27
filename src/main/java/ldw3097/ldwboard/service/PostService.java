package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.PostRepository;

import ldw3097.ldwboard.web.form.PostingForm;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void savePost(String title, String body, Board board, User user) {
        Post post = new Post();
        post.setTitle(title);
        post.setWriter(user);
        post.setCreateTime(LocalDateTime.now());
        post.setBody(body);
        post.setBoard(board);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Post post){
        postRepository.delete(post);
    }

    @Transactional
    public void update(Post post, String newTitle, String newBody){
        post.setTitle(newTitle);
        post.setBody(newBody);
    }

    @Transactional
    public void like(Post post){

    }

}
