/*
 * java��ͼ����ԭ��
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
		// �������ñ���
		this.setTitle("Hello, world");

		// ���ô�С
		this.setSize(800, 480);

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
}

// �Զ������ ���ڻ�ͼ����ʾ��ͼ
class MyPanel extends JPanel {
	// ��дJPanel paint����
	// ����Graphics
	
	Image img=
		Toolkit.getDefaultToolkit().getImage
		("res/nana.jpg");
	public void paint(Graphics g) {
		// ���ø��ຯ����ɳ�ʼ��
		super.paint(g);

		// ��Բ
		g.drawOval(10, 10, 30, 30);
		
		//������
		g.drawRect(30, 30, 30, 40);
		
		//�����Բ
		g.setColor(Color.red);
		g.fillOval(50, 50, 40, 40);
		
		//������
		g.setColor(Color.green);
		g.fillRect(90, 70, 30, 40);
		
		//���ַ���
		g.setColor(Color.pink);
		g.setFont(new Font("���Ĳ���",Font.BOLD,50));
		g.drawString("I Love You", 190, 100);
	
		//��ͼƬ
		g.drawImage(img, this.getWidth()/2-195/2, 120, 195, 256,this);

	}

}