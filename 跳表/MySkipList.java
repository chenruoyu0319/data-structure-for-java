package skipList;

/**
 * @author cry777
 * @program demo1
 * @description 定长跳表: 长度为2
 * @create 2022-01-20
 */
public class MySkipList {

    /**
     * 头结点
     */
    SkipListNode head;

    /**
     * 跳表查询
     *
     * @param key
     * @return skipList.SkipListNode
     */
    SkipListNode findNode(int key) {
        if (head == null) {
            return null;
        }
        SkipListNode skipNext = head.secondNext;
        SkipListNode skipPre = null;
        while (skipNext.key < key) {
            skipPre = skipNext;
            skipNext = skipNext.secondNext;
        }
        if (skipNext.key == key) {
            return skipNext;
        } else if (skipPre.next.key == key) {
            return skipPre.next;
        }
        return null;

    }

    /**
     * 跳表插入
     *
     * @param node
     * @return skipList.SkipListNode
     */
    void insertNode(SkipListNode node) {
        if (findNode(node.key) != null) {
            return;
        }
        SkipListNode cur = head;
        SkipListNode pre = null;
        while (cur.key > node.key){
            pre  = cur;
            cur = cur.next;
        }
        pre.next = node;
        pre.secondNext = cur;
        node.next = cur;
        node.secondNext = cur.next;
    }

    /**
     * 跳表删除
     *
     * @param node
     * @return skipList.SkipListNode
     */
    void deleteNode(SkipListNode node){
        SkipListNode cur = head;
        SkipListNode pre = null;
        while (cur.key > node.key){
            pre  = cur;
            cur = cur.next;
        }
        if (cur.key < node.key){
            return;
        }
        pre.next = cur.next;
        pre.secondNext = cur.secondNext;

    }

}


class SkipListNode {

    int key;

    String value;

    SkipListNode next;

    SkipListNode secondNext;
}
