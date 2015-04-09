/*
 * 事件处理机制
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
		j1=new JButton("黑色");
		j1.setForeground(Color.black);
		j2=new JButton("红色");
		j2.setForeground(Color.red);
		
		jp=new JPanel();
		jp.setBackground(Color.gray);
		this.add(j1,BorderLayout.NORTH);
		this.add(jp);
		this.add(j2,BorderLayout.SOUTH);
		
		//注册监听
		j1.addActionListener(this);
		j2.addActionListener(this);
		//指定action命令
		j1.setActionCommand("Black");
		j2.setActionCommand("Red");
		
		setFrameParam();
	}
	
	private void setFrameParam() {
		// 窗体设置标题
		this.setTitle("Hello, world");
		// 设置大小
		this.setSize(800, 480);
		// 取屏幕大小与已占用屏幕大小（任务栏等）
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(
				GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice().getDefaultConfiguration());
		// 设置居中坐标
		int x = (dm.width - insets.left - insets.right) / 2 - this.getWidth()
				/ 2;
		int y = (dm.height - insets.bottom - insets.top) / 2 - this.getHeight()
				/ 2;
		// 设置初始位置
		this.setLocation(x, y);
		// 退出时保证JVM退出！！！！
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 窗口大小不可更改
		this.setResizable(false);
		// 显示
		this.setVisible(true);
	}

	//事件处理方法
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

