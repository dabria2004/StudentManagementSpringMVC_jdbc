package studentassignment.model;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClassBean {	
	@NotEmpty
	private String classid;
	@NotEmpty
	private String classname;
}
