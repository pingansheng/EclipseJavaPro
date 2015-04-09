package ds;

/**
 * 链表操作
 * 
 * @author pingansheng
 * 
 */
public class LinkList {
	
	/**
	 * 初始化双向循环列表
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
		//循环指向
		p.next=head.next;
		head.next.prior=p;
		return head;
	}
	
	/**
	 * 尾插法建立单向循环链表，返回头结点
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
		//循环指向
		p.next=head;
		return head;
	}

	
	/**
	 * 头插法建立链表，返回头结点
	 * 
	 * @param n
	 */
	public static ListNode createLinkListFromHead(int n) {
		ListNode head = new ListNode(-1);
		for (int i = 0; i < n; i++) {
			ListNode node = new ListNode(i + 1);
			// 新节点指向头结点
			node.next = head;
			// 更新头结点为新节点
			head = node;
		}
		return head;
	}

	/**
	 * 尾插法建立链表，返回头结点
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
	 * 按照序号查找元素
	 * 
	 * @param index
	 * @return
	 */
	public static ListNode getItemByIndex(ListNode head, int index) {
		if (index < 1)
			throw new RuntimeException("序号非法");
		ListNode p = head;
		// 头插尾插判断
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
	 * 按照值查找元素
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
	 * 指定位置插入元素
	 * 
	 * @param head
	 * @param index
	 * @param e
	 */
	public static ListNode insertNode(ListNode head, int index, ListNode e) {
		if (index < 1)
			throw new RuntimeException("序号非法");
		ListNode p = head;
		int i = index, j = 0;
		// 头插尾插判断
		if (-1 != p.data) {
			// 头插法
			ListNode newHead = new ListNode(-1);
			newHead.next = p;
			head = p = newHead;
		}
		// 尾插法
		index--;// 找到前一个

		while (index > 0 && p != null) {
			p = p.next;
			index--;
			j++;
		}
		if (i != j + 1 || p == null) {
			throw new RuntimeException("序号非法");
		}
		e.next = p.next;
		p.next = e;
		return head;
	}

	/**
	 * 按照序号删除元素
	 * 
	 * @param index
	 */
	public static ListNode deleteNodeByIndex(ListNode head, int index) {
		if (index < 1)
			throw new RuntimeException("序号非法");
		ListNode p = head;
		int i = index, j = 0;
		// 头插尾插判断
		if (-1 != p.data) {
			// 头插法加入新头结点
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
			throw new RuntimeException("序号非法");
		}
		p.next = p.next.next;
		return head;
	}

	/**
	 * 按照值删除元素
	 * 
	 * @param index
	 */
	public static ListNode deleteNodeByData(ListNode head, int data) {
		ListNode p = head;
		// 头插尾插判断
		if (-1 != p.data) {
			// 头插法加入新头结点
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
	 * 快速找到中间节点 快速指针
	 * 
	 * @param list
	 * @return
	 */
	public static ListNode getMiddleNodeQuick(ListNode list) {
		ListNode p = list;
		ListNode s = list;
		while (s != null) {
			//先进行快速指针
			// 奇数偶数有区别
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
	 * 循环打印链表元素
	 * 
	 * @param head
	 */
	public static void displayList(ListNode head) {
		ListNode p = head;
		while (p != null) {
			// 过滤头结点
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

		// 序号查找
		ListNode item = getItemByIndex(list, 2);
		System.out.println("序号查找：序号2,找到节点值" + item.data);
		//
		// // 值查找
		ListNode item2 = getItemByData(list, 2);
		System.out.println("值查找：值2,找到节点值" + item2.data);

		// 插入测试
		ListNode item3 = new ListNode(100);
		list = insertNode(list, 1, item3);
		displayList(list);

		// 删除测试

		displayList(list);
		System.out.println("mid=" + getMiddleNodeQuick(list).data);
	}
}
