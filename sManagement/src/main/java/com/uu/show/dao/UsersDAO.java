package com.uu.show.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uu.show.entity.Users;
import com.uu.show.utils.DBTools;

public class UsersDAO {
	
	public Users insert(Users user){
		String sql="insert into users values (?,?)";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			System.out.println(user.getUsername());
			pre.setString(1, user.getUsername());
			pre.setString(2, user.getPassword());
			pre.executeUpdate();
			db.commit();
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("insert");
		return user;
	}
	
	public Users findByUserName(String userName){
		Users user=null;
		String sql="select * from users where username='"+userName+"'";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		try {
			while(rs.next()){
				user=new Users();
				String password = rs.getString("password");
				user.setUsername(userName);
				user.setPassword(password);
				db.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		return user;
	}
	
	public int update(Users user){
		String sql="update users set password=? where username=? ";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, user.getPassword());
			pre.setString(2, user.getUsername());
			pre.executeUpdate();
			db.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Users user=new Users();
		user.setUsername("3");
		user.setPassword("33");
		new UsersDAO().update(user);
		System.out.println(new UsersDAO().findByUserName("3"));
	}

}
