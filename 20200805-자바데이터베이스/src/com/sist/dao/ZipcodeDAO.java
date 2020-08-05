package com.sist.dao;

//Data Access Object  

import java.sql.*;
import java.util.*;


public class ZipcodeDAO {
	
	// 연결
	private Connection conn;
	
	// 문장전송 -> SQL
	private PreparedStatement ps;
	
	// 연결 : 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 등록
	public ZipcodeDAO()
	{
		try {
			Class.forName("oracle:jdbc:driver:OracleDriver");
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	// 연결
	public void getConnection()
	{
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
			
		} catch (Exception e) {}
	}
	
	// 닫기
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			// exit
			
		} catch (Exception e) {}
	}
	
	// 우편번호 찾기
	public ArrayList<ZipcodeVO> postfind(String dong)
	{
		ArrayList<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		
		try {
			// 연결
			getConnection();
			// SQL문장 전송
			String sql = "SELECT * FROM zipcode "
					+"WHERE dong LIKE '%'||?||'%'"; // preparedStatement : ?
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs= ps.executeQuery(); // 실행
			while(rs.next()) // next함수는 한줄 다 찾고 끝부분에 남겨진 커서를, 담줄 첨으로 움직여주는 역할
			{
				// 검색 결과 갯수만큼 메모리 주소를 지정해줘야 하니까 new를 통해 메모리 주소 생성
				ZipcodeVO vo = new ZipcodeVO();
				
				// 배열에 값 넣어주기
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setGugun(rs.getString(3));
				vo.setDong(rs.getString(4));
				vo.setBunji(rs.getString(5));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
		return list;
	}
}
