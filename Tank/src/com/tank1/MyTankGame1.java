/*
 * 坦克大战1.0
 * 
 * 1.画出坦克
 * 2.子弹连发
 * 3、防止重叠
 * 4、分关
 * 	4.1做一个开始panel
 * 	4.2闪烁
 * 5、暂停、继续
 * 6、记录玩家成绩
 * 7、声音操作
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
	// 作出我需要的菜单
	JMenuBar jmb = null;
	// 开始游戏
	JMenu jm1 = null;
	JMenuItem jmil = null;
	// 退出系统
	JMenuItem jmi2 = null;
	// 存盘退出
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

		// 创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		// 设置快捷方式
		jm1.setMnemonic('G');
		jmil = new JMenuItem("开始新游戏(N)");
		jmi2 = new JMenuItem("退出游戏(E)");
		jmi3 = new JMenuItem("存盘退出游戏(C)");
		jmi4 = new JMenuItem("继续上局游戏(S)");

		// 注册监听
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		jmi4.setMnemonic('S');
		// 注册监听
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

	// 参数初始化
	private void setFrameParam() {
		// 窗体设置标题
		this.setTitle("Hello, world");

		// 设置大小
		this.setSize(600, 500);

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

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("newgame")) {
			// 创建战场面板
			mp = new MyPanel();
			// 启动mp线程
			Thread t = new Thread(mp);
			t.start();
			// 先删除旧的开始面板
			this.remove(SP);
			this.add(mp);
			// 注册监听
			this.addKeyListener(mp);
			// 显示,刷新JFrame
			this.setVisible(true);
		}
	}
}