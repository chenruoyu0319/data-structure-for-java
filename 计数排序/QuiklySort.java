import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;

public class QuiklySort {
    public static void main(String[] args) throws Exception {

        //int data[] = { 4, 5, 6, 3, 2, 1 };
        String str = null;
        String fileName = "D:\\JavaEE软开工程师\\算法刷题\\数据结构与算法\\09 算法思想(排序)-贪心算法&动态规划\\200w.txt";
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        double data[] = new double[2100002];
        int i = 0;
        while ((str = br.readLine()) != null) {
            data[i++] = Double.valueOf(str);
        }
        System.out.println("数据读取完毕，size为" + i);
        long start = System.currentTimeMillis();
        qSort(data, 0, data.length - 1);

        File file = new File("D:\\JavaEE软开工程师\\算法刷题\\数据结构与算法\\09 算法思想(排序)-贪心算法&动态规划\\200w-qsort.txt");
        Writer out = new FileWriter(file);
        for (i = 0; i < data.length; i++) {
            out.write(String.valueOf(data[i]) + "\r\n");
        }
        out.close();
        System.out.println("快排消耗的时间为:" + (System.currentTimeMillis() - start) + "ms");
        //System.out.println(Arrays.toString(data));
    }

    public static void qSort(double data[], int left, int right) {

        double base = data[left];
        int ll = left;
        int rr = right;
        while (ll < rr) {
            while (ll < rr && data[rr] >= base) {
                rr--;
            }
            if (ll < rr) {
                double temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                ll++;
            }
            while (ll < rr && data[ll] <= base) {
                ll++;
            }
            if (ll < rr) {
                double temp = data[rr];
                data[rr] = data[ll];
                data[ll] = temp;
                rr--;
            }
        }
        if (left < ll - 1)
            qSort(data, left, ll - 1);
        if (ll + 1 < right)
            qSort(data, ll + 1, right);
    }
}
