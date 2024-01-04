package ldw3097.ldwboard.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentForm {

    @NotBlank
    private String commentBody;

}
