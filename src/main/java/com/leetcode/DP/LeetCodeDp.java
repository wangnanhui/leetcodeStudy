package com.leetcode.DP;

public class LeetCodeDp {
    public static void main(String[] args) {
        int[][] path = new int[3][3];
        path[0][0] = 1;
        path[0][1] = 3;
        path[0][2] = 1;

        path[1][0] = 1;
        path[1][1] = 5;
        path[1][2] = 1;


        path[2][0] = 4;
        path[2][1] = 2;
        path[2][2] = 1;


        System.out.println(minPathSum(path));

    }


    /**
     * 20210523
     * 题目描述
     * 给定一个 m × n 大小的非负整数矩阵，求从左上角开始到右下角结束的、经过的数字的和最 小的路径。每次只能向右或者向下移动
     * 转移方程
     * dp[i][j] = min(dp[i-1][j] , dp[i][j-1]) + path[i][j]
     * 输入输出样例
     * 输入是一个二维数组，输出是最优路径的数字和。
     *
     * @param path
     * @return
     */
    public static int minPathSum(int[][] path) {
        int m = path.length;
        int n = path[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = path[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + path[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + path[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + path[i][j];
                }


            }

        }

        return dp[m - 1][n - 1];
    }

    /**
     * 题目描述
     * 给定一个由 0 和 1 组成的二维矩阵，求每个位置到最近的 0 的距离。
     * 输入输出样例
     * 输入是一个二维 0-1 数组，输出是一个同样大小的非负整数数组，表示每个位置到最近的 0
     * 两次循环
     *      1、先找右和下距离 0 最近的位置 赋值当前dp
     *      2、再找左和上距离0最近的位置 同时更新当前dp
     *
     * https://leetcode-cn.com/problems/01-matrix/solution/2chong-bfs-xiang-jie-dp-bi-xu-miao-dong-by-sweetie/
     * 
     * 分别从左上 右下 扫描
     * @return
     */

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    dp[i][j] = 0;
                else
                    dp[i][j] = 1000001;
            }
        }
        //状态转移
        //第一次填表从左上到右下
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) { //不用考虑 当前dp[i][j]的值
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }
        //第二次填表从右下到左上
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i + 1 < m) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }
            }
        }
        return dp ;
    }

    /**
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     *
     * 转移方程 ：
     *  当当前位置为1时 找到当前位置所在的其他三个位置中最小的值 加上1就是当前最小的面积
     *  当前如果刚好在边上 dp[i][j] = 1
     *  dp[i][j] = min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1
     *
     *  找到边长后  平方就是最大面积
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0  ;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int m = matrix.length ;
        int n = matrix[0].length;
        int [][] dp = new int[m][n];
        for(int i = 0 ; i < m ;i ++){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == '1'){//转移方程  如果刚好在边上 就赋值为1
                    if(i== 0 || j == 0){
                        dp[i][j] = 1 ;
                    }
                    else{
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1 ;
                    }
                    maxSide = Math.max(maxSide,dp[i][j]);
                }
            }

        }
        return maxSide * maxSide ;
    }


    /**
     * 最大平方数 和 零钱很像
     *
     * 思路
     *   dp[i] 表示当前这个数 如dpp[12] = 12 ；
     *   当前数最差由 n个1组成 所以第一次dp[i] = i
     *   内层 i-j*j >= 0 表示当前j的平方能否组成<=i
     *   Math.min(dp[i],dp[i - j*j ] + 1 )表示
     *   比如10 可以由 3*3+1 或者说由dp[6]+2*2  也就是dp[1] + 3*3 后面+1表示当前j*j是1次
     *   dp[6]中存储这组成6的最小平方数的个数
     *
     *
     * @param n
     * @return
     */
    public static int numSquares(int n){
        int [] dp = new int[n];
        dp[0] = 0 ;
        for (int i = 1; i <= n; i++) {
            dp[i] = i ;
            for (int j = 1; i -j *j >=0 ; j++) {
                dp[i] = Math.min(dp[i],dp[i - j*j ] + 1 );
            }
        }
        return dp[n];
    }


}



