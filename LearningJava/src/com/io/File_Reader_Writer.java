/*
 * 文件字符流
 */
package com.io;

import java.io.*;

public class File_Reader_Writer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileReader fr = null;
		FileWriter fw=null;
		try {
			fr = new FileReader("files/data.dat");
			fw=new FileWriter("files/data_2.txt");
			
			char[] bs=new char[512];
			int n=0;
			while((n=fr.read(bs))!=-1)
			{
				//指定段
				fw.write(bs,0,n);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
