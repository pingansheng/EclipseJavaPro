/*
 * ���ؼ�����
 * ���ڼ�������갴�� ���ƶ��� ���̰����ƶ� (�������KeyAction)
 */
package com.listener;

import java.awt.*;
import javax.swing.*;
public class MutiListener extends JFrame{


	MyPanel mp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MutiListener ml=new MutiListener();
	}
	
	public MutiListener()
	{
		mp=new MyPanel();
		this.add(mp);
		this.addKeyListener(mp);
		this.addMouseListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		this.setSize(300,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
