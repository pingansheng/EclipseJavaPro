package algorithm;
import java.util.Scanner;

import ds.ListNode;

public class YSF {

	private static Scanner sc;

	public static void main(String[] args) {
		/**
		 * Լɪ������
		 */

		sc = new Scanner(System.in);
		System.out.println("*******Լɪ���������*******");
		int n = 0, index = 0;
		try {
			System.out.println("������������");
			n = sc.nextInt();
			System.out.println("������ڼ���������");
			index = sc.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
		}

		/*
		 * ����ѭ������
		 */
		// ��һ��Ԫ��
		ListNode node = new ListNode(1);
		ListNode head = node;
		for (int i = 2; i <= n; i++) {
			node.next = new ListNode(i);
			node = node.next;
		}
		node.next = head;

		// ��ʼ��ɱ
		int num = 1;
		while (!head.next.equals(head)) {
			num++;
			if (num == index) {
				// ��ɱ
				// ��ʱheadָ����һ����������������ڵ�
				System.out.print(head.next.data + " ");
				// Ȼ��ɾ�������ڵ㣬��ʱheadָ�������ڵ���һ��
				head.next = head.next.next;
				// ���ñ�ʶ��
				num = 1;
			}
			// ��ʱhead��ָ�������ڵ����һ���������������Ƿ�������head��ָ����һ����ʼ��˳Ӧnum=1,
			head = head.next;
		}
		System.out.println(head.data);
		System.out.println("*******Լɪ���������*******\n");

	}
}
