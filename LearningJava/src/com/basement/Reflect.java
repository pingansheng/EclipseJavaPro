/*
 * 反射机制
 * 2013年7月14日 22:29:21
 */
package com.basement;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.lang.reflect.*;
public class Reflect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReflectGet<person> rg=new ReflectGet<person>(new person());
		System.out.println(rg.getClassName());
		System.out.println(rg.getMethodCount());
		rg.showMethodsName();
	}

}

class ReflectGet<T>
{
	private T tar;
	
	public ReflectGet(T tar1)
	{
		this.tar=tar1;
	}
	
	public String getClassName()
	{
		return tar.getClass().getName();
	}
	
	public int  getMethodCount()
	{
		return tar.getClass().getDeclaredMethods().length;
	}
	
	public void showMethodsName()
	{
		Method [] ms=tar.getClass().getDeclaredMethods();
		for (Method method : ms) {
			System.out.println(method.getName());
		}
	}
}



