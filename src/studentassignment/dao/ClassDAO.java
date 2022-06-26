package studentassignment.dao;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import studentassignment.dto.ClassRequestDTO;
import studentassignment.dto.ClassResponseDTO;

@Service("classDao")
public class ClassDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		System.out.println("Got Connection...");
	}
	
	public int insertData(ClassRequestDTO dto) {
		int result=0;
		String sql="insert into class(class_id,class_name) values(?,?)";	
		try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,dto.getClassid());
				ps.setString(2,dto.getClassname());
				result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in inserting class data.");
		}
		return result;
	}
	
	public ArrayList<ClassResponseDTO> selectAll(){
		ArrayList<ClassResponseDTO> list=new ArrayList();
		String sql="select * from class";		
		try {
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ClassResponseDTO res=new ClassResponseDTO();
			res.setClassid(rs.getString("class_id"));
			res.setClassname(rs.getString("class_name"));
			list.add(res);
}
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Database error in selecting class data.");
		}
		return list;
	}
	
	public boolean checkId(String id) {
		String sql = "select * from class where class_id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public ArrayList<ClassResponseDTO> selectCoursesByStudentId(String student_id) {
		ArrayList<ClassResponseDTO> list = new ArrayList<>();
		String sql = "select class.class_name, class.class_id from selected_courses join class "
				+ "on selected_courses.class_id = class.class_id where selected_courses.student_id = ? ";
		
//		String sql = "select course_table.id from course_table join student_course on "
//				+ "course_table.id = student_course.course_id where student_course.student_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ClassResponseDTO res = new ClassResponseDTO();
				res.setClassid(rs.getString("class_id"));
				res.setClassname(rs.getString("class_name"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}
