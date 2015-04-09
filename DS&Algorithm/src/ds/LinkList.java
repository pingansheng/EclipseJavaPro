package ds;

/**
 * �������
 * 
 * @author pingansheng
 * 
 */
public class LinkList {
	
	/**
	 * ��ʼ��˫��ѭ���б�
	 * @param n
	 * @return
	 */
	public static TwoListNode initLoopTwoSideList(int n){
		TwoListNode head=new TwoListNode(-1);
		
		TwoListNode p=new TwoListNode(0);
		
		head.next=p;
		
		TwoListNode q;
		for(int i=1;i<n;i++)
		{
			q=new TwoListNode(0);
			p.next=q;
			q.prior=p;
			p=q;
		}
		//ѭ��ָ��
		p.next=head.next;
		head.next.prior=p;
		return head;
	}
	
	/**
	 * β�巨��������ѭ����������ͷ���
	 * 
	 * @param n
	 */
	public static ListNode createLoopLinkListFromTail(int n) {
		ListNode p = new ListNode(0);
		ListNode head = p;
		for (int i = 1; i < n; i++) {
			ListNode node = new ListNode(0);
			p.next = node;
			p = node;
		}
		//ѭ��ָ��
		p.next=head;
		return head;
	}

	
	/**
	 * ͷ�巨������������ͷ���
	 * 
	 * @param n
	 */
	public static ListNode createLinkListFromHead(int n) {
		ListNode head = new ListNode(-1);
		for (int i = 0; i < n; i++) {
			ListNode node = new ListNode(i + 1);
			// �½ڵ�ָ��ͷ���
			node.next = head;
			// ����ͷ���Ϊ�½ڵ�
			head = node;
		}
		return head;
	}

	/**
	 * β�巨������������ͷ���
	 * 
	 * @param n
	 */
	public static ListNode createLinkListFromTail(int n) {
		ListNode p = new ListNode(-1);
		ListNode head = p;
		for (int i = 0; i < n; i++) {
			ListNode node = new ListNode(i + 1);
			p.next = node;
			p = node;
		}
		return head;
	}

	/**
	 * ������Ų���Ԫ��
	 * 
	 * @param index
	 * @return
	 */
	public static ListNode getItemByIndex(ListNode head, int index) {
		if (index < 1)
			throw new RuntimeException("��ŷǷ�");
		ListNode p = head;
		// ͷ��β���ж�
		if (-1 != p.data) {
			index--;
		}
		while (index > 0 && p != null) {
			if (p.next != null) {
				p = p.next;
				index--;
			} else {
				break;
			}
		}
		return p;
	}

	/**
	 * ����ֵ����Ԫ��
	 * 
	 * @param index
	 * @return
	 */
	public static ListNode getItemByData(ListNode head, int data) {
		ListNode p = head;
		while (p != null) {
			if (p.data == data) {
				break;
			}
			if (null != p.next) {
				p = p.next;
			} else {
				break;
			}
		}
		return p;
	}

	/**
	 * ָ��λ�ò���Ԫ��
	 * 
	 * @param head
	 * @param index
	 * @param e
	 */
	public static ListNode insertNode(ListNode head, int index, ListNode e) {
		if (index < 1)
			throw new RuntimeException("��ŷǷ�");
		ListNode p = head;
		int i = index, j = 0;
		// ͷ��β���ж�
		if (-1 != p.data) {
			// ͷ�巨
			ListNode newHead = new ListNode(-1);
			newHead.next = p;
			head = p = newHead;
		}
		// β�巨
		index--;// �ҵ�ǰһ��

		while (index > 0 && p != null) {
			p = p.next;
			index--;
			j++;
		}
		if (i != j + 1 || p == null) {
			throw new RuntimeException("��ŷǷ�");
		}
		e.next = p.next;
		p.next = e;
		return head;
	}

	/**
	 * �������ɾ��Ԫ��
	 * 
	 * @param index
	 */
	public static ListNode deleteNodeByIndex(ListNode head, int index) {
		if (index < 1)
			throw new RuntimeException("��ŷǷ�");
		ListNode p = head;
		int i = index, j = 0;
		// ͷ��β���ж�
		if (-1 != p.data) {
			// ͷ�巨������ͷ���
			ListNode newHead = new ListNode(-1);
			newHead.next = p;
			p = newHead;
			head = newHead;
		}
		index--;
		while (index > 0 && p != null) {
			p = p.next;
			index--;
			j++;
		}
		if (i != j + 1 || p.next == null) {
			throw new RuntimeException("��ŷǷ�");
		}
		p.next = p.next.next;
		return head;
	}

	/**
	 * ����ֵɾ��Ԫ��
	 * 
	 * @param index
	 */
	public static ListNode deleteNodeByData(ListNode head, int data) {
		ListNode p = head;
		// ͷ��β���ж�
		if (-1 != p.data) {
			// ͷ�巨������ͷ���
			ListNode newHead = new ListNode(-1);
			newHead.next = p;
			p = newHead;
			head = newHead;
		}
		while (p != null) {
			if (p.next != null && p.next.data == data) {
				p.next = p.next.next;
			}
			p = p.next;
		}
		return head;
	}

	/**
	 * �����ҵ��м�ڵ� ����ָ��
	 * 
	 * @param list
	 * @return
	 */
	public static ListNode getMiddleNodeQuick(ListNode list) {
		ListNode p = list;
		ListNode s = list;
		while (s != null) {
			//�Ƚ��п���ָ��
			// ����ż��������
			if (s.next != null) {
				s = s.next.next;
			} else {
				break;
			}
			p = p.next;
		}
		return p;
	}

	/**
	 * ѭ����ӡ����Ԫ��
	 * 
	 * @param head
	 */
	public static void displayList(ListNode head) {
		ListNode p = head;
		while (p != null) {
			// ����ͷ���
			if (p.data != -1) {
				System.out.print(p.data + " ");
			}
			p = p.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ListNode list = createLinkListFromTail(2);
		displayList(list);

		// ��Ų���
		ListNode item = getItemByIndex(list, 2);
		System.out.println("��Ų��ң����2,�ҵ��ڵ�ֵ" + item.data);
		//
		// // ֵ����
		ListNode item2 = getItemByData(list, 2);
		System.out.println("ֵ���ң�ֵ2,�ҵ��ڵ�ֵ" + item2.data);

		// �������
		ListNode item3 = new ListNode(100);
		list = insertNode(list, 1, item3);
		displayList(list);

		// ɾ������

		displayList(list);
		System.out.println("mid=" + getMiddleNodeQuick(list).data);
	}
}
