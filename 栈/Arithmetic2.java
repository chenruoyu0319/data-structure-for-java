import java.util.Scanner;

/**
 * @Author: Chen ruoyu
 * @Description: 四则运算器 支持各种大小的整数运算
 * 思路: 1.遇到是数字 我们就直接入栈到数字栈里面去。
 * 2.遇到是符号 就把符号栈的栈顶拿出来做比较。如果说他比栈顶符号的优先级高就直接入栈，
 * 如果比符号栈顶的优先级低或者相同，就从符号栈里面取栈顶进行计算（从数字栈中取栈顶的2个数），计算完的结果还要再放入到数字栈中。
 * @Date Created in:  2021-11-13 20:55
 * @Modified By:
 */
public class Arithmetic2 {

    public int calculator(String s) {

        ArrayStack<String> dataStack = new ArrayStack<>(20);
        ArrayStack<String> symbolStack = new ArrayStack<>(20);
        String[] strings = s.split("(?<=[\\+\\-\\*\\/\\=])|(?=[\\+\\-\\*\\/\\=])");
        String symbolTop;
        String dataTop1;
        String dataTop2;
        int i = 0;
        // 1+3*4-500=,以'='为结束符
        while(!"=".equals(strings[i])){
            String x = strings[i++];
            // 遍历输入的字符char
            switch (x) {
                case "+":
                case "-":
                case "*":
                case "/":
                    symbolTop = symbolStack.getTop();
                    // 如果符号栈是空的,就直接入栈
                    if (null == symbolTop){
                        symbolStack.push(x);
                    } else {
                        boolean compareRes = compareRule(x, symbolTop);
                        if (compareRes) {
                            symbolStack.push(x);
                        } else {
                            symbolTop = symbolStack.pop();
                            dataTop1 = dataStack.pop();
                            dataTop2 = dataStack.pop();
                            int calRes = calculate(dataTop1, dataTop2,symbolTop);
                            dataStack.push(String.valueOf(calRes));
                            i--;
                        }
                    }
                    break;
                default:
                    dataStack.push(x);
                    break;
            }
        }
        // 计算最后的值
        symbolTop = symbolStack.pop();
        dataTop1 = dataStack.pop();
        dataTop2 = dataStack.pop();
        int res = calculate(dataTop1, dataTop2,symbolTop);
        return res;
    }

    /**
     * @param target 待比较的字符
     * @param source 栈内的字符
     * @return
     */
    private boolean compareRule(String target, String source){
        if ((target == "*" || target == "/") && (source == "+" || source == "-")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算
     * @param data1 先弹出的data
     * @param data2 后弹出的data
     * @param symbol 符号
     * @return
     */
    private int calculate(String data1, String data2, String symbol){

        int num1 = Integer.valueOf(data1);
        int num2 = Integer.valueOf(data2);
        switch (symbol) {
            case "+":
                return num1 + num2;
            case "-":
                return num2 - num1;
            case "*":
                return num1 * num2;
            case "/":
                return num2 / num1;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入计算表达式:");
        Arithmetic2 arithmetic = new Arithmetic2();
        while (scanner.hasNext()) {
            String s = scanner.next();
            System.out.println(s + "的计算结果:" + arithmetic.calculator(s));
        }

    }
}
