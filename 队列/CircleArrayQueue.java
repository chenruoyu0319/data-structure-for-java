/**
 * 循环队列的优势: 内存利用率高, 实现数据的自动平移
 */
public class CircleArrayQueue {

	/**
	 * 	数据
	 */
	private int data[];
	/**
	 * 	头下标
 	 */
	private int head = 0;
	/**
	 * 	尾下标
	 */
	private int tail = 0;
	/**
	 * 数组的大小, 最大的空间
	 */
	private int n = 0;
	/**
	 * 当前已经存了几个数了
	 */
	private int size;

	/**
	 * 初始化构造
	 * @param cap
	 */
	public CircleArrayQueue(int cap){
		data = new int[cap];
		n = cap;
	}

	public void push(int m){
		/**
		 * 队列满
		 */
		if((tail + 1) % n == head){	
			return ; 
		}
		data[tail] = m; // 加入自定义的排序规则, 改造成优先队列
		// 循环队列避免数组越界
		tail = (tail + 1) % n;
	}
	
	public int pop(){
		// 判断空
		if(isEmpty()) {
			return -1;
		}
		int m = data[head];
		head = (head + 1) % n;
		return m;
	}

	public boolean isEmpty(){
		if(head == tail) return true;
		return false;
	}

}
