/**
 * @Author: Chen ruoyu
 * @Description: 基于双向链表实现的单向队列  头入 尾出
 * @Date Created in:  2021-11-19 15:00
 * @Modified By:
 */
public class DoubleLinkedQueue<T> {

    private DListNode<T> head;

    private DListNode<T> tail;

    /**
     * 链表长度
     */
    private int size = 0;

    /**
     * 入队: 插入链表的头部
     *
     * @param data
     */
    public void push(T data) {
        DListNode<T> newNode = new DListNode<>(data);
        newNode.next = head;
        head = newNode;
        if (tail == null){
            tail = head;
        } else if (size == 1){
            tail.pre = newNode;
            head.next = tail;
        }
        size++;
    }

    /**
     * 出队: 删除链表的尾部
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            size--;
            T originTailValue = tail.value;
            DListNode<T> pre = tail.pre;
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
        DListNode<T> cur = head;
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

class DListNode<T> {

    /**
     * 值
     */
    T value;

    /**
     * 下一个的指针
     */
    DListNode<T> next;

    /**
     * 上一个的指针
     */
    DListNode<T> pre;

    DListNode(T value) {
        this.value = value;
        this.next = null;
        this.pre = null;
    }
}
