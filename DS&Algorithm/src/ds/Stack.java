package ds;

import java.util.Vector;

public class Stack<T> {
	public int Stack_Size;
	private Vector<T> stack;
	private int INIT_CAPACITY = 5;
	private int INCRE_CAPACITY = 5;

	public Stack() {
		initStack();
	}
	/**
	 * ջ�ĳ�ʼ��
	 */
	public void initStack() {
		stack = new Vector<T>(INIT_CAPACITY, INCRE_CAPACITY);
		Stack_Size=0;
	}

	/**
	 * ѹջ
	 * 
	 * @param obj
	 */
	public void push(T obj) {
		stack.addElement(obj);
		Stack_Size++;
	}

	/**
	 * ��õ�ǰԪ��
	 * 
	 * @return
	 */
	public T getTopElement() {
		return stack.get(Stack_Size-1);
	}

	/**
	 * ��ջ
	 * @return
	 */
	public T pop() {
		if (Stack_Size == 0) {
			return null;
		}
		T res=stack.get(--Stack_Size);
		//�Ѿ��Լ����
		stack.removeElementAt(Stack_Size);
		return res;
	}
	
	/**
	 * �����ǰջ����
	 */
	public void display(){
		System.out.println("��ǰջ����**********");
		for (int i = Stack_Size-1; i >0; i--) {
			System.out.print(stack.get(i)+" ");
		}
	}
	
	/**
	 * �Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return Stack_Size==0;
	}
}
