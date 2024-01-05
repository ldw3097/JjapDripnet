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

    public List<Post> postPage(String boardName, int pageNum){
        return postRepository.findPage(boardName, pageNum);
    }

    public int getTotalPageNum(String boardId){
        int pageCount =  postRepository.countPage(boardId).intValue();
        return (pageCount -1) / PostRepository.pageLimit + 1;
    }

    public void savePost(PostingForm postingForm, Board board, User user) {
        Post post = new Post();
        post.setTitle(postingForm.getTitle());
        post.setWriter(user);
        post.setCreateTime(LocalDateTime.now());
        post.setBody(postingForm.getBody());
        post.setBoard(board);
        postRepository.save(post);
    }

    public void deletePost(Post post){
        postRepository.delete(post);
    }

    @Transactional
    public void update(Post post, String newTitle, String newBody){
        post.setTitle(newTitle);
        post.setBody(newBody);
    }

}
