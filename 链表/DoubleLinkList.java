
public class DoubleLinkList {        // 双向链表

    private DNode head;        //头
    private DNode tail;        // 尾

    DoubleLinkList() {
        head = null;
        tail = null;
    }

    public void inserHead(int data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            tail = newNode;
        } else {
            head.pre = newNode;
            newNode.next = head;
        }
        head = newNode;
    }

    public void inser(int data,int position) {
        if (position == 0) {        //这个表示插入在头部了
            inserHead(data);
        } else {
            DNode cur = head;
            for (int i = 1; i < position; i++) {
                cur = cur.next;        //一直往后遍历   p=p->next;  ->是c++里面的往后找指针
            }
            DNode newNode = new DNode(data);
            //
            newNode.next = cur.next;        //新加的点指向后面 保证不断链
            newNode.pre = cur;   // 新加的点指向当前
            cur.next.pre = newNode;  // 后面的点指向新加的点
            cur.next = newNode;            //把当前的点指向新加的点
            // 平移tail
            if (newNode.next == null){
                tail = newNode;
            }
        }
    }


    public void deleteHead() {
        if (head == null) return;        //没有数据
        if (head.next == null) {        //就一个点
            //tail = null;
            tail = head;
        } else {
            head.next.pre = null;
        }
        head = head.next;
    }

    public void deleteKey(int data) {
        DNode current = head;
        if (current == null){
            System.out.println("没有节点");
        }
        while (current.value != data) {
            if (current.next == null) {
                System.out.println("没找到节点");
                return;
            }
            current = current.next;
        }
        if (current == head) {// 指向下个就表示删除第一个
            deleteHead();
        } else {
            current.pre.next = current.next;
            if (current == tail) {        //删除的是尾部
                tail = current.pre; // 移动tail标记
                tail.next = null;
            } else {
                current.next.pre = current.pre;
            }
        }
    }
}

class DNode {

    int value;        //值
    DNode next;        //下一个的指针
    DNode pre;        //指向的是前一个指针

    DNode(int value) {
        this.value = value;
        this.next = null;
        this.pre = null;
    }
}
