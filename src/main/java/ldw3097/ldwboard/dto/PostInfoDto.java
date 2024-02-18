package ldw3097.ldwboard.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostInfoDto {
    Long id;
    String title;
    String writerId;
    int likes;
    LocalDateTime createTime;
    int commentCnt;

    public PostInfoDto(Long id, String title, String writerId, int likes, LocalDateTime createTime, int commentCnt) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.likes = likes;
        this.createTime = createTime;
        this.commentCnt = commentCnt;
    }
}
