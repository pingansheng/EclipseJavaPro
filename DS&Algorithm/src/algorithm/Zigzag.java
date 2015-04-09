package algorithm;

import java.text.DecimalFormat;

public class Zigzag {
	
	public static void main(String[] args) {
		int N=8;
		
		int A[][]=new int[N][N];
		
		int M=2*N-1;
		int count=0;
		
		for (int i = 0; i < M; i++) {
			if(i<=M/2){
				if(i%2==0){
					//Å¼Êý ´Ó×óÍùÓÒ
					for(int j=0;j<i+1;j++){
						A[i-j][j]=count++;
					}
				}else{
					//ÆæÊý ´ÓÓÒÍù×ó
					for(int j=0;j<i+1;j++){
						A[j][i-j]=count++;
					}
				}
			}else{
				if(i%2==0){
					//Å¼Êý ´Ó×óÍùÓÒ
					for(int j=0;j<M-i;j++){
						A[N-j-1][i+j-N+1]=count++;
					}
				}else{
					//ÆæÊý ´ÓÓÒÍù×ó
					for(int j=0;j<M-i;j++){
						A[i+j-N+1][N-j-1]=count++;
					}
				}
			}
		}
		
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				System.out.print(new DecimalFormat("00").format(A[i][j])+"  ");
			}
			System.out.println();
		}
	}
}
