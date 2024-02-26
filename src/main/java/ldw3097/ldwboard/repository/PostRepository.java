package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.Board;
import ldw3097.ldwboard.domain.Post;
import ldw3097.ldwboard.dto.PostInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new ldw3097.ldwboard.dto.PostInfoDto(p.id, p.title, p.writer.id, p.likes, p.createTime, p.commentCnt)" +
            "from Post p where p.board.id = :boardId")
    Page<PostInfoDto> getPosts(@Param("boardId") String boardID, Pageable pageable);

}
