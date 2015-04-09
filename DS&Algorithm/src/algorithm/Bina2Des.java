package algorithm;
import ds.Stack;

public class Bina2Des {

	public static void main(String[] args) throws Exception {
		/**
		 * ������ת��Ϊ10����
		 * 
		 * @throws Exception
		 */
		String binString="11111111";
		
		char[] binChar = binString.toCharArray();
		Stack<Character> binSack = new Stack<Character>();
		for (char c : binChar) {
			if (c != '1' && c != '0') {
				throw new Exception("�Ƕ�������");
			}
			binSack.push(c);
		}

		int i = 0;
		long sum = 0;
		while (!binSack.isEmpty()) {
			if (binSack.pop() == '1') {
				sum += Math.pow(2, i);
			}
			i++;
		}
		
		System.out.println(sum);
	}
}
