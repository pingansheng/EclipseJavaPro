/*
 * 文件字节流
 * FileInputStream
 */
package com.io;

import java.io.*;

public class File_Input {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {

		File f = new File("files/data.dat");
		FileInputStream fis = null;
		// File无读写能力

		try {
			fis = new FileInputStream(f);
			byte[] bs = new byte[(int) f.length()];
			while (fis.read(bs) != -1)
				;
			String s = new String(bs);
			print(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void print(Object s) {
		System.out.println(String.valueOf(s));
	}
}
