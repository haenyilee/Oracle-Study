package com.sist.db;
import java.sql.*;
import java.util.*;

public class DataBaseMain {

	public static void main(String[] args) {
		try {
			// conn hr/happy
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 설치
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			// 연결
			Connection conn = DriverManager.getConnection(url,"hr","happy");
			// SQL문장 전송
			String sql="SELECT ename FROM emp WHERE ename LIKE '%'||?||'%'";
			
			Scanner scan = new Scanner(System.in);
			System.out.println("검색어 입력 : ");
			String data = scan.next();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, data);
			
			// 데이터 읽기
			ResultSet rs= ps.executeQuery();
			while(rs.next()) // 커서위치 아래로 하나씩 옮기기
			{
				System.out.println(rs.getString(1));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
