
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.imageio.stream.FileImageInputStream;

public class AgeStas {

	public static void main(String[] args) throws Exception {
		String str = null;
		String fileName = "D:\\JavaEE软开工程师\\算法刷题\\数据结构与算法\\02 基础数据结构\\age1.txt";
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName),"UTF-8");

		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(isr);
		int tot = 0 ;	// 14亿
		// 缓存桶
		int data[] = new int[200];
		while((str = br.readLine()) != null){		//一行一行的读 O(n)
			int age = Integer.valueOf(str);
			data[age] ++ ;
			tot ++ ;
		}
		// O(n) 14亿.
		// 单核Cpu处理速度(考虑了和内存的交互速度): 100万/秒 - 1000万/秒
		// 100s ~ 1000s之间
		// 16核 6s ~ 60s之间
		System.out.println("总共的数据大小: " + tot);
		
		for(int i = 0 ; i < 200 ; i ++){//下标从0开始的
			System.out.println(i + ":" + data[i]);
		}
		// 43757ms => 43s(linux服务器时间再缩短10倍)
		System.out.println("计算花费的时间为:" + (System.currentTimeMillis() - start) + "ms");
	}
}
