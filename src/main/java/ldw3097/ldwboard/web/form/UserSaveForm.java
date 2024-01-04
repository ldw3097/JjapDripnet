package ldw3097.ldwboard.web.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSaveForm {

    @NotNull
    private String id;

    @NotNull
    @Size(min=4, max=16)
    private String password;


}
