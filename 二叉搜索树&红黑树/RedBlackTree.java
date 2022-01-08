import java.util.Arrays;

/**
 * 红黑树
 */
public class RedBlackTree {

	private final int R = 0;
	private final int B = 1;
	private final Node nil = new Node(-1);
	private Node root = nil;

	private class Node {

		int key = -1; // data
		int color = B; // 颜色
		Node left = nil; // nil表示的是叶子结点
		Node right = nil;
		Node p = nil; //父节点

		Node(int key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", color=" + color + ", left=" + left.key + ", right=" + right.key + ", p="
					+ p.key + "]" + "\r\n";
		}
	}

	/**
	 * 中序遍历
	 */
	public void printTree(Node node) {
		if (node == nil) {
			return;
		}
		printTree(node.left);
		System.out.print(node.toString());
		printTree(node.right);
	}

	/**
	 * 红黑树的插入
	 * @param node
	 */
	private void insert(Node node) {
		Node temp = root;
		if (root == nil) {
			root = node;
			node.color = B;
			node.p = nil;
		} else {
			// 新插入节点均为红色
			node.color = R;
			while (true) {
				// 根节点大 我们放到左边
				if (node.key < temp.key) {
					if (temp.left == nil) {
						temp.left = node;
						node.p = temp;
						break;
					} else {
						temp = temp.left;
					}
				// 根节点小 我们放到右边
				} else if (node.key >= temp.key) {
					if (temp.right == nil) {
						temp.right = node;
						node.p = temp;
						break;
					} else {
						temp = temp.right;
					}
				}
			}
			// 插入完后,调整红黑树结构
			fixTree(node);
		}
	}

	/**
	 * 重构树结构
	 * 1.变颜色的情况：
	 * 当前结点的父亲是红色，且它的祖父结点的另一个子结点也是红色。（叔叔结点）：
	 * （1）把父节点设为黑色
	 * （2）把叔叔也设为黑色
	 * （3）把祖父也就是父亲的父亲设为红色（爷爷）
	 * （4）把指针定义到祖父结点(爷爷)设为当前要操作的.
	 * 2.左旋：当前父结点是红色，叔叔是黑色的时候，且当前的结点是右子树。左旋以父结点作为左旋。指针变换到父亲结点
	 * 3.右旋：当前父结点是红色，叔叔是黑色的时候，且当前的结点是左子树。右旋
	 * （1）把父结点变为黑色
	 * （2）把祖父结点变为红色 （爷爷）
	 * （3）以祖父结点旋转（爷爷）
	 * @param node
	 */
	private void fixTree(Node node) {
		// 如果父节点存在且为红色
		while (node.p.color == R) {
			// 叔叔节点
			Node uncle = nil;
			// 若“父节点”是“祖父节点的左孩子”
			if (node.p == node.p.p.left) {
				// 叔叔节点
				uncle = node.p.p.right;
				// Case 1条件：叔叔节点是红色
				if (uncle != nil && uncle.color == R) {
					// 把父节点设为黑色
					node.p.color = B;
					// 把叔叔也设为黑色
					uncle.color = B;
					// 把祖父也就是父亲的父亲设为红色（爷爷）
					node.p.p.color = R;
					//把指针定义到祖父结点(爷爷)设为当前要操作的
					node = node.p.p;
					continue;
				}
				// Case 2条件：叔叔是黑色，且当前节点是右孩子
				if (node == node.p.right) {
					// 左旋以父结点作为左旋。指针变换到父亲结点
					node = node.p;
					rotateLeft(node);
				}
				// Case 3条件：叔叔是黑色，且当前节点是左孩子。
				// 把父结点变为黑色
				node.p.color = B;
				// 把祖父结点变为红色 （爷爷）
				node.p.p.color = R;
				// 以祖父结点旋转（爷爷）
				rotateRight(node.p.p);
			} else {
				//若“的父节点”是“z的祖父节点的右孩子”
				uncle = node.p.p.left;
				if (uncle != nil && uncle.color == R) {
					node.p.color = B;
					uncle.color = B;
					node.p.p.color = R;
					node = node.p.p;
					continue;
				}
				if (node == node.p.left) {
					node = node.p;
					rotateRight(node);
				}
				node.p.color = B;
				node.p.p.color = R;
				rotateLeft(node.p.p);
			}
		}
		// 将根节点设为黑色
		root.color = B;
	}

	/**
	 * 对红黑树的节点(x)进行左旋转
	 *
	 * 左旋示意图(对节点x进行左旋)：
	 *      px                              px
	 *     /                               /
	 *    x                               y
	 *   /  \      --(左旋)-.             / \                #
	 *  lx   y                          x  ry
	 *     /   \                       / \
	 *    ly   ry                     lx  ly
     *
	 * 1.先调整y的位置
	 * 2.再调整x的位置
	 */
	void rotateLeft(Node node) {
	    // 旋转节点的父节点不为nil
		if (node.p != nil) {
		    // 旋转节点为父节点的左节点
            // px.left -> y
			if (node == node.p.left) {
			    // 父节点的左节点指向旋转节点的右节点
				node.p.left = node.right;
			} else {
                // 旋转节点为父节点的右节点
                // 父节点的右节点指向旋转节点的右节点
                // px.right -> y
				node.p.right = node.right;
			}
            // y.p -> px
			node.right.p = node.p;
			// x.p -> y
			node.p = node.right;
			if (node.right.left != nil) {
			    // ly.p -> x
				node.right.left.p = node;
			}
			// x.right -> ly
			node.right = node.right.left;
			// y.left - > x
			node.p.left = node;
		} else {
			// 旋转节点为根节点
			Node y = root.right;
			// x.right -> ly
			root.right = y.left;
			// ly.p -> x
			y.left.p = root;
			// x.p -> y
			root.p = y;
			// y.left -> x
			y.left = root;
			// y.p -> nil
			y.p = nil;
			// root == y
			root = y;
		}
	}

	/**
	 * 右旋示意图(对节点y进行左旋)：
	 *            py                               py
	 *           /                                /
	 *          y                                x
	 *         /  \      --(右旋)-.             /  \                     #
	 *        x   ry                          lx   y
	 *       / \                                   / \                   #
	 *      lx  rx                                rx  ry
	 *
	 */
	void rotateRight(Node node) {
		if (node.p != nil) {
			if (node == node.p.left) {
				// py.left -> x
				node.p.left = node.left;
			} else {
				// py.right -> x
				node.p.right = node.left;
			}
			// x.p -> py
			node.left.p = node.p;
			// y.p -> x
			node.p = node.left;
			if (node.left.right != nil) {
				// rx.p -> y
				node.left.right.p = node;
			}
			// y.left -> rx
			node.left = node.left.right;
			// x.right -> y
			node.p.right = node;
		} else {
			Node left = root.left;
			root.left = root.left.right;
			left.right.p = root;
			root.p = left;
			left.right = root;
			left.p = nil;
			root = left;
		}
	}

	public void creatTree() {
		int data[]= {23,32,15,221,3};
		Node node;
		System.out.println(Arrays.toString(data));
		for(int i = 0 ; i < data.length ; i++) {
			node = new Node(data[i]);
			insert(node);
		}
		printTree(root);
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackTree bst = new RedBlackTree();
		bst.creatTree();
	}
}
