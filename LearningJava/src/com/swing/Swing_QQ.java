package com.swing;

import java.awt.*;

import javax.swing.*;

public class Swing_QQ extends JFrame{

	//上部区域
	JLabel lab_logo;
	
	//中部区域
	JTabbedPane jtp;
	JPanel jp_qq,jp_tel,jp_email;
	JButton btn_clear;
	JLabel lab_qq,lab_passwd,lab_foget,lab_apply;
	JTextField txt_no,txt_passwd;
	JCheckBox jcb1,jcb2;
	
	
	
	//下部区域
	JPanel jp_bottom;
	JButton btn_login,btn_cancel,btn_reg;
	public static void main(String[] args) {
		new Swing_QQ().QQ();
	}

	private void QQ()
	{
		//上
		lab_logo=new JLabel(new ImageIcon("img/tou.gif"));
		this.add(lab_logo,BorderLayout.NORTH);
		
		//中
		jtp=new JTabbedPane();
		jp_qq=new JPanel();
		jp_tel=new JPanel();
		jp_email=new JPanel();
		
		lab_qq=new JLabel("QQ号码");
		lab_passwd=new JLabel("QQ密码");
		lab_foget=new JLabel("忘记密码");
		lab_foget.setFont(new Font("微软雅黑",Font.BOLD,16));
		lab_foget.setForeground(Color.blue);
		lab_apply=new JLabel("<html><a href='www.baidu.com'>申请密码保护</a></html>");
		lab_apply.setForeground(Color.red);
		lab_apply.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btn_clear=new JButton(new ImageIcon("img/clear.gif"));
		txt_no=new JPasswordField(10);
		txt_passwd=new JPasswordField(10);
		jcb1=new JCheckBox("隐身登陆");
		jcb2=new JCheckBox("记住密码");
		
		
		jp_qq.setLayout(new GridLayout(3,3));
		jp_qq.add(lab_qq);
		jp_qq.add(txt_no);
		jp_qq.add(btn_clear);
		
		jp_qq.add(lab_passwd);
		jp_qq.add(txt_passwd);
		jp_qq.add(lab_foget);
		
		jp_qq.add(jcb1);
		jp_qq.add(jcb2);
		jp_qq.add(lab_apply);
		
		jtp.add("QQ号码",jp_qq);
		jtp.add("手机号码",jp_tel);
		jtp.add("电子邮件",jp_email);
		this.add(jtp);
		
		//下
		jp_bottom=new JPanel();
		btn_login=new JButton(new ImageIcon("img/denglu.gif"));
		btn_cancel=new JButton(new ImageIcon("img/quxiao.gif"));
		btn_reg=new JButton(new ImageIcon("img/xiangdao.gif"));
		jp_bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		jp_bottom.add(btn_login);
		jp_bottom.add(btn_cancel);
		jp_bottom.add(btn_reg);
		this.add(jp_bottom,BorderLayout.SOUTH);
		
		setFrameParam();
		
	}
	private void setFrameParam()
	{
		//窗体设置标题
		this.setTitle("Hello, world");
		
		//设置大小
		this.setSize(350, 240);
		
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
