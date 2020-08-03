package com.sist.db;
import java.sql.*;
import java.util.*;

public class EmpMain {

	public static void main(String[] args) {
		try {
			// 오라클하고 연결하는 드라이버 설치
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 오라클 연결
			// 오라클 주소
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn=DriverManager.getConnection(url,"hr","happy");
			// 오라클에 명령문 전송
			String sql="SELECT empno,ename,job,sal FROM emp WHERE empno=7788"; // emp뒤&WHERE앞에 공백 안주면 오류
					// +"WHERE empno=7788";// 한줄 띄어서 쓰면 +연산자 사용해야함
			PreparedStatement ps=conn.prepareStatement(sql); // 오라클에 명령전달
			// 실행된 데이터를 읽어오기
			ResultSet rs= ps.executeQuery();
			
			// 출력
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "
						+rs.getString(2)+" "
						+rs.getString(3)+" "
						+rs.getInt(4));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
