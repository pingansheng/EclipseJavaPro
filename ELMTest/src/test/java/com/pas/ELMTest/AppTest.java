package com.pas.ELMTest;

import org.junit.Assert;
import org.junit.Test;

import com.pas.ELM.jblas.MyELM;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void testApp() {
		double mse = 10;
		int numOfNeur=0;
		int num=5000;
		int step=2;
		for (int i = 4; i < num; i += step) {
			try {
				MyELM m = new MyELM(i, "sig", 4, 1, 6);
				m.trainModel("file/train.txt");
				m.setShowDetailInfo(false);
				if (mse > m.getTrainingAccuracy()) {
					mse = m.getTrainingAccuracy();
					numOfNeur=i;
				}
				
				System.out.println("测试进度："+String.format("%.2f", (i+0.0f)/num*100)+"%,MSE="+m.getTrainingAccuracy());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("结束：最小MSE="+mse+"，神经元数量="+numOfNeur);
	}
}
