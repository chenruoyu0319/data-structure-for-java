public class ArrayTest {

    // 数组的长度
    private int size;

    // 当前已经存的数据大小
    private int data[];

    // 当前已存数据大小
    private int index;

    // 数组的初始化过程
    public ArrayTest(int size) {
        this.size = size;
        // 分配的内存空间{0,0,0,0,0}
        data = new int[size];
        index = 0;
    }

    public void print() {
        System.out.println("index:" + index);
        for (int i = 0; i < index; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    //时间复杂度 O(n);
    public void insert(int loc, int n) {
        if (index++ < size) {
            // 为什么不能写size 0~size-1 如果loc是0 O(n),O(1)=>O(n)
            for (int i = size - 1; i > loc; i--) {
                // 把数据往后移一个
                data[i] = data[i - 1];
            }
            data[loc] = n;
        } else if (index >= size * 0.75) {
            // 扩容 会把size*2 0.75
            size = size * 2;
            int temp[] = new int[size];
            // 遍历拷贝原数组元素
            for (int j = 0; j < size / 2; j++) {
                temp[j] = data[j];
            }
        }
    }

    // O(n)
    public void delete(int loc) {
        for (int i = loc; i < size; i++) {
            // 怕越界所以加一个判断
            if (i != size - 1) {
                data[i] = data[i + 1];
            } else {
                // 默认为0 就是没存数据的
                data[i] = 0;
            }
        }
        index--;
    }

    // O(1)
    public void update(int loc, int n) {
        data[loc] = n;
    }

    // O(1)
    public int get(int loc) {
        return data[loc];
    }

    public static void main(String[] args) {
        // 初始化数组
        ArrayTest array = new ArrayTest(10);

        // 插入数据
        array.insert(0,10);

        array.print();

        // 这个demo就是ArrayList的核心逻辑
    }
}
