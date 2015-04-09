package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TicTacToeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before¡­¡­");
	    
	}

	@Test
	public void test() {
		TicTacToe p =new TicTacToe();
		int a[][] ={{1,1,1},{0,1,0},{0,0,1}};
		p.setNums(a);
		assertEquals(false, p.check());
		
		int b[][] ={{0,0,0},{0,1,0},{0,0,1}};
		p.setNums(b);
		assertEquals(false, p.check());
		
		int c[][] ={{1,0,1},{1,1,1},{0,0,1}};
		p.setNums(c);
		assertEquals(false, p.check());
		
		int d[][] ={{1,0,1},{0,0,0},{0,0,1}};
		p.setNums(d);
		assertEquals(false, p.check());
		
		int e[][] ={{1,0,1},{0,1,0},{1,1,1}};
		p.setNums(e);
		assertEquals(false, p.check());
		
		int f[][] ={{1,0,1},{0,1,0},{0,0,0}};
		p.setNums(f);
		assertEquals(false, p.check());
		
		int g[][] ={{1,0,1},{1,1,0},{1,0,1}};
		p.setNums(g);
		assertEquals(false, p.check());
		
		int h[][] ={{0,0,1},{0,1,0},{0,0,1}};
		p.setNums(h);
		assertEquals(false, p.check());
		
		int i[][] ={{0,1,0},{0,1,1},{1,1,0}};
		p.setNums(i);
		assertEquals(false, p.check());
		
		int j[][] ={{1,0,0},{0,0,1},{1,0,0}};
		p.setNums(j);
		assertEquals(false, p.check());
		
		int k[][] ={{0,1,1},{1,0,1},{0,1,1}};
		p.setNums(k);
		assertEquals(false, p.check());
		
		int l[][] ={{1,0,0},{0,1,0},{1,0,0}};
		p.setNums(l);
		assertEquals(false, p.check());
		
		int m[][] ={{0,1,1},{1,0,0},{0,1,0}};
		p.setNums(m);
		assertEquals(false, p.check());
		
		int q[][] ={{1,0,1},{1,1,0},{0,1,1}};
		p.setNums(q);
		assertEquals(false, p.check());
		
		int n[][] ={{0,1,1},{1,1,0},{1,0,0}};
		p.setNums(n);
		assertEquals(false, p.check());
		
		int r[][] ={{0,1,0},{1,0,0},{1,1,0}};
		p.setNums(n);
		assertEquals(false, p.check());
		
		int o[][] ={{1,0,0},{0,1,1},{1,0,0}};
		p.setNums(o);
		assertEquals(true, p.check());
	}

}
