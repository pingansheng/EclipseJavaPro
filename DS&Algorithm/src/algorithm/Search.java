package algorithm;

public class Search {

	// 二分查找 返回数组下标
	public static int binarySearch(int A[], int num) {
		int i = 0;
		int j = A.length - 1;

		while (i<=j) {
			int flag = (i + j) / 2;
			if (A[flag] == num)
				return flag;
			else if (A[flag] > num) {
				j = flag-1;
			} else {
				i = flag+1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int A[]={2,2,3,4,5,6,12,111,123,1231};
		int ans=binarySearch(A, 2);
		System.out.println(ans);
	}
}
