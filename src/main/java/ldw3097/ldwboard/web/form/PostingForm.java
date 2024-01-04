package ldw3097.ldwboard.web.form;

import jakarta.validation.constraints.NotBlank;
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
