/**
 * 数组队列
 */
public class ArrayQueue {

	/**
	 * 数据
	 */
	private int data[];
	/**
	 * 头位置
	 */
	private int head = 0;
	/**
	 * 尾位置
	 */
	private int tail = 0;
	/**
	 * 数组的大小 最大的空间
	 */
	private int n = 0;
	/**
	 * 当前已经存了几个数了
	 */
	//private int size;

	/**
	 * 构造函数
	 * @param cap
	 */
	public ArrayQueue(int cap){
		data = new int[cap];
		n = cap;
	}

	/**
	 * 入队列  1 2 3 4 5  时间复杂度: O(1)
	 * @param m
	 */
	public void push(int m){
		// 判断我们这个队列是不是已经满了
		if(tail == n){
//			// tail回到队列尾部
//			tail--;
			// 在这里才去移动比较合适: n=1000,前999都是O(1),最后1次是O(n)
			// 平均时间复杂度: 1/n*n + (n-1)/n*1 ~ n*1/n + (n-1)/n ~ n*2/n
			return ;
		}
		// push完指向下一个位置
		data[tail++] = m;
	}

	/**
	 * 入队列  1 2 3 4 5  时间复杂度: O(1)
	 * @param m
	 */
	public void push2(int m){
		// tail == -1;
		// 判断我们这个队列是不是已经满了
		if(tail == n){
			// 在这里才去移动比较合适: n=1000,前999都是O(1),最后1次是O(n)
			// 平均时间复杂度: 1/n*n + (n-1)/n*1 ~ n*1/n + (n-1)/n ~ n*2/n
			return ;
		}
		// push完指向下一个位置
		data[++tail] = m;
	}

	/**
	 * 出队列  1 2 3 4 5  时间复杂度: O(1)  很大的空间浪费
	 * @return
	 */
	public int pop(){
		//要判断空，怎么判断空？
		if(isEmpty()) {
			return -1;		//表示空
		}
		int m = data[head];
		head ++ ;
		return m;
	}

	/**
	 * 出队列 1 2 3 4 5后每次移动  时间复杂度: O(n)  可优化为: 我们在入队的时候如果没有空间了我们在集中移动一次
	 * @return
	 */
	public int pop2(){
		// 要判断空
		if(isEmpty()) {
			return -1;
		}
		int m = data[head];
		// TODO: 数组移动, 在这里移动会导致时间复杂度O(n)
		for (int i = head; i < tail; i++) {
			// 怕越界所以加一个判断
			if (i != tail - 1) {
				data[i] = data[i + 1];
			} else {
				// 默认为0 就是没存数据的
				data[i] = 0;
			}
		}
		head ++ ;
		return m;
	}

	/**
	 * 队列判空
	 * @return
	 */
	public boolean isEmpty(){
		if(head == tail) {
			return true;
		}
		return false;
	}

}
