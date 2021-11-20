import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @Author: Chen ruoyu
 * @Description: DelayQueue实现订单的定时取消
 * 当订单定时取消需要修改数据库订单状态，但是怎么确定订单什么时候应该改变状态，解决方案有下面两种：
 * 第一种，写个定时器去每分钟扫描数据库，这样更新及时，但是如果数据库数据量大的话，会对数据库造成很大的压力。
 * 第二种，创建订单的时候再订单表里面创建一条记录，然后把这条记录保存到DelayQueue队列里面，并且用一个子线程不断地轮训这个出队的订单。然后进行订单状态修改的状态。
 * @Date Created in:  2021-11-19 11:17
 * @Modified By:
 */
public class DelayQueueDemo {
    /**
     * 需求：实现一个模拟订单自动取消的功能
     * 1. 生成6个订单，从1号到6号订单，它们的创建时间依次递增3秒。
     * 2. 规定如果一个订单在3秒内状态还是“CREATED”状态，那么就改成“CANCELED”状态。
     * 实现思路：
     * 1. 定义订单类，其实现Delayed接口，为啥要实现这个接口呢？是因为存放到DelayQueue里的元素都要实现Delayed接口，用于判断是否到了超时时间。
     * 2. 在main方法里启动两个线程，第一个线程用来往DelayQueue里添加6个订单，这6个订单的创建时间依次递增3秒。另一个线程是循环从DelayQueue里面获取超时的订单，改成取消状态，然后打印出日志出来。超时的条件是该订单的超时时间字段大于或等于当前系统时间。
     */
    // https://blog.csdn.net/ouyunwen/article/details/82383726

    //是否开启自动取消功能
    int isStarted = 1;
    //延迟队列，用来存放订单对象
    DelayQueue<Order> queue = new DelayQueue();

    /**
     * 这里模拟的是订单下达之后，如果一直都还没支付，也就是停留在创建状态的话，就将其改成取消状态。
     *
     * @param args
     */
    public static void main(String[] args) {
        DelayQueueDemo testDelayQueue = new DelayQueueDemo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //新建一个线程，用来模拟定时取消订单job
        Thread t1 = new Thread(() -> {
            System.out.println("开启自动取消订单job,当前时间：" + LocalDateTime.now().format(formatter));
            while (testDelayQueue.isStarted == 1) {
                try {
                    Order order = testDelayQueue.queue.take();
                    order.setStatus("CANCELED");

                    System.out.println("订单：" + order.getOrderNo() + "付款超时，自动取消，当前时间：" + LocalDateTime.now().format(formatter));
                    System.out.println("testDelayQueue" +testDelayQueue.queue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();

        //新建一个线程，模拟提交订单
        Thread t2 = new Thread(() -> {
            //定义最早的订单的创建时间
            long beginTime = System.currentTimeMillis();
            //下面模拟6个订单，每个订单的创建时间依次延后3秒
            testDelayQueue.queue.add(new Order("SO001", "A", 100, "CREATED", new Date(beginTime), new Date(beginTime + 3000)));
            beginTime += 3000L;
            testDelayQueue.queue.add(new Order("SO002", "B", 100, "CREATED", new Date(beginTime), new Date(beginTime + 3000)));
            beginTime += 3000L;
            testDelayQueue.queue.add(new Order("SO003", "C", 100, "CREATED", new Date(beginTime), new Date(beginTime + 3000)));
            beginTime += 3000L;
            testDelayQueue.queue.add(new Order("SO004", "D", 100, "CREATED", new Date(beginTime), new Date(beginTime + 3000)));
            beginTime += 3000L;
            testDelayQueue.queue.add(new Order("SO005", "E", 100, "CREATED", new Date(beginTime), new Date(beginTime + 3000)));
            beginTime += 3000L;
            testDelayQueue.queue.add(new Order("SO006", "F", 100, "CREATED", new Date(beginTime), new Date(beginTime + 3000)));
        });
        t2.start();
    }
}
