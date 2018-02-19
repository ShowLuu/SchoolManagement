package com.uu.show.service;

import java.util.List;

import com.uu.show.dao.ScoreDAO;
import com.uu.show.entity.Score;

public class ScoreService {

	public ScoreDAO dao = new ScoreDAO();

	public Score insert(Score Score) {
		System.out.println("ins44");
		return dao.insert(Score);
	}

	public int deletes(String xiID) {
		System.out.println("deletes");
		return dao.deletes(xiID);
	}

	public int update(Score Score) {
		return dao.update(Score);
	}

	public Score findByScoreID(String xiID) {
		return dao.findByScoreID(xiID);
	}

	public List<Score> findAll() {
		return dao.findAll();
	}

	public List<Score> preQuery(Score Score) {
		return dao.preQuery(Score);
	}

}
