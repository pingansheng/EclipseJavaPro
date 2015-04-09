package ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 树结构及其初始化 多叉树
 */
public class Tree {

	Scanner sc = new Scanner(System.in);
	List<Node> trees = null;

	// 双亲孩子表示法

	/**
	 * 孩子节点
	 * 
	 * @author pingansheng
	 *
	 */
	private class CNode {
		int nodeindex;
		CNode next=null;
	}

	/**
	 * 数据节点
	 * @author pingansheng
	 *
	 */
	private class Node {
		String data;
		int parent;
		CNode child=null;
	}

	/**
	 * 初始化树
	 */
	public synchronized List<Node> initTree() {
		trees = new ArrayList<Node>();
		Node root = new Node();
		root.parent = -1;
		info("请输入根节点值:");
		root.data = sc.next();
		trees.add(root);
		
		do {
			info("请选择要操作的根节点：");
			showNodes();
			int index=sc.nextInt();
			Node nowRoot=trees.get(index);
			info("请输入节点值:");
			
			//创建数据节点
			String data=sc.next();
			Node node=new Node();
			node.data=data;
			node.parent=index;
			trees.add(node);
			
			//创建孩子节点
			CNode cnode=new CNode();
			cnode.nodeindex=trees.size()-1;
			cnode.next=null;
			
			//链入孩子节点与当前root节点
			CNode lastCNode=getLastCNode(nowRoot);
			if (lastCNode == null) {
				nowRoot.child = cnode;
			} else {
				lastCNode.next = cnode;
			}
			
			info("操作成功，请选择：go 继续；quit推出");
			if(sc.next().equals("quit")){
				break;
			}
		}while (true);
		info("操作结束");

		showNodes();
		return trees;
	}

	/**
	 * 得到最后一个孩子节点的引用
	 */
	private CNode getLastCNode(Node node){
		CNode cNode=node.child;
		
		while(cNode!=null && cNode.next!=null){
			cNode=cNode.next;
		}
		return cNode;
	}
	
	/**
	 * 显示所有节点
	 */
	private void showNodes() {
		if (trees.size() == 0) {
			info("当前为空树");
		} else {
			info("\n当前节点列表：");
			for (int i = 0; i < trees.size(); i++) {
				System.out.print("[" + i + "]" + trees.get(i).data);
				
				CNode cNode=trees.get(i).child;
				
				while(cNode!=null){
					System.out.print("->"+cNode.nodeindex);
					cNode=cNode.next;
				}
				info("");
			}
		}
		info("");
	}

	private void info(Object o) {
		System.out.println(o);
	}

	public static void main(String[] args) {
		new Tree().initTree();
	}
}
