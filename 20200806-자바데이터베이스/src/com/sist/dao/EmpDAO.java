package com.sist.dao;
// �����ͺ��̽� �������ִ� Ŭ����

import java.util.*;
import java.sql.*;

public class EmpDAO {
	
	// �����ͺ��̽� ����Ŭ �������ִ� Ŭ���� : Connection�ȿ� Socket�� �־ ��Ʈ��ũ ��������
	private Connection conn; 
	
	// �ۼ��� ��� : BufferdReader, OutputStream�� ������ ���
	private PreparedStatement ps;
	
	// XE�� �����ͺ��̽�(����) �̸�
	private final String url="jdbc:orcle:thin:@localhost:1521:XE";
	
	// ����̹� ���
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
	
	// �̱��� ���� : �����ͺ��̽����� Connection�� �Ѱ��� �����ؼ� ����Ҷ����� ���� => static�� �Ἥ �޸� ���� �Ѱ��� ����
	public static EmpDAO newInstance()
	{
		if(dao==null)
		{
			dao=new EmpDAO();
		}
		return dao;
	}
	
	// ����
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(url,"hr","happy");
			
		} catch (Exception e) {}
	}
	
	// ����
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
	
	// =====��� �����ͺ��̽��� ���� ����
	
	
	
	
	// ���

	

}
