package com.ourlife.base.common.util;

import java.util.Arrays;

/**
 * 排序算法工具类
 * 理解参考：https://www.cnblogs.com/guoyaohua/p/8600214.html
 *
 * @author zhangchao
 * @createdOn 2020/5/12
 */
public class SortUtils {

    public static void main(String[] args) {
        int[] array = {99, 88, 100, 77, 66, 33, 44, 22, 11};
        System.out.println(Arrays.toString(heapSort(array)));
//        System.out.println(Arrays.toString(quickSort(array, 0, array.length - 1)));
    }

    /**
     * 冒泡排序(升序实现):这个算法的名字由来是因为越小的元素会经由交换慢慢"浮"到数列的顶端
     * <p>
     * 算法分析：最佳情况=T(n)=O(n);最差情况=T(n)=O(n^2);平均情况=T(n)=O(n^2);
     * 空间复杂度：O(1)  使用异或方式交换为O(0)？
     * 排序方式：内存排序
     * 稳定：如果a原本在b前面，而a=b，则排序之后a仍然在b的前面
     * <p>
     * 算法描述：
     * 1.比较相邻的元素，如果第一个比第二个大，就交换他们两个
     * 2.对每一对相邻元素做同样的工作，从开始第一队到结尾的最后一对，这样在最后的元素应该会是最大的数
     * 3.针对所有的元素重复以上的步骤，除了最后一个
     * 4.重复步骤1~3，直到排序完成
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swapValuesInArray(array, j, j + 1);
                }
            }
        }
        return array;
    }

    /**
     * 选择排序(升序实现)
     * <p>
     * 算法分析：最佳情况=T(n)=O(n);最差情况=T(n)=O(n^2);平均情况=T(n)=O(n^2);
     * 空间复杂度：O(1)  使用异或方式交换为O(0)？
     * 排序方式：内存排序
     * 不稳定
     * <p>
     * 算法描述：n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果
     *
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swapValuesInArray(array, i, minIndex);
        }
        return array;
    }

    /**
     * 交换数组中两个下标的值
     *
     * @param array
     * @param indexA
     * @param indexB
     */
    private static void swapValuesInArray(int[] array, int indexA, int indexB) {
        //避免同数组中相同下标的值交换，即自己和自己异或，会丢失数据；两个相同的值没有必要进行交换;
        if (indexA == indexB || array[indexA] == array[indexB]) {
            return;
        }
        array[indexA] = array[indexA] ^ array[indexB];
        array[indexB] = array[indexA] ^ array[indexB];
        array[indexA] = array[indexA] ^ array[indexB];
    }

    /**
     * 插入排序(升序)：通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     * <p>
     * 算法分析：最佳情况：T(n)=O(n);最差情况：T(n)=O(n^2);平均情况：T(n)=O(n^2)
     * 空间复杂度：O(1)
     * 排序方式：内存排序
     * 稳定
     * <p>
     * 算法描述：
     * 1.从第一个元素开始，该元素可以认为已经被排序
     * 2.取出下一个元素，在已排序的元素序列中从后向前扫描
     * 3.如果该元素(已排序)大于新元素，将该元素移到下一位置
     * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5.将新元素插入到该位置后
     * 6.重复步骤2~5
     *
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int next;
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            next = array[i + 1];
            while (index >= 0 && array[index] > next) {
                array[index + 1] = array[index];
                index--;
            }
            array[index + 1] = next;
        }
        return array;
    }

    /**
     * 希尔排序(升序)，也是一种插入排序，是简单排序经过改进之后的一个更高效的版本，也称为缩小增量排序。该算法是冲破O(n^2)的第一批算法之一。
     * 它与插入排序的不同之处在于，它会优先比较距离较远的元素
     * <p>
     * 算法分析：最佳情况：T(n)=O(nlog2 n);最差情况：T(n)=O(nlog2 n);平均情况：T(n)=O(nlog2 n)
     * 空间复杂度：O(1)
     * 排序方式：内存排序
     *
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array) {
        int len = array.length;
        if (len == 0) {
            return array;
        }
        int gap = len / 2;
        int current;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                current = array[i];
                int targetIndex = i - gap;
                while (targetIndex >= 0 && array[targetIndex] > current) {
                    array[targetIndex + gap] = array[targetIndex];
                    targetIndex -= gap;
                }
                array[targetIndex + gap] = current;
            }
            gap /= 2;
        }
        return array;
    }

    /**
     * 使用场景：多用于需要外部排序的场景，在内存不足的情况下，利用分治策略，利用外存保存中间结果，再用多路归并来排序。
     * 如：对一个大小为2G的数列文件进行排序，内存只有500M，如何搞？
     * 对大文件进行拆分，拆分成n个可加载到内存排序的文件(要考虑选定排序算法需要的额外空间)，然后将这n个文件排成有序的序列，
     * 最后选用多路归并去合并成一个有序的大文件，如何搞？
     * 从n个文件中分别选定 < 500/n 大小的数列到内存，然后进行对比，将最小的数字序列写入到新的文件中，当某个选定的数列到末尾之后，再重新从相应的文件中选定合适大小的数列到内存，
     * 如此反复，最终会将n个数列文件中加载完成，形成一个完整有序的文件序列文件
     * <p>
     * 归并排序(升序)：归并排序是建立在归并操作上一种有效的排序算法，该算法是分治法的典型应用。归并排序是一种稳定的排序方法。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并
     * <p>
     * 算法分析：最佳情况:T(n)=O(nlog n);最差情况:T(n)=O(nlog n);平均情况：T(n)=O(nlog n)
     * 空间复杂度：O(n)
     * 排序方式：可内存排序(递归操作排序)、也可外部排序(内存不足时，仍然适用)
     *
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并排序-将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j];
                j++;
            } else if (j >= right.length) {
                result[index] = left[i];
                i++;
            } else if (left[i] > right[j]) {
                result[index] = right[j];
                j++;
            } else {
                result[index] = left[i];
                i++;
            }
        }
        return result;
    }

    /**
     * 快速排序(升序)
     * <p>
     * 算法分析：T(n)=O(nlog n);最差情况：T(n)=O(n^2);平均情况：T(n)=o(nlog n)
     * 空间复杂度：O(log n)
     * 内存排序
     * <p>
     * 算法描述：
     * 1.从数列中挑出一个元素，成为"基准" pivot
     * 2.重新排序数列，所有元素比基准值小的摆放在基准前，所有元素比基准值大的摆在基准后。在这个分区退出后，该基准就处于数列的中间位置，这个成为分区操作
     * 3.递归的把小于基准元素的子序列和大于基准值元素的子数列排序
     *
     * @param array
     * @return
     */
    public static int[] quickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) {
            return null;
        }
        int smallIndex = partition(array, start, end);
        if (start < smallIndex) {
            quickSort(array, start, smallIndex - 1);
        }
        if (end > smallIndex) {
            quickSort(array, smallIndex + 1, end);
        }
        //递归退出条件为  smallIndex == start == end
        return array;
    }

    /**
     * 对数组进行分区，使得基准数左边的都比其小，右边的都比其大
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swapValuesInArray(array, pivot, end);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex) {
                    swapValuesInArray(array, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    /**
     * 堆排序(升序) 参考：https://www.cnblogs.com/guoyaohua/p/8595289.html
     *
     * @param array
     * @return
     */
    public static int[] heapSort(int[] array) {
        len = array.length;
        if (len < 1) {
            return array;
        }
        //构建一个最大堆
        buildMaxHeap(array);
        //循环将堆首位(最大值)与末位交换，然后再重新调整最大堆
        while (len > 0) {
            swapValuesInArray(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }

    /**
     * 记录堆排序中数组的长度
     */
    private static int len;

    /**
     * 构建最大堆
     *
     * @param array
     */
    private static void buildMaxHeap(int[] array) {
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    /**
     * 调整为最大堆
     *
     * @param array
     * @param i
     */
    private static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2] > array[maxIndex]) {
            maxIndex = i * 2;
        }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        //如果父节点不是最大值，则将父节点与最大值交换，并递归调整父节点交换的位置
        if (maxIndex != i) {
            swapValuesInArray(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }



}
