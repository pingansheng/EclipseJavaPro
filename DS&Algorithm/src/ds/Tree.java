package ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * ���ṹ�����ʼ�� �����
 */
public class Tree {

	Scanner sc = new Scanner(System.in);
	List<Node> trees = null;

	// ˫�׺��ӱ�ʾ��

	/**
	 * ���ӽڵ�
	 * 
	 * @author pingansheng
	 *
	 */
	private class CNode {
		int nodeindex;
		CNode next=null;
	}

	/**
	 * ���ݽڵ�
	 * @author pingansheng
	 *
	 */
	private class Node {
		String data;
		int parent;
		CNode child=null;
	}

	/**
	 * ��ʼ����
	 */
	public synchronized List<Node> initTree() {
		trees = new ArrayList<Node>();
		Node root = new Node();
		root.parent = -1;
		info("��������ڵ�ֵ:");
		root.data = sc.next();
		trees.add(root);
		
		do {
			info("��ѡ��Ҫ�����ĸ��ڵ㣺");
			showNodes();
			int index=sc.nextInt();
			Node nowRoot=trees.get(index);
			info("������ڵ�ֵ:");
			
			//�������ݽڵ�
			String data=sc.next();
			Node node=new Node();
			node.data=data;
			node.parent=index;
			trees.add(node);
			
			//�������ӽڵ�
			CNode cnode=new CNode();
			cnode.nodeindex=trees.size()-1;
			cnode.next=null;
			
			//���뺢�ӽڵ��뵱ǰroot�ڵ�
			CNode lastCNode=getLastCNode(nowRoot);
			if (lastCNode == null) {
				nowRoot.child = cnode;
			} else {
				lastCNode.next = cnode;
			}
			
			info("�����ɹ�����ѡ��go ������quit�Ƴ�");
			if(sc.next().equals("quit")){
				break;
			}
		}while (true);
		info("��������");

		showNodes();
		return trees;
	}

	/**
	 * �õ����һ�����ӽڵ������
	 */
	private CNode getLastCNode(Node node){
		CNode cNode=node.child;
		
		while(cNode!=null && cNode.next!=null){
			cNode=cNode.next;
		}
		return cNode;
	}
	
	/**
	 * ��ʾ���нڵ�
	 */
	private void showNodes() {
		if (trees.size() == 0) {
			info("��ǰΪ����");
		} else {
			info("\n��ǰ�ڵ��б�");
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
