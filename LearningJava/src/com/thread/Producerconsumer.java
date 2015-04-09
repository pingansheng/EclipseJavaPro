package com.thread;
public class Producerconsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Store s = new Store();
		ProThread pt = new ProThread(s);
		ConThread ct = new ConThread(s);
		Thread p1 = new Thread(pt);
		Thread p2 = new Thread(pt);
		Thread p3 = new Thread(pt);
		Thread p4 = new Thread(pt);
		Thread c1 = new Thread(ct);
		Thread c2 = new Thread(ct);
		Thread c3 = new Thread(ct);

		p1.start();
		p2.start();
		p3.start();
		// p4.start();
		c1.start();
		c2.start();
		c3.start();
	}

}

class Store {
	private static Integer FLAG = 0;

	public synchronized void pro() {
		try {
			while (FLAG == 10) {
				//仓库满 释放对象锁
				this.wait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FLAG++;
		System.out.println("生产一个,当前" + FLAG + "个产品");
		//生产完之后 唤醒消费线程
		notify();
	}

	public synchronized void con() {
		try {

			while (FLAG == 0) {
				//仓库没有产品了 唤醒生产线程
				notify();
				//释放对象锁，等待生产线程唤醒
				wait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FLAG--;
		System.out.println("消费一个，剩余" + FLAG + "个产品");
	}

}

class ProThread implements Runnable {

	private Store s;

	public ProThread(Store s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (true) {
			s.pro();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class ConThread implements Runnable {
	private Store s;

	public ConThread(Store s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (true) {
			s.con();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
