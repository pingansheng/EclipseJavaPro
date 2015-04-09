/**
 * 2013年7月7日16:05:59
 * Java集合类练习
 */
package com.basement;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

public class JavaDataSet {

	public static void main(String[] args) throws IOException {
		// Array list
		/*
		 * List<person> al=new ArrayList<person>(); for(int i=1;i<=100;i++) {
		 * al.add(new person()); } for (person pe : al) {
		 * pe.ShowInfo(System.out); }
		 */

		// LinkedList
		LinkedList<person> ll = new LinkedList<person>();

		person[] persons = { new person(), new person("1", 12),
				new person("2", 21) };

		ll.add(persons[0]);
		ll.addFirst(persons[1]);
		ll.addLast(persons[2]);

		for (int i = 0; i < ll.size(); i++)
			((person) ll.get(i)).ShowInfo(System.out);

		// Vector
		Vector vc = new Vector();
		vc.add(persons[0]);
		vc.add(persons[1]);
		vc.add(persons[2]);

		// Stack
		Stack st = new Stack();
		st.add(persons[0]);
		st.add(persons[1]);
		st.add(persons[2]);

		// HashMap
		HashMap hm = new HashMap();

		for (int i = 0; i < persons.length; i++)
			hm.put(persons[i].name, persons[i]);

		hm.put("1", persons[0]);// 相同键会覆盖
		if (hm.containsKey("1")) {
			((person) hm.get("1")).ShowInfo(System.out);
		}

		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			((person) hm.get(it.next())).ShowInfo(System.out);
		}
		
		for(Map.Entry entry:System.getenv().entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
	}

}

class person {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		person.count = count;
	}

	String name;
	int age;
	static int count;

	person(String name, int age) {
		this.name = name;
		this.age = age;
		count++;
	}

	person() {
		Random rm = new Random();
		this.age = rm.nextInt(120);
		this.name = "person" + count;
		count++;
	}

	public void ShowInfo(PrintStream printStream) throws IOException {
		printStream.print("姓名：");
		printStream.print(this.name);
		printStream.print("，年龄：");
		printStream.print(this.age);
		printStream.print('\n');
	}
}
