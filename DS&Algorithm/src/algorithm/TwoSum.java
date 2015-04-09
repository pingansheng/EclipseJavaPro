package algorithm;
import java.util.HashMap;

/**
 * Ѱ�Һ�Ϊ��ֵ��������
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
	 * hashӳ���㷨
	 */
	public static int[] twoSum1(int[] numbers, int target) {
		int len = numbers.length;
		assert (len >= 2); // �ж������Ƿ�Ϸ�
		int[] result = new int[2]; // ��¼���Ľ��

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // ���ڲ��롢����

		for (int i = 0; i < len; i++) {
			if (!map.containsKey(numbers[i])) { // �����������
				map.put(target - numbers[i], i);
			} else { // ʹ��else������else-if�жϣ���ʡʱ�䣬�ҵ��򷵻�
				int index = map.get(numbers[i]);
				result[0] = index + 1; // �±��С��ϵֱ��ȷ��
				result[1] = i + 1;
				break; // �˳�ѭ�������ؽ��
			}
		}
		return result;
	}

	/**
	 * ������ ˫ָ��ɨ��
	 */
	public static int[] twoSum2(int[] numbers, int target) {
		// ����
		int i = 0;
		int j = numbers.length - 1;
		Sort.part(i, j, numbers);

		//˫��ɨ��
		//�����1 2 3 4 5 
		while(i<j){
			if(numbers[i]+numbers[j]>target){
				//��Ҫ���ٺ�,��j��С�ķ����ƶ�
				j--;
			}else if(numbers[i]+numbers[j]<target){
				//��Ҫ���Ӻ�,��i���ķ����ƶ�
				i++;
			}else{
				return new int[]{i,j};
			}
		}
		return null;
	}
}
