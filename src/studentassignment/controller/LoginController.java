package studentassignment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import studentassignment.dao.UserDAO;
import studentassignment.dto.UserResponseDTO;

@Controller
public class LoginController {
	@GetMapping(value="/login")
	public String login() { 
		return "LGN001";
	}
	
	@GetMapping(value="/")
	public String start() { 
		return "LGN001";
	}
	
	@RequestMapping(value = "/welcomepage", method = RequestMethod.POST)
	public String finalexampage(@RequestParam("email") String email,@RequestParam("password") String password,
	HttpSession session,ModelMap model) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String currentDate = formatter.format(date);
		UserDAO dao = new UserDAO();
		if(dao.check(email, password)) {
			UserResponseDTO dto=dao.selectUserByEmail(email);
			session.setAttribute("userInfo", dto);
			session.setAttribute("date", currentDate);
			return "MNU001";
		}else {
			model.addAttribute("error","Email and Password do not match!!");
			return "LGN001";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("userInfo");
		return "redirect:/login";
	}
	
	@GetMapping(value = "/menu")
	public String menu() {
		return "MNU001";
	}
}
