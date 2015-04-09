package com.io;

import java.io.*;

public class Img_Copy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//先读内存 然后写入文件
		//二进制文件只能用字节流
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis=new FileInputStream("img/1.jpg");
			fos=new FileOutputStream("files/22.jpg");
			if(new File("files/22.jpg").exists())
			{
				System.out.println("Already Done");
				return;
			}
			byte[] buf=new byte[1024];
			int n=0;//实际读取到的字节数
			while((n=fis.read(buf))!=-1)
			{
				//输出指定文件
				fos.write(buf);
			}
			if(new File("files/22.jpg").exists())
			{
				System.out.println("Done");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
