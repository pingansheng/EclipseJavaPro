/*
 * java绘图基本原理
 */
package com.swing;

import java.awt.*;

import javax.swing.*;


public class GDI_test extends JFrame {

	MyPanel mp;

	public static void main(String[] args) {

		GDI_test gdt = new GDI_test();
	}

	public GDI_test() {
		mp = new MyPanel();

		this.add(mp);

		setFrameParam();
	}

	private void setFrameParam() {
		// 窗体设置标题
		this.setTitle("Hello, world");

		// 设置大小
		this.setSize(800, 480);

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
}

// 自定义面板 用于绘图与显示绘图
class MyPanel extends JPanel {
	// 重写JPanel paint方法
	// 画笔Graphics
	
	Image img=
		Toolkit.getDefaultToolkit().getImage
		("res/nana.jpg");
	public void paint(Graphics g) {
		// 调用父类函数完成初始化
		super.paint(g);

		// 画圆
		g.drawOval(10, 10, 30, 30);
		
		//画矩形
		g.drawRect(30, 30, 30, 40);
		
		//填充椭圆
		g.setColor(Color.red);
		g.fillOval(50, 50, 40, 40);
		
		//填充矩形
		g.setColor(Color.green);
		g.fillRect(90, 70, 30, 40);
		
		//画字符串
		g.setColor(Color.pink);
		g.setFont(new Font("华文彩云",Font.BOLD,50));
		g.drawString("I Love You", 190, 100);
	
		//画图片
		g.drawImage(img, this.getWidth()/2-195/2, 120, 195, 256,this);

	}

}