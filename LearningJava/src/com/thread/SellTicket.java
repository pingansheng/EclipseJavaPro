package com.thread;

public class SellTicket {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketWindow tw1=new TicketWindow();
		Thread t1=new Thread(tw1);
		Thread t2=new Thread(tw1);
		Thread t3=new Thread(tw1);
		t1.start();
		t2.start();
		t3.start();
	}

}

class TicketWindow implements Runnable {
	private int num = 2000;

	@Override
	public void run() {


		while (true) {

			//if else Ҫ��֤ԭ����
			synchronized (this) {
				if (num > 0) {
					System.out.println(Thread.currentThread().getName()+"�����۳���" + num + "��Ʊ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					num--;
				} else {
					break;
				}
			}
			
		}
	}

}