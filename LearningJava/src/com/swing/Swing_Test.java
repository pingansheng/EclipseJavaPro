package com.swing;

import java.awt.*;

import javax.swing.*;
public class Swing_Test extends JFrame{

	JSplitPane jsp;
	JLabel jlab;
	JList jl;
	//聊天
	JTextArea jta;
	JScrollPane jscp;
	JPanel jp;
	JComboBox jcb;
	JTextField jtf;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new Swing_Test().liaotian();
	}
	
	public void Swing_Test1()
	{
		String[] words={"1","2","3","4"};
		
		jlab=new JLabel(new ImageIcon("img/tg_mail.gif"));
		jl= new JList(words);
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jl,jlab);
		jsp.setOneTouchExpandable(true);
		this.add(jsp);
		setFrameParam();
		
	}

	public void liaotian()
	{
		jta=new JTextArea();
		jscp=new JScrollPane(jta);
		String[] f={"1","2","3","4"};
		jcb=new JComboBox(f);
		jtf=new JTextField(10);
		jp=new JPanel();
		jp.add(jcb);
		jp.add(jtf);
		jp.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.add(jscp);
		this.add(jp,BorderLayout.SOUTH);
		
		setFrameParam();

	}
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

		//显示
		this.setVisible(true);
	}
}
