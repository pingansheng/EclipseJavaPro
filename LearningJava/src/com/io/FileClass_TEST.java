/*
 * File������÷�
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
		//�ļ���С �ֽ�
		print(file.length());
		//�ļ�����·��
		print(file.getAbsolutePath());
		print(file.canRead());
		
		//�����ļ�
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
		//�����ļ���
		
		File dir=new File("files/ff/ss");
		if(dir.isDirectory())
		{
			print("�ļ����Ѵ���");
		}
		else
		{
			dir.mkdirs();
		}
		
		//�����ļ����ļ�
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
