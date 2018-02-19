package com.uu.show.service;

import java.util.List;

import com.uu.show.dao.SubjectDAO;
import com.uu.show.entity.Subject;
import com.uu.show.entity.Yuanxi;

public class SubjectService {
	
	public SubjectDAO dao = new SubjectDAO();

	public Subject insert(Subject Subject) {
		System.out.println("ins44");
		return dao.insert(Subject);
	}

	public int deletes(String subID) {
		System.out.println("deletes");
		return dao.deletes(subID);
	}

	public int update(Subject Subject) {
		return dao.update(Subject);
	}

	public Subject findBySubjectID(String subID) {
		return dao.findBySubjectID(subID);
	}

	public List<Subject> findAll() {
		return dao.findAll();
	}

	public List<Subject> preQuery(Subject Subject) {
		return dao.preQuery(Subject);
	}

	public List<Subject> query(Subject Subject) {
		return dao.query(Subject);
	}
	
}
