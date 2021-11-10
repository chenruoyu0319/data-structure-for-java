import java.util.concurrent.TimeUnit;

/**
 * LRU算法
 * LRU算法思路:每次新增节点时
 * 1.去链表里面遍历，如果找到了，删掉后插入到头部，头部就是最新的链表。
 * 2.如果不在原来的链表里，如果有空间就插入头部，如果没有空间就删除最后一个。
 */
public class MyLRU {

    private LruNode head;
    private LruNode tail;


    /**
     * 链表长度
     */
    private int size = 0;

    private static final int SIZE_MAX = 10;

    /**
     * 插入链表的头部
     *
     * @param data
     */
    public void insertHead(int data) {
        LruNode newNode = new LruNode(data);
        if (head == null){
            tail = newNode;
        } else {
            head.pre = newNode;
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    /**
     * 删除链表头部
     */
    public void deleteHead() {
        head = head.next;
        if (head != null) {
            head.pre = null;
        }
        size--;
    }

    /**
     * 删除链表尾部
     */
    public void deleteTail() {
        LruNode cur = head;
        while (cur.next != tail) {
            cur = cur.next;
        }
        tail = cur;
        tail.next = null;
        size--;
    }

    /**
     * 删除链表尾部
     */
    public void deleteTail(LruNode current) {
        // 移动tail标记
        tail = current.pre;
        tail.next = null;
        size--;
    }

    /**
     * 删除指定数据
     *
     * @param data
     */
    public void deleteKey(int data) {
        LruNode current = head;
        while (current != null) {
            if (current.value == data) {
                break;
            }
            current = current.next;
        }
        // 如果删除的是头部
        if (current == head) {
            deleteHead();
        } else {
            current.pre.next = current.next;
            // 删除的是尾部
            if (current == tail) {
                deleteTail(current);
            } else {
                current.next.pre = current.pre;
            }
        }
        size--;
    }

    /**
     * 遍历列表
     *
     * @param data
     */
    public LruNode find(int data) {
        LruNode cur = head;
        while (cur != null) {
            if (cur.value == data) {
                break;
            }
            cur = cur.next;
        }
        return cur;
    }

    /**
     * LRU: add node
     *
     * @param data
     */
    public void lruAddNode(int data) {
        // 遍历链表
        LruNode lruNode = find(data);
        // 如果新增的节点数据在原链表中
        if (null != lruNode) {
            // 删掉后插入到头部, 头部就是最新的链表。
            deleteKey(data);
            insertHead(data);
        } else {
            // 如果不在原来的链表里,如果有空间就插入头部,如果没有空间就删除最后一个
            if (size < SIZE_MAX) {
                insertHead(data);
            } else {
                deleteTail();
                insertHead(data);
            }
        }

    }

    /**
     * 打印链表
     */
    public void print() {
        LruNode cur = head;
        while (cur != null) {
            System.out.println("cur: " + cur.value);
            cur = cur.next;
        }
    }

    /**
     * 获取头
     */
    public void getHead() {
        System.out.println("head = " + head.value);
    }

    /**
     * 获取尾
     */
    public void getTail() {
        System.out.println("tail = " + tail.value);
    }


    public static void main(String[] args) {
        MyLRU myLRU = new MyLRU();
        for (int i = 10; i > 0; i--) {
            myLRU.lruAddNode(i);
        }
        myLRU.print();
        System.out.println("head: " + myLRU.head.value);
        System.out.println("tail: " + myLRU.tail.value);
        System.out.println("size: " + myLRU.size);
        myLRU.lruAddNode(8);
        myLRU.print();
        System.out.println("head: " + myLRU.head.value);
        System.out.println("tail: " + myLRU.tail.value);
        System.out.println("size: " + myLRU.size);
        myLRU.lruAddNode(11);
        myLRU.print();
        System.out.println("head: " + myLRU.head.value);
        System.out.println("tail: " + myLRU.tail.value);
        System.out.println("size: " + myLRU.size);
    }
}


class LruNode {

    /**
     * 值
     */
    int value;

    /**
     * 下一个的指针
     */
    LruNode next;

    /**
     * 上一个的指针
     */
    LruNode pre;

    LruNode(int value) {
        this.value = value;
        this.next = null;
        this.pre = null;
    }
}
