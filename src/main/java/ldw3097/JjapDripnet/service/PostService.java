package ldw3097.JjapDripnet.service;

import ldw3097.JjapDripnet.domain.Post;
import ldw3097.JjapDripnet.repository.PostRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    public List<Post> postPage(String boardName, int pageNum){
        return postRepository.findPage(boardName, pageNum);
    }

    public int getTotalPageNum(String boardId){
        int pageCount =  postRepository.countPage(boardId).intValue();
        return (pageCount -1) / PostRepository.pageLimit + 1;
    }


}
