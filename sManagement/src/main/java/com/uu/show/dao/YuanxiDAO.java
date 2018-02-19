package com.uu.show.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uu.show.entity.Yuanxi;
import com.uu.show.utils.DBTools;

public class YuanxiDAO {

	public Yuanxi insert(Yuanxi yuanxi){
		String sql="insert into yuanxi values (?,?,?)";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, yuanxi.getXiID());
			pre.setString(2, yuanxi.getXiName());
			pre.setString(3, yuanxi.getYuan());
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
		System.err.println("y-insert");
		return yuanxi;
	}
	
	public int delete(String xiID){
		String sql="delete from yuanxi where xiID='"+xiID+"'";
		DBTools db=new DBTools();
		int count= db.update(sql);
		return count;
	}
	
	public int deletes(String xiID){
		String sql="delete from yuanxi where xiID=?";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, xiID);
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
	
	public int update(Yuanxi yuanxi){
		String sql1="update yuanxi set yuan=? where xiID=?";
		String sql2="update yuanxi set xiName=? where xiID=?";
		String sql3="update yuanxi set xiName=?,yuan=? where xiID=?";
		DBTools db=new DBTools();
		if(yuanxi.getXiName().equals("")){
			PreparedStatement pre=db.getPreSta(sql1);
			try {
				pre.setString(1, yuanxi.getYuan());
				pre.setString(2, yuanxi.getXiID());
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
		}else if(yuanxi.getYuan().equals("")){
			PreparedStatement pre=db.getPreSta(sql2);
			try {
				pre.setString(1, yuanxi.getXiName());
				pre.setString(2, yuanxi.getXiID());
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
	
	public List<Yuanxi> preQuery(Yuanxi yuanxi) {
		String sql = "select * from yuanxi where xiName like ? or yuan like ? or xiID like ? order by xiID+0 desc";
		DBTools db = new DBTools();
		List<Yuanxi> result = new ArrayList<>();
		PreparedStatement pre = db.getPreSta(sql);
		Yuanxi yuanxix = null;
		try {
			System.out.println("----");
			pre.setString(1,"%"+yuanxi.getXiName()+"%");
			pre.setString(2, "%"+yuanxi.getYuan()+"%");
			pre.setString(3, "%"+yuanxi.getXiID()+"%");
			ResultSet rs=pre.executeQuery();
			db.commit();
			while (rs.next()) {
				yuanxix = new Yuanxi();
				yuanxix.setXiID(rs.getString("xiID"));
				yuanxix.setXiName(rs.getString("xiName"));
				yuanxix.setYuan(rs.getString("yuan"));
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
	
	public Yuanxi findByXiID(String xiID){
		String sql="select * from yuanxi where xiID='"+xiID+"'";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		Yuanxi yuanxi=null;
		try {
			while(rs.next()){
				yuanxi=new Yuanxi();
				yuanxi.setXiID(rs.getString("xiID"));
				yuanxi.setXiName(rs.getString("xiName"));
				yuanxi.setYuan(rs.getString("yuan"));
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
		return yuanxi;
	}
	
	
	public List<Yuanxi> query(Yuanxi yuanxi){
		String sql="select * from yuanxi where xiName='"+yuanxi.getXiName()+"' and yuan='"+yuanxi.getYuan()+"' order by xiID+0 desc";
		DBTools db=new DBTools();
		List<Yuanxi> result=new ArrayList<>();
		ResultSet rs=db.getQuery(sql);
		try {
			while(rs.next()){
				Yuanxi yuanxix=new Yuanxi();
				yuanxix.setXiID(rs.getString("xiID"));
				yuanxix.setXiName(rs.getString("xiName"));
				yuanxix.setYuan(rs.getString("yuan"));
				result.add(yuanxix);
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
	
	public List<Yuanxi> findAll(){
		String sql="select * from yuanxi order by xiID+0 desc";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		List<Yuanxi> result=new ArrayList<>();
		try {
			while(rs.next()){
				Yuanxi yuanxi=new Yuanxi();
				yuanxi.setXiID(rs.getString("xiID"));
				yuanxi.setXiName(rs.getString("xiName"));
				yuanxi.setYuan(rs.getString("yuan"));
				result.add(yuanxi);
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
	
	public static void main(String[] args) {
		Yuanxi yuanxi=new Yuanxi();
		yuanxi.setXiID("2");
		yuanxi.setXiName("计算机系");
		yuanxi.setYuan("信息工程学院");
//		new YuanxiDAO().deletes("1");
//		new YuanxiDAO().insert(yuanxi);
		System.out.println(new YuanxiDAO().findAll());
	}
	
}
