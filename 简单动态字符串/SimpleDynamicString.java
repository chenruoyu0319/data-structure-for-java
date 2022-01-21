package string;

/**
 * @author cry777
 * @program demo1
 * @description 简单动态字符串
 * @create 2022-01-21
 */
public class SimpleDynamicString {

    /**
     * 在目前版本的Redis中,SDS_MAX_PREALLOC的值为1024 * 1024,也就是说,当大小小于 1MB 的字符串执行追加操作时，sdsMakeRoomFor 就为它们分配多于所需大小一倍的空间；
     * 当字符串的大小大于1MB,那么 sdsMakeRoomFor 就为它们额外多分配 1MB 的空间。
     */
   private static final int SDS_MAX_PREALLOC = 1024 * 1024;

    /**
     * append时的动态内存分配
     */
    SdcBuf sdsMakeRoomFor(SdcBuf sdcBuf, int requiredLength){
        // 预分配空间足够，无须再进行空间分配
        if (sdcBuf.free >= requiredLength){
            return sdcBuf;
        }
        // 计算新字符串的总长度
        int newlen = sdcBuf.len + requiredLength;
        // 如果新字符串的总长度小于SDS_MAX_PREALLOC,那么为字符串分配2倍于所需长度的空间

        if (newlen < SDS_MAX_PREALLOC){
            newlen *= 2;
        } else {
            // 否则就分配所需长度加上 SDS_MAX_PREALLOC 数量的空间
            newlen += SDS_MAX_PREALLOC;
        }
        // 分配内存
        SdcBuf newSdcBuf = new SdcBuf();
        newSdcBuf.buf = new char[newlen];
        // 赋值旧数组的值到新数组
        for (int i = 0; i < newSdcBuf.buf.length; i++) {
            newSdcBuf.buf[i] = sdcBuf.buf[i];
        }
        // 更新free属性
        newSdcBuf.free = newlen - sdcBuf.len;
        // 更新len属性
        newSdcBuf.len = newlen;
        return newSdcBuf;
    }
}


class SdcBuf {

    /**
     * buf,已占用长度
     */
    int len;

    /**
     * buf,剩余可用长度
     */
    int free;

    /**
     * 实际保存字符串数据的地方
     */
    char[] buf;
}