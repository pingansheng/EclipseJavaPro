package ds;

import java.util.Scanner;

class Node
{
	public String data;
	public Node left;
	public Node right;
}
public class BiTree {
	static Scanner msc=new Scanner(System.in);
	
	/**
	 * ǰ������ݹ������
	 * @param root
	 */
	static Node initBiTree(Node root)
	{
		
		String c=msc.nextLine();
		if(" ".equals(c)){
			return null;
		}else{
			root=new Node();
			root.data=c;
			root.left=initBiTree(root.left);
			root.right=initBiTree(root.right);
			return root;
		}
	}
	
	/**
	 * ǰ�����
	 */
	static void visitTree(Node root,int level){
		if(root!=null){
			System.out.println(root.data+"λ�ڵ�"+level+"��");
			visitTree(root.left,level+1);
			visitTree(root.right,level+1);
		}
	}
	
	
	public static void main(String[] args) {
//		Node root=null;
//		System.out.println("��ʼ��������");
//		root=initBiTree(root);
//		visitTree(root,1);
		
		int n=44;
		int i=0;
		int x=44;
		while(x>0){
			i++;
			x=x&(x-1);
		}
		System.out.println(i);
	}
	
}

