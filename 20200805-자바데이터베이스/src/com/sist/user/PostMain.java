package com.sist.user;
import com.sist.dao.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class PostMain extends JFrame implements ActionListener{

    JTextField tf;
    JLabel la;
    DefaultTableModel model;
    JTable table;
    public PostMain()
    {
    	tf=new JTextField(15);
    	la=new JLabel("입력");
    	
    	String[] col= {"우편번호","주소"};
    	String[][] row=new String[0][2];
    	
    	model=new DefaultTableModel(row,col);
    	table=new JTable(model);
    	JScrollPane js=new JScrollPane(table);
    	JPanel p=new JPanel();
    	p.add(la);
    	p.add(tf);
    	add("North",p);
    	add("Center",js);
    	
    	setSize(450, 500);
    	setVisible(true);
    	
    	// tf를 누르면 동작하는 기능이 있도록 등록 => 아래 구현한 함수를 호출하는 기능
    	tf.addActionListener(this);
    }


	public static void main(String[] args) {
		
		new PostMain();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// tf를 누르면 동작하는 기능을 구현
		if(e.getSource()==tf)
		{
			String dong = tf.getText();
			// 입력이 안되었을때
			if(dong.length()<1)
			{
				JOptionPane.showMessageDialog(this,"동/읍/면을 입력하세요");
				return;
			}
			// 입력이 되었을 때 처리
		
			for(int i=model.getRowCount()-1;i>=0;i--)
			{
				// 테이블 지우기 : 안지워주면 검색한 값이 맨 첨 목록 맨 밑에 추가됨
				model.removeRow(i);
			}
			
			// 얘만이 오라클 연결하는 애라서 얘를 통해서 입력값 받아와야함
			ZipcodeDAO dao = new ZipcodeDAO();
			ArrayList<ZipcodeVO> list = dao.postfind(dong);
			
			// 출력
			for(ZipcodeVO vo : list)
			{
				String[] data = {
						vo.getZipcode(),
						vo.getAddress() // 주소를 묶어서 출력해주는 변수(vo에서 만들었음)
				};
				model.addRow(data);
			}
		}
		
	}

}
