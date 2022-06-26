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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import studentassignment.dao.ClassDAO;
import studentassignment.dao.StudentDAO;
import studentassignment.dto.ClassResponseDTO;
import studentassignment.dto.StudentRequestDTO;
import studentassignment.dto.StudentResponseDTO;
import studentassignment.model.*;

@Controller
public class StudentController {
	
	@Autowired
	private StudentDAO sdao;
	
	@Autowired
	private ClassDAO cdao;
	
	@GetMapping(value = "/setupaddstudent")
	public ModelAndView setupaddstudent(ModelMap model) {
		ClassDAO cdao = new ClassDAO();
		ArrayList<ClassResponseDTO> courseList = cdao.selectAll();
		model.addAttribute("courseList", courseList);
		return new ModelAndView ("STU001", "sbean", new StudentBean());
	}
	
	@GetMapping(value = "/setupaddstudentagain")
	public ModelAndView setupaddstudentagain(ModelMap model) {
		ClassDAO cdao = new ClassDAO();
		ArrayList<ClassResponseDTO> courseList = cdao.selectAll();
		model.addAttribute("courseList", courseList);
		model.addAttribute("success", "Successfully Registered!!");
		return new ModelAndView ("STU001", "sbean", new StudentBean());
	}
	
	@RequestMapping(value = "/addstudent", method=RequestMethod.POST)
	public String addstudent(@ModelAttribute("sbean")StudentBean sbean,ModelMap model) {
		
		ArrayList<ClassResponseDTO> courseList = cdao.selectAll();
		model.addAttribute("courseList", courseList);
		if (sbean.getAttendCourses().size() == 0) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU001";
		}
		if (sbean.getStudentname().isBlank() || sbean.getDob().isBlank() || sbean.getGender().isBlank()
				|| sbean.getPhone().isBlank() || sbean.getEducation().isBlank()) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU001";
		}
		ArrayList<StudentResponseDTO> studentList = sdao.selectAll();
		if (studentList == null) {
			studentList = new ArrayList<>();
		}
		if (studentList.size() == 0) {
			sbean.setStudentid("STU001");
		} else {
			int tempId = Integer.parseInt(studentList.get(studentList.size() - 1).getStudentid().substring(3)) + 1;
			String userId = String.format("STU%03d", tempId);
			sbean.setStudentid(userId);
		}
		StudentRequestDTO dto = new StudentRequestDTO();
		dto.setStudentid(sbean.getStudentid());
		dto.setStudentname(sbean.getStudentname());
		dto.setDob(sbean.getDob());
		dto.setGender(sbean.getGender());
		dto.setPhone(sbean.getPhone());
		dto.setEducation(sbean.getEducation());
		sdao.insertData(dto);
		String[] attendCourses = new String[sbean.getAttendCourses().size()];
		attendCourses = sbean.getAttendCourses().toArray(attendCourses);
		for (int i = 0; i < attendCourses.length; i++) {
			sdao.insertStudentCourse(attendCourses[i], sbean.getStudentid());
		}
//		model.addAttribute("message", "Registered Succesfully !!");
		return "redirect:/setupaddstudentagain";	
	}
	
	@GetMapping("/setupstudentsearch")
	public String studentManagement(ModelMap model) {
		
		ArrayList<StudentResponseDTO> studentList = sdao.selectAll();
		ArrayList<ArrayList<ClassResponseDTO>> coursesLists = new ArrayList<>();
		for (StudentResponseDTO student : studentList) {
			ArrayList<ClassResponseDTO> courseList = cdao.selectCoursesByStudentId(student.getStudentid());
			coursesLists.add(courseList);
		}
		model.addAttribute("studentList", studentList);
		model.addAttribute("coursesLists", coursesLists);
		return "STU003";
	}
	
	@GetMapping("/studentdetail/{studentid}")
	public ModelAndView seeMore(@PathVariable String studentid, ModelMap model) {
		
		StudentResponseDTO dto = sdao.selectStudentById(studentid);
		ArrayList<ClassResponseDTO> stuCourseResList = cdao.selectCoursesByStudentId(studentid);
		ArrayList<ClassResponseDTO> allCourses = cdao.selectAll();

		ArrayList<String> stuCourseList=new ArrayList<String>();
		for(ClassResponseDTO course: stuCourseResList) {
			stuCourseList.add(course.getClassid());
		}
		StudentBean sbean=new StudentBean();
		sbean.setStudentid(studentid);
		sbean.setStudentname(dto.getStudentname());
		sbean.setDob(dto.getDob());
		sbean.setGender(dto.getGender());
		sbean.setPhone(dto.getPhone());
		sbean.setEducation(dto.getEducation());
		sbean.setAttendCourses(stuCourseList);
		System.out.println(sbean);
		System.out.println(sbean.getAttendCourses());
		model.addAttribute("data", sbean);
		model.addAttribute("courseList", allCourses);
		return new ModelAndView ("STU002", "sbean", sbean);
	}
	
	@PostMapping("/updatestudent")
	public String updateStudent(@ModelAttribute("sbean") StudentBean sbean, ModelMap model) {
		ArrayList<ClassResponseDTO> courseList = cdao.selectAll();
		model.addAttribute("courseList", courseList);
		if (sbean.getAttendCourses().size() == 0) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU002";
		}
		if (sbean.getStudentname().isBlank() || sbean.getDob().isBlank() || sbean.getGender().isBlank()
				|| sbean.getPhone().isBlank() || sbean.getEducation().isBlank()) {
			model.addAttribute("error", "Fill the blank !!");
			model.addAttribute("data", sbean);
			return "STU002";
		}
		
		StudentRequestDTO dto = new StudentRequestDTO();
		dto.setStudentid(sbean.getStudentid());
		dto.setStudentname(sbean.getStudentname());
		dto.setDob(sbean.getDob());
		dto.setGender(sbean.getGender());
		dto.setPhone(sbean.getPhone());
		dto.setEducation(sbean.getEducation());
		
		sdao.updateData(dto);
		sdao.deleteAttendCoursesByStudentId(sbean.getStudentid());
		String[] attendCourses = new String[sbean.getAttendCourses().size()];
		attendCourses = sbean.getAttendCourses().toArray(attendCourses);
		for (int i = 0; i < attendCourses.length; i++) {
			sdao.insertStudentCourse(attendCourses[i], sbean.getStudentid());
		}
		model.addAttribute("success", "Successfully updated!!");
		return "redirect:/setupstudentsearch";
	}
	
	@GetMapping("/deleteStudent/{studentid}")
	public String deleteStudent(@PathVariable("studentid") String id) {
		sdao.deleteAttendCoursesByStudentId(id);
		sdao.deleteStudentById(id);
		return "redirect:/setupstudentsearch";
	}
	
	@PostMapping("/searchstudent")
	public String searchStudent(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("course") String course, ModelMap model) {
		
		String sid = id.isBlank() ? ")#<>(}" : id;
		String sname = name.isBlank() ? ")#<>(}" : name;
		String scourse = course.isBlank() ? ")#<>(}" : course;
		
		ArrayList<StudentResponseDTO> studentList = sdao.selectStudentListByIdOrNameOrCourse(sid, sname, scourse);
		ArrayList<ArrayList<ClassResponseDTO>> coursesLists = new ArrayList<>();
		for (StudentResponseDTO student : studentList) {
			ArrayList<ClassResponseDTO> courseList = cdao.selectCoursesByStudentId(student.getStudentid());
			coursesLists.add(courseList);
		}
		if (studentList.size() == 0) {
			studentList = sdao.selectAll();
			ArrayList<ArrayList<ClassResponseDTO>> coursesList = new ArrayList<>();
			for (StudentResponseDTO student : studentList) {
				ArrayList<ClassResponseDTO> courseList = cdao.selectCoursesByStudentId(student.getStudentid());
				coursesList.add(courseList);
			}
			model.addAttribute("studentList", studentList);
			model.addAttribute("coursesLists", coursesList);
			return "STU003";
		}
		model.addAttribute("studentList", studentList);
		model.addAttribute("coursesLists", coursesLists);
		return "STU003";
	
	}
}





//		ClassDAO cdao = new ClassDAO();
//		StudentResponseDTO dto = sdao.selectStudentById(studentid);
//		ArrayList<ClassResponseDTO> stuCourseResList = cdao.selectCoursesByStudentId(studentid);
//		ArrayList<ClassResponseDTO> allCourses = cdao.selectAll();
//		ArrayList<ClassBean>courseBeanList=new ArrayList<ClassBean>();
//		for(ClassResponseDTO course: allCourses) {
//			ClassBean courseBean=new ClassBean();
//			courseBean.setClassid(course.getClassid());
//			courseBean.setClassname(course.getClassname());
//			courseBeanList.add(courseBean);
//		}
//		ArrayList<String> stuCourseBeanList=new ArrayList<String>();
//		for(ClassResponseDTO course: stuCourseResList) {
//			stuCourseBeanList.add(course.getClassname());
//		}
//		StudentBean sbean=new StudentBean();
//		sbean.setStudentid(studentid);
//		sbean.setStudentname(dto.getStudentname());
//		sbean.setDob(dto.getDob());
//		sbean.setGender(dto.getGender());
//		sbean.setPhone(dto.getPhone());
//		sbean.setEducation(dto.getEducation());
//		sbean.setAttend(courseBeanList);
//		sbean.setAttendCourses(stuCourseBeanList);
//		System.out.println(sbean.getAttendCourses());
//		model.addAttribute("attendCourses", stuCourseResList);
//		model.addAttribute("courseList", allCourses);
//		model.addAttribute("data", sbean);
//		return new ModelAndView ("STU002", "sbean", sbean);



