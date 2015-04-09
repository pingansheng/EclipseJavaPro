package DataETL;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeatherInfo {

	private static String getWeather(String path) {
		Document doc = null;
		try {
			doc = Jsoup
					.connect(path)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) "
									+ "AppleWebKit/537.36 (KHTML, like Gecko) "
									+ "Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.20")
					.execute().parse();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(new File("file/wea.txt"),true);
			bw = new BufferedWriter(fw);

			if (doc != null) {
				Element content = doc.getElementById("main");
				if (content != null) {
					Elements links = content.getElementsByTag("div");
					for (Element link : links) {
						String classname = link.attr("class");
						if ("tqtongji2".equals(classname)) {
							// 入库
							Elements uls = link.getElementsByTag("ul");
							for (Element ul : uls) {
								if(ul.attr("class").equals("t1")){
									continue;
								}
								Elements lis = ul.getElementsByTag("li");
								StringBuffer sb = new StringBuffer();
								for (Element li : lis) {
									sb.append(li.text() + "  ");
								}
								bw.write(sb.toString()+"\n");
							}
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return "N/A";
	}

	public static void main(String[] args) {
		String path = "http://lishi.tianqi.com/zhangjiakou/";
		DecimalFormat df = new DecimalFormat("00");
		for (int i = 5; i < 13; i++) {
			getWeather(path + "2013" + df.format(i) + ".html");
			System.out.println("2013" + df.format(i)+"获取完毕");
		}
		for (int i = 1; i < 8; i++) {
			getWeather(path + "2014" + df.format(i) + ".html");
			System.out.println("2014" + df.format(i)+"获取完毕");
		}
	}
}
