package com.sist.dao;
// 데이터베이스 연결해주는 클래스

import java.util.*;
import java.sql.*;

public class EmpDAO {
	
	// 데이터베이스 오라클 연결해주는 클래스 : Connection안에 Socket이 있어서 네트워크 연결해줌
	private Connection conn; 
	
	// 송수신 담당 : BufferdReader, OutputStream의 역할을 담당
	private PreparedStatement ps;
	
	// XE는 데이터베이스(폴더) 이름
	private final String url="jdbc:orcle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	private static EmpDAO dao;
	
	public EmpDAO()
	{
		try 
		{

			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	// 싱글턴 패턴 : 데이터베이스에서 Connection을 한개만 생성해서 사용할때마다 재사용 => static을 써서 메모리 공간 한개만 생김
	public static EmpDAO newInstance()
	{
		if(dao==null)
		{
			dao=new EmpDAO();
		}
		return dao;
	}
	
	// 연결
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(url,"hr","happy");
			
		} catch (Exception e) {}
	}
	
	// 해제
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
	
	public Connection getConn() {
		return conn;
	}

	public PreparedStatement getPs() {
		return ps;
	}
	
	// =====모든 데이터베이스의 공통 사항
	
	
	
	
	// 기능

	

}
