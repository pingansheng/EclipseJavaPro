package com.swing;

import java.awt.*;

import javax.swing.*;

public class Swing_QQ extends JFrame{

	//�ϲ�����
	JLabel lab_logo;
	
	//�в�����
	JTabbedPane jtp;
	JPanel jp_qq,jp_tel,jp_email;
	JButton btn_clear;
	JLabel lab_qq,lab_passwd,lab_foget,lab_apply;
	JTextField txt_no,txt_passwd;
	JCheckBox jcb1,jcb2;
	
	
	
	//�²�����
	JPanel jp_bottom;
	JButton btn_login,btn_cancel,btn_reg;
	public static void main(String[] args) {
		new Swing_QQ().QQ();
	}

	private void QQ()
	{
		//��
		lab_logo=new JLabel(new ImageIcon("img/tou.gif"));
		this.add(lab_logo,BorderLayout.NORTH);
		
		//��
		jtp=new JTabbedPane();
		jp_qq=new JPanel();
		jp_tel=new JPanel();
		jp_email=new JPanel();
		
		lab_qq=new JLabel("QQ����");
		lab_passwd=new JLabel("QQ����");
		lab_foget=new JLabel("��������");
		lab_foget.setFont(new Font("΢���ź�",Font.BOLD,16));
		lab_foget.setForeground(Color.blue);
		lab_apply=new JLabel("<html><a href='www.baidu.com'>�������뱣��</a></html>");
		lab_apply.setForeground(Color.red);
		lab_apply.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btn_clear=new JButton(new ImageIcon("img/clear.gif"));
		txt_no=new JPasswordField(10);
		txt_passwd=new JPasswordField(10);
		jcb1=new JCheckBox("�����½");
		jcb2=new JCheckBox("��ס����");
		
		
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
		
		jtp.add("QQ����",jp_qq);
		jtp.add("�ֻ�����",jp_tel);
		jtp.add("�����ʼ�",jp_email);
		this.add(jtp);
		
		//��
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
		//�������ñ���
		this.setTitle("Hello, world");
		
		//���ô�С
		this.setSize(350, 240);
		
		//ȡ��Ļ��С����ռ����Ļ��С���������ȣ�
		Dimension dm=Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		
		//���þ�������
		int x=(dm.width-insets.left-insets.right)/2-this.getWidth()/2;
		int y=(dm.height-insets.bottom-insets.top)/2-this.getHeight()/2;
		
		//���ó�ʼλ��
		this.setLocation(x, y);
		
		//�˳�ʱ��֤JVM�˳���������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���ڴ�С���ɸ���
		this.setResizable(false);
		
		//��ʾ
		this.setVisible(true);
	}
}
