/*
 * FileOutputStream
 */
package com.io;
import java.io.*;
public class File_Output {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f=new File("files/outsteam.txt");
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(f);
			String s="Hello,Java\nHello,World";
			fos.write(s.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
