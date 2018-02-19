package com.uu.show.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uu.show.entity.Score;
import com.uu.show.utils.DBTools;

public class ScoreDAO {
	
	public Score insert(Score cls){
		String sql="insert into score values (?,?,?,?,?,?,?,?)";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, cls.getScoreID());
			pre.setString(2, cls.getScoreStuID());
			pre.setString(3, cls.getScoreStuName());
			pre.setString(4, cls.getScoreSubID());
			pre.setString(5, cls.getScoreSubName());
			pre.setString(6, cls.getScoreSubScore());
			pre.setString(7, cls.getScoreTime());
			pre.setString(8, cls.getScoreRemark());
			pre.executeUpdate();
			db.commit();
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("score-insert");
		return cls;
	}
	
	public int deletes(String clsID){
		String sql="delete from score where scoreID=?";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, clsID);
			pre.executeUpdate();
			db.commit();
			db.close();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int update(Score cls){
		String sql1="update score set scoreStuID=? where scoreID=?";
		String sql2="update score set scoreStuName=? where coreID=?";
		String sql3="update score set scoreSubID=? where scoreID=?";
		String sql4="update score set scoreSubName=? where scoreID=?";
		String sql5="update score set scoreSubScore=? where scoreID=?";
		String sql6="update score set scoreTime=? where scoreID=?";
		String sql7="update score set scoreRemark=? where scoreID=?";
		
		DBTools db=new DBTools();
		if(!cls.getScoreStuID().equals("")){
			PreparedStatement pre=db.getPreSta(sql1);
			try {
				pre.setString(1, cls.getScoreStuID());
				pre.setString(2, cls.getScoreID());
				pre.executeUpdate();
				db.commit();
				db.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!cls.getScoreStuName().equals("")){
			PreparedStatement pre=db.getPreSta(sql2);
			try {
				pre.setString(1, cls.getScoreStuName());
				pre.setString(2, cls.getScoreID());
				pre.executeUpdate();
				db.commit();
				db.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(!cls.getScoreSubID().equals("")){
			PreparedStatement pre=db.getPreSta(sql3);
			try {
				pre.setString(1, cls.getScoreSubID());
				pre.setString(2, cls.getScoreID());
				pre.executeUpdate();
				db.commit();
				db.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!cls.getScoreSubName().equals("")){
			PreparedStatement pre=db.getPreSta(sql4);
			try {
				pre.setString(1, cls.getScoreSubName());
				pre.setString(2, cls.getScoreID());
				pre.executeUpdate();
				db.commit();
				db.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!cls.getScoreSubScore().equals("")){
			PreparedStatement pre=db.getPreSta(sql5);
			try {
				pre.setString(1, cls.getScoreSubScore());
				pre.setString(2, cls.getScoreID());
				pre.executeUpdate();
				db.commit();
				db.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!cls.getScoreTime().equals("")){
			PreparedStatement pre=db.getPreSta(sql6);
			try {
				pre.setString(1, cls.getScoreTime());
				pre.setString(2, cls.getScoreID());
				pre.executeUpdate();
				db.commit();
				db.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!cls.getScoreRemark().equals("")){
			PreparedStatement pre=db.getPreSta(sql7);
			try {
				pre.setString(1, cls.getScoreRemark());
				pre.setString(2, cls.getScoreID());
				pre.executeUpdate();
				db.commit();
				db.close();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public List<Score> preQuery(Score yuanxi) {
		String sql = "select * from score where scoreStuID like ? or scoreStuName like ? or scoreSubID like ? or scoreSubName like ? or scoreSubScore like ? or scoreTime like ? or scoreRemark like ? or scoreID like ?  order by scoreID+0 desc";
		DBTools db = new DBTools();
		List<Score> result = new ArrayList<>();
		PreparedStatement pre = db.getPreSta(sql);
		Score yuanxix = null;
		try {
			System.out.println("----");
			pre.setString(1,"%"+yuanxi.getScoreStuID()+"%");
			pre.setString(2,"%"+yuanxi.getScoreStuName()+"%");
			pre.setString(3,"%"+yuanxi.getScoreSubID()+"%");
			pre.setString(4,"%"+yuanxi.getScoreSubName()+"%");
			pre.setString(5,"%"+yuanxi.getScoreSubScore()+"%");
			pre.setString(6,"%"+yuanxi.getScoreTime()+"%");
			pre.setString(7,"%"+yuanxi.getScoreRemark()+"%");
			pre.setString(8,"%"+yuanxi.getScoreID()+"%");
			ResultSet rs=pre.executeQuery();
			db.commit();
			while (rs.next()) {
				yuanxix = new Score();
				yuanxix.setScoreID(rs.getString("scoreID"));
				yuanxix.setScoreStuID(rs.getString("scoreStuID"));
				yuanxix.setScoreStuName(rs.getString("scoreStuName"));
				yuanxix.setScoreSubID(rs.getString("scoreSubID"));
				yuanxix.setScoreSubName(rs.getString("scoreSubName"));
				yuanxix.setScoreSubScore(rs.getString("scoreSubScore"));
				yuanxix.setScoreTime(rs.getString("scoreTime"));
				yuanxix.setScoreRemark(rs.getString("scoreRemark"));
				result.add(yuanxix);
			}
			db.commit();
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Score findByScoreID(String ScoreID){
		String sql="select * from score where scoreID='"+ScoreID+"'";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		Score cls=null;
		try {
			while(rs.next()){
				cls=new Score();
				cls.setScoreID(rs.getString("scoreID"));
				cls.setScoreStuID(rs.getString("scoreStuID"));
				cls.setScoreStuName(rs.getString("scoreStuName"));
				cls.setScoreSubID(rs.getString("scoreSubID"));
				cls.setScoreSubName(rs.getString("scoreSubName"));
				cls.setScoreSubScore(rs.getString("scoreSubScore"));
				cls.setScoreTime(rs.getString("scoreTime"));
				cls.setScoreRemark(rs.getString("scoreRemark"));
				db.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		return cls;
	}
	
	public List<Score> findAll(){
		String sql="select * from score order by scoreID+0 desc";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		List<Score> result=new ArrayList<>();
		try {
			while(rs.next()){
				Score cls=new Score();
				cls.setScoreID(rs.getString("scoreID"));
				cls.setScoreStuID(rs.getString("scoreStuID"));
				cls.setScoreStuName(rs.getString("scoreStuName"));
				cls.setScoreSubID(rs.getString("scoreSubID"));
				cls.setScoreSubName(rs.getString("scoreSubName"));
				cls.setScoreSubScore(rs.getString("scoreSubScore"));
				cls.setScoreTime(rs.getString("scoreTime"));
				cls.setScoreRemark(rs.getString("scoreRemark"));
				result.add(cls);
				db.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		System.out.println("当前有"+result.size()+"个院系");
		return result;
	}
	
}