package innerclass;

interface Counter {
	int next();
}

public class InnerTest {

	Counter getC1() {
		class LCounter implements Counter {

			public LCounter() {
				System.out.println("LC1");
			}

			@Override
			public int next() {
				System.out.println("局部内部类");
				return 1;
			}

		}
		class LCounte1r implements Counter {

			public LCounte1r() {
				System.out.println("LC1");
			}

			@Override
			public int next() {
				System.out.println("局部内部类");
				return 1;
			}

		}

		return new LCounter();
	}

	Counter getC2() {
		return new Counter() {
			{
				System.out.println("LC2");
			}
			public int next() {
				System.out.println("匿名内部类");
				return 1;
			}

		};

	}
	public static void main(String[] args) {
		InnerTest it=new InnerTest();
		Counter c1=it.getC1(),c2=it.getC2();
		c1.next();
		c2.next();
	}

}
