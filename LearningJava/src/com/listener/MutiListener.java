/*
 * 多重监听，
 * 窗口监听，鼠标按键 、移动、 键盘按下移动 (具体类见KeyAction)
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
