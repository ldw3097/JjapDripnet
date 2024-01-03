package ldw3097.JjapDripnet;

import jakarta.annotation.PostConstruct;
import ldw3097.JjapDripnet.domain.Board;
import ldw3097.JjapDripnet.domain.Comment;
import ldw3097.JjapDripnet.domain.Post;
import ldw3097.JjapDripnet.domain.User;
import ldw3097.JjapDripnet.repository.BoardRepository;
import ldw3097.JjapDripnet.repository.CommentRepository;
import ldw3097.JjapDripnet.repository.PostRepository;
import ldw3097.JjapDripnet.repository.UserRepository;
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

    @PostConstruct
    public void init(){
        Board openBoard = new Board();
        openBoard.setId("open_board");
        boardRepository.save(openBoard);
        User user1 = new User();
        user1.setId("tester");
        user1.setPassword("pass");
        userRepository.save(user1);
        int postCount = 150;
        Post[] posts = new Post[postCount];
        for(int i=0; i<postCount; i++){
            posts[i] = new Post();
            posts[i].setBoard(openBoard);
            posts[i].setUser(user1);
            posts[i].setTitle("sample title " + i);
            posts[i].setBody("sample content " + i);
            posts[i].setCreateTime(LocalDateTime.now());
            postRepository.save(posts[i]);
        }
        Post indicatingPost = new Post();
        indicatingPost.setBoard(openBoard);
        indicatingPost.setUser(user1);
        indicatingPost.setTitle("테스팅 계정 ID: tester   PW: pass");
        indicatingPost.setBody("테스팅 계정은 테스트 할때 사용하셔도 됩니다.");
        indicatingPost.setCreateTime(LocalDateTime.now());
        postRepository.save(indicatingPost);

        Comment comment= new Comment();
        comment.setUser(user1);
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
