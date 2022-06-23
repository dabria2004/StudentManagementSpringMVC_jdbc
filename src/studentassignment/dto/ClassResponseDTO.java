package studentassignment.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClassResponseDTO {
	@NotEmpty
	private String classid;
	@NotEmpty
	private String classname;
}
