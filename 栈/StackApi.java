import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @Author: Chen ruoyu
 * @Description: Java Stack类常用api
 * <p>
 * 1    boolean empty()
 * 测试堆栈是否为空。
 * 2	Object peek( )
 * 查看堆栈顶部的对象，但不从堆栈中移除它。
 * 3	Object pop( )
 * 移除堆栈顶部的对象，并作为此函数的值返回该对象。
 * 4	Object push(Object element)
 * 把项压入堆栈顶部。
 * 5	int search(Object element)
 * 返回对象在堆栈中的位置，以 1 为基数。
 * @Date Created in:  2021-11-12 10:47
 * @Modified By:
 */
public class StackApi {

    static void showpush(Stack<Integer> st, int a) {
        st.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }

    static void showpop(Stack<Integer> st) {
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }

    public static void main(String args[]) {
        Stack<Integer> st = new Stack<Integer>();
        System.out.println("stack: " + st);
        showpush(st, 42);
        showpush(st, 66);
        showpush(st, 99);
        showpop(st);
        showpop(st);
        showpop(st);
        try {
            showpop(st);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }
}
