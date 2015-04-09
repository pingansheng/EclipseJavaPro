package com.listener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
public class KeyAction extends JFrame {

	MyPanel mp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeyAction ka=new KeyAction();
	}

	public KeyAction()
	{
		mp=new MyPanel();
		this.add(mp);
		
		this.addKeyListener(mp);
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

class MyPanel extends JPanel implements WindowListener,MouseMotionListener,KeyListener,MouseListener{

	int x=10;
	int y=10;
	public void paint(Graphics g) {
		// ���ø��ຯ����ɳ�ʼ��
		super.paint(g);
		drawCir(g);
	}
	
	public void drawCir(Graphics g)
	{
		// ��Բ
		g.fillOval(x, y, 10, 10);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			x--;
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			y--;
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			y++;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			x++;
		}
		this.repaint();
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		y=e.getY();
		this.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		y=e.getY();
		this.repaint();
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showConfirmDialog(null, "ȷ��", "choose one", JOptionPane.YES_NO_OPTION);
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
