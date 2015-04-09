/*
 *布局管理器组合使用
 */
package com.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.*;

public class Swing_SeveralLayout extends JFrame{

	/**
	 * @param args
	 */
	
	JPanel jp1,jp2;
	JButton[] jbs;
	
	//登陆界面
	JLabel jlab_username,jlab_passwd;
	JTextField jtxt_username;
	JPasswordField jpasswd;
	JButton jbtn_login,jbtn_cancel;
	JPanel[] jps; 
	
	//注册界面
	JCheckBox[] jcbs;
	JRadioButton[] jrbs;
	JLabel jlab_sport,jlab_sex;
	JButton jbtn_Reg,jbtn_cancelReg;
	
	//用户调查界面
	JComboBox jcbox;
	JList jl;
	JLabel jlab_home,jlab_arealike;
	JScrollPane jsp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Swing_SeveralLayout().UserSurvey();
	}

	//用户调查界面
	public void UserSurvey()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jlab_home=new JLabel("籍贯：");
		jlab_arealike=new JLabel("目的地：");

		
		String[] homes={"北京","上海","杭州"};
		jcbox=new JComboBox(homes);
		jl=new JList(homes);
		jl.setVisibleRowCount(1);
		jsp=new JScrollPane(jl);
	
		jp1.add(jlab_home);
		jp1.add(jcbox);
		jp2.add(jlab_arealike);
		jp2.add(jsp);
		this.setLayout(new GridLayout(2,1));
		this.add(jp1);
		this.add(jp2);
		

		
		setFrameParam();
		this.setTitle("地点喜好");

	}
	
	
	//注册界面
	public void Reg()
	{
		
		this.setLayout(new GridLayout(3,1));
		
		jps=new JPanel[3];
		
		jlab_sport=new JLabel("你喜欢的运动：");
		jlab_sex=new JLabel("性别：");
		
		jcbs=new JCheckBox[3];
		jrbs=new JRadioButton[2];
		
		jcbs[0] = new JCheckBox("羽毛球");
		jcbs[1] = new JCheckBox("足球");
		jcbs[2] = new JCheckBox("篮球");
		

		jrbs[0]=new JRadioButton("男");
		jrbs[1]=new JRadioButton("女");
		
		jbtn_Reg=new JButton("用户注册");
		jbtn_cancelReg=new JButton("取消注册");
		for(int i=0;i<3;i++)
		{
			jps[i]=new JPanel();
			jps[i].setLayout(new FlowLayout(FlowLayout.CENTER));
			this.add(jps[i]);
		}

		
		ButtonGroup btng=new ButtonGroup();
		btng.add(jrbs[0]);
		btng.add(jrbs[1]);
		
		jps[0].add(jlab_sport);
		for (int i = 0; i < jcbs.length; i++) {
			jps[0].add(jcbs[i]);
		}
		
		jps[1].add(jlab_sex);
		jps[1].add(jrbs[0]);
		jps[1].add(jrbs[1]);
		
		jps[2].add(jbtn_Reg);
		jps[2].add(jbtn_cancelReg);
		setFrameParam();
		
		this.setTitle("用户注册");
	}
	
	//文本框 密码框 标签
	public void Login()
	{
		jlab_username=new JLabel("用户名：");
		jlab_passwd=new JLabel("密     码：");
		
		jtxt_username=new JTextField(10);
		jpasswd=new JPasswordField(10);
		
		jbtn_login =new JButton("登陆");
		jbtn_cancel=new JButton("取消");
		
		
		this.setLayout(new GridLayout(3,1));
		
		jps=new JPanel[3];
		
		for(int i=0;i<3;i++)
		{
			jps[i]=new JPanel();
			jps[i].setLayout(new FlowLayout(FlowLayout.CENTER));
			this.add(jps[i]);
		}
		
		jps[0].add(jlab_username);
		jps[0].add(jtxt_username);
		jps[1].add(jlab_passwd);
		jps[1].add(jpasswd);
		jps[2].add(jbtn_login);
		jps[2].add(jbtn_cancel);
		
		setFrameParam();
		this.setTitle("用户登陆");
	}
	
	
	//组合布局管理器
	public void SeveralLayout()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		
		//JPanel默认流式布局
		
		jbs=new JButton[5];
		for (int i = 0; i < jbs.length; i++) {
			jbs[i]=new JButton(String.valueOf(i));
		}
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(jbs[0],BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		
		for (int i = 1; i < 3; i++) {
			jp1.add(jbs[i]);
			jp2.add(jbs[i+2]);
			
		}
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		setFrameParam();
	}
	
	
	//默认参数设置
	private void setFrameParam()
	{
		//窗体设置标题
		this.setTitle("Hello, world");
		
		//设置大小
		this.setSize(250, 140);
		
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
