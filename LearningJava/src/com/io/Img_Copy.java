package com.io;

import java.io.*;

public class Img_Copy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//�ȶ��ڴ� Ȼ��д���ļ�
		//�������ļ�ֻ�����ֽ���
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
			int n=0;//ʵ�ʶ�ȡ�����ֽ���
			while((n=fis.read(buf))!=-1)
			{
				//���ָ���ļ�
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
