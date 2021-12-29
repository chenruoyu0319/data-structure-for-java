package leecode;


import java.util.Arrays;

/**
 * @author cry777
 * @program demo1
 * @description 合并2个数组
 * @create 2021-12-28
 */
public class Solution88 {
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 0 1 2 3 4 5
     * nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 每次从两个数组头部取出比较小的数字放到结果中
     */
    public void merge0(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        // 双指针结束条件
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                // 端口位置特殊判断, p1已经遍历完就继续遍历p2
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                // p1小,p1入队,p1++
                cur = nums1[p1++];
            } else {
                // p2小,p2入队,p2++
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = sorted[i];

        }
    }

    /**
     * 0 1 2 3 4 5
     * nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 每次从两个数组尾部取出比较大的数字放到结果中
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int cur;
        while (p1 > 0 || p2 > 0) {
            if (p1 == 0) {
                cur = nums2[p2--];
            } else if (p2 == 0) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[p1 + p2 + 2] = cur;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1,1,nums2,1);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(i);

        }
    }

}

