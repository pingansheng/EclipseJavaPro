package algorithm;
import ds.LinkList;
import ds.ListNode;

public class MagicCard {

	public static void main(String[] args) {
		/**
		 * 魔术师发牌问题
		 * 
		 * 魔术师手中有A、2、3……J、Q、K十三张黑桃扑克牌。 在表演魔术前，魔术师已经将他们按照一定的顺序叠放好
		 * （有花色的一面朝下）。魔术表演过程为：一开始，魔术师数1， 然后把最上面的那张牌翻过来，是黑桃A；然后将其放到桌面上；
		 * 第二次,魔术师数1、2；将第一张牌放到这些牌的最下面，将第二张牌翻转过来，正好是黑桃2；
		 * 第三次，魔术师数1、2、3；将第1、2张牌依次放到这些牌的最下面，
		 * 将第三张牌翻过来正好是黑桃3；……直到将所有的牌都翻出来为止。问原来牌的顺序是如何的。
		 */
		System.out.println("*******魔术师发牌问题*******");
		ListNode head = LinkList.createLoopLinkListFromTail(13);
		ListNode p = head;
		head.data = 1;
		int cardCount = 2;
		while (true) {

			// 第一个数两次
			for (int i = 0; i < cardCount; i++) {
				p = p.next;
				// 已经有牌了就到下一个位置
				if (p.data != 0) {
					i--;
				}
			}

			if (p.data == 0) {
				p.data = cardCount;
				cardCount++;
				if (cardCount == 14)
					break;
			}
		}

		p = head;
		System.out.print("序号" + p.data + " ");
		p = p.next;
		while (p.data != head.data) {
			System.out.print("序号" + p.data + " ");
			p = p.next;
		}
		System.out.println();
		System.out.println("*******魔术师发牌问题结束*******\n");
		System.out.println();

	}
}
