/*
 * �̳�Thread�����߳�
 */
package com.thread;

import javax.xml.stream.events.StartDocument;

public class ThreadTest{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dog dog=new dog();
		cat cat=new cat();
		//�����߳�
		cat.start();
		Thread t=new Thread(dog);
		t.start();//����run��������
	}

}

class cat extends Thread {

	int times=0;
	// ��дRun����
	public void run() {
		while (true) {
			try {
				// ����һ��
				Thread.sleep(2000);// sleep�����߳̽�������״̬ ���ͷ���Դ
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("hello world,cat."+times);
			if(times==10)
				break;
		}
	}
}

//java ֻ�ܵ��̳У����Կ�������ʵ��runnable�����ʵ���߳�
class dog implements Runnable {

	int times=0;
	// ��дRun����
	public void run() {
		while (true) {
			try {
				// ���߶���
				Thread.sleep(1000);// sleep�����߳̽�������״̬ ���ͷ���Դ
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("hello world,dog"+times);
			if(times==10)
				break;
		}
	}
}