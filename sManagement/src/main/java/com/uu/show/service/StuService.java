package com.uu.show.service;

import java.util.List;

import com.uu.show.dao.StuDAO;
import com.uu.show.entity.Stu;
import com.uu.show.entity.Yuanxi;

public class StuService {

	public StuDAO dao = new StuDAO();

	public Stu insert(Stu Stu) {
		System.out.println("ins44");
		return dao.insert(Stu);
	}

	public int deletes(String xiID) {
		System.out.println("deletes");
		return dao.deletes(xiID);
	}

	public int update(Stu Stu) {
		return dao.update(Stu);
	}

	public Stu findByStuID(String xiID) {
		return dao.findByStuID(xiID);
	}

	public List<Stu> findAll() {
		return dao.findAll();
	}

	public List<Stu> preQuery(Stu Stu) {
		return dao.preQuery(Stu);
	}

}
