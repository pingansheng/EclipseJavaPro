package algorithm;
import java.util.Scanner;

import ds.ListNode;

public class YSF {

	private static Scanner sc;

	public static void main(String[] args) {
		/**
		 * 约瑟夫问题
		 */

		sc = new Scanner(System.in);
		System.out.println("*******约瑟夫问题求解*******");
		int n = 0, index = 0;
		try {
			System.out.println("请输入人数：");
			n = sc.nextInt();
			System.out.println("请输入第几人死亡：");
			index = sc.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
		}

		/*
		 * 构建循环链表
		 */
		// 第一个元素
		ListNode node = new ListNode(1);
		ListNode head = node;
		for (int i = 2; i <= n; i++) {
			node.next = new ListNode(i);
			node = node.next;
		}
		node.next = head;

		// 开始自杀
		int num = 1;
		while (!head.next.equals(head)) {
			num++;
			if (num == index) {
				// 自杀
				// 此时head指向上一个，首先输出死亡节点
				System.out.print(head.next.data + " ");
				// 然后删除死亡节点，此时head指向死亡节点上一个
				head.next = head.next.next;
				// 重置标识数
				num = 1;
			}
			// 此时head仍指向死亡节点的上一个，无论死亡后还是非死亡，head均指向下一个开始以顺应num=1,
			head = head.next;
		}
		System.out.println(head.data);
		System.out.println("*******约瑟夫问题结束*******\n");

	}
}
