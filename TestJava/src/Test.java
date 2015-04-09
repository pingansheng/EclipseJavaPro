import java.util.Formatter;


public class Test {
	
	public void Do()
	{
		System.out.println(""+String.valueOf(this)+"");
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//		Formatter fm=new Formatter(System.out);
//		System.out.println(String.format("%05X", 121));
		
		char[] a=new char[1];
		System.out.println(a.getClass().isPrimitive());
	}
}
