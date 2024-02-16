package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.dto.PostInfoDto;
import ldw3097.ldwboard.repository.BoardRepository;
import ldw3097.ldwboard.repository.ComplexPostRepository;
import ldw3097.ldwboard.repository.PostRepository;
import ldw3097.ldwboard.repository.PostSearchKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ComplexPostRepository complexPostRepository;
    private final PostRepository postRepository;

    @Transactional
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }


    public Page<PostInfoDto> getPage(String boardId, PageRequest pageRequest) {
        return complexPostRepository.searchPost(boardId, null, null, pageRequest);
    }

//    public Page<Post> getPage(String boardId, PageRequest pageRequest) {
//        Page<Post> postPage = postRepository.findByBoardId(boardId, pageRequest);
//        postPage.stream()
//                .forEach(post -> {
//                    Integer viewCnt = viewCntCache.get(post.getId(), Integer.class);
//                    log.info("while getting page, got viewCnt of {}", viewCnt);
//                    if (viewCnt != null) {
//                        post.setViewCnt(viewCnt);
//                    }
//                });
//        return postPage;
//    }

    public Page<PostInfoDto> searchPage(String boardId, PostSearchKey postSearchKey, String postSearchVal, PageRequest pageable){
        return complexPostRepository.searchPost(boardId, postSearchKey, postSearchVal, pageable);
    }


}
