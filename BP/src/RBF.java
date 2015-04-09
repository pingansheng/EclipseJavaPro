import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.RBFNetwork;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class RBF {

	public static void main(String[] args) throws Exception {
		// InstanceQuery query = new InstanceQuery();

		// 2.����Ҫ������sql1��������ģ��

		// query.setQuery("");

		// 3.��ȡ����
		DataSource trainSource = new DataSource("file/train.arff");
		DataSource testSource = new DataSource("file/test.arff");
		Instances insTrain = trainSource.getDataSet();

		// 4.����ҪԤ�������

		insTrain.setClassIndex(insTrain.numAttributes() - 1);// ���Ե��±꣬Ĭ�϶������һ������

		// 5.����������

		RBFNetwork mp = new RBFNetwork();

		// 6.��������ʼ������

		// �������
		mp.setMaxIts(1121211);
		mp.setRidge(0.12);
		mp.setClusteringSeed(2);
		mp.setDebug(false);// ���ƴ�ӡ��Ϣ

		// 7.�������Իع����

		mp.buildClassifier(insTrain);

		// 8.����

		Evaluation eval = new Evaluation(insTrain);

		eval.evaluateModel(mp, insTrain);

		// 9.��ȡ���۱�׼

		System.out.println("ƽ��������" + eval.meanAbsoluteError());// ԽСԽ��

		System.out.println("��������" + eval.rootMeanSquaredError());// ԽСԽ��

		System.out.println("�����ϵ��:" + eval.correlationCoefficient());// Խ�ӽ�1Խ��

		System.out.println("��������" + eval.rootMeanSquaredError());// ԽСԽ��

		System.out.println("�Ƿ�׼ȷ�Ĳο�ֵ��" + eval.meanAbsoluteError());// ԽСԽ��

		// // 10.��ȡģ�͹�ʽ
		//
		String model = mp.toString();// ������ģ�͵���˼
		System.out.println("ģ�͹�ʽ��" + model);// ԽСԽ��
		// // ������ʹ����ʷ���ݻ�ȡģ���õ��ķ�����ʹ���������ҪԤ������ݽ���Ԥ�⻹������ķ�����
		// // 11.��ȡ��Ԥ������ݣ�sql2�еĴ�Ԥ�������ã���ʾ��
		//
		// // query.setQuery("");
		//
		Instances insTest = testSource.getDataSet();// ��ȡ��Ԥ������
		//
		insTest.setClassIndex(insTest.numAttributes() - 1);// ���ó���ͬ�������±꣬Ĭ�϶������һ������
		//
		// // 12��ȡԤ���������ַ�����
		// //
		// // ����һ��ֱ�ӷ��ص�������
		//
		double yuce[] = eval.evaluateModel(mp, insTest);// ��Ԥ�������������ʽ����
		//
		// // �����������÷���������classifyInstance��Instance instance��ѭ�����ÿһ��ʵ����Ԥ��ֵ
		//
		double sum = insTest.numInstances();// ��ȡԤ��ʵ��������
		for (int i = 0; i < sum; i++) {//
			double goal = insTrain.instance(i).value(4);
			// <---���Ԥ������--->
			System.out.println(goal + " : " + yuce[i]);
		}
	}
}
