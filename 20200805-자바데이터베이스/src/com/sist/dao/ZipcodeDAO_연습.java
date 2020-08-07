package com.sist.dao;

import java.sql.*;
import java.util.*;

public class ZipcodeDAO_연습 {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	private ZipcodeDAO_연습() {
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
			if(ps!=null) ps.close(); // 명령어가 없으면 명령 종료
			if(conn!=null) conn.close(); // 연결이 끝어지면 연결 종료
			
		} catch (Exception e) {}
	}
	
	public ArrayList<ZipcodeVO> postfind(String dong)
	{
		ArrayList<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		
		try {
			getConnection();
			String sql="SELECT * FROM zipcode WHERE dong LIKE '%'||?||'?'";
			ps=conn.prepareStatement(sql);
			
			// ?에 값 집어넣어주는 과정
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			
			// 얘만 있으면 메모리 저장공간 하나에 값이 여러번 저장되기 때문에 이전 값은 날라가고 마지막 저장한 값만 남음 => 이 문제를 해결하기 위해서 Class를 만들어서 new를 통해 한 번씩 메모리 공간 만들어서 값을 넣어주는 것임
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
