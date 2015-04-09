package algorithm;

import java.util.*;

public class Sort {
	private static int[] S;

	public static void main(String[] args) {
		int NUM = 10;
		S = new int[NUM];
		Random r = new Random();
		System.out.println("=======�������" + NUM + "������=======");
		long start = System.currentTimeMillis();
		for (int i = 0; i < NUM; i++) {
			S[i] = r.nextInt(10);
		}
		long end = System.currentTimeMillis();
		System.out.println("=======��ʱ" + (end - start) / 1000f + "����=======");

		int i = 0;
		int j = S.length - 1;

		// ˫�����
		System.out.println("=======��������˫��ɨ���������������������=======");
		start = System.currentTimeMillis();
		part(i, j, S);
		end = System.currentTimeMillis();
		System.out.println("=======��ʱ" + (end - start) / 1000f + "����=======");

		// �������
		System.out.println("=======�������򣬵���ɨ����������ɴ�С=======");
		start = System.currentTimeMillis();
		part_single_s2b(i, j, S);
		end = System.currentTimeMillis();
		 print();
		System.out.println("=======��ʱ" + (end - start) / 1000f + "����=======");

		// ˫�����
		System.out.println("=======��������˫��ɨ�����������С����=======");
		start = System.currentTimeMillis();
		part(i, j, S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======��ʱ" + (end - start) / 1000f + "����=======");

		// ð������
		System.out.println("=======ð��������С����=======");
		start = System.currentTimeMillis();
		Bubble_Sort(S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======��ʱ" + (end - start) / 1000f + "����=======");

		// ѡ������
		System.out.println("=======ѡ��������С����=======");
		start = System.currentTimeMillis();
		Select_Sort(S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======��ʱ" + (end - start) / 1000f + "����=======");

		// ��������
		System.out.println("=======����������С����=======");
		start = System.currentTimeMillis();
		Insert_Sort(S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======��ʱ" + (end - start) / 1000f + "����=======");
	}

	/**
	 * ð������
	 */
	public static void Bubble_Sort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					a[i] = a[i] + a[j];
					a[j] = a[i] - a[j];
					a[i] = a[i] - a[j];
				}
			}
		}
	}

	/**
	 * ѡ������
	 */
	public static void Select_Sort(int[] a) {
		int minIndex = 0;
		for (int i = 0; i < a.length - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[minIndex]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				a[i] ^= a[minIndex];
				a[minIndex] ^= a[i];
				a[i] ^= a[minIndex];
			}
		}
	}

	// ��������
	public static void Insert_Sort(int[] a) {
		int length = a.length;
		int insertval = 0;
		int index = 0;
		for (int i = 1; i < length; i++) {
			insertval = a[i];
			index = i - 1;
			/*
			 * 5 4 3 2 1
			 * 4 5 3 2 1
			 * 3 4 5 2 1
			 * 2 3 4 5 1
			 * 1 2 3 4 5
			 */
			while (index >= 0 && insertval < a[index]) {
				a[index + 1] = a[index];
				index--;
			}

			a[index + 1] = insertval;
		}

	}

	/**
	 * �������򡪡�˫����������ص�����key��λ��
	 */
	public static void part(int l, int r, int A[]) {

		if (l < r) {
			int i = l, j = r;
			int key = A[i];// ȡ����һ������Ϊkey���ڳ���һ����
			while (i < j) {
				// ѭ����ĩβ�ҵ���һ����keyС����ڵ���������j
				while (i < j && A[j] > key)
					j--;

				if (i < j)
					// �������keyС��������key���Ŀӣ��γ��¿�����j��ͬʱ��������i
					A[i++] = A[j];
				// ѭ����i��ʼ�ҵ���һ�������key����������i
				while (i < j && A[i] < key)
					i++;

				if (i < j)
					// ������������ϴεĿ�j�ڣ�ͬʱj���¸���
					A[j--] = A[i];
			}
			// ��keyֵ�����п�
			A[i] = key;
			part(l, i - 1, A);
			part(i + 1, r, A);
		}
	}

	/**
	 * �������򡪡��������� �ɴ�С
	 */
	public static void part_single_s2b(int l, int r, int[] A) {

		if (l < r) {
			int i = l - 1;
			int key = A[r];

			for (int j = l; j < r; j++) {
				if (A[j] > key) {
					i++;
					int tmp = A[i];
					A[i] = A[j];
					A[j] = tmp;
				}
			}
			A[r] = A[i + 1];
			A[i + 1] = key;
			part_single_s2b(l, i, A);
			part_single_s2b(i + 2, r, A);
		}

	}

	private static void print() {
		for (int j = 0; j < S.length; j++) {
			System.out.print(" " + S[j]);
		}
		System.out.println(" len=" + S.length);
	}
}
