package ldw3097.ldwboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Slf4j
//@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Post {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String title;

    @Lob
    private String body;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment;

    private int commentCnt;

    private LocalDateTime createTime;


    private int likes;

    private int dislikes;


    public void like(){
        ++likes;
    }

    public void unlike(){
        --likes;
    }

    public void dislike(){
        ++dislikes;
    }

    public void undislike(){
        --dislikes;
    }

    public void incCommentCnt(){
        ++commentCnt;
    }

    public void decCommentCnt(){
        --commentCnt;
    }


}
