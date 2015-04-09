/*
 * �¼��������
 */
package com.swing;

import javax.swing.*;

import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing_MethodAction extends JFrame implements ActionListener{

	
	JButton j1,j2;
	JPanel jp;
	
	public static void main(String[] args) {

		Swing_MethodAction sme=new Swing_MethodAction();
	}
	
	public Swing_MethodAction()
	{
		j1=new JButton("��ɫ");
		j1.setForeground(Color.black);
		j2=new JButton("��ɫ");
		j2.setForeground(Color.red);
		
		jp=new JPanel();
		jp.setBackground(Color.gray);
		this.add(j1,BorderLayout.NORTH);
		this.add(jp);
		this.add(j2,BorderLayout.SOUTH);
		
		//ע�����
		j1.addActionListener(this);
		j2.addActionListener(this);
		//ָ��action����
		j1.setActionCommand("Black");
		j2.setActionCommand("Red");
		
		setFrameParam();
	}
	
	private void setFrameParam() {
		// �������ñ���
		this.setTitle("Hello, world");
		// ���ô�С
		this.setSize(800, 480);
		// ȡ��Ļ��С����ռ����Ļ��С���������ȣ�
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(
				GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice().getDefaultConfiguration());
		// ���þ�������
		int x = (dm.width - insets.left - insets.right) / 2 - this.getWidth()
				/ 2;
		int y = (dm.height - insets.bottom - insets.top) / 2 - this.getHeight()
				/ 2;
		// ���ó�ʼλ��
		this.setLocation(x, y);
		// �˳�ʱ��֤JVM�˳���������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ڴ�С���ɸ���
		this.setResizable(false);
		// ��ʾ
		this.setVisible(true);
	}

	//�¼�������
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Black"))
		{
			jp.setBackground(Color.black);
		}
		else if(e.getActionCommand().equals("Red"))
		{
			jp.setBackground(Color.red);
		}
	}

}

