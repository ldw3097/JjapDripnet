package ldw3097.ldwboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private User writer;

    @ManyToOne(fetch=FetchType.LAZY)
    private Post post;

    @Lob
    private String body;

    private LocalDateTime createTime;
}
