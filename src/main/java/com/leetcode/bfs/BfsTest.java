package com.leetcode.bfs;

import java.util.*;

public class BfsTest {
    public static void main(String[] args) {
        BfsTest bfs = new BfsTest();
        bfs.findLadders("hit", "cog",Arrays.asList("hot","dot","dog","lot","log","cog"));


        int l = bfs.shortestBridge(new int[][]{
                {1,0},{0,1}});
      //  System.out.println(l);
    }


    /**
     * leetcode 934
     * 被棧坑了一把  踏馬噠
     * @param grid
     * @return
     */
    public int shortestBridge(int[][] grid) {
        boolean island = false;
        Queue<List<Integer>> stack = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            if (island) {
                break;
            }
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {//是岛的时候
                    shortestBridgeBfs(i, j, grid, stack);//先找出一个岛
                    island = true;
                    break;
                }

            }
        }
        int[] array = {-1,0,1,0,-1};//因为要上下左右 所以由加减

        int step = 0;

        while (!stack.isEmpty()) {
            step++;
            int size = stack.size();
            while (size -- >0) {
                List<Integer> pos = stack.poll();

                for (int i = 0; i < 4; i++) {
                    int x = pos.get(0);
                    int y = pos.get(1);
                    x += array[i];
                    y += array[i+1];
                    if (x >= 0 && y >= 0 && x < grid.length && y < grid[x].length) {
                        if (grid[x][y] == 2) {
                            continue;
                        }
                        if (grid[x][y] == 1) {
                            return step;
                        }
                        grid[x][y] = 2;
                        stack.add(Arrays.asList(x, y));
                    }
                }

            }
        }
        return step;


    }

    void shortestBridgeBfs(int x, int y, int[][] grid, Queue<List<Integer>> stack) {
        if (x == grid.length || x < 0 || y < 0 || y == grid[x].length || grid[x][y] == 2) {
            return;
        }
        if (grid[x][y] == 0) {
            stack.add(Arrays.asList(x, y));
            return;
        }
        grid[x][y] = 2;

        shortestBridgeBfs(x + 1, y, grid, stack);
        shortestBridgeBfs(x - 1, y, grid, stack);
        shortestBridgeBfs(x, y + 1, grid, stack);
        shortestBridgeBfs(x, y - 1, grid, stack);

    }

    /**
     * leetcode 126
     * 总算憋出来一个题了....
     * 思路就是修改当前字符串（1个位置 如a-b-c）的每个位置上从a-z看是否在候选词列表有  有的话加入集合中继续 以此类推 如果当前字符串==要找到的就是找到了 输出这个路径即可
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        boolean contain = false ;
        Queue<Node> first = new LinkedList<>();
        Set<String> set = new HashSet<>();
        for(String str : wordList){
            set.add(str);
            if(str.equals(endWord)){
                contain =true ;
            }
        }
        if(!contain){
            return null ;
        }
        List<List<String>> list = new ArrayList<>();
        getDifficultCount(beginWord,first,set,beginWord);
        while(!first.isEmpty()){
            int size = first.size();
            for(Node n : first){
                set.remove(n.c2);
            }
            while(size -- > 0 ){
                Node str = first.poll();
                System.out.println(str.prefix);
                if(str.c2.equals(endWord)){
                    list.add(Arrays.asList(str.prefix.split(",")));
                    break;
                }
                getDifficultCount(str.c2,first,set,str.prefix);
            }
        }
        System.out.println(list);

        return list;
    }


    public void getDifficultCount(String s1 ,Queue<Node> list , Set<String> set , String prefix  ){
        List<String[]> list1 = new ArrayList<>();
        char [] chs = s1.toCharArray();
        for(int i = 0 ; i < chs.length ; i++){
            char [] chstemp = s1.toCharArray();
            char c = chs[i] ;
            while( c - 'a' > 0){
                c -=1 ;
                chstemp[i] = c ;
                if(set.contains(new String(chstemp))){
                    list.add(new Node(s1,new String(chstemp),prefix));
                }
            }
            c = chs[i] ;
            while (c - 'z' <0){
                c +=1 ;
                chstemp[i] = c ;
                chstemp[i] = c ;
                if(set.contains(new String(chstemp))){
                    list.add(new Node(s1,new String(chstemp),prefix));
                }
            }



        }




    }


}

class Node{
    public Node(String c1 , String c2 , String prefix ){
        this.c1 = c1 ;
        this.c2 = c2 ;
        this.prefix = prefix+","+c2;
    }
    public String c1 ;
    public String c2 ;
    public String prefix ;

    @Override
    public String toString() {
        return "Node{" +
                "c1='" + c1 + '\'' +
                ", c2='" + c2 + '\'' +
                '}';
    }
}
