import java.util.concurrent.TimeUnit;

/**
 * @Author: Chen ruoyu
 * @Description: 约瑟夫问题
 * 约瑟夫问题是个有名的问题：N个人围成一圈，从第一个开始报数，第M个将被杀掉，最后剩下一个，其余人都将被杀掉。
 * 例如N=6，M=5，被杀掉的顺序是：5，4，6，2，3，1。
 * 现在问你最后留下的人是谁？
 * 比如N=6，M=Q
 * 留下的就是1
 * 1 2 3 4 5 6 => 6 1 2 3 4 => 6 1 2 3 =>1 2 3 => 1 3 => 1
 * @Date Created in:  2021-11-08 23:00
 * @Modified By:
 */
public class Joseph {

    private Node head;
    private Node tail;
    private int size = 0;

    /**
     * 插入链表的头部
     *
     * @param data
     */
    public void insertHead(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * 插入链表的中间
     *
     * @param data
     * @param position
     */
    public void insertNth(int data, int position) {
        //这个表示插入在头部了
        if (position == 0) {
            insertHead(data);
        } else {
            Node cur = head;
            for (int i = 1; i < position; i++) {
                //一直往后遍历   p=p->next;  ->是c++里面的往后找指针
                cur = cur.next;
            }
            Node newNode = new Node(data);
            //新加的点指向后面 保证不断链
            newNode.next = cur.next;
            //把当前的点指向新加的点
            cur.next = newNode;
            if (newNode.next == head) {
                tail = newNode;
                tail.next = head;
            }
        }
        size++;
    }

    /**
     * 删除头
     */
    public void deleteHead() {
        head = head.next;
        size--;
    }

    /**
     * 删除指定位置的节点, 并将下一个节点作为下一次遍历的head
     *
     * @param position
     */
    public void deleteNth0(int position) throws InterruptedException {
        if (position == 0) {
            deleteHead();
        } else {
            Node cur = head;
            for (int i = 1; i < position; i++) {
                cur = cur.next;
            }
            //cur.next 表示的是删除的点，后一个next就是我们要指向的
            cur.next = cur.next.next;
            head = cur.next;
            tail = cur;
            tail.next = head;
        }
        System.out.println("head: " + head.value + ",tail: " + tail.value + "\n");
        size--;
    }

    /**
     * 删除指定位置的节点, 并将下一个节点作为下一次遍历的head
     *
     * @param position
     */
    public void deleteNth(int position) throws InterruptedException {
        if (position == 0) {
            deleteHead();
        } else {
            Node cur = head;
            for (int i = 1; i < position; i++) {
                cur = cur.next;
            }
            //cur.next 表示的是删除的点，后一个next就是我们要指向的
            cur.next = cur.next.next;
            head = cur.next;
        }
        System.out.println("head: " + head.value + ",tail: " + tail.value + "\n");
        size--;
    }

    /**
     * 打印链表
     *
     * @throws InterruptedException
     */
    public void print() throws InterruptedException {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println();
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

    public void doJoseph(int m, Joseph myList) throws InterruptedException {
        do {
            myList.deleteNth(m - 1);
        } while (myList.size != 1);
        myList.print();
    }

    public static void main(String[] args) throws InterruptedException {
        Joseph myList = new Joseph();
        myList.insertHead(6);
        myList.insertHead(5);
        myList.insertHead(4);
        myList.insertHead(3);
        myList.insertHead(2);
        myList.insertHead(1);
        myList.doJoseph(5,myList);
    }
}

class Node {

    /**
     * 值
     */
    int value;

    /**
     * 下一个的指针
     */
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}

