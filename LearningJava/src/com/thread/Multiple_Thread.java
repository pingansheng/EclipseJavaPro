/*
 * ���̲߳���
 */
package com.thread;
import java.util.*;
public class Multiple_Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		cal cal=new cal(10);
		count count=new count();
		
		Thread t1=new Thread(cal);
		Thread t2=new Thread(count);
		
		t1.start();
		t2.start();
		
	}

}

class cal implements Runnable{

	int n=0;
	int res=0;
	int times;
	public cal(int n)
	{
		this.n=n;
	}
	public void run() {
		
		while(true)
		{
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			res+=(++times);
			System.out.println("��ǰ�����"+res);			
			if(times==n)
			{
				System.out.println("�������"+res);
				break;
			}
		}
	}
	
}

class count implements Runnable
{
	int n=0;
	int times=0;
	public void run() {
		
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("����һ���̣߳����������"+times+"��hello world");
			
			if(times==20)
			{
				System.out.println("����һ���̣߳�������");
				break;
			}
				
		}
	}
}