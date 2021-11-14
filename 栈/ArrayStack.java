
public class ArrayStack<Item> implements MyStack<Item>{

	/**0
	 * 最好就是开始的时候就设置大小
	 */
	private Item [] a = (Item[]) new Object[10];

	/**
	 * 大小 初始的元素个数
	 */
	private int n = 0;

	/**
	 * 构造方法: 不需要带泛型
	 * @param cap
	 */
	public ArrayStack(int cap) {
		a = (Item[]) new Object[cap];
	}


	/**
	 * 入栈: 时间复杂度 O(1)
	 * @param item
	 * @return
	 */
	@Override
	public void push(Item item) {
		// 入栈前先判断是否需要扩容
		judgeSize();
		a[n++] = item;
	}

	/**
	 * 判断是否需要扩容
	 */
	private void judgeSize(){
		// 元素个数已经达到了数组的长度的75%
		if(n >= a.length * 0.75){
			resize(2 * a.length);		// 10*2*2=40个大小了，我出栈了20个了，只剩下20了吧。
		}else if(n > 0 && n <= a.length / 2 * 0.75){
			// 元素个数不足数组长度的一半
			resize(a.length / 2);
		}
	}

	/**
	 * 扩容O（n）
	 * @param size
	 */
	private void resize(int size){
		Item[] temp = (Item[]) new Object[size];
		for(int i = 0 ; i < n; i ++){
			temp[i] = a[i];
		}
		a = temp;
	}

	/**
	 * //出栈: O(1)
	 * @return
	 */
	@Override
	public Item pop() {
		if(isEmpty()){
			return null;
		}
		// 先--再取值
		Item item = a[--n];
		// 把减完的a[n]至为null
		a[n] = null;
		return item;
	}

	/**
	 * //获取栈顶元素
	 * @return
	 */
	@Override
	public Item getTop() {
		if(isEmpty()){
			return null;
		}
		// 先--再取值
		int i = n - 1;
		Item item = a[i];
		// 把减完的a[n]至为null
		return item;
	}

	/**
	 * 取栈大小
	 * @return
	 */
	@Override
	public int size() {
		return n;
	}

	/**
	 * 判空
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * 获取stack
	 * @return
	 */
	@Override
	public void print(){
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<>(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.print();
		System.out.println("=========================");
		stack.pop();
		stack.print();
	}
}
