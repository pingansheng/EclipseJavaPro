package com.tank1;

import java.awt.Toolkit;
import java.util.*;

//记录类,也可以保存设置
class Recorder
{
	//记录没关有多少敌人
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

	//设置我有多少可用人
	private static int myLife=3;

	public Recorder()
	{
		
	}
}
//子弹类
class Bullet implements Runnable {
	int x = 0;
	int y = 0;
	int d = 0;// 方向
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
				// 上
				y -= speed;
				break;
			case 1:
				// 下
				y += speed;
				break;
			case 2:
				// 左
				x -= speed;
				break;
			case 3:
				// 右
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

// 坦克类
class Tank {
	// 横纵坐标 方向
	boolean isLive = true;
	int x = 0;
	int y = 0;
	int d = 0;
	int color = 0;
	Vector<Bullet> bus = new Vector<Bullet>();
	Bullet bu = null;
	// 速度
	int speed = 1;

	//得到全部坦克
		Vector<Tank> ets=new Vector<Tank>();
		
		public void setTanks(Vector<Tank> ets)
		{
			this.ets=ets;
		}
		//判断是否碰到别人坦克
		public boolean isToucher_Others()
		{
			boolean b=false;
			
			switch (this.getD()) {
			case 0:
				//自我向上
				//取出所有敌人坦克
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//敌人方向上或者下
						if(en.getD()==0||en.getD()==1)
						{
							//左上点
							if(this.getX()>en.getX()&& this.getX()<en.getX()+20
									&&this.getY()-3>en.getY()&& this.getY()-3<en.getY()+30)
							{
								return true;
							}
							//右上点
							if(this.getX()+20>en.getX()&& this.getX()+20<en.getX()+20
									&&this.getY()-3>en.getY()&& this.getY()-3<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//左上点
							if(this.getX()>en.getX()&& this.getX()<en.getX()+30
									&&this.getY()-3>en.getY()&& this.getY()-3<en.getY()+20)
							{
								return true;
							}
							//右上点
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
				//自我向下
				//取出所有敌人坦克
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//敌人方向上或者下
						if(en.getD()==0||en.getD()==1)
						{
							//左下点
							if(this.getX()>en.getX()&& this.getX()<en.getX()+20
									&&this.getY()+33>en.getY()&& this.getY()+33<en.getY()+30)
							{
								return true;
							}
							//右下点
							if(this.getX()+20>en.getX()&& this.getX()+20<en.getX()+20
									&&this.getY()+33>en.getY()&& this.getY()+33<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//左下点
							if(this.getX()>en.getX()&& this.getX()<en.getX()+30
									&&this.getY()+33>en.getY()&& this.getY()+33<en.getY()+20)
							{
								return true;
							}
							//右下点
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
				//自我向左
				//取出所有敌人坦克
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//敌人方向上或者下
						if(en.getD()==0||en.getD()==1)
						{
							//左上点
							if(this.getX()-3>en.getX()&& this.getX()-3<en.getX()+20
									&&this.getY()>en.getY()&& this.getY()<en.getY()+30)
							{
								return true;
							}
							//左下点
							if(this.getX()-3>en.getX()&& this.getX()-3<en.getX()+20
									&&this.getY()+20>en.getY()&& this.getY()+20<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//左上点
							if(this.getX()-3>en.getX()&& this.getX()-3<en.getX()+30
									&&this.getY()>en.getY()&& this.getY()<en.getY()+20)
							{
								return true;
							}
							//左下点
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
				//自我向右
				//取出所有敌人坦克
				for (int i = 0; i < ets.size(); i++) {
					Tank en = ets.get(i);
					if(en!=this)
					{
						//敌人方向上或者下
						if(en.getD()==0||en.getD()==1)
						{
							//右上点
							if(this.getX()+30+3>en.getX()&& this.getX()+30+3<en.getX()+20
									&&this.getY()>en.getY()&& this.getY()<en.getY()+30)
							{
								return true;
							}
							//右下点
							if(this.getX()+30+3>en.getX()&& this.getX()+30+3<en.getX()+20
									&&this.getY()+20>en.getY()&& this.getY()+20<en.getY()+30)
							{
								return true;
							}
						}else
						{
							//右上点
							if(this.getX()+30+3>en.getX()&& this.getX()+30+3<en.getX()+30
									&&this.getY()>en.getY()&& this.getY()<en.getY()+20)
							{
								return true;
							}
							//右下点
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
			th.start();// 启动子弹线程
		}

	}

	// 坦克向上移动
	public void moveUp() {
		if(this.getY()-3>0&& !this.isToucher_Others())
		{
		this.y -= this.speed;
		}
	}

	// 坦克向右移动
	public void moveRight() {
		if(this.getX()+38<400&& !this.isToucher_Others())
		{
		this.x += this.speed;
		}
	}

	// 坦克向下移动
	public void moveDown() {
		if(this.getY()+60<300&& !this.isToucher_Others())
		{
		this.y += this.speed;
		}
	}

	// 坦克向左移动
	public void moveLeft() {
		if(this.getX()-3>0&& !this.isToucher_Others())
		{
		this.x -= this.speed;
		}
	}
	

}

// 我方坦克
class Hero extends Tank {
	public Hero(int x, int y) {
		super(x, y);
	}

	public Hero(int x, int y, int d) {
		this(x, y);
		this.setD(d);
	}
}

// 敌方坦克
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
				// 向上
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
				// 向下
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
				// 向左
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
				// 向右
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

			// 随机转向
			this.setD((int) (Math.random() * 4));

			// 判断敌人是否死亡
			if (this.isLive() == false) {
				// 死亡后退出线程
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

// 炸弹类
class Bomb {
	// 坐标
	int x, y;
	// 炸弹生命
	int life = 9;
	boolean isLive = true;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 减少生命值
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			isLive = false;
		}
	}
}

