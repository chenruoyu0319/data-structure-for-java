
import java.util.Scanner;

/**
 * 计一个括号匹配的功能
 */
public class BracketMatchStack {

    public static boolean isOk(String s) {

        MyStack<Character> brackets = new ArrayStack<>(20);
        char c[] = s.toCharArray();
        Character top;
        //O(n)
        for (char x : c) {
            // 遍历输入的字符char
            switch (x) {
                case '{':
                case '(':
                case '[':
                    brackets.push(x);//O(1)
                    break;
                // 匹配
                case '}':
                    top = brackets.pop();//O(1)
                    if (top == null) {
						return false;
					}
                    if (top == '{') {
                        // 弹出的是'{', 则代表匹配上, 跳出switch
                        break;
                    } else {
                        return false;
                    }
                case ')':
                    top = brackets.pop();//O(1)
                    if (top == null) {
                        return false;
                    }
                    // 弹出的是'(', 则代表匹配上, 跳出switch
                    if (top == '(') {
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    top = brackets.pop();//O(1)
                    if (top == null) {
                        return false;
                    }
                    // 弹出的是'[', 则代表匹配上, 跳出switch
                    if (top == '[') {
                        break;
                    } else {
                        return false;
                    }
                default:
                    break;
            }
        }
        return brackets.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            System.out.println(s + "的匹配结果:" + isOk(s));
        }
    }

}
