package algorithm;

import java.util.*;

public class Sort {
	private static int[] S;

	public static void main(String[] args) {
		int NUM = 10;
		S = new int[NUM];
		Random r = new Random();
		System.out.println("=======随机数组" + NUM + "个数据=======");
		long start = System.currentTimeMillis();
		for (int i = 0; i < NUM; i++) {
			S[i] = r.nextInt(10);
		}
		long end = System.currentTimeMillis();
		System.out.println("=======耗时" + (end - start) / 1000f + "毫秒=======");

		int i = 0;
		int j = S.length - 1;

		// 双向检索
		System.out.println("=======快速排序，双向扫描快速排序，生成有序数列=======");
		start = System.currentTimeMillis();
		part(i, j, S);
		end = System.currentTimeMillis();
		System.out.println("=======耗时" + (end - start) / 1000f + "毫秒=======");

		// 单向检索
		System.out.println("=======快速排序，单向扫描快速排序，由大到小=======");
		start = System.currentTimeMillis();
		part_single_s2b(i, j, S);
		end = System.currentTimeMillis();
		 print();
		System.out.println("=======耗时" + (end - start) / 1000f + "毫秒=======");

		// 双向检索
		System.out.println("=======快速排序，双向扫描快速排序，由小到大=======");
		start = System.currentTimeMillis();
		part(i, j, S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======耗时" + (end - start) / 1000f + "毫秒=======");

		// 冒泡排序
		System.out.println("=======冒泡排序，由小到大=======");
		start = System.currentTimeMillis();
		Bubble_Sort(S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======耗时" + (end - start) / 1000f + "毫秒=======");

		// 选择排序
		System.out.println("=======选择排序，由小到大=======");
		start = System.currentTimeMillis();
		Select_Sort(S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======耗时" + (end - start) / 1000f + "毫秒=======");

		// 插入排序
		System.out.println("=======插入排序，由小到大=======");
		start = System.currentTimeMillis();
		Insert_Sort(S);
		end = System.currentTimeMillis();
		// print();
		System.out.println("=======耗时" + (end - start) / 1000f + "毫秒=======");
	}

	/**
	 * 冒泡排序
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
	 * 选择排序
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

	// 插入排序
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
	 * 快速排序——双向检索，返回调整后key的位置
	 */
	public static void part(int l, int r, int A[]) {

		if (l < r) {
			int i = l, j = r;
			int key = A[i];// 取出第一个数作为key，挖出第一个坑
			while (i < j) {
				// 循环从末尾找到第一个比key小或等于的数的索引j
				while (i < j && A[j] > key)
					j--;

				if (i < j)
					// 将这个比key小的数填入key处的坑，形成新坑索引j，同时增加索引i
					A[i++] = A[j];
				// 循环从i开始找到第一个打大于key的数字索引i
				while (i < j && A[i] < key)
					i++;

				if (i < j)
					// 将这个数填入上次的坑j内，同时j向下更新
					A[j--] = A[i];
			}
			// 将key值填入中坑
			A[i] = key;
			part(l, i - 1, A);
			part(i + 1, r, A);
		}
	}

	/**
	 * 快速排序——单向搜索 由大到小
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
