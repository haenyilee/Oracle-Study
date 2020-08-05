package com.sist.user;

import com.sist.dao.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;


public class PostMain_���� extends JFrame implements ActionListener{
	
	JTextField tf;
    JLabel la;
    DefaultTableModel model;
    JTable table;
    
    public PostMain_����()
    {
    	tf=new JTextField(15);
    	la=new JLabel("�Է�");
    	
    	String[] col= {"�����ȣ","�ּ�"};
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
    	
    	// tf�� ������ �����ϴ� ����� �ֵ��� ��� => �Ʒ� ������ �Լ��� ȣ���ϴ� ���
    	tf.addActionListener(this);
    }
	public static void main(String[] args) {
		new PostMain();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==tf)
		{
			String dong = tf.getText();
			if(dong.length()<1)
			{
				JOptionPane.showMessageDialog(this, "�˻�� �Է����ּ���");
				return;
			}
			for(int i=model.getRowCount()-1;i>=0;i--)
			{
				model.removeRow(i);
			}
			
			ZipcodeDAO dao= new ZipcodeDAO();
			ArrayList<ZipcodeVO> list= dao.postfind(dong);
			
			for(ZipcodeVO vo:list)
			{
				String[] data = {
					vo.getZipcode(),
					vo.getAddress()
				};
				model.addRow(data);
			}
			
			
		}
		
	}

}
