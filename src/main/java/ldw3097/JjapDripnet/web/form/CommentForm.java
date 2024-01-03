package ldw3097.JjapDripnet.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentForm {

    @NotBlank
    private String commentBody;

}
