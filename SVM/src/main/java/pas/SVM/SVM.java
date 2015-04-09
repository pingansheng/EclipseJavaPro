package pas.SVM;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;

public class SVM {

	//训练集合
	public static svm_node[][] trainDataSet;
	public static double[] trainY;
	public static int trainSetCapacity;
	
	//测试集合
	public static svm_node[][] testDataSet;
	public static double[] testY;
	public static int testSetCapacity;

	// 真实值与预测值对比
	public static List<double[]> true_predict;
	
	/*
	 * 归一化参数
	 */
	public static final String MAX_MIN="maxmin";//最大最小标准化
	
	public static final String Z_SCORE="z_score";//Z_Score标准化
	
	public static String STANDARDIZE_Method="";
	// 归一化的最大最小值
	public static double trainMaxY, trainMinY, testMaxY, testMinY;
	// Z_Score标准化参数
	public static double trainAvg_Y, trainSigma_Y, testAvg_Y, testSigma_Y;
	
	
	// 输入变量个数
	public static int inputVarNum;
	/**
	 * 初始化的MASE用于参数寻优
	 */
	static double MASE = 3000000;

	static double cFinal, oFinal, gammaFinal,pFinal;
	
	static double testMSE;
	static double testE;
	static double testR_square;
	
	/**
	 * 一键运行模型
	 * @param trainDataFile 训练文件（同时为测试文件）
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void go(String trainDataFile,boolean isCross,String standardizeMethod,Object...param) throws NumberFormatException, Exception
	{
		go(trainDataFile,null,isCross,standardizeMethod,param);
	}
	
	/**
	 * 一键运行模型
	 * @param trainDataFile 训练文件
	 * @param testDataFile 测试文件
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void go(String trainDataFile, String testDataFile,boolean isCross,String standardizeMethod,Object... param) throws NumberFormatException,
			Exception {
		// 加载训练与测试集合
		if (null != trainDataFile && !"".equals(trainDataFile)) {
			SVM.loadTrainDataSet(trainDataFile);
			
			//归一化训练集合
			double[] params=SVM.preProcessDataSet(standardizeMethod, trainDataSet, trainY);
			if (STANDARDIZE_Method.equals(SVM.Z_SCORE)) {
				trainAvg_Y = params[0];
				trainSigma_Y = params[1];
			} else {
				trainMaxY = params[0];
				trainMinY = params[1];
			}
			

			
			if (null == testDataFile) {
				// 若测试文件为空则使用训练文件作为测试文件
				SVM.loadTestDataSet(trainDataFile);
			} else {
				SVM.loadTestDataSet(testDataFile);
			}
			//归一化测试集合
			params=SVM.preProcessDataSet(STANDARDIZE_Method, testDataSet, testY);
			
			
			if (STANDARDIZE_Method.equals(SVM.Z_SCORE)) {
				testAvg_Y = params[0];
				testSigma_Y = params[1];
			} else {
				testMaxY = params[0];
				testMinY = params[1];
			}
			
			//显示归一化之后的结果
//			displayNodes(trainDataSet);
//			displayNodes(trainY);
//			displayNodes(testDataSet);
//			displayNodes(testY);
			
		}
		
//		
		svm_model model = null;
		if (isCross) {
			model = SVM.cross_vadiation(0.05,param);
		} else {
			model = SVM.findParaBy3Step((double[]) param[0],
					(double[]) param[1], (double[]) param[2],
					Double.parseDouble(param[3].toString()),
					Double.parseDouble(param[4].toString()),
					Double.parseDouble(param[5].toString()));
		}
		if (model == null) {
			System.out.println("模型训练失败");
		} else {
			SVM.predictByModel(model, true);
		}
		// 反归一化
		SVM.undoPre();
		// 真实值预测值对比
		SVM.showPredict_True();
		//保存模型
//		saveModel(model);
	}
	
	
	/**
	 * 一键运行模型 使用整体归一化方式
	 * @param trainDataFile 训练文件
	 * @param testDataFile 测试文件
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void goWithGlobalStand(String trainDataFile, String testDataFile,boolean isCross,String standardizeMethod,Object... param) throws NumberFormatException,
			Exception {
		// 加载训练与测试集合
		if (null != trainDataFile && !"".equals(trainDataFile)) {
			SVM.loadTrainDataSet(trainDataFile);
			if (null == testDataFile) {
				// 若测试文件为空则使用训练文件作为测试文件
				SVM.loadTestDataSet(trainDataFile);
			} else {
				SVM.loadTestDataSet(testDataFile);
			}
			
			//归一化训练集合
			double[] params=SVM.preProcessDataSet(standardizeMethod);
			if (STANDARDIZE_Method.equals(SVM.Z_SCORE)) {
				testAvg_Y = params[0];
				testSigma_Y = params[1];
			} else {
				testMaxY = params[0];
				testMinY = params[1];
			}
			
			//显示归一化之后的结果
//			displayNodes(trainDataSet);
//			displayNodes(trainY);
		}
		
		svm_model model = null;
		if (isCross) {
			model = SVM.cross_vadiation(0.05,param);
		} else {
			model = SVM.findParaBy3Step((double[]) param[0],
					(double[]) param[1], (double[]) param[2],
					Double.parseDouble(param[3].toString()),
					Double.parseDouble(param[4].toString()),
					Double.parseDouble(param[5].toString()));
		}
		if (model == null) {
			System.out.println("模型训练失败");
		} else {
			SVM.predictByModel(model, true);
		}
		// 反归一化
		SVM.undoPre();
		// 真实值预测值对比
		SVM.showPredict_True();
		//保存模型
//		saveModel(model);
	}
	
	
	/**
	 * 留一交叉检验
	 * 
	 * @param nfold
	 */
	public static svm_model cross_vadiation(double expect_mse,Object...param) {
		int vadia_num = trainSetCapacity;

		// 克隆训练数据
		svm_node[][] trainData = trainDataSet.clone();
		double[] trainT = trainY.clone();
		// 克隆测试数据
		svm_node[][] testData = testDataSet.clone();
		double[] testT = testY.clone();

		// 要返回的最终模型
		svm_model finalmodel = null;
		double finalC = 10000;

		for (int i = 0; i < vadia_num; i++) {
			/*
			 * 测试集合初始化
			 */

			// 此处使用的是实际测试集合，测试完毕后需要复原
			testDataSet = new svm_node[1][inputVarNum];
			// 测试集合输入初始化
			for (int j = 0; j < inputVarNum; j++) {
				testDataSet[0][j] = trainData[i][j];
			}

			// 测试集合输出初始化，此处使用的是实际测试集合，测试完毕后需要复原
			testY = new double[] { trainT[i] };

			/*
			 * 训练集合初始化
			 */

			// 此处使用的是实际训练集合，测试完毕后需要复原
			trainDataSet = new svm_node[trainSetCapacity - 1][inputVarNum];

			// 此处使用的是实际训练集合，测试完毕后需要复原
			trainY = new double[trainSetCapacity - 1];

			for (int j = 0, k = 0; j < trainSetCapacity; j++) {

				if (j == i) {
					continue;// 跳过留一的那条数据
				} else {
					for (int k2 = 0; k2 < inputVarNum; k2++) {
						// 给新的训练集合赋值 trainData用的是原始序号j
						// 新集合用的是k
						trainDataSet[k][k2] = trainData[j][k2];
						trainY[k] = trainT[j];
					}
					k++;
				}
			}

			svm_model model = null;
			try {

				model = findParaBy3Step((double[]) param[0],
						(double[]) param[1], (double[]) param[2],
						Double.parseDouble(param[3].toString()),
						Double.parseDouble(param[4].toString()),
						Double.parseDouble(param[5].toString()));

				// 更新最终模型
				if (MASE <= expect_mse && cFinal < finalC) {
					finalmodel = model;
					finalC = cFinal;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 恢复数据
		trainDataSet = trainData.clone();
		trainY = trainT.clone();
		testDataSet = testData.clone();
		testY = testT.clone();
		info("交叉验证结束：C=" + cFinal + ",gamma=" + gammaFinal + ",MASE=" + MASE);
		return finalmodel;
	}

	/**
	 * 三步搜索法
	 * 
	 * @param C_range
	 *            C范围[1,12]
	 * @param O_Range
	 *            O范围[0.1,0.9]
	 * @param c_step
	 *            C步长
	 * @param o_step
	 *            O步长
	 * @throws Exception
	 */
	public static svm_model findParaBy3Step(double[] C_range, double[] O_range,double[] P_range,
			double c_step, double o_step,double p_step) throws Exception {
		if (C_range.length != 2 || O_range.length != 2)
			throw new Exception("范围数据错误");

		double c_min = C_range[0];
		double c_max = C_range[1];
		double o_min = O_range[0];
		double o_max = O_range[1];
		double p_min=P_range[0];
		double p_max=P_range[1];
		
		double cStep = c_step;
		double oStep = o_step;
		double pStep=p_step;
		
		double c = (c_min + c_max) / 2.0;
		double o = (o_min + o_max) / 2.0;
		double p=(p_min+p_max)/2.0;
		
		double[] cArray = null;
		double[] oArray = null;
		double pNow=0;
		/**
		 * 三步搜索法寻优
		 */
		// 要返回的最终模型
		svm_model finalmodel = null;
		svm_model model=null;
		double finalMASE = MASE;

		double startTime = System.currentTimeMillis();
		do {
			cArray = new double[] { c - cStep, c - cStep, c - cStep, c,
					c + cStep, c + cStep, c + cStep, c };
			oArray = new double[] { o - oStep, o, o + oStep, o + oStep,
					o + oStep, o, o - oStep, o - oStep };
			for (int pi = -1; pi < 2; pi++) {
				for (int i = 0; i < cArray.length; i++) {

					// 防止c小于0
					if (cArray[i] <= 0)
						cArray[i] = 1;

					// 防止p小于0
					pNow = p + pi * pStep;
					if (pNow < 0) {
						pNow = 0.01;
					}
					model = test(cArray[i], 1.0 / (2 * oArray[i] * oArray[i]),
							pNow);

					if (null != model) {
						// 更新最终模型
						if (MASE < finalMASE) {
							finalmodel = model;
							finalMASE = MASE;
						}
					}
				}
			}

			c = cFinal;
			o = oFinal;
			p = pFinal;

			cStep = cStep * 0.95;
			oStep = oStep * 0.95;
			pStep = pStep * 0.95;

		} while (cStep >= 0.05 && oStep >= 0.05 && pStep > 0.00000000001);

		double endtime = System.currentTimeMillis();
		
		info("********************************************************");
		info("三步搜索法，搜索时间" + (endtime - startTime) / 1000 + "秒\n，c=" + cFinal
				+ ",gamma=" + gammaFinal + "\nMASE=" + MASE+",p="+pFinal);
		info("********************************************************");
		return finalmodel;
	}

	/**
	 * 测试模型 通过模型文件
	 * 
	 * @param c
	 * @param o
	 * @param showRes
	 * @return
	 */
	public static void predictByModel(String modelfile)
	{
		svm_model model=null;
		try {
			model = svm.svm_load_model(modelfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		predictByModel(model,true);
	}
	/**
	 * 测试模型 通过模型对象
	 * 
	 * @param c
	 * @param o
	 * @param showRes
	 * @return MASE
	 */
	public static double predictByModel(svm_model model, boolean isShowAnaPara) {

		if (null != model) {
			double err_square = 0.0;
			true_predict = new ArrayList<double[]>();

			double r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0;

			// 此处不可使用testSetCapacity,训练集合在变化（交叉验证）

			int testDataSize = testDataSet.length;
			for (int i = 0; i < testDataSize; i++) {
				double truevalue = testY[i];
				double predictValue = svm.svm_predict(model, testDataSet[i]);
				true_predict.add(new double[] { truevalue, predictValue });
				err_square += (predictValue - truevalue)
						* (predictValue - truevalue);

				// 计算R2参数
				r1 += predictValue * truevalue;
				r2 += predictValue;
				r3 += truevalue;
				r4 += predictValue * predictValue;
				r5 += truevalue * truevalue;
			}
			testR_square = (testDataSize * r1 - r2 * r3)
					* (testDataSize * r1 - r2 * r3)
					/ ((testDataSize * r4 - r2 * r2) * (testDataSize * r5 - r3
							* r3));
			double tmpE = err_square / testDataSize;
			testMSE = Math.sqrt(tmpE);
					
			if (isShowAnaPara) {
				info("\r****************************************");
				info("预测结束，E=" + tmpE + "\n\tMASE=" + testMSE + "\n\tR_square="
						+ testR_square);
				info("****************************************\r");
			}
			return Math.sqrt(testMSE);
		}
		return -1;
	}

	
	/**
	 * 测试模型 通过参数构造模型
	 * 
	 * @param c
	 * @param o
	 * @param showRes
	 * @return
	 */
	public static svm_model test(double c, double gamma, double p) {
		
		svm_model model = getModelByPara(c, gamma, p);
		
		double temMase = predictByModel(model, false);
		
		if (temMase < MASE) {
			MASE = temMase;
			cFinal = model.param.C;
			gammaFinal = model.param.gamma;
			pFinal=model.param.p;
		}
		
		return model;
	}

	/**
	 * 通过参数构造模型
	 * @param c
	 * @param gamma
	 * @param p
	 * @return
	 */
	public static svm_model getModelByPara(double c,double gamma,double p){
		svm_problem problem = new svm_problem();
		problem.l = trainDataSet.length;
		problem.x = trainDataSet;
		problem.y = trainY;

		svm_parameter param = new svm_parameter();
		param.svm_type = svm_parameter.EPSILON_SVR;
		param.kernel_type = svm_parameter.RBF;
		param.cache_size = 300;
		param.eps = 0.1;// 停机标准
		param.p = p;// e-svr损失函数松弛变量
		param.C = c;
		param.gamma = gamma;
		// 测试模型参数的正确性
		String modelerr = svm.svm_check_parameter(problem, param);
		if (modelerr != null) {
			info("模型参数出错:" + modelerr);
		}
		// 训练模型
		return svm.svm_train(problem, param);
	}
	
	/**
	 * 归一化数据 测试集合训练集合同时归一化
	 * @param Method 归一化方法 max-min z-score
	 * @return 返回相应的参数数组 (maxY,minY) or (AVGY,sigma)
	 */
	public static double[] preProcessDataSet(String Method) {
		
		if (!Method.equals(SVM.MAX_MIN) && !Method.equals(SVM.Z_SCORE)) {
			throw new RuntimeException("参数错误");
		}
		//返回的参数
		double[] resParam=new double[2];
		
		STANDARDIZE_Method=Method;
		
		
		//构造测试训练集合总集合
		int trainSize=trainDataSet.length;
		int testSize=testDataSet.length;
		
		svm_node[][] X=new svm_node[trainSize+testSize][];
		double[] Y=new double[trainSize+testSize];
		
		for (int i = 0; i < X.length; i++) {
			if(i<trainSize){
				X[i]=trainDataSet[i].clone();
				Y[i]=trainY[i];
			}else{
				X[i]=testDataSet[i-trainSize].clone();
				Y[i]=testY[i-trainSize];
			}
		}
		
		
		// 集合Y归一化
		double maxY = Y[0];
		double minY = Y[0];
		double AVG_Y = 0;// 均值
		double Sigama_square = 0;// 方差
				
		for (int i = 0; i < Y.length; i++) {
			AVG_Y += Y[i]/Y.length;
			maxY = Math.max(Y[i], maxY);
			minY = Math.min(Y[i], minY);
		}
		
		//求方差
		if(Method.equals(SVM.Z_SCORE)){
			for (int i = 0; i < Y.length; i++) {
				Sigama_square+=(Y[i]-AVG_Y)*(Y[i]-AVG_Y);
			}
			double Sigama=Math.sqrt(Sigama_square/(Y.length-1));
			
			
			for (int i = 0; i < Y.length; i++) {
				Y[i] = (Y[i] - AVG_Y) /Sigama;
			}
			
			resParam[0]=AVG_Y;
			resParam[1]=Sigama;
			
		}else{
			
			for (int i = 0; i < Y.length; i++) {
				Y[i] = (Y[i] - minY) / (maxY - minY);
			}
			
			resParam[0]=maxY;
			resParam[1]=minY;
		}
		
		
		// 训练集合X归一化
		// 从列开始
		for (int i = 0; i < X[0].length; i++) {
			double maxX = X[0][i].value;
			double minX = X[0][i].value;
			
			double X_AVG=0;
			double X_Sigama_square=0;
			
			for (int j = 0; j < X.length; j++) {
				X_AVG+=X[j][i].value/X.length;
				maxX = Math.max(X[j][i].value, maxX);
				minX = Math.min(X[j][i].value, minX);
			}
			//计算方差
			if (Method.equals(Z_SCORE)) {
				for (int j = 0; j < X.length; j++) {
					X_Sigama_square += (X[j][i].value - X_AVG)
							* (X[j][i].value - X_AVG);
				}
				//yangbe
				double X_Sigama = Math.sqrt(X_Sigama_square / (X.length - 1));
				
				for (int j = 0; j < X.length; j++) {
					X[j][i].value = (X[j][i].value - X_AVG)/ X_Sigama;
				}
			}else{
				for (int j = 0; j < X.length; j++) {
					X[j][i].value = (X[j][i].value - minX)
							/ (maxX - minX);
				}
			}
		}
		
		//分解数据
		
		for (int i = 0; i < X.length; i++) {
			if(i<trainSize){
				trainDataSet[i]=X[i].clone();
				trainY[i]=Y[i];
			}else{
				testDataSet[i-trainSize]=X[i].clone();
				testY[i-trainSize]=Y[i];
			}
		}
		
		return resParam;
	}

	
	/**
	 * 归一化数据
	 * @param Method 归一化方法 max-min z-score
	 * @return 返回相应的参数数组 (maxY,minY) or (AVGY,sigma)
	 */
	public static double[] preProcessDataSet(String Method,svm_node[][] dataSet,double[] Y) {
		
		if (!Method.equals(SVM.MAX_MIN) && !Method.equals(SVM.Z_SCORE)) {
			throw new RuntimeException("参数错误");
		}
		//返回的参数
		double[] resParam=new double[2];
		
		STANDARDIZE_Method=Method;
		
		// 集合Y归一化
		double maxY = Y[0];
		double minY = Y[0];
		double AVG_Y = 0;// 均值
		double Sigama_square = 0;// 方差
				
		for (int i = 0; i < Y.length; i++) {
			AVG_Y += Y[i]/Y.length;
			maxY = Math.max(Y[i], maxY);
			minY = Math.min(Y[i], minY);
		}
		
		//求方差
		if(Method.equals(SVM.Z_SCORE)){
			for (int i = 0; i < Y.length; i++) {
				Sigama_square+=(Y[i]-AVG_Y)*(Y[i]-AVG_Y);
			}
			double Sigama=Math.sqrt(Sigama_square/(Y.length-1));
			
			
			for (int i = 0; i < Y.length; i++) {
				Y[i] = (Y[i] - AVG_Y) /Sigama;
			}
			
			resParam[0]=AVG_Y;
			resParam[1]=Sigama;
			
		}else{
			
			for (int i = 0; i < Y.length; i++) {
				Y[i] = (Y[i] - minY) / (maxY - minY);
			}
			
			resParam[0]=maxY;
			resParam[1]=minY;
		}
		
		
		// 训练集合X归一化
		// 从列开始
		for (int i = 0; i < dataSet[0].length; i++) {
			double maxX = dataSet[0][i].value;
			double minX = dataSet[0][i].value;
			
			double X_AVG=0;
			double X_Sigama_square=0;
			
			for (int j = 0; j < dataSet.length; j++) {
				X_AVG+=dataSet[j][i].value/dataSet.length;
				maxX = Math.max(dataSet[j][i].value, maxX);
				minX = Math.min(dataSet[j][i].value, minX);
			}
			//计算方差
			if (Method.equals(Z_SCORE)) {
				for (int j = 0; j < dataSet.length; j++) {
					X_Sigama_square += (dataSet[j][i].value - X_AVG)
							* (dataSet[j][i].value - X_AVG);
				}
				//yangbe
				double X_Sigama = Math.sqrt(X_Sigama_square / (dataSet.length - 1));
				
				for (int j = 0; j < dataSet.length; j++) {
					dataSet[j][i].value = (dataSet[j][i].value - X_AVG)/ X_Sigama;
				}
			}else{
				for (int j = 0; j < dataSet.length; j++) {
					dataSet[j][i].value = (dataSet[j][i].value - minX)
							/ (maxX - minX);
				}
			}
		}
		
		return resParam;
	}


	/**
	 * 测试结果反归一化
	 */
	public static void undoPre() {
		for (int i = 0; i < true_predict.size(); i++) {
			if (STANDARDIZE_Method.equals(MAX_MIN)) {
				true_predict.get(i)[0] = true_predict.get(i)[0]
						* (testMaxY - testMinY) + testMinY;
				true_predict.get(i)[1] = true_predict.get(i)[1]
						* (testMaxY - testMinY) + testMinY;
			} else {
				true_predict.get(i)[0] = true_predict.get(i)[0] * testSigma_Y
						+ testAvg_Y;
				true_predict.get(i)[1] = true_predict.get(i)[1] * testSigma_Y
						+ testAvg_Y;
			}
		}
	}

	/**
	 * 输出真实值,预测值
	 */
	public static void showPredict_True() {
		info("\r**************************************************************");
		info("真实值\t\t预测值\t\t绝对误差\t\t相对误差");
		DecimalFormat df = new DecimalFormat("0.0000");
		for (double[] value : true_predict) {
			System.out.print(df.format(value[0]) + " \t");
			System.out.print(df.format(value[1]) + " \t");
			System.out.print(df.format(Math.abs(value[1] - value[0])) + " \t");
			info(df.format(Math.abs(value[1] - value[0])
					/ value[0] * 100)
					+ "%");
		}
		info("**************************************************************\r");
	}

	/**
	 * 加载训练集数据
	 * 
	 * @param traindatafile
	 */
	public static void loadTrainDataSet(String traindatafile) {
		List<Double> label = new ArrayList<Double>();
		List<svm_node[]> nodeSet = new ArrayList<svm_node[]>();
		getData(nodeSet, label, traindatafile);

		// 从首行获取输入变量数
		inputVarNum = nodeSet.get(0).length;
		trainSetCapacity = nodeSet.size();
		// 初始化训练集合
		trainDataSet = new svm_node[nodeSet.size()][inputVarNum];
		for (int i = 0; i < trainDataSet.length; i++) {
			for (int j = 0; j < inputVarNum; j++) {
				trainDataSet[i][j] = nodeSet.get(i)[j];
			}
		}
		trainY = new double[label.size()]; // 训练集Y
		for (int i = 0; i < trainY.length; i++) {
			trainY[i] = label.get(i);
		}
	}

	/**
	 * 加载测试数据集合
	 * 
	 * @param testDatafile
	 */
	public static void loadTestDataSet(String testDatafile) {
		// 测试集
		List<Double> testlabel = new ArrayList<Double>();
		List<svm_node[]> testnodeSet = new ArrayList<svm_node[]>();
		getData(testnodeSet, testlabel, testDatafile);

		// inputVarNum输入变量数从加载训练集合获取
		testSetCapacity = testnodeSet.size();
		testDataSet = new svm_node[testnodeSet.size()][inputVarNum];
		for (int i = 0; i < testDataSet.length; i++) {
			for (int j = 0; j < inputVarNum; j++) {
				testDataSet[i][j] = testnodeSet.get(i)[j];
			}
		}
		testY = new double[testlabel.size()];
		for (int i = 0; i < testY.length; i++) {
			testY[i] = testlabel.get(i);
		}
	}

	/**
	 * 载入数据
	 */
	public static void getData(List<svm_node[]> nodeSet, List<Double> label,
			String filename) {
		FileReader fr = null;
		BufferedReader br = null;
		try {

			fr = new FileReader(new File(filename));
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if("".equals(line) || line.startsWith("#")){
					//#号忽略空白忽略
					continue;
				}
				String[] datas = line.split(",");
				svm_node[] vector = new svm_node[datas.length - 1];
				for (int i = 0; i < datas.length - 1; i++) {
					svm_node node = new svm_node();
					node.index = i + 1;
					node.value = Double.parseDouble(datas[i]);
					vector[i] = node;
				}
				nodeSet.add(vector);
				double lablevalue = Double.parseDouble(datas[datas.length - 1]);
				label.add(lablevalue);
			}
		} catch (Exception e) {
			info("数据文件存在错误，请检查数据文件");
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 保存模型
	 * @param model
	 */
	public static void saveModel(svm_model model) {
		// 保存模型
		try {
			String path="file/model"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
			svm.svm_save_model(path,model);
			
			File file=new File(path);
			FileWriter fw=new FileWriter(file,true);
			BufferedWriter bw=new BufferedWriter(fw);
			
			bw.append("\n");
			bw.append("C="+cFinal+",MSE="+MASE);
			bw.append("\n");
			bw.append("**************************************************************\n");
			bw.append("original\tpredicted\tAbErr\tRelErr\n");
			DecimalFormat df = new DecimalFormat("0.0000");
			for (double[] value : true_predict) {
				bw.append(df.format(value[0]) + " \t");
				bw.append(df.format(value[1]) + " \t");
				bw.append(df.format(Math.abs(value[1] - value[0])) + " \t");
				bw.append(df.format(Math.abs(value[1] - value[0])
						/ value[0] * 100)
						+ "%\n");
			}
			bw.append("**************************************************************");
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出信息
	 * 
	 * @param mess
	 */
	private static void info(Object mess) {
		System.out.flush();
		System.out.println(mess.toString());
		System.out.flush();
	}
	
	/**
	 * 输出节点集合
	 * @param nodes
	 */
	private static void displayNodes(svm_node[][] nodes){
		for(int i=0;i<nodes.length;i++){
			for (int j = 0; j < nodes[0].length; j++) {
				System.out.print(nodes[i][j].value+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * 输出节点集合
	 * @param nodes
	 */
	private static void displayNodes(double[] nodes){
		for(int i=0;i<nodes.length;i++){
				System.out.println(nodes[i]);
		}
		System.out.println();
	}
	
}
