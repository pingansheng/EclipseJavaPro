import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.RBFNetwork;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class RBF {

	public static void main(String[] args) throws Exception {
		// InstanceQuery query = new InstanceQuery();

		// 2.设置要分析的sql1用于生成模型

		// query.setQuery("");

		// 3.获取数据
		DataSource trainSource = new DataSource("file/train.arff");
		DataSource testSource = new DataSource("file/test.arff");
		Instances insTrain = trainSource.getDataSet();

		// 4.设置要预测的属性

		insTrain.setClassIndex(insTrain.numAttributes() - 1);// 属性的下标，默认都是最后一个属性

		// 5.创建分类器

		RBFNetwork mp = new RBFNetwork();

		// 6.分类器初始化参数

		// 设置入参
		mp.setMaxIts(1121211);
		mp.setRidge(0.12);
		mp.setClusteringSeed(2);
		mp.setDebug(false);// 控制打印信息

		// 7.进行线性回归分析

		mp.buildClassifier(insTrain);

		// 8.评估

		Evaluation eval = new Evaluation(insTrain);

		eval.evaluateModel(mp, insTrain);

		// 9.获取评价标准

		System.out.println("平均绝对误差：" + eval.meanAbsoluteError());// 越小越好

		System.out.println("均方根误差：" + eval.rootMeanSquaredError());// 越小越好

		System.out.println("相关性系数:" + eval.correlationCoefficient());// 越接近1越好

		System.out.println("根均方误差：" + eval.rootMeanSquaredError());// 越小越好

		System.out.println("是否准确的参考值：" + eval.meanAbsoluteError());// 越小越好

		// // 10.获取模型公式
		//
		String model = mp.toString();// 看不出模型的意思
		System.out.println("模型公式：" + model);// 越小越好
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
		for (int i = 0; i < sum; i++) {//
			double goal = insTrain.instance(i).value(4);
			// <---输出预测数据--->
			System.out.println(goal + " : " + yuce[i]);
		}
	}
}
