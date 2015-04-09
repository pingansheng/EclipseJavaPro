package com.tank1;

import java.awt.Toolkit;
import java.util.*;

//��¼��,Ҳ���Ա�������
class Recorder
{
	//��¼û���ж��ٵ���
	private static int enNum=8;
	public static int getEnNum() {
		return enNum;
	}

	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}

	public static int getMyLife() {
		return myLife;
	}

	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}

	//�������ж��ٿ�����
	private static int myLife=3;

	public Recorder()
	{
		
	}
}
//�ӵ���
class Bullet implements Runnable {
	int x = 0;
	int y = 0;
	int d = 0;// ����
	int speed = 1;
	boolean isAlive = true;

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Bullet(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void run() {
		while (true) {

			if (!this.isAlive()) {
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (this.getD()) {
			case 0:
				// ��
				y -= speed;
				break;
			case 1:
				// ��
				y += speed;
				break;
			case 2:
				// ��
				x -= speed;
				break;
			case 3:
				// ��
				x += speed;
				break;
			}

			if (x < 0 || x > 400 || y < 0 || y > 300) {
				this.isAlive = false;

				break;
			}
		}
	}
}

// ̹����
class Tank {
	// �������� ����
	boolean isLive = true;
	int x = 0;
	int y = 0;
	int d = 0;
	int color = 0;
	Vector<Bullet> bus = new Vector<Bullet>();
	Bullet bu = null;
	// �ٶ�
	int speed = 1;

	//�õ�ȫ��̹��
		Vector<Tank> ets=new Vector<Tank>();
		
		public void setTanks(Vector<Tank> ets)
		{
			this.ets=ets;
		}
		//�ж��Ƿ���������̹��
		public boolean isToucher_Others()
		{
			boolean b=false;
			
			switch (this.getD()) {
			case 0:
				//��������
				//ȡ�����е���̹��
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//���˷����ϻ�����
						if(en.getD()==0||en.getD()==1)
						{
							//���ϵ�
							if(this.getX()>en.getX()&& this.getX()<en.getX()+20
									&&this.getY()-3>en.getY()&& this.getY()-3<en.getY()+30)
							{
								return true;
							}
							//���ϵ�
							if(this.getX()+20>en.getX()&& this.getX()+20<en.getX()+20
									&&this.getY()-3>en.getY()&& this.getY()-3<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//���ϵ�
							if(this.getX()>en.getX()&& this.getX()<en.getX()+30
									&&this.getY()-3>en.getY()&& this.getY()-3<en.getY()+20)
							{
								return true;
							}
							//���ϵ�
							if(this.getX()+20>en.getX()&& this.getX()+20<en.getX()+30
									&&this.getY()-3>en.getY()&& this.getY()-3<en.getY()+20)
							{
								return true;
							}
						}
					}
					
				}
				break;
			case 1:
				//��������
				//ȡ�����е���̹��
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//���˷����ϻ�����
						if(en.getD()==0||en.getD()==1)
						{
							//���µ�
							if(this.getX()>en.getX()&& this.getX()<en.getX()+20
									&&this.getY()+33>en.getY()&& this.getY()+33<en.getY()+30)
							{
								return true;
							}
							//���µ�
							if(this.getX()+20>en.getX()&& this.getX()+20<en.getX()+20
									&&this.getY()+33>en.getY()&& this.getY()+33<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//���µ�
							if(this.getX()>en.getX()&& this.getX()<en.getX()+30
									&&this.getY()+33>en.getY()&& this.getY()+33<en.getY()+20)
							{
								return true;
							}
							//���µ�
							if(this.getX()+20>en.getX()&& this.getX()+20<en.getX()+30
									&&this.getY()+33>en.getY()&& this.getY()+33<en.getY()+20)
							{
								return true;
							}
						}
					}
					
				}
				break;
			case 2:
				//��������
				//ȡ�����е���̹��
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//���˷����ϻ�����
						if(en.getD()==0||en.getD()==1)
						{
							//���ϵ�
							if(this.getX()-3>en.getX()&& this.getX()-3<en.getX()+20
									&&this.getY()>en.getY()&& this.getY()<en.getY()+30)
							{
								return true;
							}
							//���µ�
							if(this.getX()-3>en.getX()&& this.getX()-3<en.getX()+20
									&&this.getY()+20>en.getY()&& this.getY()+20<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//���ϵ�
							if(this.getX()-3>en.getX()&& this.getX()-3<en.getX()+30
									&&this.getY()>en.getY()&& this.getY()<en.getY()+20)
							{
								return true;
							}
							//���µ�
							if(this.getX()-3>en.getX()&& this.getX()-3<en.getX()+30
									&&this.getY()+20>en.getY()&& this.getY()+20<en.getY()+20)
							{
								return true;
							}
						}
					}
					
				}
				break;
			case 3:
				//��������
				//ȡ�����е���̹��
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//���˷����ϻ�����
						if(en.getD()==0||en.getD()==1)
						{
							//���ϵ�
							if(this.getX()+30+3>en.getX()&& this.getX()+30+3<en.getX()+20
									&&this.getY()>en.getY()&& this.getY()<en.getY()+30)
							{
								return true;
							}
							//���µ�
							if(this.getX()+30+3>en.getX()&& this.getX()+30+3<en.getX()+20
									&&this.getY()+20>en.getY()&& this.getY()+20<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//���ϵ�
							if(this.getX()+30+3>en.getX()&& this.getX()+30+3<en.getX()+30
									&&this.getY()>en.getY()&& this.getY()<en.getY()+20)
							{
								return true;
							}
							//���µ�
							if(this.getX()+30+3>en.getX()&& this.getX()+30+3<en.getX()+30
									&&this.getY()+20>en.getY()&& this.getY()+20<en.getY()+20)
							{
								return true;
							}
						}
					}
					
				}
				break;
			}
			
			return b;
			
		}
	
	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public Vector<Bullet> getBus() {
		return bus;
	}

	public void setBus(Vector<Bullet> bus) {
		this.bus = bus;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Tank(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

	public void Shot() {
		for (int i = 0; i < this.getBus().size(); i++) {
			if (this.getBus().get(i).isAlive == false) {
				this.getBus().remove(i);
			}
		}
		if (this.getBus().size() < 5) {
			switch (this.d) {
			case 0:
				bu = new Bullet(x + 9, y - 3, this.d);
				break;
			case 1:
				bu = new Bullet(x + 9, y + 32, this.d);
				break;
			case 2:
				bu = new Bullet(x - 5, y + 9, this.d);
				break;
			case 3:
				bu = new Bullet(x + 32, y + 9, this.d);
				break;
			}

			bus.add(bu);

			Thread th = new Thread(bu);
			th.start();// �����ӵ��߳�
		}

	}

	// ̹�������ƶ�
	public void moveUp() {
		if(this.getY()-3>0&& !this.isToucher_Others())
		{
		this.y -= this.speed;
		}
	}

	// ̹�������ƶ�
	public void moveRight() {
		if(this.getX()+38<400&& !this.isToucher_Others())
		{
		this.x += this.speed;
		}
	}

	// ̹�������ƶ�
	public void moveDown() {
		if(this.getY()+60<300&& !this.isToucher_Others())
		{
		this.y += this.speed;
		}
	}

	// ̹�������ƶ�
	public void moveLeft() {
		if(this.getX()-3>0&& !this.isToucher_Others())
		{
		this.x -= this.speed;
		}
	}
	

}

// �ҷ�̹��
class Hero extends Tank {
	public Hero(int x, int y) {
		super(x, y);
	}

	public Hero(int x, int y, int d) {
		this(x, y);
		this.setD(d);
	}
}

// �з�̹��
class Enemy extends Tank implements Runnable {
	
	
	public Enemy(int x, int y) {
		super(x, y);
	}

	public Enemy(int x, int y, int d) {
		this(x,y);
		this.setD(d);
	}

	@Override
	public void run() {

		Random rm = new Random();
		int step_num=0;
		while (true) {
			step_num=rm.nextInt(15)+15;
			switch (this.getD()) {
			case 0:
				// ����
				for (int i = 0; i < step_num; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.moveUp();
				}
				break;
			case 1:
				// ����
				for (int i = 0; i < step_num; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}

					this.moveDown();
					
				}
				break;
			case 2:
				// ����
				for (int i = 0; i < step_num; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}

					this.moveLeft();
					
				}
				break;
			case 3:
				// ����
				for (int i = 0; i < step_num; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.printStackTrace();
					}

					this.moveRight();
					
				}
				break;
			}

			// ���ת��
			this.setD((int) (Math.random() * 4));

			// �жϵ����Ƿ�����
			if (this.isLive() == false) {
				// �������˳��߳�
				break;
			}
			
			if(rm.nextInt(100)<30)
			{			
				this.Shot();
				for (Bullet bu : this.getBus()) {
					bu.setSpeed(5);
				}
			}
		}

	}
}

// ը����
class Bomb {
	// ����
	int x, y;
	// ը������
	int life = 9;
	boolean isLive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ��������ֵ
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			isLive = false;
		}
	}
}

