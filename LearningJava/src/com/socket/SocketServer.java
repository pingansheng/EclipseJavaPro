/*
 * Server��  �����˿�9999
 * ����Client��������Ϣ
 */
package com.socket;

import java.io.*;
import java.net.*;

public class SocketServer {

	public SocketServer() {

		try {
			// 9999�˿ڼ���
			ServerSocket ss = new ServerSocket(1989);

			String info = null;

			System.out.println("Listening����");
			Socket s = ss.accept();
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			InputStreamReader isr_col = new InputStreamReader(System.in);
			BufferedReader br_col = new BufferedReader(isr_col);
			while (true) {
				System.out.println("�ͻ��ˣ�" + br.readLine().trim());
				System.out.println("�������Ӧ��");
				pw.println(br_col.readLine().trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SocketServer a = new SocketServer();
	}

}
