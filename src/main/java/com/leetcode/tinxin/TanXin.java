package com.leetcode.tinxin;

import java.sql.Struct;
import java.util.Arrays;
import java.util.Comparator;

public class TanXin {
    public static void main(String[] args) {
        TanXin t = new TanXin();
        t.eraseOverlapIntervals(new int [][]{{0,2},{1,3},{2,4},{3,5},{4,6}});
        t.candy(new int[]{1,3,2,2,1});
    }

    /**
     * leetcoed 455 贪心算法
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        int c = 0 ;
        for(int i = 0 ; i < g.length ; i ++){
            int current = g[i];
            int min = Integer.MAX_VALUE ;
            int curJ = -1 ;
            for(int j = 0 ; j < s.length ; j ++){
                if(s[j] >= current){
                    min = Math.min(min,s[j]);
                    if(min == s[j]){
                        curJ = j ;
                    }
                }
            }
            if(curJ != -1){
                s[curJ] = -1 ;
                c++ ;
            }


        }
        return c ;
    }

    /**
     * leetcode 135. 分发糖果
     * @param ratings
     * @return
     */
    public int candy(int [] ratings) {
        int candy = 0 ;
        int [] candys = new int[ratings.length];
        candys[0] = 1 ;
        for (int i = 1; i <ratings.length ; i++) {
            candys[i] = 1;
            if(ratings[i] > ratings[i-1]){
                candys[i] = Math.max(candys[i] , candys[i-1]) + 1 ;
            }
        }
        for (int i = ratings.length - 1 ; i >0 ; i--) {
            if(ratings[i] < ratings[i - 1 ]){
                candys[i-1] = Math.max(candys[i]+1 , candys[i-1]) ;
            }
        }
        for (int i = 0; i < candys.length; i++) {
            candy+=candys[i];
        }
        System.out.println(candy);
        return candy;
    }


    /**
     * leetcode 435. 无重叠区间
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0 ;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1])
                    return 0 ;
                return o1[1] > o2[1] ? 1 : -1 ;
            }
        });



        int j = 0 ;
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[j][1] > intervals[i][0]){
                count++;
            }else{
                j = i  ;
            }
        }
        return count;
    }







}
