package ds;

/**
 * 双向链表结点
 * @author pingansheng
 *
 */
public class TwoListNode {

	public TwoListNode prior;
	public int data;
	public TwoListNode next;

	public TwoListNode(int d) {
		this.data = d;
	}
}