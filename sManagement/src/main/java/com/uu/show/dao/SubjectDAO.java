package com.uu.show.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uu.show.entity.Subject;
import com.uu.show.utils.DBTools;

public class SubjectDAO {
	
	public Subject insert(Subject Subject){
		String sql="insert into subject values (?,?,?)";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, Subject.getSubID());
			pre.setString(2, Subject.getSubName());
			pre.setString(3, Subject.getSubRemark());
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
		System.err.println("s-insert");
		return Subject;
	}
	
	public int deletes(String SubjectID){
		String sql="delete from subject where subID=?";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, SubjectID);
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
	
	public int update(Subject Subject){
		String sql1="update subject set subName=? where subID=?";
		String sql2="update subject set subRemark=? where subID=?";
		String sql3="update subject set subName=?,subRemark=? where subID=?";
		DBTools db=new DBTools();
		if(Subject.getSubName().equals("")){
			PreparedStatement pre=db.getPreSta(sql2);
			try {
				pre.setString(1, Subject.getSubRemark());
				pre.setString(2, Subject.getSubID());
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
		}else if(Subject.getSubRemark().equals("")){
			PreparedStatement pre=db.getPreSta(sql1);
			try {
				pre.setString(1, Subject.getSubName());
				pre.setString(2, Subject.getSubID());
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
		}else{
			PreparedStatement pre=db.getPreSta(sql3);
			try {
				pre.setString(1, Subject.getSubName());
				pre.setString(2, Subject.getSubRemark());
				pre.setString(3, Subject.getSubID());
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
	
	public List<Subject> preQuery(Subject yuanxi) {
		String sql = "select * from subject where subName like ? or subRemark like ? or subID like ? order by subID+0 desc";
		DBTools db = new DBTools();
		List<Subject> result = new ArrayList<>();
		PreparedStatement pre = db.getPreSta(sql);
		Subject yuanxix = null;
		try {
			System.out.println("----");
			pre.setString(1,"%"+yuanxi.getSubName()+"%");
			pre.setString(2, "%"+yuanxi.getSubRemark()+"%");
			pre.setString(3, "%"+yuanxi.getSubID()+"%");
			ResultSet rs=pre.executeQuery();
			db.commit();
			while (rs.next()) {
				yuanxix = new Subject();
				yuanxix.setSubID(rs.getString("subID"));
				yuanxix.setSubName(rs.getString("subName"));
				yuanxix.setSubRemark(rs.getString("subRemark"));
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
	
//	public List<Subject> preQuery(Subject Subject){
//		String sql1="select * from subject where subName like '%"+Subject.getSubName()+"%' order by subID+0 desc";
//		String sql2="select * from subject where subRemark like '%"+Subject.getSubRemark()+"%' order by subID+0 desc";
//		DBTools db=new DBTools();
//		List<Subject> result=new ArrayList<>();
//		try {
//			if(!Subject.getSubName().equals(null)){
//				ResultSet rs=db.getQuery(sql1);
//				try {
//					while(rs.next()){
//						Subject Subjectx=new Subject();
//						Subjectx.setSubID(rs.getString("subID"));
//						Subjectx.setSubName(rs.getString("subName"));
//						Subjectx.setSubRemark(rs.getString("subRemark"));
//						result.add(Subjectx);
//					}
//					db.commit();
//					db.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} catch (Exception e1) {
//		}
//		try {
//			if(!Subject.getSubRemark().equals(null)){
//				ResultSet rs=db.getQuery(sql2);
//				try {
//					while(rs.next()){
//						Subject Subjectx=new Subject();
//						Subjectx.setSubID(rs.getString("subID"));
//						Subjectx.setSubName(rs.getString("subName"));
//						Subjectx.setSubRemark(rs.getString("subRemark"));
//						result.add(Subjectx);
//					}
//					db.commit();
//					db.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} catch (Exception e) {
//		}
//		return result;
//	}
	
	public Subject findBySubjectID(String SubjectID){
		String sql="select * from subject where subID='"+SubjectID+"'";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		Subject Subject=null;
		try {
			while(rs.next()){
				Subject=new Subject();
				Subject.setSubID(rs.getString("subID"));
				Subject.setSubName(rs.getString("subName"));
				Subject.setSubRemark(rs.getString("subRemark"));
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
		return Subject;
	}
	
	public List<Subject> query(Subject Subject){
		String sql="select * from subject where subName='"+Subject.getSubName()+"' and subRemark='"+Subject.getSubRemark()+"' order by subID+0 desc";
		DBTools db=new DBTools();
		List<Subject> result=new ArrayList<>();
		ResultSet rs=db.getQuery(sql);
		try {
			while(rs.next()){
				Subject=new Subject();
				Subject.setSubID(rs.getString("subID"));
				Subject.setSubName(rs.getString("subName"));
				Subject.setSubRemark(rs.getString("subRemark"));
				result.add(Subject);
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
		return result;
	}
	
	public List<Subject> findAll(){
		String sql="select * from subject order by subID+0 desc";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		List<Subject> result=new ArrayList<>();
		try {
			while(rs.next()){
				Subject Subject=new Subject();
				Subject.setSubID(rs.getString("subID"));
				Subject.setSubName(rs.getString("subName"));
				Subject.setSubRemark(rs.getString("subRemark"));
				result.add(Subject);
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
		return result;
	}
	
	public List<Subject> byID(String subID) {
		String sql = "select * from Subject where subID='" + subID + "'";
		DBTools db = new DBTools();
		List<Subject> result = new ArrayList<>();
		ResultSet rs = db.getQuery(sql);
		try {
			while (rs.next()) {
				Subject Subjectx = new Subject();
				Subjectx.setSubID(rs.getString("subID"));
				Subjectx.setSubName(rs.getString("subName"));
				Subjectx.setSubRemark(rs.getString("subRemark"));
				result.add(Subjectx);
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

}
