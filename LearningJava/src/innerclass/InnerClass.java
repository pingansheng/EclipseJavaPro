package innerclass;

abstract class MyClass{
		
		public MyClass() {
			System.out.println("MyClass��ʼ��");
		}
		public abstract void get();
	}
public class InnerClass {
	
	
	
	public MyClass test()
	{
		/**
		 * ���������̳�һ�������ʵ��һ���ӿ�
		 */
		return new MyClass(){
			public void get(){
				System.out.println("GET");
			}
		};
	}
	
	
	public static void main(String[] args) {
		new InnerClass().test().get();
	}
}
