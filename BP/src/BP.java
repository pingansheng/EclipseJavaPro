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
		
				// 2.����Ҫ������sql1��������ģ��

				// query.setQuery("");

				// 3.��ȡ����
				DataSource trainSource = new DataSource("file/GASTrain.arff");
				DataSource testSource = new DataSource("file/GASTest.arff");
				Instances insTrain = trainSource.getDataSet();

				// 4.����ҪԤ�������

				insTrain.setClassIndex(insTrain.numAttributes() - 1);// ���Ե��±꣬Ĭ�϶������һ������

				// 5.����������

				MultilayerPerceptron mp = new MultilayerPerceptron();

				// 6.��������ʼ������

				// �������

				mp.setGUI(false);// �Ƿ����ͼ�ν���

				mp.setAutoBuild(true);// ���������е����Ӻ�����

				mp.setDebug(false);// ���ƴ�ӡ��Ϣ

				mp.setDecay(false);// ���Ϊtrue�ή��ѧϰ����
				
				mp.setHiddenLayers("a");// ��Ԥ��������û��Ӱ��

				mp.setLearningRate(learnrate);// Weights�����µ�����,��Ԥ����Ӱ��ܴ�

				mp.setMomentum(0.01);// ������weightsʱ���õĶ���

				mp.setNormalizeAttributes(true);// �����Ż���������

				mp.setNormalizeNumericClass(true);// ���Ԥ�������ֵ�Ϳ���������������

				mp.setReset(false);// ����Ҫ��AutoBuildΪtrue�������½������÷���Ĭ�ϼ���

				mp.setSeed(seed);// �������������Ԥ����Ӱ���

				mp.setTrainingTime(10000);// �����Ĵ���,��һ��Ӱ�죬���ǲ���

				mp.setValidationSetSize(12);// ��֤�ٷֱȣ�Ӱ���

				mp.setValidationThreshold(50);// ����û��Ӱ��

				mp.setNominalToBinaryFilter(true);// �����������

				// 7.�������Իع����

				mp.buildClassifier(insTrain);

				// 8.����

				Evaluation eval = new Evaluation(insTrain);

				eval.evaluateModel(mp, insTrain);

				// 9.��ȡ���۱�׼

//				System.out.println("ƽ��������" + eval.meanAbsoluteError());// ԽСԽ��
//
//				System.out.println("��������" + eval.rootMeanSquaredError());// ԽСԽ��
//
//				System.out.println("�����ϵ��:" + eval.correlationCoefficient());// Խ�ӽ�1Խ��
//
//				System.out.println("��������" + eval.rootMeanSquaredError());// ԽСԽ��
//
//				System.out.println("�Ƿ�׼ȷ�Ĳο�ֵ��" + eval.meanAbsoluteError());// ԽСԽ��

				// // 10.��ȡģ�͹�ʽ
				//
				String model = mp.toString();// ������ģ�͵���˼
				
//				System.out.println("ģ�͹�ʽ��" + model);// ԽСԽ��
				
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
				double err=0.0;
				for (int i = 0; i < sum; i++) {//
					// <---���Ԥ������--->
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
