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
	 * 栈的初始化
	 */
	public void initStack() {
		stack = new Vector<T>(INIT_CAPACITY, INCRE_CAPACITY);
		Stack_Size=0;
	}

	/**
	 * 压栈
	 * 
	 * @param obj
	 */
	public void push(T obj) {
		stack.addElement(obj);
		Stack_Size++;
	}

	/**
	 * 获得当前元素
	 * 
	 * @return
	 */
	public T getTopElement() {
		return stack.get(Stack_Size-1);
	}

	/**
	 * 弹栈
	 * @return
	 */
	public T pop() {
		if (Stack_Size == 0) {
			return null;
		}
		T res=stack.get(--Stack_Size);
		//已经自减完毕
		stack.removeElementAt(Stack_Size);
		return res;
	}
	
	/**
	 * 输出当前栈数据
	 */
	public void display(){
		System.out.println("当前栈数据**********");
		for (int i = Stack_Size-1; i >0; i--) {
			System.out.print(stack.get(i)+" ");
		}
	}
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return Stack_Size==0;
	}
}
