/**
 * @Author: Chen ruoyu
 * @Description: 希尔排序
 * @Date Created in:  2021-11-27 23:07
 * @Modified By:
 */
public class ShellSort {
    public static void main(String[] args) {
        int a[] = {9, 8, 7, 0, 1, 3, 2};
        int[] sort = sort(a);
        for (int i = 0; i < sort.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * 希尔排序
     *
     * @param a
     */
    public static int[] sort(int a[]) {
        int n = a.length;
        int gap = n;
        while (gap >= 1) {
            gap = gap / 2;
            //加一层分段, 加一层以步长gap为总循环次数的最外层循环,次外层i增加的步长为gap
            for (int g = 0; g < gap; g++) {
                for (int i = g + gap; i < n; i = i + gap) {        //为什么i要从0开始？ 第一个也需要分段，我们就把数组从i分开，[0,i)的认为已经排好序
                    int j = i - 1;
                    int data = a[i];
                    for (; j >= 0; j--) {     //从已排序序列的尾到从已排序序列的头
                        if (a[j] > data) {
                            a[j + 1] = a[j];        // 数据往后移动
                        } else {    //因为前面已经是排好序的 那么找到一个比他小的就不用找了，因为前面的肯定更小
                            break; //O(1)		如果这个break执行的越多 那么我是不是效率就越高?
                        }
                    }
                    a[j + 1] = data;
                }

            }
        }
        return a;
    }
}
