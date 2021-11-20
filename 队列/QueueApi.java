import javax.xml.bind.annotation.XmlAnyAttribute;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @Author: Chen ruoyu
 * @Description: java-queue-api
 * 非阻塞队列不能阻塞，多线程时，当队列满或者队列空时，只能使用队列 wait()，notify() 进行队列消息传送
 * 阻塞队列可以阻塞，当阻塞队列当队列里面没有值时，会阻塞直到有值输入。输入也一样，当队列满的时候，会阻塞，直到队列有空间
 * @Date Created in:  2021-11-19 9:22
 * @Modified By:
 */
public class QueueApi {

    /**
     * 非阻塞队列
     * 对于非阻塞队列，一般情况下建议使用 offer、poll 和 peek 三个方法，不建议使用 add 和 remove 方法。
     * offer、poll 和 peek 三个方法可以通过返回值判断操作成功与否，而使用 add 和 remove 方法却不能达到这样的效果。
     * 注意：非阻塞队列中的方法都没有进行同步措施。
     */
    public void linkedList(){
        // LinkedList 除了实现的 List 接口，也实现了 Deque 接口，可以当做队列来使用
    }

    public void arrayQueue(){
        // ArrayQueue
    }

    /**
     * 非阻塞队列
     */
    public void priorityQueue(){
        //PriorityQueue 类实质上维护了一个有序列表。加入到 Queue 中的元素根据它们的天然排序（通过其 java.util.Comparable 实现）或者根据传递给构造函数的 java.util.Comparator 实现来定位。该队列不允许使用 null 元素也不允许插入不可比较的对象
        //PriorityQueue 队列的头指排序规则最小那个元素。如果多个元素都是最小值则随机选一个。
        //PriorityQueue 是一个无界队列，但是初始的容量（实际是一个Object[]），随着不断向优先级队列添加元素，其容量会自动扩容，无需指定容量增加策略的细节。
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 将元素 e 插入到队列末尾，如果插入成功，则返回 true；如果插入失败（即队列已满），则会抛出异常；
        boolean add = priorityQueue.add(1);
        // 将元素 e 插入到队列末尾，如果插入成功，则返回 true；如果插入失败（即队列已满），则返回 false；
        boolean offer = priorityQueue.offer(1);
        // 获取队首元素，若成功，则返回队首元素，方法失败(即队首为空)则抛出异常
        Integer element = priorityQueue.element();
        // 获取队首元素，若成功，则返回队首元素；否则返回 null
        Integer peek = priorityQueue.peek();
        // 获取并删除队首元素，若成功，则返回队首元素；方法失败(即队首为空)则抛出异常
        Integer remove = priorityQueue.remove();
        // 获取并删除队首元素，若成功，则返回队首元素；否则返回 null
        Integer poll = priorityQueue.poll();
        // Collection接口的方法, 删除指定元素（如果有多个相等，只删除一个）
        boolean remove1 = priorityQueue.remove(1);
    }

    /**
     * 非阻塞队列
     */
    public void concurrentLinkedQueue(){
        //ConcurrentLinkedQueue 是链表的、线程安全的队列（CAS）。并发访问不需要同步。因为它在队列的尾部添加元素并从头部删除它们
        // 所以只要不需要知道队列的大小ConcurrentLinkedQueue，对公共集合的共享访问就可以工作得很好。收集关于队列大小的信息会很慢，需要遍历队列。
    }

    /**
     * 阻塞队列: 一个由数组支持的有界队列
     */
    public void arrayBlockingQueue () throws InterruptedException {
        // 一个由数组支持的有界队列。
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        // 用来向队尾存入元素，如果队列满，则等待；
        arrayBlockingQueue.put(1);
        // 用来从队首取元素，如果队列为空，则等待；
        Integer take = arrayBlockingQueue.take();
        // 用来向队尾存入元素，如果队列满，则等待一定的时间，当时间期限达到时，如果还没有插入成功，则返回false；否则返回true；
        boolean offer = arrayBlockingQueue.offer(1, 3, TimeUnit.SECONDS);
        // 用来从队首取元素，如果队列空，则等待一定的时间，当时间期限达到时，如果取到，则返回null；否则返回取得的元素；
        Integer poll = arrayBlockingQueue.poll(3, TimeUnit.SECONDS);
    }

    /**
     * 阻塞队列: 是一个基于链表实现的可选边界的阻塞队列: 默认大小Integer.MAX_VALUE
     */
    public void linkedBlockingQueue(){
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue<>();
    }

    /**
     * 是一个无界阻塞队列，它使用与PriorityQueue(优先级堆)相同的排序规则，并提供阻塞检索操作。
     */
    public void priorityBlockingQueue(){

    }

    /**
     * 一个由优先级堆支持的、基于时间的调度队列。即一种由延迟元素组成的无界阻塞队列.
     */
    public void delayQueue (){
        //应用场景1: 当用户超时未支付时，给用户发提醒消息。
        //应用场景2: 超时未付款，订单自动取消。通常，订单创建的时候可以向延迟队列种插入一条消息，到时间自动执行。
        //也可以用临时表，把这些未支付的订单放到一个临时表中，或者Redis，然后定时任务去扫描。
        //RocketMQ有延时队列，RibbitMQ也可以实现，Java自带的也有延时队列。
        //DelayQueue是一种由延迟元素组成的无界阻塞队列，在该队列中，仅当元素的延迟到期时才可以使用该元素。队头是已经过期的延迟元素，它已过期时间最长。
        //如果没有过期的延迟，则队列没有头部，此时调用poll将返回null。当调用元素的getDelay(TimeUnit.NANOSECONDS)方法返回值小于或等于0时，就会发生过期。即使元素没有过期，也不能用take或者poll将其删除。
    }

    /**
     * 一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制
     */
    public void synchronousQueue (){

    }

    /**
     * 阻塞队列被设计主要用于生产者-消费者队列
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Bread> queue = new ArrayBlockingQueue<>(5);
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);

        new Thread(p1,"p1").start();
        new Thread(p1,"p2").start();
        new Thread(c1,"c1").start();
        new Thread(c2,"c2").start();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{

    private BlockingQueue<Bread> queue;

    public Producer(BlockingQueue<Bread> queue) {
        this.queue = queue;
    }

    /**
     * 每过2秒生产1个面包
     * @return
     * @throws InterruptedException
     */
    public Bread produce() throws InterruptedException {
        Thread.sleep(Math.round(2000));
        System.out.println("生产了1个面包, 当前线程名: " + Thread.currentThread().getName());
        return new Bread();

    }

    @Override
    public void run() {
        while (true){
            try {
                queue.put(produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{

    private BlockingQueue<Bread> queue;

    public Consumer(BlockingQueue<Bread> queue) {
        this.queue = queue;
    }

    /**
     * 每过2秒消费1个面包
     * @return
     * @throws InterruptedException
     */
    public void consume(Bread bread) throws InterruptedException {
        Thread.sleep(Math.round(2000));
        System.out.println("消费了1个面包, 当前线程名: " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        while (true){
            try {
                consume(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 实体
 */
class Bread{

}