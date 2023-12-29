package ldw3097.JjapDripnet.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;


}
