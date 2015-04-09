package com.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Buffer_Reader_Writer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FileReader fr=null;
		BufferedReader br=null;
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			fr=new FileReader("files/data.dat");
			br=new BufferedReader(fr);
			fw=new FileWriter("files/data_1.txt");
			bw=new BufferedWriter(fw);
			String sb=new String();
			while((sb=br.readLine())!=null)
			{
				bw.write(sb+System.getProperty("line.separator"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
			try {
				bw.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
