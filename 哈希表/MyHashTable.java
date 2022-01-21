package hashtable;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-21
 */
public class MyHashTable {

    /**
     * 哈希表数组,每个元素都是一个指向dict.h/dictEntry结构的指针,每个dictEntry结构保存着一个键值对
     */
    DictEntry[] table;

    /**
     * 哈希表大小,table,数组的大小
     */
    long size;

    /**
     * 哈希表大小掩码,用于计算索引值
     * 总是等于 size - 1,这个属性和哈希值一起决定一个键应该被放到table数组的哪个索引上面
     */
    long sizeMask;

    /**
     * 该哈希表已有节点的数量
     */
    long used;
}


class DictEntry {

    /**
     * 键
     */
    String key;

    /**
     * 值
     */
    String value;

    /**
     * 指向下个哈希表节点,形成链表,用解决hash冲突
     */
    DictEntry next;

}
