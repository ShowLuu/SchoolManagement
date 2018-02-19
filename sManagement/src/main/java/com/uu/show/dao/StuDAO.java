package com.uu.show.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uu.show.entity.Stu;
import com.uu.show.utils.DBTools;

public class StuDAO {
	
	public Stu insert(Stu cls){
		//clsID=replace('clsID',' ','') clsName=replace('clsName',' ','') clsType=replace('clsType',' ','')
		//insert into stu replace(clsID,' ','') replace(clsName,' ','') replace(clsType,' ','') values (?,?,?)
		String sql="insert into stu values (?,?,?,?,?,?,?,?)";
		DBTools db=new DBTools();
		PreparedStatement pre=db.getPreSta(sql);
		try {
			pre.setString(1, cls.getStuID());
			pre.setString(2, cls.getStuName());
			pre.setString(3, cls.getStuSex());
			pre.setString(4, cls.getStuTime());
			pre.setString(5, cls.getStuBirth());
			pre.setString(6, cls.getStuPhone());
			pre.setString(7, cls.getStuAddr());
			pre.setString(8, cls.getStuCls());
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
		System.err.println("stu-insert");
		return cls;
	}
	
	public int deletes(String clsID){
		String sql="delete from stu where stuID=?";
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
	
	public int update(Stu cls){
		String sql1="update stu set stuName=? where stuID=?";
		String sql2="update stu set stuSex=? where stuID=?";
		String sql3="update stu set stuTime=? where stuID=?";
		String sql4="update stu set stuBirth=? where stuID=?";
		String sql5="update stu set stuPhone=? where stuID=?";
		String sql6="update stu set stuAddr=? where stuID=?";
		String sql7="update stu set stuCls=? where stuID=?";
		
		DBTools db=new DBTools();
		if(!cls.getStuName().equals("")){
			PreparedStatement pre=db.getPreSta(sql1);
			try {
				pre.setString(1, cls.getStuName());
				pre.setString(2, cls.getStuID());
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
		}else if(!cls.getStuSex().equals("")){
			PreparedStatement pre=db.getPreSta(sql2);
			try {
				pre.setString(1, cls.getStuSex());
				pre.setString(2, cls.getStuID());
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
		else if(!cls.getStuTime().equals("")){
			PreparedStatement pre=db.getPreSta(sql3);
			try {
				pre.setString(1, cls.getStuTime());
				pre.setString(2, cls.getStuID());
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
		}else if(!cls.getStuBirth().equals("")){
			PreparedStatement pre=db.getPreSta(sql4);
			try {
				pre.setString(1, cls.getStuBirth());
				pre.setString(2, cls.getStuID());
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
		}else if(!cls.getStuPhone().equals("")){
			PreparedStatement pre=db.getPreSta(sql5);
			try {
				pre.setString(1, cls.getStuPhone());
				pre.setString(2, cls.getStuID());
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
		}else if(!cls.getStuAddr().equals("")){
			PreparedStatement pre=db.getPreSta(sql6);
			try {
				pre.setString(1, cls.getStuAddr());
				pre.setString(2, cls.getStuID());
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
		}else if(!cls.getStuCls().equals("")){
			PreparedStatement pre=db.getPreSta(sql7);
			try {
				pre.setString(1, cls.getStuCls());
				pre.setString(2, cls.getStuID());
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
	
	public List<Stu> preQuery(Stu yuanxi) {
		String sql = "select * from stu where stuName like ? or stuSex like ? or stuTime like ? or stuBirth like ? or stuPhone like ? or stuAddr like ? or stuCls like ? or stuID like ?  order by stuID+0 desc";
		DBTools db = new DBTools();
		List<Stu> result = new ArrayList<>();
		PreparedStatement pre = db.getPreSta(sql);
		Stu yuanxix = null;
		try {
			System.out.println("----");
			pre.setString(1,"%"+yuanxi.getStuName()+"%");
			pre.setString(2,"%"+yuanxi.getStuSex()+"%");
			pre.setString(3,"%"+yuanxi.getStuTime()+"%");
			pre.setString(4,"%"+yuanxi.getStuBirth()+"%");
			pre.setString(5,"%"+yuanxi.getStuPhone()+"%");
			pre.setString(6,"%"+yuanxi.getStuAddr()+"%");
			pre.setString(7,"%"+yuanxi.getStuCls()+"%");
			pre.setString(8,"%"+yuanxi.getStuID()+"%");
			ResultSet rs=pre.executeQuery();
			db.commit();
			while (rs.next()) {
				yuanxix = new Stu();
				yuanxix.setStuID(rs.getString("stuID"));
				yuanxix.setStuName(rs.getString("stuName"));
				yuanxix.setStuSex(rs.getString("stuSex"));
				yuanxix.setStuTime(rs.getString("stuTime"));
				yuanxix.setStuBirth(rs.getString("stuBirth"));
				yuanxix.setStuPhone(rs.getString("stuPhone"));
				yuanxix.setStuAddr(rs.getString("stuAddr"));
				yuanxix.setStuCls(rs.getString("stuCls"));
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
	
	public Stu findByStuID(String stuID){
		String sql="select * from stu where stuID='"+stuID+"'";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		Stu cls=null;
		try {
			while(rs.next()){
				cls=new Stu();
				cls.setStuID(rs.getString("stuID"));
				cls.setStuName(rs.getString("stuName"));
				cls.setStuSex(rs.getString("stuSex"));
				cls.setStuTime(rs.getString("stuTime"));
				cls.setStuBirth(rs.getString("stuBirth"));
				cls.setStuPhone(rs.getString("stuPhone"));
				cls.setStuAddr(rs.getString("stuAddr"));
				cls.setStuCls(rs.getString("stuCls"));
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
	
	public List<Stu> findAll(){
		String sql="select * from stu order by stuID+0 desc";
		DBTools db=new DBTools();
		ResultSet rs=db.getQuery(sql);
		List<Stu> result=new ArrayList<>();
		try {
			while(rs.next()){
				Stu cls=new Stu();
				cls.setStuID(rs.getString("stuID"));
				cls.setStuName(rs.getString("stuName"));
				cls.setStuSex(rs.getString("stuSex"));
				cls.setStuTime(rs.getString("stuTime"));
				cls.setStuBirth(rs.getString("stuBirth"));
				cls.setStuPhone(rs.getString("stuPhone"));
				cls.setStuAddr(rs.getString("stuAddr"));
				cls.setStuCls(rs.getString("stuCls"));
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