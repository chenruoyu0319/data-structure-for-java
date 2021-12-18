import java.io.*;

/**
 * ��������
 */
public class CountSort2 {
	public static void main(String[] args) throws Exception {
		String str = null;
		String fileName = "D:\\JavaEE������ʦ\\�㷨ˢ��\\���ݽṹ���㷨\\09 �㷨˼��(����)-̰���㷨&��̬�滮\\200w.txt";
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
		System.out.println("���ݶ�ȡ��ϣ�sizeΪ" + i);
		long start = System.currentTimeMillis();
		countSort(data, 0, data.length - 1);
		System.out.println("�����������ĵ�ʱ��Ϊ:" + (System.currentTimeMillis() - start) + "ms");
	}

	public static void countSort(int data[], int min, int max) throws Exception {

		File file = new File("D:\\JavaEE������ʦ\\�㷨ˢ��\\���ݽṹ���㷨\\09 �㷨˼��(����)-̰���㷨&��̬�滮\\200w-csort.txt");
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
