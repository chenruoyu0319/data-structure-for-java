package ziplist;

import java.util.LinkedList;

/**
 * @author cry777
 * @program demo1
 * @description 快速列表
 * @create 2022-01-26
 */
public class QuickList {

    LinkedList<ZipList> quicklist;
}

class ZipList {

    /**
     * ziplist的长度（单位: 字节)，是一个32位无符号整数
     */
    int zlbytes;

    /**
     * ziplist最后一个节点的偏移量，反向遍历ziplist或者pop尾部节点的时候有用。
     */
    Entry zltail;

    /**
     *  ziplist的节点（entry）个数
     */
    int zllen;

    /**
     * 节点0
     */
    Entry entry0;

    /**
     * 节点1
     */
    Entry entry1;

    /**
     * 节点2
     */
    Entry entry2;

    /**
     * 值为0xFF，用于标记ziplist的结尾
     */
    String zlend = "0xFF";
}

/**
 * 压缩列表实体类
 */
class Entry{

    /**
     * 记录上一个节点的长度，为了方便反向遍历ziplist
     */
    int prevlengh;

    /**
     * 当前节点的编码规则: 类型
     */
    String encoding;

    /**
     * 当前节点的值，可以是数字或字符串; 如果是作为hash结构的底层实现，则使用前后相邻的2个节点分别存储key-value数据
     */
    String data;
}