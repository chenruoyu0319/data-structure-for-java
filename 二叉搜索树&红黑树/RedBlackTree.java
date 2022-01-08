import java.util.Arrays;

/**
 * �����
 */
public class RedBlackTree {

	private final int R = 0;
	private final int B = 1;
	private final Node nil = new Node(-1);
	private Node root = nil;

	private class Node {

		int key = -1; // data
		int color = B; // ��ɫ
		Node left = nil; // nil��ʾ����Ҷ�ӽ��
		Node right = nil;
		Node p = nil; //���ڵ�

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
	 * �������
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
	 * ������Ĳ���
	 * @param node
	 */
	private void insert(Node node) {
		Node temp = root;
		if (root == nil) {
			root = node;
			node.color = B;
			node.p = nil;
		} else {
			// �²���ڵ��Ϊ��ɫ
			node.color = R;
			while (true) {
				// ���ڵ�� ���Ƿŵ����
				if (node.key < temp.key) {
					if (temp.left == nil) {
						temp.left = node;
						node.p = temp;
						break;
					} else {
						temp = temp.left;
					}
				// ���ڵ�С ���Ƿŵ��ұ�
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
			// �������,����������ṹ
			fixTree(node);
		}
	}

	/**
	 * �ع����ṹ
	 * 1.����ɫ�������
	 * ��ǰ���ĸ����Ǻ�ɫ���������游������һ���ӽ��Ҳ�Ǻ�ɫ���������㣩��
	 * ��1���Ѹ��ڵ���Ϊ��ɫ
	 * ��2��������Ҳ��Ϊ��ɫ
	 * ��3�����游Ҳ���Ǹ��׵ĸ�����Ϊ��ɫ��үү��
	 * ��4����ָ�붨�嵽�游���(үү)��Ϊ��ǰҪ������.
	 * 2.��������ǰ������Ǻ�ɫ�������Ǻ�ɫ��ʱ���ҵ�ǰ�Ľ�����������������Ը������Ϊ������ָ��任�����׽��
	 * 3.��������ǰ������Ǻ�ɫ�������Ǻ�ɫ��ʱ���ҵ�ǰ�Ľ����������������
	 * ��1���Ѹ�����Ϊ��ɫ
	 * ��2�����游����Ϊ��ɫ ��үү��
	 * ��3�����游�����ת��үү��
	 * @param node
	 */
	private void fixTree(Node node) {
		// ������ڵ������Ϊ��ɫ
		while (node.p.color == R) {
			// ����ڵ�
			Node uncle = nil;
			// �������ڵ㡱�ǡ��游�ڵ�����ӡ�
			if (node.p == node.p.p.left) {
				// ����ڵ�
				uncle = node.p.p.right;
				// Case 1����������ڵ��Ǻ�ɫ
				if (uncle != nil && uncle.color == R) {
					// �Ѹ��ڵ���Ϊ��ɫ
					node.p.color = B;
					// ������Ҳ��Ϊ��ɫ
					uncle.color = B;
					// ���游Ҳ���Ǹ��׵ĸ�����Ϊ��ɫ��үү��
					node.p.p.color = R;
					//��ָ�붨�嵽�游���(үү)��Ϊ��ǰҪ������
					node = node.p.p;
					continue;
				}
				// Case 2�����������Ǻ�ɫ���ҵ�ǰ�ڵ����Һ���
				if (node == node.p.right) {
					// �����Ը������Ϊ������ָ��任�����׽��
					node = node.p;
					rotateLeft(node);
				}
				// Case 3�����������Ǻ�ɫ���ҵ�ǰ�ڵ������ӡ�
				// �Ѹ�����Ϊ��ɫ
				node.p.color = B;
				// ���游����Ϊ��ɫ ��үү��
				node.p.p.color = R;
				// ���游�����ת��үү��
				rotateRight(node.p.p);
			} else {
				//�����ĸ��ڵ㡱�ǡ�z���游�ڵ���Һ��ӡ�
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
		// �����ڵ���Ϊ��ɫ
		root.color = B;
	}

	/**
	 * �Ժ�����Ľڵ�(x)��������ת
	 *
	 * ����ʾ��ͼ(�Խڵ�x��������)��
	 *      px                              px
	 *     /                               /
	 *    x                               y
	 *   /  \      --(����)-.             / \                #
	 *  lx   y                          x  ry
	 *     /   \                       / \
	 *    ly   ry                     lx  ly
     *
	 * 1.�ȵ���y��λ��
	 * 2.�ٵ���x��λ��
	 */
	void rotateLeft(Node node) {
	    // ��ת�ڵ�ĸ��ڵ㲻Ϊnil
		if (node.p != nil) {
		    // ��ת�ڵ�Ϊ���ڵ����ڵ�
            // px.left -> y
			if (node == node.p.left) {
			    // ���ڵ����ڵ�ָ����ת�ڵ���ҽڵ�
				node.p.left = node.right;
			} else {
                // ��ת�ڵ�Ϊ���ڵ���ҽڵ�
                // ���ڵ���ҽڵ�ָ����ת�ڵ���ҽڵ�
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
			// ��ת�ڵ�Ϊ���ڵ�
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
	 * ����ʾ��ͼ(�Խڵ�y��������)��
	 *            py                               py
	 *           /                                /
	 *          y                                x
	 *         /  \      --(����)-.             /  \                     #
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
