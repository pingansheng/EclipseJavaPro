/**
 * java二进制相关操作：位运算与移位运算
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
		out(a & b);//按位与，两个都是1才是1
		out(a | b);//按位或,有一个是1就是1
		out(a ^ b);//按位异或，一个0一个1才是1
		out(~a);   //按位取反
		out(a<<2);//算数左移符号位不变，低位补零
		out(a>>2);//算数右移，低位溢出，符号位不变，并用符号位补溢出的高位
		out(a>>>2);//逻辑右移，低位溢出，高位补零
	}
	
	public static void out(Object s)
	{
		System.out.println(s.toString());
	}

}
