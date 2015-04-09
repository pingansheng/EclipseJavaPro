package DataETL;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

/**
 * 能耗信息处理 原始值表//id,code,value,time 汇总表 id time type value info
 */
public class EnergyInfoETL {

	public static SqlHelper sqlhelper = new SqlHelper();
	public LinkedHashMap<String, DataNode> infoSet = new LinkedHashMap<String, DataNode>();
	
	/**
	 * 压缩空气 根据日期计算每日消耗
	 * @param timeString
	 */
	public void air(Calendar cal) {
		double value = getSumValue(cal, "锻造空压累计流量");
		DataNode airD_Sum = new DataNode(cal, "压缩空气_累计差", value, "二级计量");
		infoSet.put("压缩空气_累计差", airD_Sum);
	}
	
	/**
	 * 水信息处理
	 */
	public void water(Calendar cal)
	{
		//自来水
		double value = getSumValue(cal, "锻造%自来水");
		DataNode water = new DataNode(cal, "自来水", value,
				"二级计量");
		infoSet.put("自来水", water);
		
		//热水
		value= getSumValue(cal, "锻造%热水_东面偏北")+ getSumValue(cal, "锻造%热水_东面中间");
		DataNode hotwater = new DataNode(cal, "热水", value,
				"二级计量");
		infoSet.put("热水", hotwater);
	}
	
	

	/**
	 * 天然气信息处理
	 */
	public void gas(Calendar cal)
	{
		
		// 天然气二级计量
		double value_pro = getSumValue(cal, "锻造%天然气生产");
		double value_hot = getSumValue(cal, "锻造%天然气采暖");
		double value = value_pro + value_hot;
		DataNode gas = new DataNode(cal, "天然气", value, "二级计量");
		DataNode gas_produ = new DataNode(cal, "天然气生产", value_pro, "二级计量");
		DataNode gas_hot = new DataNode(cal, "天然气采暖", value_hot, "二级计量");
		infoSet.put("天然气", gas);
		infoSet.put("天然气生产", gas_produ);
		infoSet.put("天然气采暖", gas_hot);
		
		//天然气三级计量
		String[] deviceList={"锻造1号350KJ天然气炉",
				"锻造2号350KJ天然气炉",
				"锻造3号350KJ天然气炉",
				"锻造1号175KJ天然气炉",
				"锻造2号175KJ天然气炉",
				"锻造30KJ天然气炉",
				"锻造1号400KJ对击锤",
				"锻造2号400KJ对击锤",
				"锻造125KJ天然气炉",
				"锻造80KJ国产天然气炉",
				"锻造80KJ进口天然气炉",
				"锻造25KJ天然气炉",
				"锻造1号自由锻",
				"锻造2号自由锻"};
		for (int i = 0; i < deviceList.length; i++) {
			value=getSumValue(cal, deviceList[i]);
			DataNode gas_device = new DataNode(cal, deviceList[i], value, "三级计量");
			infoSet.put(deviceList[i], gas_device);
		}
	}
	
	/**
	 * 电信息处理
	 */
	public void elec(Calendar cal)
	{
		
		// 电二级计量
		double t1 = getSumValue(cal, "锻造分厂T1");
		double t2 = getSumValue(cal, "锻造分厂T2");
		double t3 = getSumValue(cal, "锻造分厂T3");
		double t4 = getSumValue(cal, "锻造分厂T4");
		double t5_6 = getSumValue(cal, "锻造分厂T5_6");
		double value = t1+t2+t3+t4+t5_6;
		DataNode ele = new DataNode(cal, "电正向功", value, "二级计量");
		infoSet.put("电正向功", ele);
		
		//电三级计量
		HashMap<String, String> deviceList = new HashMap<String, String>() {
			{
					put("4#台车炉","DZ3AP10C");
					put("5#台车炉","DZ3AP10B");
					put("1600t压力机","DZ2AP4");
					put("400KJ对击锤","DZ2AP3");
					put("6#台车炉","DZ3AP10A");
					put("7#台车炉","DZ3AP10正向");
					put("8#台车炉","DZ3AP9");
					put("9#台车炉","DZ3AP8");
					put("1#台车炉","DZ3AP7");
					put("2#台车炉","DZ3AP6");
					put("10#台车炉","DZ3AP4");
					put("3#台车炉","DZ3AP5");
					put("扩孔机","DZ1AP11下");
					put("1t自由锻锤","DZ1AP11上");
					put("取料机","DZ1AP8");
					put("10t操作机","DZ1AP9");
					put("25KJ电液锤","DZ2AP12");
					put("80KJ电液模锻锤","DZ2AP10");
					put("80KJ电液模锻锤","DZ2AP9");
					put("125KJ电液模锻锤","DZ2AP7");
					put("1250t压力机","DZ2AP5");
			}
		};
		for (Entry<String, String> ent : deviceList.entrySet()) {
			value=getSumValue(cal, ent.getValue());
			DataNode ele_device = new DataNode(cal,"电正向功_"+ent.getKey(), value, "三级计量");
			infoSet.put("电正向功_"+ent.getKey(), ele_device);
		}
	}
	
	/**
	 * 累计类数据计算，返回一天内的使用量
	 */
	private double getSumValue(Calendar cal, String deviceCode) {
		// 计算累计差
		String sql_1 = "SELECT min(Parameter_Value) FROM rawvalue"
				+ getTableName(cal) + " where Parameter_Code like '"+deviceCode+"%' and Parameter_Time like '% 00:00:00%'";
		// 日期自加
		Calendar newcal=Calendar.getInstance();
		newcal.setTime(cal.getTime());
		newcal.add(Calendar.DAY_OF_MONTH, 1);
		
		String sql_2 = "SELECT min(Parameter_Value) FROM rawvalue"
				+ getTableName(newcal) + " where Parameter_Code like '"+deviceCode+"%' and Parameter_Time like '% 00:00:00%'";
		List<Object> obj1 = sqlhelper.executeQuery(sql_1);
		List<Object> obj2=null;
		try {
			obj2= sqlhelper.executeQuery(sql_2);
		} catch (Exception e) {
			if(e.getMessage().contains("doesn't exist")){
				sql_2="SELECT max(Parameter_Value) FROM rawvalue"
						+ getTableName(cal) + " where Parameter_Code like '"+deviceCode+"%' and Parameter_Time like '% 23:45:00%'";
				obj2=sqlhelper.executeQuery(sql_2);
			}
		}
		return getfirstValue(obj2)-getfirstValue(obj1);
	}
	
	/**
	 * 根据日期得到表名
	 * @param c
	 * @return
	 */
	public static String getTableName(Calendar c){
		int year=c.get(Calendar.YEAR);
		int m=c.get(Calendar.MONTH);
		int d=c.get(Calendar.DAY_OF_MONTH);
		DecimalFormat df=new DecimalFormat();
		df.applyPattern("00");
		return year+df.format(m)+df.format(d);
	}
	
	private double getfirstValue(List<Object> sqlreturn){
		return Double.parseDouble(((Object[]) sqlreturn.get(0))[0].toString());
	}
	
	/**
	 * 入库
	 */
	public void store(){
		int num=infoSet.size();
		String[] sqls=new String[num];
		Object[][] params=new Object[num][4];
		int i=0;
		for (Entry<String, DataNode> en : infoSet.entrySet()) {
			DataNode node=en.getValue();
			sqls[i]="insert into energyconsu(time,type,value,info) values(?,?,?,?)";
			params[i][0]=node.time;
			params[i][1]=node.type;
			params[i][2]=node.value;
			params[i][3]=node.info;
			i++;
		}
		sqlhelper.executeUpdate2(sqls, params);
	}
}

/**
 * 信息存储模型
 * @author pingansheng
 *
 */
class DataNode {
	public String time;
	public String type;
	public Double value;
	public String info;

	public DataNode() {
		// TODO Auto-generated constructor stub
	}

	public DataNode(Calendar cal, String type, Double value, String info) {
		this.time = EnergyInfoETL.getTableName(cal);
		this.type = type;
		this.value = value;
		this.info = info;
	}
}
