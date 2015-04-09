/*
 * ̹�˴�ս1.0
 * 
 * 1.����̹��
 * 2.�ӵ�����
 * 3����ֹ�ص�
 * 4���ֹ�
 * 	4.1��һ����ʼpanel
 * 	4.2��˸
 * 5����ͣ������
 * 6����¼��ҳɼ�
 * 7����������
 */
package com.tank1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.*;

public class MyTankGame1 extends JFrame implements ActionListener {

	public MyPanel mp;
	public MyStartPanel SP;
	// ��������Ҫ�Ĳ˵�
	JMenuBar jmb = null;
	// ��ʼ��Ϸ
	JMenu jm1 = null;
	JMenuItem jmil = null;
	// �˳�ϵͳ
	JMenuItem jmi2 = null;
	// �����˳�
	JMenuItem jmi3 = null;
	JMenuItem jmi4 = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTankGame1 mt = new MyTankGame1();
	}

	public MyTankGame1() {
		SP = new MyStartPanel();
		this.add(SP);
		Thread t = new Thread(SP);
		t.start();

		// �����˵����˵�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ(G)");
		// ���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmil = new JMenuItem("��ʼ����Ϸ(N)");
		jmi2 = new JMenuItem("�˳���Ϸ(E)");
		jmi3 = new JMenuItem("�����˳���Ϸ(C)");
		jmi4 = new JMenuItem("�����Ͼ���Ϸ(S)");

		// ע�����
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		jmi4.setMnemonic('S');
		// ע�����
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi3.setMnemonic('C');

		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi2.setMnemonic('E');

		jmil.addActionListener(this);
		jmil.setActionCommand("newgame");
		jmil.setMnemonic('N');

		jm1.add(jmil);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		this.setJMenuBar(jmb);
		setFrameParam();
	}

	// ������ʼ��
	private void setFrameParam() {
		// �������ñ���
		this.setTitle("Hello, world");

		// ���ô�С
		this.setSize(600, 500);

		// ȡ��Ļ��С����ռ����Ļ��С���������ȣ�
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(
				GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice().getDefaultConfiguration());

		// ���þ�������
		int x = (dm.width - insets.left - insets.right) / 2 - this.getWidth()
				/ 2;
		int y = (dm.height - insets.bottom - insets.top) / 2 - this.getHeight()
				/ 2;

		// ���ó�ʼλ��
		this.setLocation(x, y);

		// �˳�ʱ��֤JVM�˳���������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ���ڴ�С���ɸ���
		this.setResizable(false);

		// ��ʾ
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("newgame")) {
			// ����ս�����
			mp = new MyPanel();
			// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ��ɾ���ɵĿ�ʼ���
			this.remove(SP);
			this.add(mp);
			// ע�����
			this.addKeyListener(mp);
			// ��ʾ,ˢ��JFrame
			this.setVisible(true);
		}
	}
}