package studentassignment.model;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserBean {
	
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
