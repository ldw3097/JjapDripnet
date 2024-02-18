package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.*;
import ldw3097.ldwboard.repository.PostRepository;
import ldw3097.ldwboard.repository.UserPostDislikesRepository;
import ldw3097.ldwboard.repository.UserPostLikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserPostLikesRepository userPostLikesRepository;
    private final UserPostDislikesRepository userPostDislikesRepository;

    @Cacheable(value = "postCache", key="#postId")
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }



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
    @CacheEvict(value = "postCache", key="#post.id")
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Transactional
    @CachePut(value = "postCache", key="#post.id")
    public Post update(Post post, String newTitle, String newBody) {
        post.setTitle(newTitle);
        post.setBody(newBody);
        return post;
    }

    @Transactional
    @CachePut(value = "postCache", key="#post.id")
    public Post likeOrUnlike(Post post, User user) {
        if (userPostLikesRepository.existsByUserIdAndPostId(user.getId(), post.getId())) {
            post.unlike();
            userPostLikesRepository.deleteByUserIdAndPostId(user.getId(), post.getId());
        } else {
            post.like();
            userPostLikesRepository.save(new UserPostLikes(user, post));
        }
        return post;
    }


    public boolean isLiked(Post post, User user) {
        return userPostLikesRepository.existsByUserIdAndPostId(user.getId(), post.getId());
    }

    public boolean isDisliked(Post post, User user) {
        return userPostDislikesRepository.existsByUserIdAndPostId(user.getId(), post.getId());
    }

    @Transactional
    public void dislikeOrUndislike(Post post, User user) {
        if (userPostDislikesRepository.existsByUserIdAndPostId(user.getId(), post.getId())) {
            post.undislike();
            userPostDislikesRepository.deleteByUserIdAndPostId(user.getId(), post.getId());
        } else {
            post.dislike();
            userPostDislikesRepository.save(new UserPostDislikes(user, post));
        }
    }


}
