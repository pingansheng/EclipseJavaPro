package com.thread;

public class Thread_Warning{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dog dog=new dog();
		cat cat=new cat();
		//�����߳�
		cat.start();
		//cat.start();//һ���̶߳��󲻿���������
		Thread t=new Thread(dog);
		Thread t1=new Thread(dog);
		t.start();//����run��������
		t1.start();//���� �̶߳���ͬ
	}

}

