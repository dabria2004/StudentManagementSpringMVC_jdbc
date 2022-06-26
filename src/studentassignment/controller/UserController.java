package studentassignment.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import studentassignment.dao.UserDAO;
import studentassignment.dto.UserRequestDTO;
import studentassignment.dto.UserResponseDTO;
import studentassignment.model.UserBean;

@Controller
public class UserController {
	@Autowired
	private UserDAO udao;
	
	@GetMapping(value="/setupusersearch")
	public String usersearchpage(ModelMap model) { 
		ArrayList<UserResponseDTO> userlist = udao.selectAll();
		model.addAttribute("searchInfo", userlist);
		System.out.println("ShowAllUsers =>" + userlist.toString()); 
		return "USR003";
	}
	@RequestMapping(value="/usersearch", method=RequestMethod.POST)
	public String usersearch(@RequestParam("userid") String userid,@RequestParam("username") String username,ModelMap model) {
		ArrayList<UserResponseDTO> searchUserList = null;
		if(userid.isBlank()) {
			searchUserList=udao.selectUserByName(username);
			System.out.println("userid is blank => "+searchUserList);
		}else if(username.isBlank()){
			searchUserList=udao.selectUserById(userid);
			System.out.println("username is blank => "+searchUserList);
		}else if(userid.isBlank() || username.isBlank()){
			searchUserList = udao.selectAll();
			System.out.println("something is blank => "+searchUserList);
		}else {
			searchUserList = udao.selectUserByIdOrName(userid, username);
		}
		
		if(searchUserList.size()==0) {
			searchUserList = udao.selectAll();
		} 
		model.addAttribute("searchInfo", searchUserList);
		return "USR003";
	}

	@RequestMapping(value="/setupadduser", method=RequestMethod.GET)
	public ModelAndView setupadduser() {
		return new ModelAndView("USR001","bean",new UserBean());
	}
	
	@GetMapping(value="/setupadduseragain")
	public ModelAndView setupadduseragain(ModelMap model) {
		model.addAttribute("success", "Succesfully Registered!!");
		return new ModelAndView("USR001","bean",new UserBean());
	}
	
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	public String adduser(@ModelAttribute("bean")UserBean bean,ModelMap model) {
//		if(bs.hasErrors()) {
//			return "addUser";
//		}
		if(bean.getUsername().isBlank() || bean.getUseremail().isBlank() || bean.getPassword().isBlank() || bean.getConpassword().isBlank() || bean.getRole().isBlank()) {
			model.addAttribute("error", "You must fullfill the fields.");
			return "USR001";
				}
		else if(!bean.getPassword().equals(bean.getConpassword())) {
			model.addAttribute("password", "Passwords do not match!!");
			return "USR001";
		}else {
			
			ArrayList<UserResponseDTO> userlist = udao.selectAll();
			if(userlist.size() == 0 ) {
				bean.setUserid("USR001");
			}else {
				int tempId = Integer.parseInt(userlist.get(userlist.size() - 1).getUserid().substring(3)) + 1;
				String userId = String.format("USR%03d", tempId);
				bean.setUserid(userId);
			}
			UserRequestDTO dto = new UserRequestDTO();
			dto.setUserid(bean.getUserid());
			dto.setUsername(bean.getUsername());
			dto.setUseremail(bean.getUseremail());
			dto.setPassword(bean.getPassword());
			dto.setConpassword(bean.getConpassword());
			dto.setRole(bean.getRole());
			int i = udao.insertData(dto);
			if(i>0) {
				model.addAttribute("success", "Successfully Registered!!");
				return "redirect:/setupadduseragain";
		}else {
			model.addAttribute("fail", "Register Failed!!");
			return "USR001";
			}
		}			
	}
	
	@GetMapping(value="/setupUpdateUser/{userid}")
	public ModelAndView setupUpdatebook(@PathVariable String userid) {
		UserRequestDTO dto=new UserRequestDTO();
		dto.setUserid(userid);
		return new ModelAndView("USR002","bean", udao.selectUserById(dto));
	}
	
	@RequestMapping(value="/updateuser", method=RequestMethod.POST)
	public String updatebook(@ModelAttribute("bean") @Validated UserBean bean, BindingResult bs, ModelMap model) {
//		if(bs.hasErrors()) {
//			return "USR002";
//		}
		if(bean.getUsername().isBlank() || bean.getUseremail().isBlank() || bean.getPassword().isBlank() || bean.getConpassword().isBlank() || bean.getRole().isBlank()) {
			model.addAttribute("error", "You must fullfill the fields.");
			return "USR002";
				}
		else if(!bean.getPassword().equals(bean.getConpassword())) {
			model.addAttribute("password", "Passwords do not match!!");
			return "USR002";
		}else {
			
			UserRequestDTO dto = new UserRequestDTO();
			dto.setUserid(bean.getUserid());
			dto.setUsername(bean.getUsername());
			dto.setUseremail(bean.getUseremail());
			dto.setPassword(bean.getPassword());
			dto.setConpassword(bean.getConpassword());
			dto.setRole(bean.getRole());
			int i = udao.updateData(dto);
			if(i>0) {
				model.addAttribute("success", "Successfully Updated!!");
				return "USR002";
			}else {
				model.addAttribute("error", "Update Failed!!");
				return "USR002";
			}
		}
	}
	
	@RequestMapping(value="/deleteuser/{userid}", method=RequestMethod.GET)
	public String deleteuser(@PathVariable String userid,ModelMap model) {
		int res=udao.deleteData(userid);
		if(res==0) {
			model.addAttribute("error", "Delete Failed");
		}
		return "redirect:/setupusersearch";
	}
}
