package algorithm;
import java.util.HashMap;

/**
 * 寻找和为定值的两个数
 * 
 * @author pingansheng
 * 
 */
public class TwoSum {

	public static void main(String[] args) {

		int[] nums = new int[] { 2, 7, 11, 15 };
		int[] res = twoSum2(nums, 13);
		System.out.println(nums[res[0]] + " " + nums[res[1]]);

	}

	/**
	 * hash映射算法
	 */
	public static int[] twoSum1(int[] numbers, int target) {
		int len = numbers.length;
		assert (len >= 2); // 判断数据是否合法
		int[] result = new int[2]; // 记录最后的结果

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // 用于插入、查找

		for (int i = 0; i < len; i++) {
			if (!map.containsKey(numbers[i])) { // 新数据则插入
				map.put(target - numbers[i], i);
			} else { // 使用else，不用else-if判断，节省时间，找到则返回
				int index = map.get(numbers[i]);
				result[0] = index + 1; // 下标大小关系直接确定
				result[1] = i + 1;
				break; // 退出循环，返回结果
			}
		}
		return result;
	}

	/**
	 * 先排序 双指针扫描
	 */
	public static int[] twoSum2(int[] numbers, int target) {
		// 排序
		int i = 0;
		int j = numbers.length - 1;
		Sort.part(i, j, numbers);

		//双向扫描
		//排序后1 2 3 4 5 
		while(i<j){
			if(numbers[i]+numbers[j]>target){
				//需要减少和,则j向小的方向移动
				j--;
			}else if(numbers[i]+numbers[j]<target){
				//需要增加和,则i向大的方向移动
				i++;
			}else{
				return new int[]{i,j};
			}
		}
		return null;
	}
}
