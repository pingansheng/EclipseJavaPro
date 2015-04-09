/**
 * GUI开发
 */
package com.swing;

import java.awt.*;

import javax.swing.*;
public class SwingTest  extends JFrame{

	/**
	 * @param args
	 */
	
	//定义组件引用
	JButton jb1=null;
	JButton[] jbs=null;
	static int flag=0;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		SwingTest st=new SwingTest(3);
	}
	
	//Swing简单测试
	public SwingTest(int key)
	{
		
		switch (key) {
		case 0:
			SwingSimpleTest();
			break;
		case 1:
			SwingBoderLayout();
		case 2:
			SwingFlowLayout();
		case 3:
			SwingGridLayout();
		default:
			setFrameParam();
			break;
		}
		setFrameParam();
		
	}
	
	/*
	 * Swing简单测试
	 */
	private void SwingSimpleTest()
	{
		//新建按钮组件
		JButton jb1=new JButton("This is a button.");	
		//添加组件
		this.add(jb1);
		
	}
	
	/*
	 * BoderLayout测试
	 */
	private void SwingBoderLayout()
	{
		jbs=new JButton[5];
		String[] strs={"东","西","南","北","中"};
		for(int i=0;i<jbs.length;i++)
		{
			jbs[i]=new JButton(strs[i]);
		}
		this.add(jbs[0],BorderLayout.EAST);
		this.add(jbs[1],BorderLayout.WEST);
		this.add(jbs[2],BorderLayout.SOUTH);
		this.add(jbs[3],BorderLayout.NORTH);
		this.add(jbs[4],BorderLayout.CENTER);
	}
	
	
	/*
	 * FlowLayout测试
	 */
	private void SwingFlowLayout()
	{
		jbs=new JButton[5];
		String[] strs={"东","西","南","北","中"};
		for(int i=0;i<jbs.length;i++)
		{
			jbs[i]=new JButton(strs[i]);
			this.add(jbs[i]);
		}
	    //设置布局管理器
		this.setLayout(new FlowLayout(FlowLayout.LEFT));//默认居中对齐
	}
	
	/*
	 * GridLayout
	 */
	private void SwingGridLayout()
	{
		//创建组件,添加组件
		jbs=new JButton[9];
		//设置布局管理器
		this.setLayout(new GridLayout(3,3,10,20));
		
		for(int i=0;i<9;i++)
		{
			jbs[i]=new JButton(String.valueOf(i));
			ste(jbs[i]);
			this.add(jbs[i]);
		}
		

		
		
	}
	
	//迭代一次
	private void ste(JButton jb)
	{
		//创建组件,添加组件
		JButton[] jbs=new JButton[9];
		//设置布局管理器
		jb.setLayout(new GridLayout(3,3));
		
		for(int i=0;i<9;i++)
		{
			jbs[i]=new JButton(String.valueOf(i));
			jb.add(jbs[i]);
		}
	}
	
	/*
	 * 设置默认窗体属性
	 */
	private void setFrameParam()
	{
		//窗体设置标题
		this.setTitle("Hello, world");
		
		//设置大小
		this.setSize(800, 480);
		
		//取屏幕大小与已占用屏幕大小（任务栏等）
		Dimension dm=Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		
		//设置居中坐标
		int x=(dm.width-insets.left-insets.right)/2-this.getWidth()/2;
		int y=(dm.height-insets.bottom-insets.top)/2-this.getHeight()/2;
		
		//设置初始位置
		this.setLocation(x, y);
		
		//退出时保证JVM退出！！！！
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//窗口大小不可更改
		this.setResizable(false);
		
		this.setIconImage(new ImageIcon("img/qq.gif").getImage());
		//显示
		this.setVisible(true);
	}
}
