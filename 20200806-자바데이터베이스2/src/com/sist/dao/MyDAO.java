package com.sist.dao;
import java.util.*;
import java.sql.*;

public class MyDAO {
	EmpDAO dao;
	
	public MyDAO()
	{
		dao=EmpDAO.newInstance();
	}
	
	public void empAllData()
	{
		dao.getConnection();
	}

}
