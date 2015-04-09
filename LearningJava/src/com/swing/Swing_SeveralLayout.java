/*
 *���ֹ��������ʹ��
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
	
	//��½����
	JLabel jlab_username,jlab_passwd;
	JTextField jtxt_username;
	JPasswordField jpasswd;
	JButton jbtn_login,jbtn_cancel;
	JPanel[] jps; 
	
	//ע�����
	JCheckBox[] jcbs;
	JRadioButton[] jrbs;
	JLabel jlab_sport,jlab_sex;
	JButton jbtn_Reg,jbtn_cancelReg;
	
	//�û��������
	JComboBox jcbox;
	JList jl;
	JLabel jlab_home,jlab_arealike;
	JScrollPane jsp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Swing_SeveralLayout().UserSurvey();
	}

	//�û��������
	public void UserSurvey()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jlab_home=new JLabel("���᣺");
		jlab_arealike=new JLabel("Ŀ�ĵأ�");

		
		String[] homes={"����","�Ϻ�","����"};
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
		this.setTitle("�ص�ϲ��");

	}
	
	
	//ע�����
	public void Reg()
	{
		
		this.setLayout(new GridLayout(3,1));
		
		jps=new JPanel[3];
		
		jlab_sport=new JLabel("��ϲ�����˶���");
		jlab_sex=new JLabel("�Ա�");
		
		jcbs=new JCheckBox[3];
		jrbs=new JRadioButton[2];
		
		jcbs[0] = new JCheckBox("��ë��");
		jcbs[1] = new JCheckBox("����");
		jcbs[2] = new JCheckBox("����");
		

		jrbs[0]=new JRadioButton("��");
		jrbs[1]=new JRadioButton("Ů");
		
		jbtn_Reg=new JButton("�û�ע��");
		jbtn_cancelReg=new JButton("ȡ��ע��");
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
		
		this.setTitle("�û�ע��");
	}
	
	//�ı��� ����� ��ǩ
	public void Login()
	{
		jlab_username=new JLabel("�û�����");
		jlab_passwd=new JLabel("��     �룺");
		
		jtxt_username=new JTextField(10);
		jpasswd=new JPasswordField(10);
		
		jbtn_login =new JButton("��½");
		jbtn_cancel=new JButton("ȡ��");
		
		
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
		this.setTitle("�û���½");
	}
	
	
	//��ϲ��ֹ�����
	public void SeveralLayout()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		
		//JPanelĬ����ʽ����
		
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
	
	
	//Ĭ�ϲ�������
	private void setFrameParam()
	{
		//�������ñ���
		this.setTitle("Hello, world");
		
		//���ô�С
		this.setSize(250, 140);
		
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
