package com.leetcode.tinxin;

public class TanXin {
    public static void main(String[] args) {

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
}
