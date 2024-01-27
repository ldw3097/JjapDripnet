package ldw3097.ldwboard.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentForm {

    private Long commentId;

    @NotBlank
    private String commentBody;



}
