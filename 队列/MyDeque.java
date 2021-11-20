import java.util.concurrent.RejectedExecutionHandler;

/**
 * @Author: Chen ruoyu
 * @Description: 双向链表实现双端队列
 * @Date Created in:  2021-11-19 17:04
 * @Modified By:
 */
public class MyDeque<T> {

    private DequeListNode<T> head;

    private DequeListNode<T> tail;

    /**
     * 链表长度
     */
    private int size = 0;

    /**
     * 入队: 插入链表的头部
     *
     * @param data
     */
    public void addFirst(T data) {
        DequeListNode<T> newNode = new DequeListNode<>(data);
        if (head == null){
            head = newNode;
        }else {
            newNode.next = head;
            head.pre = newNode;
            head = newNode;
        }
        if (tail == null){
            tail = head;
        } else if (size == 1){
            tail.pre = newNode;
            head.next = tail;
        }
        size++;
    }

    /**
     * 入队: 插入链表的尾部
     *
     * @param data
     */
    public void addLast(T data) {
        DequeListNode<T> newNode = new DequeListNode<>(data);
        if (tail == null){
            tail = newNode;
            head = tail;
        }
        tail.next = newNode;
        newNode.pre = tail;
        tail = newNode;
        size++;
    }

    /**
     * 出队: 删除链表的头部
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size--;
            T popValue = head.value;
            head = head.next;
            return popValue;
        }
    }

    /**
     * 出队: 删除链表的尾部
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size--;
            T originTailValue = tail.value;
            DequeListNode<T> pre = tail.pre;
            // 大于1个元素
            if (tail.pre != null){
                pre.next = null;
                tail = pre;
            }else {
                tail = null;
            }
            return originTailValue;
        }
    }

    /**
     * 判空
     *
     * @return
     */
    public Boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * show queue
     */
    public void show() {
        DequeListNode<T> cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 测试队列头插入/删除功能
     * @param myDeque
     * @param a
     * @param b
     * @param c
     */
    public void testOprFirst(MyDeque<T> myDeque,T a,T b,T c){
        myDeque.addFirst(a);
        myDeque.addFirst(b);
        myDeque.addFirst(c);
        myDeque.show();
        myDeque.removeFirst();
        myDeque.show();
        myDeque.removeFirst();
        myDeque.show();
    }

    /**
     * 测试队列尾插入/删除功能
     * @param myDeque
     * @param a
     * @param b
     * @param c
     */
    public void testOprLast(MyDeque<T> myDeque,T a,T b,T c){
        myDeque.addLast(a);
        myDeque.addLast(b);
        myDeque.addLast(c);
        myDeque.show();
        myDeque.removeLast();
        myDeque.show();
        myDeque.removeLast();
        myDeque.show();
    }

    public static void main(String[] args) {
//        MyDeque<Integer> myDeque = new MyDeque<>();
//        myDeque.testOprFirst(myDeque,1,2,3);
        MyDeque<Integer> myDeque = new MyDeque<>();
        myDeque.testOprLast(myDeque,1,2,3);
        RejectedExecutionHandler
    }
}

class DequeListNode<T> {

    /**
     * 值
     */
    T value;

    /**
     * 下一个的指针
     */
    DequeListNode<T> next;

    /**
     * 上一个的指针
     */
    DequeListNode<T> pre;

    DequeListNode(T value) {
        this.value = value;
        this.next = null;
        this.pre = null;
    }
}
