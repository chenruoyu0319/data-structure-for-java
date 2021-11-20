/**
 * @Author: Chen ruoyu
 * @Description: 基于单向链表实现的单向队列, 头出 尾入
 * @Date Created in:  2021-11-19 15:00
 * @Modified By:
 */
public class LinkedQueue<T> {

    private ListNode<T> head;

    private ListNode<T> tail;

    /**
     * 链表长度
     */
    private int size = 0;

    /**
     * 入队: 插入链表的尾部
     *
     * @param data
     */
    public void push(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        if (tail == null){
            tail = newNode;
            head = tail;
        }
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    /**
     * 出队: 删除链表的头部
     */
    public T pop() {
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
        ListNode<T> cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.push(1);
        linkedQueue.push(2);
        linkedQueue.push(3);
        linkedQueue.show();
        linkedQueue.pop();
        linkedQueue.show();
        linkedQueue.pop();
        linkedQueue.show();
    }
}

class ListNode<T> {

    /**
     * 值
     */
    T value;

    /**
     * 下一个的指针
     */
    ListNode<T> next;

    ListNode(T value) {
        this.value = value;
        this.next = null;
    }
}
