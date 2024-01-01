package ldw3097.JjapDripnet.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @Column(name="user_id")
    private String id;

    private String password;


}
