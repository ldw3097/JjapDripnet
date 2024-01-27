package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.*;
import ldw3097.ldwboard.repository.PostRepository;

import ldw3097.ldwboard.repository.UserPostDislikesRepository;
import ldw3097.ldwboard.repository.UserPostLikesRepository;
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
    private final UserPostLikesRepository userPostLikesRepository;
    private final UserPostDislikesRepository userPostDislikesRepository;

    @Transactional
    public Long savePost(String title, String body, Board board, User user) {
        Post post = new Post();
        post.setTitle(title);
        post.setWriter(user);
        post.setCreateTime(LocalDateTime.now());
        post.setBody(body);
        post.setBoard(board);
        postRepository.save(post);
        return post.getId();
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
    public void likeOrUnlike(Post post, User user){
        if(userPostLikesRepository.existsByUserIdAndPostId(user.getId(), post.getId())){
            post.unlike();
            userPostLikesRepository.deleteByUserIdAndPostId(user.getId(), post.getId());
        }else{
            post.like();
            userPostLikesRepository.save(new UserPostLikes(user, post));
        }
    }

    public boolean isLiked(Post post, User user){
        return userPostLikesRepository.existsByUserIdAndPostId(user.getId(), post.getId());
    }

    public boolean isDisliked(Post post, User user){
        return userPostDislikesRepository.existsByUserIdAndPostId(user.getId(), post.getId());
    }

    @Transactional
    public void dislikeOrUndislike(Post post, User user){
        if(userPostDislikesRepository.existsByUserIdAndPostId(user.getId(), post.getId())){
            post.undislike();
            userPostDislikesRepository.deleteByUserIdAndPostId(user.getId(), post.getId());
        }else{
            post.dislike();
            userPostDislikesRepository.save(new UserPostDislikes(user, post));
        }
    }

}
