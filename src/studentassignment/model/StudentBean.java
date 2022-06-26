package studentassignment.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import studentassignment.dto.ClassResponseDTO;

@Data
public class StudentBean {
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
//	private List<CourseBean> attend;
//	private List<String> stuCourse;
}
