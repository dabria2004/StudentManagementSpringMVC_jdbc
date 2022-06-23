package studentassignment.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClassRequestDTO {
	@NotEmpty
	private String classid;
	@NotEmpty
	private String classname;
}
