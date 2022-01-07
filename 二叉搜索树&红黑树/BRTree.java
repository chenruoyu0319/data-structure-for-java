package leecode;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-07
 */
public class BRTree {

    BRTreeNode root;    // 根结点

    /*
     * 中序遍历"二叉树"
     */
    private void inOrder(BRTreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    /**
     * 搜索: (递归实现)查找"二叉树x"中键值为key的节点
     */
    public BRTreeNode find(BRTreeNode root, int target) {
        if (target < root.data) {
            find(root.left, target);
        } else if (target > root.data) {
            find(root.right, target);
        } else {
            return root;
        }
        return null;
    }

    /**
     * 搜索root为根节点的最小节点
     */
    public BRTreeNode findMinimum(BRTreeNode root) {

        if (root.left != null) {
            findMinimum(root.left);
        }
        return root;

    }

    /**
     * 搜索root为根节点的最大节点
     */
    public BRTreeNode findMaximum(BRTreeNode root) {

        if (root.right != null) {
            findMaximum(root.right);
        }
        return root;

    }

    /**
     * 搜索后继节点: 即，查找"二叉树中数据值大于该结点"的"最小结点"。
     */
    public BRTreeNode findSuccessor(BRTreeNode root) {
        // 如果右子节点不为空,直接返回右子树的最小节点
        if (root.right != null) {
            return findSuccessor(root.right);
        }
        // 如果右子节点为空,则有以下2种可能
        // 1.root是"一个左孩子"，则"root的后继结点"为 "它的父结点"。
        // 2.root是"一个右孩子"，则查找"root的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        BRTreeNode parent = root.parent;
        // 递归判断: 是否满足条件1,不满足就root = root.parent,直到满足条件1或root.parent =null为止跳出循环
        while (parent != null && root == parent.right) {
            root = parent;
            parent = root.parent;
        }

        return parent;
    }

    /**
     * 插入节点
     */
    public void insert(BRTreeNode root, int target) {
        BRTreeNode parent = null;

        // 查找z的插入位置
        while (root != null) {
            parent = root;
            if (target < root.data) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        BRTreeNode brTreeNode = new BRTreeNode(target);
        brTreeNode.parent = parent;

        if (target < parent.data) {
            parent.left = brTreeNode;
        } else {
            parent.right = brTreeNode;
        }
        // todo: 红黑树整形
    }



    /**
     * 删除
     */
    public void delete(BRTreeNode root, int target) {
        BRTreeNode cur = find(root, target);
        // 如果是叶子节点,把父节点的left或right置为null
        BRTreeNode parent = cur.parent;
        if (cur.left == null && cur.right == null) {
            if (parent.left == cur) {
                parent.left = null;
            } else if (parent.right == cur) {
                parent.right = null;
            }
        }
        // 如果是只有一个子节点的节点,把父节点的left或right置为子节点的left或right
        // 待删除节点只有左子树
        if (cur.left != null && cur.right == null) {
            if (parent.left == cur) {
                parent.left = cur.left;
            } else if (parent.right == cur) {
                parent.right = cur.left;
            }
        }
        //待删除节点只有右子树
        if (cur.right != null && cur.left == null) {
            if (parent.left == cur) {
                parent.left = cur.right;
            } else if (parent.right == cur) {
                parent.right = cur.right;
            }
        }
        // 删除带两个节点的节点(bst的后继节点不可能有左子树,前继节点不可能有右子树)
        // 后继节点为待删除节点的子节点
        BRTreeNode successor = findSuccessor(cur);  //搜索后继节点
        BRTreeNode successorParent = successor.parent;  //搜索后继节点的父节点
        // 在后继节点为待删除节点的子节点的前提下,该后继节点有右子树和没有右子树的操作是相同的,都是先将"后继节点"替代"待删除节点",再将"待删除节点的左子树"赋值给"后继节点的左子树"
        if (cur.right == successor) {
            if (parent.left == cur) {
                parent.left = successor;
                successor.left = cur.left;
            } else if (parent.right == cur) {
                parent.right = successor;
                successor.left = cur.left;
            }
        } else if (cur.right != successor && successor.right == null) {
            // 在后继节点不为待删除节点的子节点的前提下,后继节点没有右子节点,与上面的后继节点为待删除节点的子节点相比需要增加一个操作,需要将"后继节点父节点的左子节点"赋值为"null"
            if (parent.left == cur) {
                parent.left = successor;
                successorParent.left = successor.right;    //后继节点的右子树作为后继节点父节点的左子树
                successor.left = cur.left;
                successor.right = cur.right; //当前节点的右子树作为后继节点的右子树
                successorParent.left = null;
            } else if (parent.right == cur) {
                parent.right = successor;
                successor.left = cur.left;
                successor.right = cur.right;
                successorParent.left = null;
            }
        } else if (cur.right != successor && successor.right != null) {
            // 后继节点有右子节点,与上面的后继节点没有右子节点相比需要增加一个操作,"需要将后继节点的右子树"赋值给"后继节点的父节点的左子树"
            if (parent.left == cur) {
                parent.left = successor;
                successorParent.left = successor.right;    //后继节点的右子树作为后继节点父节点的左子树
                successor.left = cur.left;
                successor.right = cur.right; //当前节点的右子树作为后继节点的右子树
            } else if (parent.right == cur) {
                parent.right = successor;
                successorParent.left = successor.right; //后继节点的右子树作为后继节点父节点的左子树
                successor.left = cur.left;
                successor.right = cur.right; //当前节点的右子树作为后继节点的右子树
            }
        }

        //todo: 调整红黑树架构

    }

}

class BRTreeNode {
    int B = 0;
    int R = 1;
    int data;
    BRTreeNode left;//左子树
    BRTreeNode right;//右子树
    BRTreeNode parent; //父结点
    int color = B; // 红黑树颜色

    public BRTreeNode() {
    }

    public BRTreeNode(int data) {
        this.data = data;
    }

    public BRTreeNode(int data, BRTreeNode left, BRTreeNode right, BRTreeNode parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
