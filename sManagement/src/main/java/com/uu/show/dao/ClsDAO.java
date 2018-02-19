package com.uu.show.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uu.show.entity.Cls;
import com.uu.show.utils.DBTools;

public class ClsDAO {
	
	public Cls insert(Cls cls){
		//clsID=replace('clsID',' ','') clsName=replace('clsName',' ','') clsType=replace('clsType',' ','')
		//insert into class replace(clsID,' ','') replace(clsName,' ','') replace(clsType,' ','') values (?,?,?)
		String sql="insert into class values (?,?,?)";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, cls.getClsID());
			pre.setString(2, cls.getClsName());
			pre.setString(3, cls.getClsType());
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
		System.err.println("c-insert");
		return cls;
	}
	
	public int deletes(String clsID){
		String sql="delete from class where clsID=?";
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
	
	public int update(Cls cls){
		String sql1="update class set clsName=? where clsID=?";
		String sql2="update class set clsType=? where clsID=?";
		DBTools db=new DBTools();
		if(cls.getClsName().equals("")){
			PreparedStatement pre=db.getPreSta(sql2);
			try {
				pre.setString(1, cls.getClsType());
				pre.setString(2, cls.getClsID());
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
		}else if(cls.getClsType().equals("")){
			PreparedStatement pre=db.getPreSta(sql1);
			try {
				pre.setString(1, cls.getClsName());
				pre.setString(2, cls.getClsID());
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
	
	public List<Cls> preQuery(Cls yuanxi) {
		String sql = "select * from class where clsName like ? or clsType like ? or clsID like ? order by clsID+0 desc";
		DBTools db = new DBTools();
		List<Cls> result = new ArrayList<>();
		PreparedStatement pre = db.getPreSta(sql);
		Cls yuanxix = null;
		try {
			System.out.println("----");
			pre.setString(1,"%"+yuanxi.getClsName()+"%");
			pre.setString(2, "%"+yuanxi.getClsType()+"%");
			pre.setString(3, "%"+yuanxi.getClsID()+"%");
			ResultSet rs=pre.executeQuery();
			db.commit();
			while (rs.next()) {
				yuanxix = new Cls();
				yuanxix.setClsID(rs.getString("clsID"));
				yuanxix.setClsName(rs.getString("clsName"));
				yuanxix.setClsType(rs.getString("clsType"));
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
	
	public Cls findByClsID(String clsID){
		String sql="select * from class where clsID='"+clsID+"'";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		Cls cls=null;
		try {
			while(rs.next()){
				cls=new Cls();
				cls.setClsID(rs.getString("clsID"));
				cls.setClsName(rs.getString("clsName"));
				cls.setClsType(rs.getString("clsType"));
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
	
	public List<Cls> query(Cls cls){
		String sql="select * from class where clsName='"+cls.getClsName()+"' and clsType='"+cls.getClsType()+"' order by clsID+0 desc";
		DBTools db=new DBTools();
		List<Cls> result=new ArrayList<>();
		ResultSet rs=db.getQuery(sql);
		try {
			while(rs.next()){
				cls=new Cls();
				cls.setClsID(rs.getString("clsID"));
				cls.setClsName(rs.getString("clsName"));
				cls.setClsType(rs.getString("clsType"));
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
		return result;
	}
	
	public List<Cls> findAll(){
		String sql="select * from class order by clsID+0 desc";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		List<Cls> result=new ArrayList<>();
		try {
			while(rs.next()){
				Cls cls=new Cls();
				cls.setClsID(rs.getString("clsID"));
				cls.setClsName(rs.getString("clsName"));
				cls.setClsType(rs.getString("clsType"));
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