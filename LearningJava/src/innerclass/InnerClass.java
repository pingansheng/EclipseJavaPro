package innerclass;

abstract class MyClass{
		
		public MyClass() {
			System.out.println("MyClass初始化");
		}
		public abstract void get();
	}
public class InnerClass {
	
	
	
	public MyClass test()
	{
		/**
		 * 匿名类必须继承一个父类或实现一个接口
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
