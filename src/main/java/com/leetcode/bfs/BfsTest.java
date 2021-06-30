package com.leetcode.bfs;

import java.util.*;

public class BfsTest {
    public static void main(String[] args) {
        BfsTest bfs = new BfsTest();
        int l = bfs.shortestBridge(new int[][]{
                {1,0},{0,1}});
        System.out.println(l);
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
}
