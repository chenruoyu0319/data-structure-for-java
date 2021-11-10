import java.util.concurrent.TimeUnit;

/**
 * 单向循环列表
 */
public class MyCycleLinkedList {

    private CycleListNode head;
    private CycleListNode tail;
    private int size = 0;        //

    /**
     * head 和 tail分开写
     *
     * @param data
     */
    public void insertHead0(int data) {        //插入链表的头部		data就是插入的数据
        CycleListNode newNode = new CycleListNode(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            if (tail == null) {
                tail = head;
                tail.next = newNode;
            } else {
                tail.next = newNode;
            }
            head = newNode;
        }
        //插入O(1)
    }

    /**
     * head 和 tail合并写
     *
     * @param data
     */
    public void insertHead(int data) {        //插入链表的头部		data就是插入的数据
        CycleListNode newNode = new CycleListNode(data);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            tail.next = newNode;
            head = newNode;
        }
        //插入O(1)
    }

    public void insertNth(int data, int position) {        //插入链表的中间 假设定义在第N个插入 O(n)
        if (position == 0) {        //这个表示插入在头部了
            insertHead(data);
        } else {
            CycleListNode cur = head;
            for (int i = 1; i < position; i++) {
                cur = cur.next;        //一直往后遍历   p=p->next;  ->是c++里面的往后找指针
            }
            CycleListNode newNode = new CycleListNode(data);
            //
            newNode.next = cur.next;        //新加的点指向后面 保证不断链
            cur.next = newNode;            //把当前的点指向新加的点
            if (newNode.next == head) {
                tail = newNode;
                tail.next = head;
            }
        }
    }
	/*int a = 1;
	int b = a;
	int a = 2;*/

    public void deleteHead() {//O(1)
        head = head.next;
    }

    public void deleteNth(int position) {//O(n)
        if (position == 0) {
            deleteHead();
        } else {
            CycleListNode cur = head;
            for (int i = 1; i < position; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next; //cur.next 表示的是删除的点，后一个next就是我们要指向的
        }
    }

    public void find(int data) {//O(n)
        CycleListNode cur = head;
        while (cur != null) {
            if (cur.value == data) {
                break;
            }
            cur = cur.next;
        }
    }

    public void print() throws InterruptedException {
        CycleListNode cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println();
    }

    public void getHead() {
        System.out.println("head = " + head.value);
    }

    public void getTail() {
        System.out.println("tail = " + tail.value);
    }


    public static void main(String[] args) throws InterruptedException {
        MyCycleLinkedList myList = new MyCycleLinkedList();
        myList.insertHead(5);
        myList.insertHead(7);
        myList.insertHead(10);
//		myList.print(); // 10 -> 7 -> 5
//		myList.deleteNth(0);
//		myList.print(); // 7 -> 5
//		myList.deleteHead();
//		myList.print(); // 5
        myList.insertNth(11, 3);
        myList.getHead();
        myList.getTail();
        myList.print(); // 5 -> 11
//		myList.deleteNth(1);
//		myList.print(); // 5

    }
}

class CycleListNode {

    int value;        //值
    CycleListNode next;    //下一个的指针

    CycleListNode(int value) {
        this.value = value;
        this.next = null;
    }
}
