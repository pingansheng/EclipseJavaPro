/**
 * java��������ز�����λ��������λ����
 */
package com.basement;

public class Binary {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		int a=2,b=1;
		//
		out(a & b);//��λ�룬��������1����1
		out(a | b);//��λ��,��һ����1����1
		out(a ^ b);//��λ���һ��0һ��1����1
		out(~a);   //��λȡ��
		out(a<<2);//�������Ʒ���λ���䣬��λ����
		out(a>>2);//�������ƣ���λ���������λ���䣬���÷���λ������ĸ�λ
		out(a>>>2);//�߼����ƣ���λ�������λ����
	}
	
	public static void out(Object s)
	{
		System.out.println(s.toString());
	}

}
