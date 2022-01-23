import java.util.Arrays;

/**
 * @Author: Chen ruoyu
 * @Description: 维护10个热点词汇
 * 给你1亿个不重复的数字（整数，1~2^32-1），求出top10。前10大的数字，还可动态添加新数字，但总个数不会超过1亿。
 * @Date Created in:  2022-01-23 11:58
 * @Modified By:
 */
public class HotWords {
    public static void main(String[] args) {
        int data[] = {8, 4, 20, 7, 3, 1, 25, 14, 17, 30};
        int len = data.length;
        // 遍历全部节点建一个小顶堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            HeapSort.minHeap(data, i, len);
        }
        System.out.println(Arrays.toString(data));
        top10(data,100);
        top10(data,20);
        top10(data,30);
        // 最后做一次排序即可
        System.out.println("10大热点数据: ");
        HeapSort.heapSort(data);
        System.out.println(Arrays.toString(data));
    }

    /**
     * 每次新进来的数, 和data[0]进行比较, 如果比data[0]大就做个替换,然后做一次堆化
     * @param data
     * @param target
     */
    public static void top10(int data[], int target) {
        if (target > data[0]) {
            data[0] = target;
            HeapSort.minHeap(data,0,data.length);
        }
        System.out.println(Arrays.toString(data));
    }
}
