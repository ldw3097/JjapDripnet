package ldw3097.JjapDripnet.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id
    @Column(name="board_name")
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;


}
