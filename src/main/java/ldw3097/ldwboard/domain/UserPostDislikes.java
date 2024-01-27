package ldw3097.ldwboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserPostDislikes {
    @Id @GeneratedValue
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    Post post;

    public UserPostDislikes(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public UserPostDislikes() {

    }
}
