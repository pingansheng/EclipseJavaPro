package com.swing;

import java.awt.*;

import javax.swing.*;
public class Swing_Test extends JFrame{

	JSplitPane jsp;
	JLabel jlab;
	JList jl;
	//����
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
		//�������ñ���
		this.setTitle("Hello, world");
		
		//���ô�С
		this.setSize(800, 480);
		
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
