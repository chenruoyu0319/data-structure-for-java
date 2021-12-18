import java.io.*;

/**
 * 计数排序
 */
public class CountSort2 {
	public static void main(String[] args) throws Exception {
		String str = null;
		String fileName = "D:\\JavaEE软开工程师\\算法刷题\\数据结构与算法\\09 算法思想(排序)-贪心算法&动态规划\\200w.txt";
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
			data[(int)a]++;
			// System.out.println((int) a);
		}
		System.out.println("数据读取完毕，size为" + i);
		long start = System.currentTimeMillis();
		countSort(data, 0, data.length - 1);
		System.out.println("计数排序消耗的时间为:" + (System.currentTimeMillis() - start) + "ms");
	}

	public static void countSort(int data[], int min, int max) throws Exception {

		File file = new File("D:\\JavaEE软开工程师\\算法刷题\\数据结构与算法\\09 算法思想(排序)-贪心算法&动态规划\\200w-csort.txt");
		Writer out = new FileWriter(file);

		for (int i = 0; i <= max; i++) {
			if (data[i] > 0) {
				for (int j = 0; j < data[i]; j++) {
					out.write(((double) (i / 100.0)) + "\r\n");
				}
			}
		}
		out.close();
	}
}
