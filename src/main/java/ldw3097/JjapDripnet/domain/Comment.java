package ldw3097.JjapDripnet.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    private Post post;

    @Lob
    private String body;

    private LocalDateTime createTime;
}
