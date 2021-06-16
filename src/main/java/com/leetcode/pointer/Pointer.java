package com.leetcode.pointer;

public class Pointer {

    public static void main(String[] args) {
        int []  arr1 = {1,2};
        int [] arr2 = {3,4} ;
        System.out.println( findMedianSortedArrays(arr1,arr2));
    }
    /**
     * leetcode 11
     * 双指针
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int maxArea(int[] height) {
        int area = 0 ;
        int i = 0 ;
        int j = height.length - 1 ;
        while(i < j ){
            int hight = j - i ;
            int areaTemp = Math.min(height[i],height[j]) *  hight;
            area = Math.max(areaTemp,area);
            if(height[i] < height [j]){
                i ++ ;
            }else{
                j--;
            }



        }
        return area ;
    }


    /**
     * leetcode 4
     *
     * 跟合并两个有序链表差不多
     *
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] nums3 = new int[n1 + n2];
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                nums3[i + j] = nums1[i];
                i++;
            } else if (nums1[i] == nums2[j]) {
                nums3[i + j] = nums1[i];
                i++;
                nums3[i + j] = nums2[j];
                j++;
            } else {
                nums3[i + j] = nums2[j];
                j++;

            }
        }
        if (n1 - i > 0) {
            for (; i < n1; i++) {
                nums3[i + j] = nums1[i];
            }
        }
        if (n2 - j > 0) {
            for (; j < n2; j++) {
                nums3[i + j] = nums2[j];
            }
        }
        if ((nums3.length & 1) == 1) {
            return nums3[nums3.length >> 1];

        } else {
           int i1 = nums3[(nums3.length >> 1) ];
           int i2 = nums3[(nums3.length >> 1) - 1];
           return  (double) (i1 + i2 ) / 2 ;

        }
    }
}
