import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class CountSort {
	public static void main(String[] args) throws Exception {
		String str = null;
		String fileName = "D:\\JavaEE软开工程师\\算法刷题\\数据结构与算法\\09 算法思想(排序)-贪心算法&动态规划\\ 200w.txt";
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		int data[] = new int[2100002];
		int i = 0;
		while ((str = br.readLine()) != null) {
			// 873.84
			double a = Double.valueOf(str);
			// 87384
			a = a * 100;
			// data[0] = 87384
			data[i++] = (int) a;
			// System.out.println((int) a);
		}
		System.out.println("数据读取完毕，size为" + i);
		long start = System.currentTimeMillis();
		countSort(data, 0, data.length - 1);
		System.out.println("快排消耗的时间为:" + (System.currentTimeMillis() - start) + "ms");
	}

	public static void countSort(int data[], int min, int max) throws Exception {
		int counts[] = new int[max + 1];
		for (int i = 0; i < data.length; i++) {
			// counts[87384] = 1
			// 把data[i]作为下标存起来
			counts[data[i]]++;
		}

		File file = new File("E:\\workspace\\sort\\200w-csort.txt");
		Writer out = new FileWriter(file);

		for (int i = 0; i <= max; i++) {
			if (counts[i] > 0) {
				for (int j = 0; j < counts[i]; j++) {
					out.write(((double) (i / 100.0)) + "\r\n");
				}
			}
		}
		out.close();
	}
}
