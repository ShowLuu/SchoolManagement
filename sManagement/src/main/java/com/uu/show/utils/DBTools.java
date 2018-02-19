package com.uu.show.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTools {
	
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static String dbDriver;
	private static String dbUrl;
	private static String dbUser;
	private static String dbPassword;
	private int count;
	
	static{
		dbDriver="com.mysql.jdbc.Driver";
		dbUrl="jdbc:mysql://127.0.0.1:3306/stu?useUnicode=true&characterEncoding=utf-8";
		dbUser="root";
		dbPassword="111111";
	}
	
	public Connection getCon() {
		try {
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			connection.setAutoCommit(false);
			System.out.println("getCon");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public Statement getSta(){
		try {
			if(connection==null||connection.isClosed()){
				connection=getCon();
			}
			statement=connection.createStatement();
			System.out.println("getSta");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
	
	public PreparedStatement getPreSta(String sql){
		try {
			if(connection==null||connection.isClosed()){
				connection=getCon();
			}
			preparedStatement=connection.prepareStatement(sql);
			System.out.println("pre");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	public ResultSet getQuery(String sql){
		try {
			if(statement==null||statement.isClosed()){
				statement=getSta();
			}
			resultSet=statement.executeQuery(sql);
			System.out.println("res");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public int update(String sql){
		try {
			if(statement==null||statement.isClosed()){
				statement=getSta();
			}
			count=statement.executeUpdate(sql);
			System.out.println("update");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public void commit(){
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			if(resultSet!=null&&!resultSet.isClosed()){
				resultSet.close();
			}
			if(statement!=null&&!statement.isClosed()){
				statement.close();
			}
			if(connection!=null&&!connection.isClosed()){
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
