package ldw3097.JjapDripnet.web.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {

    @NotNull
    private String id;

    @NotNull
    private String password;
}
