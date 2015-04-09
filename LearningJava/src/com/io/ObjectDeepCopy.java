package com.io;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 深度复制 串行化
 * 
 * @author pingansheng
 * 
 */
public class ObjectDeepCopy {
	public static void main(String[] args) throws Exception {
		Car car = new Car("车1");
		Wheel w = new Wheel("轮子1");
		car.getWheels().add(w);
		System.out.println("车1:" + car.hashCode());
		System.out.println("轮子1:" + w.hashCode());

		// ------深度复制-------
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			 oos= new ObjectOutputStream(bos);

			oos.writeObject(car);
			oos.close();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes=bos.toByteArray();
		ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
		ObjectInputStream ois=new ObjectInputStream(bis);
		Car car2=(Car) ois.readObject();
		ois.close();
		bis.close();
		
		System.out.println("新车："+car2.hashCode());
		System.out.println("轮子："+car2.getWheels().get(0).hashCode());
	}
}

class Car implements Serializable{
	private String name;
	private List<Wheel> wheels = new ArrayList<Wheel>(0);

	public List<Wheel> getWheels() {
		return wheels;
	}

	public void setWheels(List<Wheel> wheels) {
		this.wheels = wheels;
	}

	public Car(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Wheel implements Serializable{
	private String name;

	public Wheel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}