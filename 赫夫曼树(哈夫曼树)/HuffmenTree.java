import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmenTree {

	HfmNode root;
	List<HfmNode> leafs; // 叶子节点
	Map<Character, Integer> weights; // 叶子节点的权重, a,b,c,d,e

	public HuffmenTree(Map<Character, Integer> weights) {
		this.weights = weights;
		leafs = new ArrayList<HfmNode>();
	}

	/**
	 * 打印解码后的值
	 * @param str
	 * @param code2
	 */
	public void decode(String str,Map<String, Character> code2) {
		String[] split = str.split(",");
		StringBuilder sb = new StringBuilder();
		for (String s:split) {
			String c = code2.get(s).toString();
			sb.append(c);
		}
		System.out.println(sb);
	}

	/**
	 * 打印编码后的值
	 * @param str
	 * @param code
	 */
	public String encode(String str,Map<Character, String> code) {
		char s[] = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c:s) {
			String code0 = code.get(c);
			sb.append(code0).append(",");
		}
		//去掉最后一个逗号
		String sbs = sb.toString();
		String substring = sbs.substring(0, sbs.length() - 1);
		System.out.println(substring);
		return substring;
	}

	/**
	 * 对叶子节点进行编码
	 * @return
	 */
	public Map<Character, String> code() {

		Map<Character, String> map = new HashMap<Character, String>();
		for (HfmNode node : leafs) {
			String code = "";
			Character c = new Character(node.chars.charAt(0)); // 叶子节点肯定只有一个字符
			HfmNode current = node; // 只有一个点
			do {
				if (current.parent != null && current == current.parent.left) { // 说明当前点是左边
					code = "0" + code; // 前向相加保证正向遍历
				} else {
					code = "1" + code;
				}
				current = current.parent;
			} while (current.parent != null); // parent == null就表示到了根节点
			map.put(c, code);
			System.out.println(c + ":" + code);
		}
		return map;

	}

	/**
	 * 对叶子节点进行解码
	 * @return
	 */
	public Map<String, Character> code2() {

		Map<String, Character> map = new HashMap<>();
		for (HfmNode node : leafs) {
			String code = "";
			Character c = new Character(node.chars.charAt(0)); // 叶子节点肯定只有一个字符
			HfmNode current = node; // 只有一个点
			do {
				if (current.parent != null && current == current.parent.left) { // 说明当前点是左边
					code = "0" + code; // 前向相加保证正向遍历
				} else {
					code = "1" + code;
				}
				current = current.parent;
			} while (current.parent != null); // parent == null就表示到了根节点
			map.put(code, c);
			System.out.println(code + ":" + c);
		}
		return map;

	}

	public void creatTree() {
		Character keys[] = weights.keySet().toArray(new Character[0]); // 拿出所有的点
		PriorityQueue<HfmNode> priorityQueue = new  PriorityQueue<HfmNode>(); // jdk底层的优先队列
		for (Character c : keys) {
			HfmNode hfmNode = new HfmNode();
			hfmNode.chars = c.toString();
			hfmNode.fre = weights.get(c); // 权重
			priorityQueue.add(hfmNode); // 首先把我们的优先队列初始化进去
			leafs.add(hfmNode);
		}

		int len = priorityQueue.size();
		for (int i = 1; i <= len - 1; i++) { // 每次找最小的两个点合并
			HfmNode n1 = priorityQueue.poll(); //
			HfmNode n2 = priorityQueue.poll(); // 每次取优先队列的前面两个 就一定是两个最小的

			HfmNode newNode = new HfmNode();
			newNode.chars = n1.chars + n2.chars; // 我们把值赋值一下，也可以不复制
			newNode.fre = n1.fre + n2.fre; // 把权重相加

			// 维护出树的结构
			newNode.left = n1;
			newNode.right = n2;
			n1.parent = newNode;
			n2.parent = newNode;

			priorityQueue.add(newNode);
		}
		root = priorityQueue.poll(); // 最后这个点就是我们的根节点
		System.out.println("构建完成");
	}

	public static void main(String[] args) {
		// a:3 b:24 c:6 d:20 e:34 f:4 g:12
		Map<Character, Integer> weights = new HashMap<Character, Integer>();
		//一般来说：动态的加密，最开始是不知道里面有什么内容的。我们需要一个密码本，往往就是某个字典。如果是英文就用英文字典，统计次数。
		//换密码本
		//静态的文件。针对性的做编码.图像加密,没有特性的。hash加密（MD5）
		weights.put('a', 3);
		weights.put('b', 24);
		weights.put('c', 6);
		weights.put('d', 1);
		weights.put('e', 34);
		weights.put('f', 4);
		weights.put('g', 12);

		HuffmenTree huffmenTree = new HuffmenTree(weights);
		huffmenTree.creatTree();
		Map<Character, String> code = huffmenTree.code();
		Map<String, Character> code2 = huffmenTree.code2();
		String str = "aceg";
		System.out.println("编码后的：");
		String encode = huffmenTree.encode(str, code);
		System.out.println("解码后的：");
		huffmenTree.decode(encode,code2);

	}
/*
 a:10110
b:01
c:1010
d:00
e:11
f:10111
g:100

 * *
 */
}
