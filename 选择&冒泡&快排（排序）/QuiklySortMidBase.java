import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 经典快速排序: 以左边界为基准值
 */
public class QuiklySortMidBase {

    static int i = 1;

    public static void main(String[] args) {
        int data[] = {45, 28, 80, 90, 50, 16, 100, 10};
        qSort(data, 0, data.length - 1);
        for (int j = 0; j < data.length; j++) {
            System.out.print(data[j] + " ");
        }

    }

    /**
     * 这边有个细节，就是每次调换完以后的ll和rr，都有一方是基准数
     * 全部遍历完后，ll和rr会和基准数的位置重合
     */
    public static void qSort(int data[], int left, int right) {
        int base_idx = mid_idx(data, left, (left + right ) / 2 + 1, right);
        // 就是我们的基准数，取序列的第一个,不能用data[0]
        exchange(data,base_idx,left);
        int base = data[left];
        int ll = left; // 表示的是从左边找的位置
        int rr = right; // 表示从右边开始找的位置
        while (ll < rr) {
            // 从后面往前找比基准数小的数
            while (ll < rr && data[rr] >= base) {
                rr--; // 没找到就--
            }
            if (ll < rr) { // 表示是找到有比之大的
                exchange(data, ll, rr);
                ll++;
            }
            while (ll < rr && data[ll] <= base) {
                ll++;
            }
            if (ll < rr) {
                exchange(data, ll, rr);
                rr--;
            }
        }
        // 肯定是递归 分成了三部分,左右继续快排，注意要加条件不然递归就栈溢出了
        if (left < ll - 1) {
            qSort(data, left, ll - 1);
        }
        if (ll < right - 1) {
            qSort(data, ll + 1, right);
        }
    }

    public static void exchange(int data[], int a, int b) {
        if (a != b) {
            data[a] = data[a] - data[b];
            data[b] = data[a] + data[b];
            data[a] = data[b] - data[a];
        } else {
            return;
        }

    }

    public static int mid_idx(int data[], int left, int mid, int right) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        // 保存值和下标关系
        hashMap.put(data[left], left);
        hashMap.put(data[mid], mid);
        hashMap.put(data[right], right);
        int data0[] = new int[3];
        data0[0] = data[left];
        data0[1] = data[mid];
        data0[2] = data[right];
        // 找一个排序算法，找出中值对应的下标，这边简单处理(建议插入排序)
        QuiklySort.qSort(data0, 0, 2);
        // 获取到中值对应的下标
        Integer midd = hashMap.get(data0[1]);
        return midd;
    }
}
