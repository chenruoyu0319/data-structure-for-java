import java.util.Arrays;

public class HeapSort {

	private static int end;

    public static void main(String[] args) {
        int data[] = {8, 4, 20, 7, 3, 1, 25, 14, 17};
        heapSort(data);
        System.out.println(Arrays.toString(data));
		// 从最后一个非叶子节点开始遍历, 生成一个大顶堆
        for (int i = data.length / 2 - 1; i >= 0; i--){
			maxHeap(data,i,data.length);
		}
		System.out.println(Arrays.toString(data));
		insert(data,16, data.length);
		removeHead(data);
        remove(data,2);
    }

    /**
     * 插入
     *
     * @param data
     * @param target
     */
    public static void insert(int data[], int target, int end) {
    	if (end > data.length -1){
			int[] newData = new int[(int) (data.length * 1.5)];
			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
        while (end > 0) {
			data[end] = target;
			int preEnd  =  (end - 2) / 2;
        	if (data[end] < data[preEnd]){
				System.out.println(Arrays.toString(data));
				return;
			}
        	// 交换end和preEnd的元素后, 再进行比较
			int temp = data[end];
        	data[end] = data[preEnd];
        	data[preEnd] = temp;
        	end = preEnd;
        }
		HeapSort.end = end;
		System.out.println(Arrays.toString(data));
		return;
    }

	/**
	 * 堆顶的删除，交换头尾2个元素，然后对头上元素做一次堆化
	 * @param data
	 */
    public static void removeHead(int data[]){
    	// 交换头尾2个元素
		int temp = data[0];
		data[0] = data[data.length - 1];
		data[data.length -1 ] = temp;
		// 刪除尾部元素
		data[data.length - 1] = 0;
		// 对data[0]做一次堆化
		maxHeap(data,0, data.length - 1);
		System.out.println(Arrays.toString(data));

	}


    /**
     * 堆内元素的删除，交换目标位置和头元素，再交换头尾2个元素，然后对头上元素做一次堆化
     * @param data
     */
    public static void remove(int data[],int loc){
        // 交换目标位置和头元素
        int temp = data[0];
        data[0] = data[loc];
        data[loc] = temp;
        // 交换头尾2个元素
        temp = data[0];
        data[0] = data[data.length - 1];
        data[data.length -1 ] = temp;
        // 刪除尾部元素
        data[data.length - 1] = 0;
        // 对data[0]做一次堆化
        maxHeap(data,0, data.length - 1);
        System.out.println(Arrays.toString(data));

    }

    /**
     * 从start开始建一个大顶堆，从最后一个非叶子节点开始遍历
     *
     * @param data
     * @param start
     * @param end
     */
    public static void maxHeap(int data[], int start, int end) { // 建一个大顶堆,end表示最多建到的点 lgn

        int parent = start;
        int son = parent * 2 + 1; // 下标是从0开始的就要加1，从1就不用
        while (son < end) {
            int temp = son;
            // 比较左右节点和父节点的大小
            if (son + 1 < end && data[son] < data[son + 1]) { // 表示右节点比左节点大
                temp = son + 1; // 就要继续比较右节点跟父节点
            }
            // temp表示的是 我们左右节点大的那一个
            if (data[parent] > data[temp]) {
                return; // 不用交换
            } else { // 交换
                int t = data[parent];
                data[parent] = data[temp];
                data[temp] = t;
                parent = temp; // 继续堆化，这里主要是为了确保底层子树都要满足堆树性质
                son = parent * 2 + 1;
            }
        }
        return;
    }

    /**
     * 从start开始建一个小顶堆，从最后一个非叶子节点开始遍历
     *
     * @param data
     * @param start
     * @param end
     */
    public static void minHeap(int data[], int start, int end) { // 建一个小顶堆,end表示最多建到的点 lgn

        int parent = start;
        int son = parent * 2 + 1; // 下标是从0开始的就要加1，从1就不用
        while (son < end) {
            int temp = son;
            // 比较左右节点和父节点的大小
            if (son + 1 < end && data[son] > data[son + 1]) { // 表示右节点比左节点大
                temp = son + 1; // 就要继续比较右节点跟父节点
            }
            // temp表示的是 我们左右节点小的那一个
            if (data[parent] < data[temp]) {
                return; // 不用交换
            } else { // 交换
                int t = data[parent];
                data[parent] = data[temp];
                data[temp] = t;
                parent = temp; // 继续堆化，这里主要是为了确保底层子树都要满足堆树性质
                son = parent * 2 + 1;
            }
        }
        return;
    }

    public static void heapSort(int data[]) {

        int len = data.length;
        // 遍历全部节点建一个大顶堆
        for (int i = len / 2 - 1; i >= 0; i--) { //o(nlgn)
            maxHeap(data, i, len);        //
        }
        for (int i = len - 1; i > 0; i--) { //o(nlgn)
            // 每次交换堆顶元素和堆底元素
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            // 每次对交换完后的堆，从堆顶元素开始建堆，保证堆顶元素始终是除堆末元素外的最大值，时刻满足堆特性
            maxHeap(data, 0, i);    //这个i能不能理解？因为len~i已经排好序了
        }
    }

}
