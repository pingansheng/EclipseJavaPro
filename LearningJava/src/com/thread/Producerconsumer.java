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
				//�ֿ��� �ͷŶ�����
				this.wait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FLAG++;
		System.out.println("����һ��,��ǰ" + FLAG + "����Ʒ");
		//������֮�� ���������߳�
		notify();
	}

	public synchronized void con() {
		try {

			while (FLAG == 0) {
				//�ֿ�û�в�Ʒ�� ���������߳�
				notify();
				//�ͷŶ��������ȴ������̻߳���
				wait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FLAG--;
		System.out.println("����һ����ʣ��" + FLAG + "����Ʒ");
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
