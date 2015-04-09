package com.pas.ELM.jblas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.jblas.DoubleMatrix;
import org.jblas.Singular;
import org.jblas.Solve;
import org.jblas.exceptions.LapackException;


public class MyELM {

	/**
	 * 训练相关
	 */
	// 训练集
	private DoubleMatrix train_set;
	// 训练集数据量
	private int train_data_Count;
	// 训练集输入
	private DoubleMatrix P;
	// 训练集输出
	private DoubleMatrix T;
	// 输入权重
	private DoubleMatrix InputWeight;
	// 训练时间
	private float TrainingTime;
	// 测试时间
	private float TestingTime;
	// 训练精度
	private double TrainingAccuracy;
	// 隐含层神经元数量
	private int NumberofHiddenNeurons;
	// 输出层神经元数量
	private int NumberofOutputNeurons;
	// 输入层神经元数量
	private int NumberofInputNeurons;
	//总列数
	private int totalColCount;
	// 激活函数类型字符串，暂时有sig
	private String func="sig";
	
	// 隐含层神经元的阈值
	private DoubleMatrix BiasofHiddenNeurons;
	
	// 输出层权重矩阵
	private DoubleMatrix OutputWeight;

	/**
	 * 测试相关
	 */
	// 测试精度
	private double TestingAccuracy;
	// 测试集数据量
	private int test_data_Count;
	// 测试集合
	private DoubleMatrix test_set;
	// 测试集输入
	private DoubleMatrix testP;
	// 测试集输出
	private DoubleMatrix testT;
	//是否输出详细信息
	private boolean showDetailInfo;

	public MyELM(){
		
	}
	/**
	 * 
	 * @param numOfHiddenNeurons 隐含层神经元个数
	 * @param ActivationFunction 激活函数类型，暂时选择"sig"
	 * @param numOfInputNeurons 输入神经元个数
	 * @param numOfOutPutNeurons 输出神经元个数
	 * @param countOfTrainSet 训练集数据量
	 */
	public MyELM(int numOfHiddenNeurons,String ActivationFunction,int numOfInputNeurons,int numOfOutPutNeurons,int countOfTrainSet){
		NumberofHiddenNeurons=numOfHiddenNeurons;
		func=ActivationFunction;
		NumberofInputNeurons=numOfInputNeurons;
		NumberofOutputNeurons = numOfOutPutNeurons;
		train_data_Count=countOfTrainSet;
		totalColCount=NumberofInputNeurons+NumberofOutputNeurons;
		//初始化其他变量
		TrainingTime = 0;
		TestingTime = 0;
		TrainingAccuracy = 0;
		TestingAccuracy = 0;
		
	}
	
	/**
	 * 文件载入矩阵
	 */
	private DoubleMatrix loadMatrix(String trainSetPath,int rowNum,int colNum) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(new File(trainSetPath)));
		//第一行为文件信息
		DoubleMatrix matrix=new DoubleMatrix(rowNum, colNum);
		String lineStr="";
		for(int i=0;i<rowNum;i++){
			lineStr=br.readLine();
			if(lineStr.startsWith("#")||"".equals(lineStr)){
				continue;
			}
			String[] strings=lineStr.split(",");
			for (int j = 0; j < colNum; j++) {
				matrix.put(i, j, Double.parseDouble(strings[j]));
			}
		}
		br.close();
		return matrix;
	}
	
	/**
	 * 训练模型
	 * @param trainSetPath
	 */
	public void trainModel(String trainSetPath){
		try {
			//行数为数据量，列数为输入层加隐含层
			train_set=loadMatrix(trainSetPath,train_data_Count,totalColCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		train();
	}

	/**
	 * 训练模型
	 */
	private void train() {
		printInfo("训练数据载入完毕，开始训练模型………………训练数据规模"+train_set.rows+"x"+train_set.columns);
		//输入矩阵初始化 横向即每列为一组输入
		/*
		 *    4 8 …… N
		 *    
		 *    1 5 …… N
		 *    2 6 …… N
		 *    3 7 …… N
		 */
		P=new DoubleMatrix(NumberofInputNeurons, train_data_Count);
		//测试机输出矩阵 横向
		T=new DoubleMatrix(NumberofOutputNeurons, train_data_Count);
		//初始化
		for(int i=0;i<train_data_Count;i++){
			
			for(int j=0;j<totalColCount;j++)
			{
				if(j<NumberofInputNeurons){
					//输入部分
					P.put(j, i, train_set.get(i, j));
				}else{
					T.put(j-NumberofInputNeurons, i, train_set.get(i, j));
				}
			}
		}
		printInfo("训练矩阵初始化完毕………………开始隐含层神经元权重与阈值");
		//end 初始化
		long start_time_train = System.currentTimeMillis();
		//随机初始化输入权值矩阵
		//行数为隐含层神经元个数，列数为输入层神经元
		/**
		 *    W11 W12 W13(第一个隐含神经元对应各输入的权值)
		 *    W21 W22 W23(第二个隐含神经元对应各输入的权值)
		 *    ………………………………(第N个隐含神经元对应各输入的权值)
		 */
		InputWeight=DoubleMatrix.rand(NumberofHiddenNeurons, NumberofInputNeurons);
		
		//初始化阈值
		BiasofHiddenNeurons=DoubleMatrix.rand(NumberofHiddenNeurons, 1);
		printInfo("隐含层神经元权重与阈值初始化完毕………………开始计算输入权重");
		DoubleMatrix tmpH=new DoubleMatrix(NumberofHiddenNeurons, train_data_Count);
		tmpH=InputWeight.mmul(P);
		printInfo("输入矩阵计算权重完毕………………开始计算输入权重");
		//加阈值
		//注意横向问题
		for (int i = 0; i < NumberofHiddenNeurons; i++) {
			for (int j = 0; j < train_data_Count; j++) {
				tmpH.put(i, j, tmpH.get(i, j)+BiasofHiddenNeurons.get(i, 0));
			}
		}
		printInfo("输入矩阵计算阈值完毕………………开始计算输入激活函数");
		//输出矩阵
		DoubleMatrix H=new DoubleMatrix(NumberofHiddenNeurons, train_data_Count);
		
		if(func.startsWith("sig")){
			for (int i = 0; i < NumberofHiddenNeurons; i++) {
				for (int j = 0; j < train_data_Count; j++) {
					double tmp=tmpH.get(i, j);
					tmp=1.0f/(1+Math.exp(-tmp));
					H.put(i, j, tmp);
				}
			}
		}
		else{
			throw new IllegalArgumentException("不支持的激活函数类型");
		}
		printInfo("输出矩阵计算激活函数完毕，开始求解广义逆………………矩阵规模"+H.columns+"x"+H.rows);
		//将H转置
		DoubleMatrix Ht=H.transpose();
		
		//求广义逆
		DoubleMatrix Ht_MP=getMPMatrix(Ht);
		printInfo("输出矩阵广义逆求解完毕………………开始求解输出矩阵权重");
		//隐含层输出权重矩阵= Ht_MP * Tt
		OutputWeight=Ht_MP.mmul(T.transpose());
		printInfo("输出矩阵权重求解完毕………………");
		long end_time_train = System.currentTimeMillis();
		TrainingTime = (end_time_train - start_time_train) * 1.0f / 1000;
		
		printInfo("模型训练完毕………………开始评估模型");
		
		
		//误差评估
		DoubleMatrix Yt = new DoubleMatrix(train_data_Count, NumberofOutputNeurons);
		Yt=Ht.mmul(OutputWeight);
		double MSE = 0;
		printInfo("共有"+NumberofOutputNeurons+"个输出值");
		for(int out=0;out<NumberofOutputNeurons;out++){
			printInfo("计算第"+(out+1)+"个输出值");
			double mseTem=0.0;
			for (int i = 0; i < train_data_Count; i++) {
				System.out.println(Yt.get(i, out)+" "+T.get(out,i));
				mseTem += (Yt.get(i, out) - T.get(out,i))
						* (Yt.get(i, out) - T.get(out,i));
			}
			printInfo("第"+NumberofOutputNeurons+"个输出值计算完毕,MSE="+Math.sqrt(mseTem/train_data_Count));
			MSE+=mseTem;
		}
		
		TrainingAccuracy = Math.sqrt(MSE / train_data_Count);
		printInfo("模型评估完毕………………");
	}

	
	/**
	 * 测试模型
	 * @param filename
	 */
	public void test_model(String filename,int testCount){
		try {
			this.test_data_Count=testCount;
			test_set=loadMatrix(filename, test_data_Count, totalColCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		printInfo("测试数据载入完毕…………");
		// 测试数据输入矩阵初始化 横向即每列为一组输入
		testP = new DoubleMatrix(NumberofInputNeurons, test_data_Count);
		// 测试机输出矩阵 横向
		testT = new DoubleMatrix(NumberofOutputNeurons, test_data_Count);
		// 初始化
		for (int i = 0; i < test_data_Count; i++) {

			for (int j = 0; j < totalColCount; j++) {
				if (j < NumberofInputNeurons) {
					// 输入部分
					testP.put(j, i, test_set.get(i, j));
				} else {
					testT.put(j - NumberofInputNeurons, i, test_set.get(i, j));
				}
			}
		}
		// end 初始化
		printInfo("测试数据初始化完毕…………");
		long start_time_test = System.currentTimeMillis();
		// 计算输入权重与阈值部分

		DoubleMatrix tmpH = new DoubleMatrix(NumberofHiddenNeurons,
				test_data_Count);
		tmpH = InputWeight.mmul(testP);
		printInfo("权重计算完毕…………");
		// 加阈值
		// 注意横向问题
		for (int i = 0; i < NumberofHiddenNeurons; i++) {
			for (int j = 0; j < test_data_Count; j++) {
				tmpH.put(i, j, tmpH.get(i, j) + BiasofHiddenNeurons.get(i, 0));
			}
		}
		printInfo("阈值计算完毕…………");
		// 输出矩阵
		DoubleMatrix H = new DoubleMatrix(NumberofHiddenNeurons,
				test_data_Count);

		if (func.startsWith("sig")) {
			for (int i = 0; i < NumberofHiddenNeurons; i++) {
				for (int j = 0; j < test_data_Count; j++) {
					double tmp = tmpH.get(i, j);
					tmp = 1.0f / (1 + Math.exp(-tmp));
					H.put(i, j, tmp);
				}
			}
		} else {
			throw new IllegalArgumentException("不支持的激活函数类型");
		}
		printInfo("激活函数计算完毕…………");
		// 将H转置
		DoubleMatrix Ht = H.transpose();

		// 测试误差
		DoubleMatrix Yt = new DoubleMatrix(test_data_Count,
				NumberofOutputNeurons);
		Yt = Ht.mmul(OutputWeight);
		printInfo("测试完毕…………");
		long end_time_test = System.currentTimeMillis();
		TestingTime=(end_time_test - start_time_test) * 1.0f / 1000;
		double MSE = 0;
		printInfo("共有"+NumberofOutputNeurons+"个输出值");
		for(int out=0;out<NumberofOutputNeurons;out++){
			printInfo("计算第"+(out+1)+"个输出值");
			double mseTem=0.0;
			for (int i = 0; i < test_data_Count; i++) {
				mseTem += (Yt.get(i, out) - testT.get(out,i))
						* (Yt.get(i, out) - testT.get(out,i));
			}
			printInfo("第"+NumberofOutputNeurons+"个输出值计算完毕,MSE="+Math.sqrt(mseTem/train_data_Count));
			MSE+=mseTem;
		}
		TestingAccuracy = Math.sqrt(MSE / test_data_Count);
		printInfo("计算MSE完毕…………");
	}
	/**
	 * 求逆矩阵
	 * @param mt
	 * @return
	 */
	public DoubleMatrix inverse(DoubleMatrix mt){
		int m=mt.rows;
		DoubleMatrix E=DoubleMatrix.zeros(m, m);
		for (int i = 0; i < m; i++) {
			E.put(i, i, 1);
		}
		return Solve.solve(mt, E);
	}
	
	/**
	 * 求广义逆矩阵
	 *  Theory:最大秩分解
	 *	U*S*Vt = A
	 *	C=U*sqrt(S) D=sqrt(S)*Vt <==> A=C*D
	 *	MP(A) = D'*inv(D*D')*inv(C'*C)*C'
	 * @param ht
	 * @return
	 */
	public DoubleMatrix getMPMatrix(DoubleMatrix ht) {
		
		int m=ht.rows;
		int n=ht.columns;
		DoubleMatrix[] USV=Singular.fullSVD(ht);
		DoubleMatrix U=USV[0];
		double[] s_raw=USV[1].data.clone();//奇异值
		//去除0
		int k=0;
		while(k<s_raw.length && s_raw[k]>0.1e-5){
			k++;
		}
		double[] s=new double[k];
		for (int i = 0; i < s.length; i++) {
			s[i]=s_raw[i];
		}
		
		DoubleMatrix Vt=USV[2].transpose();//n*n
		int sn=s.length;
		for (int i = 0; i < sn; i++) {
			s[i] = Math.sqrt(s[i]);
		}
		
		//构造m*sn sn*n矩阵
		DoubleMatrix S1 = new DoubleMatrix(m, sn);
		DoubleMatrix S2 = new DoubleMatrix(sn, n);
		for (int i = 0; i < sn; i++) {
			S1.put(i, i, s[i]);
			S2.put(i, i, s[i]);
		}
		DoubleMatrix C=U.mmul(S1);
		DoubleMatrix D=S2.mmul(Vt);
		
		DoubleMatrix DT = D.transpose();
		DoubleMatrix DD = D.mmul(DT);
		DoubleMatrix invDD = inverse(DD);
		
		DoubleMatrix DDD = DT.mmul(invDD);
		
		DoubleMatrix CT = C.transpose();
		DoubleMatrix CC = CT.mmul(C);
		DoubleMatrix invCC = inverse(CC);
		
		DoubleMatrix CCC = invCC.mmul(CT);
		DoubleMatrix Ainv = DDD.mmul(CCC);
		
		return Ainv;
	}
	
	/**
	 * 求广义逆矩阵,使用正交投影方法
	 * @param ht
	 * @return
	 */
	public DoubleMatrix getMPMatrixByOP(DoubleMatrix H) {
		DoubleMatrix M;
		try {
			M=H.mmul(H.transpose());//HHt
			addPositiveValue(M);
			return H.transpose().mmul(inverse(M));
		} catch (LapackException e) {
			M=H.transpose().mmul(H);//HtH
			addPositiveValue(M);
			return inverse(M).mmul(H.transpose());
		}
	}
	
	/**
	 * 对角线增加值
	 * @param matrix
	 * @throws Exception 
	 */
	private void addPositiveValue(DoubleMatrix matrix){
		int m=matrix.rows;
		
		if(matrix.columns!=m) return;
		for (int i = 0; i < m; i++) {
			matrix.put(i,i, matrix.get(i, i)+Math.random());
		}
	}
	
	/*
	 * 输出信息，根据信息输出开关
	 */
	private void printInfo(String info) {
		if (showDetailInfo == true) {
			System.out.println(info);
		}
	}
	
	
	
	
	
	
	
	
	
	public DoubleMatrix getTrain_set() {
		return train_set;
	}
	public void setTrain_set(DoubleMatrix train_set) {
		this.train_set = train_set;
	}
	public int getTrain_data_Count() {
		return train_data_Count;
	}
	public void setTrain_data_Count(int train_data_Count) {
		this.train_data_Count = train_data_Count;
	}
	public DoubleMatrix getP() {
		return P;
	}
	public void setP(DoubleMatrix p) {
		P = p;
	}
	public DoubleMatrix getT() {
		return T;
	}
	public void setT(DoubleMatrix t) {
		T = t;
	}
	public DoubleMatrix getInputWeight() {
		return InputWeight;
	}
	public void setInputWeight(DoubleMatrix inputWeight) {
		InputWeight = inputWeight;
	}
	public float getTrainingTime() {
		return TrainingTime;
	}
	public void setTrainingTime(float trainingTime) {
		TrainingTime = trainingTime;
	}
	public float getTestingTime() {
		return TestingTime;
	}
	public void setTestingTime(float testingTime) {
		TestingTime = testingTime;
	}
	public double getTrainingAccuracy() {
		return TrainingAccuracy;
	}
	public void setTrainingAccuracy(double trainingAccuracy) {
		TrainingAccuracy = trainingAccuracy;
	}
	public int getNumberofHiddenNeurons() {
		return NumberofHiddenNeurons;
	}
	public void setNumberofHiddenNeurons(int numberofHiddenNeurons) {
		NumberofHiddenNeurons = numberofHiddenNeurons;
	}
	public int getNumberofOutputNeurons() {
		return NumberofOutputNeurons;
	}
	public void setNumberofOutputNeurons(int numberofOutputNeurons) {
		NumberofOutputNeurons = numberofOutputNeurons;
	}
	public int getNumberofInputNeurons() {
		return NumberofInputNeurons;
	}
	public void setNumberofInputNeurons(int numberofInputNeurons) {
		NumberofInputNeurons = numberofInputNeurons;
	}
	public int getTotalColCount() {
		return totalColCount;
	}
	public void setTotalColCount(int totalColCount) {
		this.totalColCount = totalColCount;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public DoubleMatrix getBiasofHiddenNeurons() {
		return BiasofHiddenNeurons;
	}
	public void setBiasofHiddenNeurons(DoubleMatrix biasofHiddenNeurons) {
		BiasofHiddenNeurons = biasofHiddenNeurons;
	}
	public DoubleMatrix getOutputWeight() {
		return OutputWeight;
	}
	public void setOutputWeight(DoubleMatrix outputWeight) {
		OutputWeight = outputWeight;
	}
	public double getTestingAccuracy() {
		return TestingAccuracy;
	}
	public void setTestingAccuracy(double testingAccuracy) {
		TestingAccuracy = testingAccuracy;
	}
	public int getTest_data_Count() {
		return test_data_Count;
	}
	public void setTest_data_Count(int test_data_Count) {
		this.test_data_Count = test_data_Count;
	}
	public DoubleMatrix getTest_set() {
		return test_set;
	}
	public void setTest_set(DoubleMatrix test_set) {
		this.test_set = test_set;
	}
	public DoubleMatrix getTestP() {
		return testP;
	}
	public void setTestP(DoubleMatrix testP) {
		this.testP = testP;
	}
	public DoubleMatrix getTestT() {
		return testT;
	}
	public void setTestT(DoubleMatrix testT) {
		this.testT = testT;
	}
	public boolean isShowDetailInfo() {
		return showDetailInfo;
	}
	public void setShowDetailInfo(boolean showDetailInfo) {
		this.showDetailInfo = showDetailInfo;
	}
}
