/*
 * 继承Thread开发线程
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
		//启动线程
		cat.start();
		Thread t=new Thread(dog);
		t.start();//导致run函数运行
	}

}

class cat extends Thread {

	int times=0;
	// 重写Run函数
	public void run() {
		while (true) {
			try {
				// 休眠一秒
				Thread.sleep(2000);// sleep导致线程进入阻塞状态 并释放资源
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

//java 只能单继承，所以可以运用实现runnable借口来实现线程
class dog implements Runnable {

	int times=0;
	// 重写Run函数
	public void run() {
		while (true) {
			try {
				// 休眠二秒
				Thread.sleep(1000);// sleep导致线程进入阻塞状态 并释放资源
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