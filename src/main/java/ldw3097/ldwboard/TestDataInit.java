package ldw3097.ldwboard;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.domain.Comment;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.BoardRepository;
import ldw3097.ldwboard.repository.CommentRepository;
import ldw3097.ldwboard.repository.PostRepository;
import ldw3097.ldwboard.repository.UserRepository;
import ldw3097.ldwboard.service.BoardService;
import ldw3097.ldwboard.service.CommentService;
import ldw3097.ldwboard.service.PostService;
import ldw3097.ldwboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestDataInit implements ApplicationRunner {

    private final BoardService boardService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Board openBoard = new Board();
        openBoard.setId("open_board");
        boardService.saveBoard(openBoard);
        em.flush();

        userService.addUser("test", "1234");
        User user1 = userRepository.findById("test").orElseThrow();

        for (int i = 0; i < 150; i++) {
            postService.savePost("sample title no." + i, "sample content no." + i, openBoard, user1);
        }

        Long indicatingPostId = postService.savePost("테스팅 계정 ID: test  PW: 1234", "테스팅 계정은 테스트 할때 사용하셔도 됩니다.", openBoard, user1);
        Post indicatingPost  = postRepository.findById(indicatingPostId).orElseThrow();
        commentService.saveComment("댓글 테스트", indicatingPost, user1);

        Board readings = new Board();
        readings.setId("readings");
        boardService.saveBoard(readings);
        Board qna = new Board();
        qna.setId("qna");
        boardService.saveBoard(qna);
        Board game = new Board();
        game.setId("game");
        boardService.saveBoard(game);
    }


}


