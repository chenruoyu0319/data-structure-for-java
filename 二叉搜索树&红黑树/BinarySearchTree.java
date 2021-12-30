package leecode;

/**
 * @author cry777
 * @program demo1
 * @description BST  实现逻辑参考c语言实现逻辑 [https://www.cnblogs.com/lanhaicode/p/11298338.html]
 * @create 2021-12-30
 */
public class BinarySearchTree {

    /**
     * 搜索
     */
    public BSTreeNode find(BSTreeNode root, int target) {
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
     * 搜索父节点
     * root: 根节点
     * child: 目标子节点
     */
    public BSTreeNode findParent(BSTreeNode root, BSTreeNode child) {
        if (root != null) {
            if (root.left == child || root.right == child) {
                return root;
            }
            findParent(root.left, child);
            findParent(root.right, child);
        }
        return null;
    }

    /**
     * 搜索最小节点
     */
    public BSTreeNode findMinimum(BSTreeNode root) {

        if (root.left != null) {
            findMinimum(root.left);
        }
        return root;

    }

    /**
     * 搜索最大节点
     */
    public BSTreeNode findMaximum(BSTreeNode root) {

        if (root.right != null) {
            findMaximum(root.right);
        }
        return root;

    }

    /**
     * 搜索后继节点
     */
    public BSTreeNode findSuccessor(BSTreeNode root) {
        // 如果右子节点不为空,直接返回右子树的最小节点
        if (root.right != null) {
            return findSuccessor(root.right);
        }
        // 如果右子节点为空,返回null
        return null;
    }

    /**
     * 搜索后继节点的父节点
     */
    public BSTreeNode findSuccessorParent(BSTreeNode root, BSTreeNode child) {
        // 如果右子节点不为空,直接返回右子树的最小节点
        if (root.right != null) {
            if (root.left == child || root.right == child) {
                return root;
            }
            findSuccessorParent(root.left, child);
            findSuccessorParent(root.right, child);
        }
        return null;
    }

    /**
     * 搜索前继节点
     */
    public BSTreeNode findPredecessor(BSTreeNode root) {
        // 如果左子节点不为空,直接返回左子树的最小节点
        if (root.left != null) {
            return findPredecessor(root.left);
        }
        // 如果左子节点为空,返回null
        return null;
    }

    /**
     * 删除
     */
    public void delete(BSTreeNode root, int target) {
        BSTreeNode cur = find(root, target);
        BSTreeNode parent = findParent(root, cur);
        // 如果是叶子节点,把父节点的left或right置为null
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
        BSTreeNode successor = findSuccessor(cur);  //搜索后继节点
        BSTreeNode successorParent = findSuccessorParent(root, cur);  //搜索后继节点的父节点
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


    }

}

class BSTreeNode {
    int data;
    BSTreeNode left;//左子树
    BSTreeNode right;//右子树
}
