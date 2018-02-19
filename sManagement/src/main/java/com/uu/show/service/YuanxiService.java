package com.uu.show.service;

import java.util.List;

import com.uu.show.dao.YuanxiDAO;
import com.uu.show.entity.Yuanxi;

public class YuanxiService {
	
	public YuanxiDAO dao=new YuanxiDAO();
	
	public Yuanxi insert(Yuanxi yuanxi){
		System.out.println("ins44");
		return dao.insert(yuanxi);
	}
	
	public int delete(String xiID){
		System.out.println("deletes");
		return dao.delete(xiID);
	}
	
	public int deletes(String xiID){
		System.out.println("deletes");
		return dao.deletes(xiID);
	}
	
	public int update(Yuanxi yuanxi){
		return dao.update(yuanxi);
	}

	public Yuanxi findByXiID(String xiID){
		return dao.findByXiID(xiID);
	}
	
	public List<Yuanxi> findAll(){
		return dao.findAll();
	}
	
	public List<Yuanxi> preQuery(Yuanxi yuanxi){
		return dao.preQuery(yuanxi);
	}
	
	public List<Yuanxi> query(Yuanxi yuanxi){
		return dao.query(yuanxi);
	}
	
	
}
