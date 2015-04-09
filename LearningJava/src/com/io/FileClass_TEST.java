/*
 * File类基本用法
 */
package com.io;
import java.awt.Toolkit;
import java.io.*;
public class FileClass_TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File file=new File("files/data.dat");
		//文件大小 字节
		print(file.length());
		//文件绝对路径
		print(file.getAbsolutePath());
		print(file.canRead());
		
		//创建文件
		File f=new File("files/da.txt");
		if(!f.exists())
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO: handle exception
			}
			
		}
		else
		{
			print(f.getAbsoluteFile());
		}
		//创建文件夹
		
		File dir=new File("files/ff/ss");
		if(dir.isDirectory())
		{
			print("文件夹已存在");
		}
		else
		{
			dir.mkdirs();
		}
		
		//遍历文件夹文件
		File dir1=new File("files/");
		if(dir1.isDirectory())
		{
			File[] fs=dir1.listFiles();
			for (File ff : fs) {
				print(ff.getName());
			}
		}
	}
	
	public static void print(Object s)
	{
		System.out.println(String.valueOf(s));
	}
}
