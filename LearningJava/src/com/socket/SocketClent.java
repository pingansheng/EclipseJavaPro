/*
 * 客户端Socket
 */
package com.socket;

import java.io.*;
import java.net.*;

public class SocketClent {

	public SocketClent() {
		try {
			Socket s = new Socket("127.0.0.1", 1989);

			// 读取服务器信息
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			InputStreamReader isr_col = new InputStreamReader(System.in);
			BufferedReader br_col = new BufferedReader(isr_col);

			while (true) {
					System.out.println("请输入对服务器的回应：");
					pw.println(br_col.readLine().trim());

					System.out.println("服务器：" + br.readLine().trim());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SocketClent sc = new SocketClent();
	}

}
