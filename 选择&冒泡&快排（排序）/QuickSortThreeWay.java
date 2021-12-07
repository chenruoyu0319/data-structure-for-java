import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayList;

/**
 * 快速排序的优化: 把与基准值相等的放在中间（与其说放在中间倒不如说交换数组中的位置时放在了一起）
 * 空间换时间版本: 每次遇到数据与基准数相等时，把它们单独放到一个栈里存起来
 */
public class QuickSortThreeWay {

    static int i = 1;

    public static void main(String[] args) {
        int data[] = {45, 28, 80, 90, 50, 16, 100, 45, 45, 45,16};
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

        ArrayQueue<Integer> base_eq = new ArrayQueue<>(data.length);
        int base = data[left]; // 就是我们的基准数，取序列的第一个,不能用data[0]
        int ll = left; // 表示的是从左边找的位置
        int rr = right; // 表示从右边开始找的位置
        while (ll < rr) {
            // 从后面往前找比基准数小的数
            while (ll < rr && data[rr] >= base) {
                if (data[rr] > base) {
                    rr--; // 没找到就--
                } else if (data[rr] == base) {
                    base_eq.add(rr);
                    rr--;
                }
            }
            if (ll < rr) { // 表示是找到有比之大的
                exchange(data, ll, rr);
                ll++;
            }
            while (ll < rr && data[ll] <= base) {
                if (data[ll] < base) {
                    ll++;
                } else {
                    base_eq.add(ll);
                    rr--;
                }
            }
            if (ll < rr) {
                exchange(data, ll, rr);
                rr--;
            }
        }
        // 每次排序完以后，把和base相等的值和base+1的位置交换一下
        // 因为每次排序完ll==rr==base，所以操作起来很方便
        for (int j = 1; j < base_eq.size() + 1; j++) {
            int base_eq_idx = base_eq.get(j - 1);
            int temp = data[base_eq_idx];
            data[base_eq_idx] = data[ll + j];
            data[ll + j] = temp;
        }

        // 肯定是递归 分成了三部分,左右继续快排，注意要加条件不然递归就栈溢出了
        if (left < ll - 1) {
            qSort(data, left, ll - 1);
        }
        if (ll < right - 1) {
            qSort(data, ll + 1 + base_eq.size(), right);
        }

    }

    public static void exchange(int data[], int a, int b) {
        if (a!=b){
            data[a] = data[a] - data[b];
            data[b] = data[a] + data[b];
            data[a] = data[b] - data[a];
        }else {
            return;
        }

    }

}
