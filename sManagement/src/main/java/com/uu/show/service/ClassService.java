package com.uu.show.service;

import java.util.List;

import com.uu.show.dao.ClsDAO;
import com.uu.show.entity.Cls;
import com.uu.show.entity.Yuanxi;

public class ClassService {

	public ClsDAO dao = new ClsDAO();

	public Cls insert(Cls Cls) {
		System.out.println("ins44");
		return dao.insert(Cls);
	}

	public int deletes(String xiID) {
		System.out.println("deletes");
		return dao.deletes(xiID);
	}

	public int update(Cls Cls) {
		return dao.update(Cls);
	}

	public Cls findByClsID(String xiID) {
		return dao.findByClsID(xiID);
	}

	public List<Cls> findAll() {
		return dao.findAll();
	}

	public List<Cls> preQuery(Cls Cls) {
		return dao.preQuery(Cls);
	}

	public List<Cls> query(Cls Cls) {
		return dao.query(Cls);
	}
	
}
