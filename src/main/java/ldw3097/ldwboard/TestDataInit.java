package ldw3097.ldwboard;

import jakarta.annotation.PostConstruct;
import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.domain.Comment;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.BoardRepository;
import ldw3097.ldwboard.repository.CommentRepository;
import ldw3097.ldwboard.repository.PostRepository;
import ldw3097.ldwboard.repository.UserRepository;
import ldw3097.ldwboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    @PostConstruct
    public void init(){
        Board openBoard = new Board();
        openBoard.setId("open_board");
        boardRepository.save(openBoard);

        userService.addUser("test", "1234");
        User user1 = userRepository.findById("test").orElseThrow();

        int postCount = 150;
        Post[] posts = new Post[postCount];
        for(int i=0; i<postCount; i++){
            posts[i] = new Post();
            posts[i].setBoard(openBoard);
            posts[i].setWriter(user1);
            posts[i].setTitle("sample title no." + i);
            posts[i].setBody("sample content no." + i);
            posts[i].setCreateTime(LocalDateTime.now());
            postRepository.save(posts[i]);
        }
        Post indicatingPost = new Post();
        indicatingPost.setBoard(openBoard);
        indicatingPost.setWriter(user1);
        indicatingPost.setTitle("테스팅 계정 ID: test  PW: 1234");
        indicatingPost.setBody("테스팅 계정은 테스트 할때 사용하셔도 됩니다.");
        indicatingPost.setCreateTime(LocalDateTime.now());
        postRepository.save(indicatingPost);

        Comment comment= new Comment();
        comment.setWriter(user1);
        comment.setPost(indicatingPost);
        comment.setCreateTime(LocalDateTime.now());
        comment.setBody("댓글 테스트");
        commentRepository.save(comment);

        Board readings = new Board();
        readings.setId("readings");
        boardRepository.save(readings);
        Board qna = new Board();
        qna.setId("qna");
        boardRepository.save(qna);
        Board game = new Board();
        game.setId("game");
        boardRepository.save(game);

    }
}
