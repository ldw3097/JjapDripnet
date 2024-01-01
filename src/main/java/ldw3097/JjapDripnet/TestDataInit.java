package ldw3097.JjapDripnet;

import jakarta.annotation.PostConstruct;
import ldw3097.JjapDripnet.domain.Board;
import ldw3097.JjapDripnet.domain.Post;
import ldw3097.JjapDripnet.domain.User;
import ldw3097.JjapDripnet.repository.BoardRepository;
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

    @PostConstruct
    public void init(){
        Board openBoard = new Board();
        openBoard.setName("open_board");
        boardRepository.save(openBoard);
        User user1 = new User();
        user1.setId("tester1");
        user1.setPassword("pass1");
        userRepository.save(user1);
        int postCount = 150;
        Post[] posts = new Post[postCount];
        for(int i=0; i<postCount; i++){
            posts[i] = new Post();
            posts[i].setBoard(openBoard);
            posts[i].setUser(user1);
            posts[i].setTitle("sampletitle" + i);
            posts[i].setBody("samplebody" + i);
            posts[i].setCreateTime(LocalDateTime.now());
            postRepository.save(posts[i]);

        }


    }
}
