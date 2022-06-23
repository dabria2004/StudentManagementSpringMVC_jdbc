package studentassignment.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserResponseDTO {
	@NotEmpty
	String userid;
	@NotEmpty
	String username;
	@NotEmpty
	String useremail;
	@NotEmpty
	String password;
	@NotEmpty
	String conpassword;
	@NotEmpty
	String role;
}
