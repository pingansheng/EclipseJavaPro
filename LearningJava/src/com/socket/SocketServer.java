/*
 * Server端  监听端口9999
 * 接受Client发来的信息
 */
package com.socket;

import java.io.*;
import java.net.*;

public class SocketServer {

	public SocketServer() {

		try {
			// 9999端口监听
			ServerSocket ss = new ServerSocket(1989);

			String info = null;

			System.out.println("Listening……");
			Socket s = ss.accept();
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			InputStreamReader isr_col = new InputStreamReader(System.in);
			BufferedReader br_col = new BufferedReader(isr_col);
			while (true) {
				System.out.println("客户端：" + br.readLine().trim());
				System.out.println("请输入回应：");
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
