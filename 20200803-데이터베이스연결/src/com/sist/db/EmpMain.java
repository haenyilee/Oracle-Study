package com.sist.db;
import java.sql.*;
import java.util.*;

public class EmpMain {

	public static void main(String[] args) {
		try {
			// ����Ŭ�ϰ� �����ϴ� ����̹� ��ġ
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ����Ŭ ����
			// ����Ŭ �ּ�
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn=DriverManager.getConnection(url,"hr","happy");
			// ����Ŭ�� ��ɹ� ����
			String sql="SELECT empno,ename,job,sal FROM emp WHERE empno=7788"; // emp��&WHERE�տ� ���� ���ָ� ����
					// +"WHERE empno=7788";// ���� �� ���� +������ ����ؾ���
			PreparedStatement ps=conn.prepareStatement(sql); // ����Ŭ�� �������
			// ����� �����͸� �о����
			ResultSet rs= ps.executeQuery();
			
			// ���
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
