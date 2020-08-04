package com.sist.db;
import java.sql.*;
import java.util.*;

public class DataBaseMain {

	public static void main(String[] args) {
		try {
			// conn hr/happy
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����̹� ��ġ
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			// ����
			Connection conn = DriverManager.getConnection(url,"hr","happy");
			// SQL���� ����
			String sql="SELECT ename FROM emp WHERE ename LIKE '%'||?||'%'";
			
			Scanner scan = new Scanner(System.in);
			System.out.println("�˻��� �Է� : ");
			String data = scan.next();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, data);
			
			// ������ �б�
			ResultSet rs= ps.executeQuery();
			while(rs.next()) // Ŀ����ġ �Ʒ��� �ϳ��� �ű��
			{
				System.out.println(rs.getString(1));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
