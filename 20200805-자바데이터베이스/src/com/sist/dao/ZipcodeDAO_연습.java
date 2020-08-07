package com.sist.dao;

import java.sql.*;
import java.util.*;

public class ZipcodeDAO_���� {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	private ZipcodeDAO_����() {
		try {
			Class.forName("oracle:jdbc:driver:OracleDriver");
			
		} catch (Exception e) {e.getMessage();}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close(); // ��ɾ ������ ��� ����
			if(conn!=null) conn.close(); // ������ �������� ���� ����
			
		} catch (Exception e) {}
	}
	
	public ArrayList<ZipcodeVO> postfind(String dong)
	{
		ArrayList<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		try {
			getConnection();
			String sql="SELECT * FROM zipcode WHERE dong LIKE '%'||?||'?'";
			ps=conn.prepareStatement(sql);
			
			// ?�� �� ����־��ִ� ����
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			
			// �길 ������ �޸� ������� �ϳ��� ���� ������ ����Ǳ� ������ ���� ���� ���󰡰� ������ ������ ���� ���� => �� ������ �ذ��ϱ� ���ؼ� Class�� ���� new�� ���� �� ���� �޸� ���� ���� ���� �־��ִ� ����
			while(rs.next())
			{
				ZipcodeVO vo = new ZipcodeVO();
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setGugun(rs.getString(3));
				vo.setDong(rs.getString(4));
				vo.setBunji(rs.getString(5));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			disConnection();
		} return list;
		
	}

}
