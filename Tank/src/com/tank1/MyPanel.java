package com.tank1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


class MyStartPanel extends JPanel implements Runnable{
	int times=0;
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//��ʾ��Ϣ
		if(times%2==0)
		{
		g.setColor(Color.yellow);
		Font font=new Font("������κ",Font.BOLD,30);
		g.setFont(font);
		g.drawString("Satge:1", 150, 100);
		}
	}

	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
			times++;
			this.repaint();
		}
		
	}
}
//�Զ������ ���ڻ�ͼ����ʾ��ͼ
class MyPanel extends JPanel implements KeyListener, Runnable {

	//���ڴ�С
	double w=400;
	double h=300;
	//��ըЧ��ͼƬ������ͼƬ���һ��ը��
	Image img1=null;
	Image img2=null;
	Image img3=null;
	
	//����ը������
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	// ����һ���ҵ�̹��
	Hero hero = null;

	// ����һȺ�з�̹��
	Vector<Enemy> ems = null;
	int enSize =0;
	Vector<Tank> tanks=null;
	// ���캯��
	public MyPanel() {
		tanks=new Vector<Tank>();
		
		hero = new Hero(200, 230, 0);
		hero.setSpeed(2);
		hero.setTanks(tanks);
		tanks.add(hero);
		// �з�̹�˳�ʼ��
		enSize=Recorder.getEnNum();
		ems = new Vector<Enemy>();
		int y=1;
		for (int i = 0; i < enSize; i++) {
			if(((i % 6)+1)==1)
			{
				y+=40;
			}
			Enemy em = new Enemy(((i%6)+1) * 50, y, 1);
			em.setSpeed(2);
			em.setColor(1);
			
			tanks.add(em);
			em.setTanks(tanks);
			
			ems.add(em);
			Thread t=new Thread(em);
			t.start();
		}
		
		//ͼƬ��ʼ��
		try {
			img1=ImageIO.read(new File("res/bomb_1.gif"));
			img2=ImageIO.read(new File("res/bomb_2.gif"));
			img3=ImageIO.read(new File("res/bomb_3.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		// ���ø��ຯ����ɳ�ʼ��
		super.paint(g);
		g.fillRect(0, 0, (int)w, (int)h);
		
		//��ʾ̹��
		this.drawInfo(g);
		
		//�����ҷ�̹��
		if(hero.isLive())
		{
			this.drawTank(hero.getX(), hero.getY(), g, hero.getD(), hero.getColor());
		}
		else
		{
			hero = new Hero(200, 230, 0);
			hero.setSpeed(2);
			hero.setTanks(tanks);
		}

		// �����ҷ��ӵ�
		for (Bullet bull : hero.getBus()) {
			if (bull != null && bull.isAlive()) {
				g.fill3DRect(bull.getX(), bull.getY(), 2, 2, true);
			}
		}
		
		//����̹���ӵ�
		for(int i=0;i<ems.size();i++)
		{
			for (Bullet bull : ems.get(i).getBus()) {
				if (bull != null && bull.isAlive()) {
					g.fill3DRect(bull.getX(), bull.getY(), 2, 2, true);
				}
			}
		}

		//����ը��
		for(int i=0;i<bombs.size();i++)
		{
			Bomb b=bombs.get(i);
			if(b.life>6)
			{
				//�����
				g.drawImage(img1, b.x, b.y, 30, 30,this);
				

			}
			else if(b.life>4)
			{
				g.drawImage(img2, b.x, b.y, 30, 30,this);
				
			}else{
				g.drawImage(img3, b.x, b.y, 30, 30,this);
			}
			
			//��������ֵ
			b.lifeDown();
			
			if(b.life==0)
			{
				b.isLive=false;
				bombs.remove(b);
			}
		}
		for (int i = 0; i < ems.size(); i++) {
			if (ems.get(i).isLive()) {
				this.drawTank(ems.get(i).getX(), ems.get(i).getY(), g,
						ems.get(i).getD(), ems.get(i).getColor());
			}
		}
	}

	public void drawInfo(Graphics g)
	{
		this.drawTank(10, 310, g, 0, 0);
		this.drawTank(60, 310, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(":" + String.valueOf(Recorder.getMyLife()), 35, 328);
		g.drawString(":" + String.valueOf(Recorder.getEnNum()), 85, 328);
	}
	// ���� �ж��Ƿ��ӵ����е���̹��
	public void hitTank(Bullet bu, Tank en) {
		

		// �ж�̹�˷���
		switch (en.getD()) {
		// ��
		case 0:
		case 1:
			if (bu.getX() > en.getX() && bu.getX() < en.getX() + 20
					&& bu.getY() > en.getY() && bu.getY() < en.getY() + 30) {
				// ����
				//����һ��ը������ vector
				Bomb b=new Bomb(en.getX(), en.getY());
				bombs.add(b);
				// �ӵ�����
				bu.setAlive(false);
				// ̹������
				en.setLive(false);
				if (en.isLive() == false)
				{
					tanks.remove(en);
				}
				if(en.getClass().getName().contains("Hero"))
				{
					//����̹����Ŀ
					Recorder.setMyLife(Recorder.getMyLife()-1);
				}
				else
				{
					Recorder.setEnNum(ems.size());
				}
			}
			break;
		case 2:
		case 3:
			if (bu.getX() > en.getX() && bu.getX() < en.getX() + 30
					&& bu.getY() > en.getY() && bu.getY() < en.getY() + 20) {
				// ����
				//����һ��ը������ vector
				Bomb b=new Bomb(en.getX(), en.getY());
				bombs.add(b);
				// �ӵ�����
				bu.setAlive(false);
				// ̹������
				en.setLive(false);
				if (en.isLive() == false)
				{
					tanks.remove(en);
				}
				if(en.getClass().getName().contains("Hero"))
				{
					//����̹����Ŀ
					Recorder.setMyLife(Recorder.getMyLife()-1);
				}
				else
				{
					Recorder.setEnNum(ems.size());
				}
			}
			break;
		}
	}

	public void drawTank(int x, int y, Graphics g, int direct, int type) {

		switch (type) {
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;

		}

		switch (direct) {
		// ����
		case 0:
			// 1��������߾���
			g.fill3DRect(x, y, 5, 30, false);
			// ��߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x, y + i * 3, x + 4, y + i * 3);
			}
			// 2���м����
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 3���ұ߾���
			g.fill3DRect(x + 15, y, 5, 30, false);
			// �ұ߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x + 15, y + i * 3, x + 19, y + i * 3);
			}
			// 4���м�Բ��
			g.drawOval(x + 5, y + 10, 8, 8);
			// 5����ֱ��
			g.drawLine(x + 9, y + 15, x + 9, y - 3);
			break;
		// ����
		case 1:
			// 1��������߾���
			g.fill3DRect(x, y, 5, 30, false);
			// ��߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x, y + i * 3, x + 4, y + i * 3);
			}
			// 2���м����
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 3���ұ߾���
			g.fill3DRect(x + 15, y, 5, 30, false);
			// �ұ߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x + 15, y + i * 3, x + 19, y + i * 3);
			}
			// 4���м�Բ��
			g.drawOval(x + 5, y + 10, 8, 8);
			// 5����ֱ��
			g.drawLine(x + 9, y + 13, x + 9, y + 32);
			break;
		// ����
		case 2:
			// 1��������߾���
			g.fill3DRect(x, y, 30, 5, false);
			// �ϱ߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x + i * 3, y, x + i * 3, y + 4);
			}
			// 2���м����
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 3���±߾���
			g.fill3DRect(x, y + 15, 30, 5, false);
			// �±߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x + i * 3, y + 15, x + i * 3, y + 19);
			}
			// 4���м�Բ��
			g.drawOval(x + 10, y + 5, 8, 8);
			// 5����ֱ��
			g.drawLine(x - 5, y + 9, x + 14, y + 9);
			break;
		// ����
		case 3:
			// 1��������߾���
			g.fill3DRect(x, y, 30, 5, false);
			// �ϱ߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x + i * 3, y, x + i * 3, y + 4);
			}
			// 2���м����
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 3���±߾���
			g.fill3DRect(x, y + 15, 30, 5, false);
			// �±߶���
			for (int i = 0; i < 10; i++) {
				g.drawLine(x + i * 3, y + 15, x + i * 3, y + 19);
			}
			// 4���м�Բ��
			g.drawOval(x + 10, y + 5, 8, 8);
			// 5����ֱ��
			g.drawLine(x + 32, y + 9, x + 13, y + 9);
			break;
		}

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A) {
			hero.setD(2);
			hero.moveLeft();

		} else if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {
			hero.setD(0);
			hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_S) {
			hero.setD(1);
			hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_D) {
			hero.setD(3);
			hero.moveRight();
		}
		// �ж��Ƿ���J(����)
		if (e.getKeyCode() == KeyEvent.VK_J) {
			hero.Shot();
			for (Bullet bull : hero.getBus()) {
				bull.setSpeed(5);
			}
		}
		this.repaint();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// ÿ��100ms�ػ�
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// �ж��Ƿ����
			for (Bullet bu : hero.getBus()) {
				if (bu.isAlive()) {
					for (Enemy en : this.ems) {
						hitTank(bu, en);
					}
				}
			}
			//�Ƴ���������
			for(int i=0;i<ems.size();i++)
			{
				Enemy em=ems.get(i);
				if(em.isLive()==false)
				{
					ems.remove(em);
				}
			}
			
			// �ж��Ƿ�����ҷ�̹��
			for (int i = 0; i < ems.size(); i++) {
				Enemy em = ems.get(i);
				if (em.getBus().size() > 0) {
					for (Bullet bu : em.getBus()) {
						if (bu.isAlive()) {
							hitTank(bu, hero);
						}
					}
				}
			}
			
			this.repaint();
		}
	}

}