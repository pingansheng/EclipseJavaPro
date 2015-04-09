package com.exception;


class E1 extends Exception{
	@Override
	public String toString() {
		return "E1!";
	}
}

class E2 extends Exception{
	@Override
	public String toString() {
		return "E2!";
	}
}

public class ExceptionTest {
	void f() throws E1{
		throw new E1();
	}
	
	void dispose() throws E2 {
		throw new E2();
	}
	public static void main(String[] args) {
		try{
			ExceptionTest et=new ExceptionTest();
			try {
				et.f();
			} finally{
				et.dispose();
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
