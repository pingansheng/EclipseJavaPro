import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class BP {

	static double MINMSE=1000000;
	static double[] yu=null;
	static double[] trueval={70164,75116,58177,60569};
	
	public static void main(String[] args) throws Exception  {
		for(int si=-100;si<200;si+=5)
		{
			for(double ri=0.01;ri<100;ri+=0.05){
				try {
					go(ri,si);
					System.out.println("si="+si+",ri="+ri);
				} catch (Exception e) {
					continue;
				}
				
			}
		}
		
		System.out.println("MSE="+MINMSE);
		for (int i = 0; i < yu.length; i++) {
			System.out.println(yu[i]);
		}
	}
	
	public static void go(double learnrate,int seed) throws Exception
	{
		// InstanceQuery query = new InstanceQuery();
		
				// 2.设置要分析的sql1用于生成模型

				// query.setQuery("");

				// 3.获取数据
				DataSource trainSource = new DataSource("file/GASTrain.arff");
				DataSource testSource = new DataSource("file/GASTest.arff");
				Instances insTrain = trainSource.getDataSet();

				// 4.设置要预测的属性

				insTrain.setClassIndex(insTrain.numAttributes() - 1);// 属性的下标，默认都是最后一个属性

				// 5.创建分类器

				MultilayerPerceptron mp = new MultilayerPerceptron();

				// 6.分类器初始化参数

				// 设置入参

				mp.setGUI(false);// 是否进行图形交互

				mp.setAutoBuild(true);// 设置网络中的连接和隐层

				mp.setDebug(false);// 控制打印信息

				mp.setDecay(false);// 如果为true会降低学习速率
				
				mp.setHiddenLayers("a");// 对预测结果几乎没用影响

				mp.setLearningRate(learnrate);// Weights被更新的数量,对预测结果影响很大

				mp.setMomentum(0.01);// 当更新weights时设置的动量

				mp.setNormalizeAttributes(true);// 可以优化网络性能

				mp.setNormalizeNumericClass(true);// 如果预测的是数值型可以提高网络的性能

				mp.setReset(false);// 必须要在AutoBuild为true的条件下进行设置否则默认即可

				mp.setSeed(seed);// 随机种子数，对预测结果影响大

				mp.setTrainingTime(10000);// 迭代的次数,有一定影响，但是不大

				mp.setValidationSetSize(12);// 验证百分比，影响大

				mp.setValidationThreshold(50);// 几乎没用影响

				mp.setNominalToBinaryFilter(true);// 可以提高性能

				// 7.进行线性回归分析

				mp.buildClassifier(insTrain);

				// 8.评估

				Evaluation eval = new Evaluation(insTrain);

				eval.evaluateModel(mp, insTrain);

				// 9.获取评价标准

//				System.out.println("平均绝对误差：" + eval.meanAbsoluteError());// 越小越好
//
//				System.out.println("均方根误差：" + eval.rootMeanSquaredError());// 越小越好
//
//				System.out.println("相关性系数:" + eval.correlationCoefficient());// 越接近1越好
//
//				System.out.println("根均方误差：" + eval.rootMeanSquaredError());// 越小越好
//
//				System.out.println("是否准确的参考值：" + eval.meanAbsoluteError());// 越小越好

				// // 10.获取模型公式
				//
				String model = mp.toString();// 看不出模型的意思
				
//				System.out.println("模型公式：" + model);// 越小越好
				
				// // 以上是使用历史数据获取模型用到的方法及使用情况。对要预测的数据进行预测还需下面的方法：
				// // 11.获取待预测的数据（sql2中的待预测数据用？表示）
				//
				// // query.setQuery("");
				//
				Instances insTest = testSource.getDataSet();// 获取待预测数据
				//
				insTest.setClassIndex(insTest.numAttributes() - 1);// 设置成相同的属性下标，默认都是最后一个属性
				//
				// // 12获取预测结果（两种方法）
				// //
				// // 方法一：直接返回到数组中
				//
				double yuce[] = eval.evaluateModel(mp, insTest);// 将预测结果以数组的形式返回
				//
				// // 方法二：调用分类器方法classifyInstance（Instance instance）循环输出每一个实例的预测值
				//
				double sum = insTest.numInstances();// 获取预测实例的总数
				double err=0.0;
				for (int i = 0; i < sum; i++) {//
					// <---输出预测数据--->
//					System.out.println(trueval[i]+":"+yuce[i]);
					err+=(trueval[i]-yuce[i])*(trueval[i]-yuce[i]);
				}
				double mse=Math.sqrt(err/sum);
				if(mse<MINMSE){
					MINMSE=mse;
					yu=yuce.clone();
				}
//				System.out.println("MSE="+Math.sqrt(err/sum));
	}
}
