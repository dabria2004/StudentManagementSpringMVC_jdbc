package studentassignment.dao;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import studentassignment.dto.StudentRequestDTO;
import studentassignment.dto.StudentResponseDTO;

@Service("studentDao")
public class StudentDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		System.out.println("Got Connection...");
	}
	public ArrayList<StudentResponseDTO> selectAll() {
		ArrayList<StudentResponseDTO> list = new ArrayList<>();
		String sql = "select * from student";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();
				res.setStudentid(rs.getString("student_id"));
				res.setStudentname(rs.getString("student_name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEducation(rs.getString("education"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public int insertData(StudentRequestDTO dto) {
		String sql = "insert into student values(?, ?, ?, ?, ?, ?)";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStudentid());
			ps.setString(2, dto.getStudentname());
			ps.setString(3, dto.getDob());
			ps.setString(4, dto.getGender());
			ps.setString(5, dto.getPhone());
			ps.setString(6, dto.getEducation());
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	public int deleteStudentById(String student_id) {
		String sql = "delete from student where student_id=?";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public int insertStudentCourse(String course_id, String student_id) {
		String sql = "insert into selected_courses (student_id, class_id) values(?, ?)";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ps.setString(2, course_id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public StudentResponseDTO selectStudentById(String student_id) {
		String sql = "select * from student where student_id=?";
		StudentResponseDTO res = new StudentResponseDTO();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setStudentid(rs.getString("student_id"));
				res.setStudentname(rs.getString("student_name"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEducation(rs.getString("education"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	public int updateData(StudentRequestDTO dto) {
		String sql = "update student set student_name=?, dob=?, gender=?, phone=?, education=? where student_id=?";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStudentname());
			ps.setString(2, dto.getDob());
			ps.setString(3, dto.getGender());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getEducation());
			ps.setString(6, dto.getStudentid());
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public int deleteAttendCoursesByStudentId(String student_id) {
		String sql = "delete from selected_courses where student_id=?";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}
	
	public ArrayList<StudentResponseDTO> selectStudentListByIdOrNameOrCourse(String id, String name, String course) {
		ArrayList<StudentResponseDTO> list = new ArrayList<>();
		String sql = "select distinct student.student_id, student.student_name "
				+ "from selected_courses join student "
				+ "on selected_courses.student_id = student.student_id join class "
				+ "on selected_courses.class_id = class.class_id "
				+ "where student.student_id like ? or student.student_name like ? or class.class_name like ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ps.setString(3, "%" + course + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO res = new StudentResponseDTO();
				res.setStudentid(rs.getString("student_id"));
				res.setStudentname(rs.getString("student_name"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

}
