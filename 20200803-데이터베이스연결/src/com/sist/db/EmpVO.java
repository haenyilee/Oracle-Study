package com.sist.db;
/*
 EMPNO                                     NOT NULL NUMBER(4)		=> int
 ENAME                                              VARCHAR2(10)	=> String
 JOB                                                VARCHAR2(9)		=> String
 MGR                                                NUMBER(4)		=> int
 HIREDATE                                           DATE			=> java.util.Date
 SAL                                                NUMBER(7,2)		=> int (Double)
 COMM                                               NUMBER(7,2)		=> int (Double)
 DEPTNO                                             NUMBER(2)		=> int (Double)
 */

import java.util.*;
public class EmpVO {
	private int empno; // 사번
	private String ename; // 이름
	private String job;  // 직위
	private int mgr;  // 사수번호
	private Date hiredate;  // 입사일
	private int sal;  // 급여
	private int comm; // 성과급
	private int deptno; // 부서번호
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	
	

}
