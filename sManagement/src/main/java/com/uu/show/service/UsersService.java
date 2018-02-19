package com.uu.show.service;

import com.uu.show.dao.UsersDAO;
import com.uu.show.entity.Users;

public class UsersService {
	
	public UsersDAO dao=new UsersDAO();
	
	public Users insert(Users user){
		System.out.println("in2");
		return dao.insert(user);
	}
	
	public Users findByUserName(String userName){
		return dao.findByUserName(userName);
	}
	
	public int update(Users user){
		return dao.update(user);
	}
	
}
