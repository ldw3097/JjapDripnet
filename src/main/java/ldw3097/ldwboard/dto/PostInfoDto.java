package ldw3097.ldwboard.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostInfoDto {
    Long id;
    String title;
    String writerId;
    int likes;
    int dislikes;
    LocalDateTime createTime;
    int viewCnt;
    int commentCnt;

    public PostInfoDto(Long id, String title, String writerId, int likes, int dislikes, LocalDateTime createTime, int viewCnt, int commentCnt) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.createTime = createTime;
        this.viewCnt = viewCnt;
        this.commentCnt = commentCnt;
    }
}
