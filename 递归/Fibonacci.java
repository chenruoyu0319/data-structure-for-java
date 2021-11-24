/**
 * 斐波那契数列 / 阶乘数列
 */
public class Fibonacci {

    /**
     * 斐波那契数列 普通递归
     * 1 1 2 3 5 8 13
     * f(n)=f(n-1)+f(n-2)
     * 分析一段代码好坏，有两个指标，时间复杂度和空间复杂度 都是：O(2^n)
     *
     * @param n
     * @return
     */
    public static int fab(int n) {
        // 递归的终止条件
        if (n <= 2) {
            return 1;
        }
        // 继续递归的过程
        return fab(n - 1) + fab(n - 2);
    }

    /**
     * 求N的阶乘 普通递归
     * 5! = 5*4*3*2*1=> f(n) = n * f(n-1)
     *
     * @param n
     * @return
     */
    public static int fac(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * fac(n - 1);
    }

    /**
     * 斐波那契数列 不用递归 用普通循环
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     * 用for循环保存中间结果
     *
     * @param n
     * @return
     */
    public static int noFab(int n) {
        // 循环
        if (n <= 2) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 初始换全部是0
     */
    private static int data[];

    /**
     * 斐波那契数列, 加了缓存优化, 将时间复杂度降为O(n), 空间复杂度也降至为O(n)
     * 使用数组保存中间结果。
     *
     * @param n
     * @return
     */
    public static int fab2(int n) {
        // 递归的终止条件
        if (n <= 2) {
            return 1;
        }
        if (data[n] > 0) {
            return data[n];
        }
        // 继续递归的过程
        int res = fab2(n - 1) + fab2(n - 2);
        data[n] = res;
        return res;
    }

    /**
     * 尾递归: 以当前的角度来想
     * f(6) -> f(5) -> f(4) , f(6) = f(5) + f(4)
     *
     * @param pre 下下一个的值, f(4)
     * @param res 下一个的值, 比如当前是f(6), res就是f(5)
     * @param res 实际上就是当前值, 或者说是下一个的当前值, 填对应的公式
     * @param n
     * @return
     */
    public static int tailfab(int pre, int res, int n) { // 分析一段代码好坏，有两个指标，时间复杂度和空间复杂度 都是： O(n)
        if (n <= 2) {
            return res; // 递归的终止条件
        }
        // 隐含计算公式
        /**
         * 大概描述一下计算过程: 比如当前是f(6),pre就是f(4),res就是f(5),
         * 下一次的迭代传入的res就是f(6)(他的值是f(4)+f(5)),pre就是f(5)
         * 下下一次的迭代传入的res就是f(5)(他的值就是f(4) + f(3)),pre就是f(4)
         * 下下下一次的迭代传入的res就是f(4),pre就是f(3)
         * 画一下图可以看到符合这道题要求的树形结构
         */
        return tailfab(res, pre + res, n - 1);        //JDK源码

    }

    /**
     * 尾递归: 以当前的角度来想
     * f(6) -> f(5) -> f(4) , f(6) = 6 * f(5)
     *
     * @param n
     * @param res 就是我们每次保存的结果, 初始值传入1开启递归(要根据终止条件来变化), 代表下一个的当前值
     * @param res 实际上就是当前值, 或者说是下一个的当前值, 填对应的公式
     *            整个递归的过程: res * (n * n-1 * n-2 ...)
     * @return
     */
    public static int tailFac(int n, int res) {
        System.out.println(n + ":" + res);
        if (n <= 1) {
            return res;
        }
        // 隐含计算公式
        return tailFac(n - 1, n * res);    //倒着算
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ":" + tailfab(1, 1, i) + " 所花费的时间为"
                    + (System.currentTimeMillis() - start) + "ms");
        }
//        tailFac(5, 1);

        /*
         * for (int i = 1; i <= 45; i++) { long start =
         * System.currentTimeMillis(); System.out.println(i + ":" + fab(i) +
         * " 所花费的时间为" + (System.currentTimeMillis() - start) + "ms"); }
         */

		/*for (int i = 1; i <= 45; i++) {
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + noFab(i) + " 所花费的时间为"
					+ (System.currentTimeMillis() - start) + "ms");
		}
		System.out.println("==================================");
		System.out.println("加了缓存的情况");
		for (int i = 1; i <= 45; i++) {
			data = new int[46];
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + fab2(i) + " 所花费的时间为"
					+ (System.currentTimeMillis() - start) + "ms");
		}*/
    }

}
