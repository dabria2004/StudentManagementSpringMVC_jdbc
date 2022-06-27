package studentassignment.dao;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import studentassignment.dto.UserRequestDTO;
import studentassignment.dto.UserResponseDTO;

@Service("userDao")

public class UserDAO {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
		System.out.println("Got Connection...");
	}
	public int insertData(UserRequestDTO dto) {
		int result=0;
		String sql="insert into user values(?,?,?,?,?,?)";	
		try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,dto.getUserid());
				ps.setString(2,dto.getUsername());
				ps.setString(3, dto.getUseremail());
				ps.setString(4,dto.getPassword());
				ps.setString(5, dto.getConpassword());
				ps.setString(6, dto.getRole());
				result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in inserthing user data.");
		}
		return result;
	}
	
	public int updateData(UserRequestDTO dto) {
		int result=0;
		String sql="update user set user_name=?,user_email=?,user_password=?,user_conpassword=?,user_role=? where user_id=?";	
		try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,dto.getUsername());
				ps.setString(2,dto.getUseremail());
				ps.setString(3,dto.getPassword());
				ps.setString(4,dto.getConpassword());
				ps.setString(5,dto.getRole());
				ps.setString(6,dto.getUserid());
				result=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in updating user data.");
		}
		return result;
	}
	
	public int deleteData(String id) {
		int result=0;
		String sql="delete from user where user_id=?";	
		try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,id);
				ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in deleting user data.");
		}
		return result;
	}
	
	public ArrayList<UserResponseDTO> selectAll(){
		ArrayList<UserResponseDTO> list=new ArrayList<>();
		String sql="select * from user";		
		try {
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			UserResponseDTO res=new UserResponseDTO();
			res.setUserid(rs.getString("user_id"));
			res.setUsername(rs.getString("user_name"));
			res.setUseremail(rs.getString("user_email"));
			res.setPassword(rs.getString("user_password"));
			res.setConpassword(rs.getString("user_conpassword"));
			res.setRole(rs.getString("user_role"));
			list.add(res);
}
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<UserResponseDTO> selectUserById(String id) {
		ArrayList<UserResponseDTO> list = new ArrayList<>();
		String sql = "select * from user where user_id like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserResponseDTO res = new UserResponseDTO();
				res.setUserid(rs.getString("user_id"));
				res.setUsername(rs.getString("user_name"));
				res.setUseremail(rs.getString("user_email"));
				res.setPassword(rs.getString("user_password"));
				res.setConpassword(rs.getString("user_conpassword"));
				res.setRole(rs.getString("user_role"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public ArrayList<UserResponseDTO> selectUserByName(String name) {
		ArrayList<UserResponseDTO> list = new ArrayList<>();
		String sql = "select * from user where user_name like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserResponseDTO res = new UserResponseDTO();
				res.setUserid(rs.getString("user_id"));
				res.setUsername(rs.getString("user_name"));
				res.setUseremail(rs.getString("user_email"));
				res.setPassword(rs.getString("user_password"));
				res.setConpassword(rs.getString("user_conpassword"));
				res.setRole(rs.getString("user_role"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public ArrayList<UserResponseDTO> selectUserByIdOrName(String id, String name) {
		ArrayList<UserResponseDTO> list = new ArrayList<>();
		String sql = "select * from user where user_id like ? or user_name like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserResponseDTO res = new UserResponseDTO();
				res.setUserid(rs.getString("user_id"));
				res.setUsername(rs.getString("user_name"));
				res.setUseremail(rs.getString("user_email"));
				res.setPassword(rs.getString("user_password"));
				res.setConpassword(rs.getString("user_conpassword"));
				res.setRole(rs.getString("user_role"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public UserResponseDTO selectUserByEmail(String email) {
		String sql = "select * from user where user_email=?";
		UserResponseDTO res = new UserResponseDTO();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setUserid(rs.getString("user_id"));
				res.setUsername(rs.getString("user_name"));
				res.setUseremail(rs.getString("user_email"));
				res.setPassword(rs.getString("user_password"));
				res.setConpassword(rs.getString("user_conpassword"));
				res.setRole(rs.getString("user_role"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	public UserResponseDTO selectUserById(UserRequestDTO dto) {
		String sql = "select * from user where user_id=?";
		UserResponseDTO res = new UserResponseDTO();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserid());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setUserid(rs.getString("user_id"));
				res.setUsername(rs.getString("user_name"));
				res.setUseremail(rs.getString("user_email"));
				res.setPassword(rs.getString("user_password"));
				res.setConpassword(rs.getString("user_conpassword"));
				res.setRole(rs.getString("user_role"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	public boolean check(String user_email, String user_password) {

		String sql = "select * from user where user_email=? && user_password=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, user_email);
			st.setString(2, user_password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}
	
	public boolean checkemail(String email) {

		String sql = "select * from user where user_email=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}
}
