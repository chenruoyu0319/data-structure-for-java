
public interface MyStack<Item> {

	/**
	 * 入栈
	 * @param item
	 * @return
	 */
	void push(Item item);

	/**
	 * 出栈
	 * @return
	 */
	Item pop();

	/**
	 * 获取栈顶元素
	 * @return
	 */
	Item getTop();

	/**
	 * 获取大小
	 * @return
	 */
	int size();

	/**
	 * 判斷是否为空
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 打印stack
	 * @return
	 */
	void print();
}
