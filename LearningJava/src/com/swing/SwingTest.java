/**
 * GUI����
 */
package com.swing;

import java.awt.*;

import javax.swing.*;
public class SwingTest  extends JFrame{

	/**
	 * @param args
	 */
	
	//�����������
	JButton jb1=null;
	JButton[] jbs=null;
	static int flag=0;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		SwingTest st=new SwingTest(3);
	}
	
	//Swing�򵥲���
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
	 * Swing�򵥲���
	 */
	private void SwingSimpleTest()
	{
		//�½���ť���
		JButton jb1=new JButton("This is a button.");	
		//������
		this.add(jb1);
		
	}
	
	/*
	 * BoderLayout����
	 */
	private void SwingBoderLayout()
	{
		jbs=new JButton[5];
		String[] strs={"��","��","��","��","��"};
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
	 * FlowLayout����
	 */
	private void SwingFlowLayout()
	{
		jbs=new JButton[5];
		String[] strs={"��","��","��","��","��"};
		for(int i=0;i<jbs.length;i++)
		{
			jbs[i]=new JButton(strs[i]);
			this.add(jbs[i]);
		}
	    //���ò��ֹ�����
		this.setLayout(new FlowLayout(FlowLayout.LEFT));//Ĭ�Ͼ��ж���
	}
	
	/*
	 * GridLayout
	 */
	private void SwingGridLayout()
	{
		//�������,������
		jbs=new JButton[9];
		//���ò��ֹ�����
		this.setLayout(new GridLayout(3,3,10,20));
		
		for(int i=0;i<9;i++)
		{
			jbs[i]=new JButton(String.valueOf(i));
			ste(jbs[i]);
			this.add(jbs[i]);
		}
		

		
		
	}
	
	//����һ��
	private void ste(JButton jb)
	{
		//�������,������
		JButton[] jbs=new JButton[9];
		//���ò��ֹ�����
		jb.setLayout(new GridLayout(3,3));
		
		for(int i=0;i<9;i++)
		{
			jbs[i]=new JButton(String.valueOf(i));
			jb.add(jbs[i]);
		}
	}
	
	/*
	 * ����Ĭ�ϴ�������
	 */
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
		
		this.setIconImage(new ImageIcon("img/qq.gif").getImage());
		//��ʾ
		this.setVisible(true);
	}
}
