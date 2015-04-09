package com.thread;

public class Thread_Warning{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dog dog=new dog();
		cat cat=new cat();
		//启动线程
		cat.start();
		//cat.start();//一个线程对象不可启动两次
		Thread t=new Thread(dog);
		Thread t1=new Thread(dog);
		t.start();//导致run函数运行
		t1.start();//可以 线程对象不同
	}

}

