package com.tuling.kafka.kafkaDemo;

/**
 * @Author: Chen ruoyu
 * @Description: 稀疏索引
 * @Date Created in:  2022-02-12 23:44
 * @Modified By:
 */
public class SparseIndex {

    /**
     * log文件的首地址
     */
    private static int startPosition;

    /**
     * 一个数组对象: 模拟存储msg的log文件
     */
    private static KafkaMsg[] msgArray;

    /**
     * 一个数组对象: 模拟存储msg索引的index文件
     */
    private static KafkaIdx[] kafkaIdxArray;

    public static void main(String[] args) {
        int offset = 1000;
        findMsgByIndex(kafkaIdxArray,msgArray,1000);
    }

    /**
     * 根据index数组找到log文件中实际存储的msg
     *
     * @param kafkaIdxArray index文件
     * @param msgArray      msg文件
     * @param offset        待查找的msg的唯一标识符
     * @return
     */
    public static String findMsgByIndex(KafkaIdx[] kafkaIdxArray, KafkaMsg[] msgArray, int offset) {
        // 用二分查找查出最近最小的那个offset
        int position = binarySearch(kafkaIdxArray, offset);
        // 找到对应的msg
        while (true){
            KafkaMsg kafkaMsg = msgArray[position];
            if (kafkaMsg.offset == offset){
                return kafkaMsg.msg;
            }
            position++;
        }
    }

    /**
     * 二分查找: 查出距离我要查的offset最近偏小的那个offset
     *
     * @param kafkaIdxArray
     * @param offset
     * @return
     */
    public static int binarySearch(KafkaIdx[] kafkaIdxArray, int offset) {
        // 二分查找过程
        int mid = kafkaIdxArray.length / 2;
        int left = 0;
        int right = kafkaIdxArray.length - 1;
        while (mid > left && mid < right) {
            if (kafkaIdxArray[mid].offset <= offset && kafkaIdxArray[mid + 1].offset >= offset){
                return kafkaIdxArray[mid].position;
            } else if (kafkaIdxArray[mid].offset <= offset && kafkaIdxArray[mid + 1].offset <= offset){
                mid = (mid + right) / 2;
                left = mid;
            } else if (kafkaIdxArray[mid].offset >= offset && kafkaIdxArray[mid + 1].offset >= offset){
                mid = (left + mid) / 2;
                right = mid;
            }
        }
        return -1;
    }

}

/**
 * 一个数组对象: 模拟硬盘上存储了kafka消息的index文件
 */
class KafkaIdx {

    /**
     * msg的唯一序号
     */
    int offset;

    /**
     * msg在log文件中的偏移量
     */
    int position;
}

/**
 * 一个数组对象: 模拟硬盘上存储了kafka消息的文件
 */
class KafkaMsg {

    /**
     * msg
     */
    String msg;

    /**
     * msg的唯一序号
     */
    int offset;
}