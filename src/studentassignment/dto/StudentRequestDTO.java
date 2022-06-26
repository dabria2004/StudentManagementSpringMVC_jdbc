package studentassignment.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import studentassignment.model.ClassBean;

@Data
public class StudentRequestDTO {
	@NotEmpty
	private String studentid;
	@NotEmpty
	private String studentname;
	@NotEmpty
	private String dob;
	@NotEmpty
	private String gender;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String education;
	@NotEmpty
	private List<String> attendCourses;
	@NotEmpty
	private List<ClassBean> attend;
}
