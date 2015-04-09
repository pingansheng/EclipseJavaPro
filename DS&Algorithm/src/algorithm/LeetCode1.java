package algorithm;

/**
 * Reverse Words in a String
 * 
 * @author pingansheng
 *
 */
public class LeetCode1 {
	public static void main(String[] args) {
		System.out.println(reverseWords("   a   b "));
	}

	public static String reverseWords(String s) {
		char[] chars=s.toCharArray();
		int k=0;
		StringBuffer sb=new StringBuffer();
		for (int i = chars.length-1; i >=0; i--) {
			if(chars[i]==' '){
				k=i+1;
				while(k<=chars.length-1 && chars[k]!=' ') 
					{sb.append(chars[k]);k++;}
				if(i!=0 && k!=i+1)sb.append(" ");//k+i²âÊÔÑ­»·ÊÇ·ñÖ´ÐÐ
			}else if(i==0){
				k=0;
				while(k<chars.length && chars[k]!=' ') 
				{sb.append(chars[k]);k++;}
			}
		}
		
		return sb.toString().trim();
	}
}
