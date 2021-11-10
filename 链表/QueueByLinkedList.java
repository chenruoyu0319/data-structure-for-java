import java.util.LinkedList;

/**
 * @Author: Chen ruoyu
 * @Description: 使用linkedList来实现队列效果
 * @Date Created in:  2021-11-01 1:56
 * @Modified By:
 */
public class QueueByLinkedList {
    public static void main(String[] args) {
        Queue queue = new Queue();
        System.out.println(Integer.MAX_VALUE);
        for (int i = 0; i < 10; i++) {
            queue.put(Integer.toString(i));
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.get());
        }
    }
}

class Queue {
    private LinkedList list = new LinkedList();
    public void put(Object v) {
        list.addFirst(v);
    }
    public Object get() {
        return list.removeLast();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

