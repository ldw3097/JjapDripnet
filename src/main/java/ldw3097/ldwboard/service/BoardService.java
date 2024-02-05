package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.repository.BoardRepository;
import ldw3097.ldwboard.repository.ComplexPostRepository;
import ldw3097.ldwboard.repository.PostSearchKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ComplexPostRepository complexPostRepository;

    @Transactional
    public void saveBoard(Board board){
        boardRepository.save(board);
    }

    public Page<Post> searchPage(String boardId, PostSearchKey postSearchKey, String postSearchVal, PageRequest pageable){
        return complexPostRepository.searchPost(boardId, postSearchKey, postSearchVal, pageable);
    }

}
