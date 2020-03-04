package com.how2java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class DAO {
	public DAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shiro?characterEncoding=UTF-8",
				"root","1234");
	}

	//根据用户名获取密码
	public String getPassword(String userName){
		String sql = "SELECT u.password FROM USER u WHERE u.`name` = ?";
		try (Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return rs.getString("password");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//根据用户名获取角色
	public Set<String> listRole(String userName){
		String sql = "SELECT r.name FROM USER u "
				+ "LEFT JOIN user_role ur ON u.id = ur.`uid` "
				+ "LEFT JOIN role r ON r.`id` = ur.`rid` "
				+ "WHERE u.name = ?";
		Set<String> roles = new HashSet<>();
		try (Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				roles.add(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roles;
	}
	//根据用户名获取权限
	public Set<String> listPermissions(String userName){
		String sql ="SELECT p.name FROM USER u LEFT JOIN user_role ur ON u.id = ur.`uid` "
				+ "LEFT JOIN role r ON r.`id` = ur.`rid` "
				+ "LEFT JOIN role_permission rp ON r.`id` = rp.rid "
				+ "LEFT JOIN permission p ON p.id = rp.pid "
				+ "WHERE u.`name` = ?";
		Set<String> permissions = new HashSet<>();
		try (Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				permissions.add(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return permissions;
	}
	//根据用户名获取用户
	public User getUser(String userName){
		String sql = "SELECT*FROM USER u WHERE u.`name` = ?";
		User user = null;
		try (Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql)){
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
	//创建用户
	  public String createUser(String name, String password) {
	         
	        String sql = "insert into user values(null,?,?,?)";
	         
	        String salt = new SecureRandomNumberGenerator().nextBytes().toString(); //盐量随机
	        String encodedPassword= new SimpleHash("md5",password,salt,2).toString();
	         
	        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	             
	            ps.setString(1, name);
	            ps.setString(2, encodedPassword);
	            ps.setString(3, salt);
	            ps.execute();
	        } catch (SQLException e) {
	 
	            e.printStackTrace();
	        }
	        return null;       
	         
	    }
	 public static void main(String[] args) {
		 System.out.println(new DAO().listRole("zhang3"));
		 System.out.println(new DAO().listRole("li4"));
		 System.out.println(new DAO().listPermissions("zhang3"));
		 System.out.println(new DAO().listPermissions("li4"));
		
	 }
}
