package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void saveBoard(Board board){
        boardRepository.save(board);
    }

}
