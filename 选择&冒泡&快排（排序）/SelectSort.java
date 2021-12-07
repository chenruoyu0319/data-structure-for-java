/**
 * @Author: Chen ruoyu
 * @Description: 选择排序
 * @Date Created in:  2021-11-29 1:24
 * @Modified By:
 */
public class SelectSort {
    public static void main(String[] args) {
        int a[] = {2, 9, 8, 5, 6, 2, 7, 8};
        int n = a.length;
        for (int i = 0; i < n; i++) { //从第1个数开始选择
            int minloc = i; //未排序的最小值下标
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minloc]){
                    minloc = j;
                }
            }
            SwapRes swap = swap(a[i], a[minloc]);
            a[i] = swap.a;
            a[minloc] = swap.b;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }

    private static SwapRes swap(int a,int b){
        // 1 3
        a = a - b; // -2
        b = a + b; // 1
        a = b - a; // 3
        SwapRes swapRes = new SwapRes();
        swapRes.a = a;
        swapRes.b = b;
        return swapRes;
    }
}

class SwapRes{
    int a;
    int b;
}
