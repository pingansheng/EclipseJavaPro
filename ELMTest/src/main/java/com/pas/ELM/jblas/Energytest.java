package com.pas.ELM.jblas;

public class Energytest {

	public static void main(String[] args) {
		try {
			MyELM m=new MyELM(6000, "sig",14, 1, 12);
			m.trainModel("file/energyTrainGAS");
			System.out.println("****************************************");
			System.out.println("训练结束，耗时"+m.getTrainingTime()+"Sec");
			System.out.println("训练总MSE="+m.getTrainingAccuracy());
			System.out.println("****************************************");
			System.out.println("");
			
//			m.test_model("file/test.txt",13);
//			System.out.println("****************************************");
//			System.out.println("测试结束，耗时"+m.getTestingTime());
//			System.out.println("测试MSE="+m.getTestingAccuracy());
//			System.out.println("****************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
