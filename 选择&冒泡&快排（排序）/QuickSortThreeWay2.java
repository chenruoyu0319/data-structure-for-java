/**
 * 快速排序的优化2
 * 具体过程有两步：一.在划分过程中将与基准值相等的元素放入数组两端；二.划分结束后，再将两端的元素移到基准值周围。
 */
public class QuickSortThreeWay2 {

    static int i = 1;
    //int data[] = {16, 28, 80, 90, 50, 16, 100, 45, 45, 45,45};   ll = 28  rr = 45
    //int data[] = {16, 28, 45, 90, 50, 16, 100, 45, 45, 45,80};   ll = 45  rr = 45
    //int data[] = {16, 28, 45, 90, 50, 16, 100, 45, 45, 80,45};   ll = 45  rr = 45
    //int data[] = {16, 28, 45, 90, 50, 16, 100, 45, 80, 45,45};   ll = 45  rr = 45
    //int data[] = {16, 28, 45, 90, 50, 16, 100, 80, 45,45,45};   ll = 45  rr = 45
    //int data[] = {16, 28, 45, 90, 50, 16, 100, 80, 45,45,45};   ll = 45  rr = 100
    //int data[] = {16, 28, 16, 90, 50, 45,100, 80, 45,45,45};   ll = 90 rr = 45
    //int data[] = {16, 28, 16, 45, 50, 90,100, 80, 45,45,45};   ll = 45 rr = 50
    //int data[] = {16, 28, 16, 45, 50, 90,100, 80, 45,45,45};   ll = 45 rr = 45
    //int data[] = {16, 28, 16, 45, 45, 45,45, 80,100,90,50};   ll = 45 rr = 45
    public static void main(String[] args) {
        int data[] = {45, 28, 80, 90, 50, 16, 100, 45, 45, 45, 16};
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

        int base = data[left]; // 就是我们的基准数，取序列的第一个,不能用data[0]
        int ll = left; // 表示的是从左边找的位置
        int rr = right; // 表示从右边开始找的位置
        int kr = 0;
        int kl = 0;
        while (ll < rr) {
            // 从后面往前找比基准数小的数
            while (ll < rr && data[rr] >= base) {
                if (data[rr] > base) {
                    rr--; // 没找到就--
                } else if (data[rr] == base) {
                    // 右边找到一次, 次数+1, 把他放到最右边
                    exchange(data, rr, right - kr);
                    rr--;
                    kr++;
                }
            }
            if (ll < rr) { // 表示是找到有比之大的
                exchange(data, ll, rr);
                ll++;     //int data[] = {16, 28, 45, 90, 50, 16, 100, 45, 80, 45,45};   ll = 45  rr = 45
            }
            while (ll < rr && data[ll] <= base) {
                if (data[ll] < base) {
                    ll++;
                } else {
                    // 左边找到一次, 次数+1, 把他放到最左边
                    exchange(data, left + kl, ll);
                    ll++;
                    kl++;
                }
            }
            if (ll < rr) {
                exchange(data, ll, rr);
                rr--;
            }
        }
        // 每次排序完以后，把和base相等的值交换到base两边
        // 因为每次排序完ll==rr==base，所以操作起来很方便
        // 交换右边
        for (int j = 0; j < kr; j++) {
            exchange(data, right - j, rr + j + 1);
        }
        // 交换左边
        for (int j = 0; j < kl; j++) {
            exchange(data, left + j, ll - j - 1);
        }
        // 肯定是递归 分成了三部分,左右继续快排，注意要加条件不然递归就栈溢出了
        if (left < ll - 1) {
            qSort(data, left, ll - 1 - kl);
        }
        if (ll < right - 1) {
            qSort(data, ll + 1 + kr, right);
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
