package com.sist.dao;
import java.util.*;
import java.sql.*;


public class MusicDAO {
	
	// 연결
	private Connection conn;
	
	// 오라클에 전송할 쿼리
	private PreparedStatement ps;
	
	// 오라클에 연결하려면 url이 필요함
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 순서대로 값 넣어주기
	/*   mno NUMBER(3),
   title VARCHAR2(300),
   singer VARCHAR2(100),
   album VARCHAR2(200),
   poster VARCHAR2(1000),
   state CHAR(6), 
   idcrement NUMBER(3),
   key VARCHAR2(50)
   */
	
	// 드라이버 등록
	public MusicDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	// 오라클 연결
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 오라클 연결해제
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
		}
	}
	
	// 데이터 추가(기능)
	public void musicInsert(MusicVO vo)
	{
		try {
			getConnection();
			// 방식1 : 과거방식
			/*
			String sql="INSERT INTO genie_music VALUES( "
					+vo.getMno()+",'"+vo.getTitle()+"','"+vo.getSinger()
					+"','"+vo.getAlbum()+"','"+vo.getPoster()+"','"
					+vo.getState()+"',"+vo.getIdcrement()+",'"+vo.getKey()
					+"')";
			*/

			
			// 방식2 : 더욱편리, 현재 기본방식
			String sql="INSERT INTO genie_music VALUES(?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
			// ? 값을 채운다
			ps.setInt(1, vo.getMno());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getSinger());
			ps.setString(4, vo.getAlbum());
			ps.setString(5, vo.getPoster());
			ps.setString(6, vo.getState());
			ps.setInt(7, vo.getIdcrement());
			ps.setString(8, vo.getKey());
			
			// 실행
			ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			disConnection();
		}
	}

}
