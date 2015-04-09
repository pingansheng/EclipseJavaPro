package algorithm;
import ds.LinkList;
import ds.ListNode;

public class MagicCard {

	public static void main(String[] args) {
		/**
		 * ħ��ʦ��������
		 * 
		 * ħ��ʦ������A��2��3����J��Q��Kʮ���ź����˿��ơ� �ڱ���ħ��ǰ��ħ��ʦ�Ѿ������ǰ���һ����˳����ź�
		 * ���л�ɫ��һ�泯�£���ħ�����ݹ���Ϊ��һ��ʼ��ħ��ʦ��1�� Ȼ���������������Ʒ��������Ǻ���A��Ȼ����ŵ������ϣ�
		 * �ڶ���,ħ��ʦ��1��2������һ���Ʒŵ���Щ�Ƶ������棬���ڶ����Ʒ�ת�����������Ǻ���2��
		 * �����Σ�ħ��ʦ��1��2��3������1��2�������ηŵ���Щ�Ƶ������棬
		 * ���������Ʒ����������Ǻ���3������ֱ�������е��ƶ�������Ϊֹ����ԭ���Ƶ�˳������εġ�
		 */
		System.out.println("*******ħ��ʦ��������*******");
		ListNode head = LinkList.createLoopLinkListFromTail(13);
		ListNode p = head;
		head.data = 1;
		int cardCount = 2;
		while (true) {

			// ��һ��������
			for (int i = 0; i < cardCount; i++) {
				p = p.next;
				// �Ѿ������˾͵���һ��λ��
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
		System.out.print("���" + p.data + " ");
		p = p.next;
		while (p.data != head.data) {
			System.out.print("���" + p.data + " ");
			p = p.next;
		}
		System.out.println();
		System.out.println("*******ħ��ʦ�����������*******\n");
		System.out.println();

	}
}
