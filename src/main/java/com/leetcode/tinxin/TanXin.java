package com.leetcode.tinxin;

import java.util.*;

public class TanXin {
    public static void main(String[] args) {
        TanXin t = new TanXin();
        t.partitionLabels("ntswuqqbidunnixxpoxxuuupotaatwdainsotwvpxpsdvdbwvbtdiptwtxnnbtqbdvnbowqitudutpsxsbbsvtipibqpvpnivottsxvoqqaqdxiviidivndvdtbvadnxboiqivpusuxaaqnqaobutdbpiosuitdnopoboivopaapadvqwwnnwvxndpxbapixaspwxxxvppoptqxitsvaaawxwaxtbxuixsoxoqdtopqqivaitnpvutzchkygjjgjkcfzjzrkmyerhgkglcyffezmehjcllmlrjghhfkfylkgyhyjfmljkzglkklykrjgrmzjyeyzrrkymccefggczrjflykclfhrjjckjlmglrmgfzlkkhffkjrkyfhegyykrzgjzcgjhkzzmzyejycfrkkekmhzjgggrmchkeclljlyhjkchmhjlehhejjyccyegzrcrerfzczfelzrlfylzleefgefgmzzlggmejjjygehmrczmkrc");
       // t.canPlaceFlowers(new int[]{0,1,0},1);
        t.findMinArrowShots(new int [][]{{10,16},{2,8},{1,6},{7,12}});
        //t.candy(new int[]{1,3,2,2,1});
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









    public boolean canPlaceFlowers(int[] flowers , int n ) {
        int temp = n ;
        for (int i = 0; i < flowers.length; i++ ) {
             if((2 * i + 1) >= flowers.length ){
                 break;
             }
             flowers[2 * i + 1 ] = 0 ;

        }

        for (int i = 0; i < flowers.length; i++) {
            if(flowers[i] == 1 ){
                temp --;
            }
        }


        return temp<=0 ;

    }




    public int findMinArrowShots(int[][] points) {
        int count = points.length ;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1])
                    return 0 ;
                return o1[1] > o2[1] ? 1 : -1 ;
            }
        });
        int j = 0 ;
        for (int i = 1; i < points.length; i++) {
            System.out.println(points[i][0] +"\t"+points[j][1]);
            if((points[i][0] - points[j][1])  >= 0 ){
                count-- ;
                j = i ;
            }
        }
        System.out.println(count);
        return count;
    }





    public List<Integer> partitionLabels(String s) {
        List<Integer> integers = new ArrayList<>();
        char [] chs = s.toCharArray();
        List<Integer>[] map = new List[26];
        for (int i = 0; i < chs.length ; i++) {
            List<Integer> pos = map[chs[i] - 97];
            if(pos == null){
                pos = new ArrayList<>();
                map[chs[i] - 97] = pos;
            }
            pos.add(i);
        }
        Arrays.sort(map, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1== null && o2 != null){
                    return 1 ;
                }
                if(o1 != null && o2 == null){
                    return -1;
                }
                if(o1 == null && o2 == null){
                    return 0 ;
                }
                if(o1.get(0) == o2.get(0))
                     return 0;
                return o1.get(0) > o2.get(0) ? 1 : -1 ;
            }
        });
        int max = map[0].get(map[0].size() - 1);
        int min = map[0].get(0);
        int j = 0 ;
        int i = 1;
        String s1 = max+","+min;
        for (; i < map.length; i++) {
            List<Integer> cur = map[i];
            if(cur == null){
                break ;
            }
            int size = cur.size();
            while(--size  >= 0 ){
                if(max > cur.get(size)){
                    break;
                }
            }
            if(size >= 0 ){ //大于0 说明全部都在区间内 max 不变
                max = Math.max(max,cur.get(cur.size() - 1 ));
                j=i;
            }else{ //小于0 说明还小于当前数组的最小一个 说明不包含
                System.out.println(s.substring(min,max+1));
                integers.add(max-min + 1);
                max = cur.get(cur.size() - 1 );
                min = cur.get(0);
                j = i ;


            }



        }
        if( j!= i || i == map.length){
            integers.add(max-min + 1);
            System.out.println(s.substring(min,max+1));
        }
        System.out.println(integers);
        return integers ;
    }































}
