import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: Chen ruoyu
 * @Description:
 * @Date Created in:  2021-11-01 1:29
 * @Modified By:
 */
public class LinkedListApi {
    public static void main(String[] args) {
        LinkedList<Object> lList = new LinkedList<>();
        lList.add("1");
        lList.add("2");
        lList.add("3");
        lList.add("4");
        lList.add("5");
        lList.add(1,"6");
        System.out.println("链表的第一个元素是 : " + lList.getFirst());
        System.out.println("链表的第二个元素是 : " + lList.get(1));
        System.out.println("链表最后一个元素是 : " + lList.getLast());
        // 删除链表的第一个和最后一个元素
//        lList.removeFirst();
//        lList.removeLast();
        // 返回元素第一次出现的索引
        System.out.println(lList.indexOf("2"));
        // 返回元素最后一次出现的索引
        System.out.println(lList.lastIndexOf("2"));
        // 根据范围删除列表元素
        System.out.println(lList);
        lList.subList(2, 5).clear();
        System.out.println(lList);

    }
}
