package com.ourlife.base.common.util;

import java.util.Arrays;

/**
 * 排序算法工具类
 *
 * @author zhangchao
 * @createdOn 2020/5/12
 */
public class SortUtils {

    public static void main(String[] args) {
        int[] array = {99, 88, 100, 77, 66, 33, 44, 22, 11};
        System.out.println(Arrays.toString(insertSort(array)));
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
     * 插入排序(升序)：通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     *
     * 算法分析：最佳情况：T(n)=O(n);最差情况：T(n)=O(n^2);平均情况：T(n)=O(n^2)
     * 空间复杂度：O(1)
     * 排序方式：内存排序
     * 稳定
     *
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
            array[index + 1] =next;
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
        //避免同数组中相同下标的值交换，即自己和自己异或，会丢失数据；两个相同的值没有比较进行交换;
        if (indexA == indexB || array[indexA] == array[indexB]) {
            return;
        }
        array[indexA] = array[indexA] ^ array[indexB];
        array[indexB] = array[indexA] ^ array[indexB];
        array[indexA] = array[indexA] ^ array[indexB];
    }
}
