package test;

import static org.junit.Assert.fail;

import java.util.Scanner;

public class TicTacToe {

	public int[][] nums;
	public TicTacToe() {
		nums=new int[3][3];
	}
	public int[][] getNums() {
		// TODO Auto-generated method stub
		return nums;
	}
	public boolean check(){
		int num[][]=this.getNums();
		int rowSum=0;
		int colSum=0;
		boolean flag=true;
		for (int i = 0; i < 3; i++) {
			rowSum=num[i][0]+num[i][1]+num[i][2];
			colSum=num[0][i]+num[1][i]+num[2][i];
			if(rowSum==0 || rowSum==3){
				System.out.println("失败！第"+(i+1)+"行出错！");
				flag= false;
			}
			if(colSum==0 || colSum==3){
				System.out.println("失败！第"+(i+1)+"列出错！");
				flag= false;
			}
		}
		int left=num[0][0]+num[1][1]+num[2][2];
		int right=num[0][2]+num[1][1]+num[2][0];
		if(left==0 || left==3){
			System.out.println("失败！左对角线出错！");
			flag= false;
		}
		if(right==0 || right==3){
			System.out.println("失败！右对角线出错！");
			flag= false;
		}
//		System.out.println("成功！");
		return flag;
	}
	public void setNums(int [][] a) {
		this.nums=a;
		
	}
	
	public static void main(String[] args) {
		Scanner mysc=new Scanner(System.in);
		TicTacToe ttt=new TicTacToe();
		int a[][]={{4,4,4},{4,4,4},{4,4,4}};
		ttt.setNums(a);
		for(int i=0; i<3 ;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.println("第"+(i+1)+"行，第"+(j+1)+"列是：");
				ttt.getNums()[i][j]=mysc.nextInt();
			}
			if(ttt.check()==true) continue;
			else break;
		}
	}
	
}













