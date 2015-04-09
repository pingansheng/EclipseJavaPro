package pas.SVM;

import libsvm.svm_model;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) {
//		test();
		go();
//		goWithGlobalStand();
		///TODO::测试数据为一条怎么办 训练集合测试集合一起归一化
		
	}

	public static void go() {
		double[] c_range = { 0.1, 8000 };
		double[] o_range = { 0.01,8000 };
		double[] p_range = { 0.0001,0.001};
		double c_step = (c_range[1]-c_range[0])/16;
		double o_step = (o_range[1]-o_range[0])/16;
		double p_step=(p_range[1]-p_range[0])/16;
		try {
			SVM.go("file/SVMenergyTrainGAS","file/SVMenergyTestGAS", false,SVM.Z_SCORE, c_range,o_range,p_range,c_step,o_step,p_step);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void goWithGlobalStand() {
		double[] c_range = { 1, 100000 };
		double[] o_range = { 0.01, 30000 };
		double[] p_range = { 0.0001,0.001};
		double c_step = (c_range[1]-c_range[0])/16;
		double o_step = (o_range[1]-o_range[0])/16;
		double p_step=(p_range[1]-p_range[0])/16;
		try {
			SVM.goWithGlobalStand("file/SVMenergyTrainELE","file/SVMenergyTestELE", false,SVM.Z_SCORE, c_range,o_range,p_range,c_step,o_step,p_step);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void test()
	{
		double bestMSE=2000000;
		double R2=0;
		double bestCMax = 0,bestOMax = 0,bestpMax = 0;
		for(double cMax=10;cMax<10000;cMax+=300)
		{
			for(double oMax=1;oMax<3000;oMax+=80)
			{
				for(double pMax=0.0001;pMax<5;pMax*=5)
				{
					double[] c_range = { 1, cMax };
					double[] o_range = { 0.01, oMax };
					double[] p_range = { 0.00001, pMax };
					double c_step = (c_range[1]-c_range[0])/16;
					double o_step = (o_range[1]-o_range[0])/16;
					double p_step=(p_range[1]-p_range[0])/16;
					try {
						SVM.goWithGlobalStand("file/SVMenergyTrainGAS","file/SVMenergyTestGAS", false,SVM.Z_SCORE, c_range,o_range,p_range,c_step,o_step,p_step);
						
						if(R2<SVM.testR_square){
							bestMSE=SVM.testMSE;
							bestCMax=cMax;
							bestOMax=oMax;
							bestpMax=pMax;
							R2=SVM.testR_square;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	
		System.out.println("结束：bestR2="+R2+"bestMSE="+bestMSE+"maxC="+bestCMax+",maxO="+bestOMax+", pMax="+bestpMax);
	}
}
