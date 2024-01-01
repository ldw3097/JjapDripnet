package ldw3097.JjapDripnet.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PostingForm {

    @NotBlank
    private String title;

    @NotBlank
    private String body;
}
