package com.leetcode.DP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCodeDp {
    public static void main(String[] args) {

        int [] arr = {1,1,1,1,1};
        System.out.println( findTargetSumWays(arr,1));











        int length = lcs("1234567","2345");

        System.out.println(length);





       int [] kn =  {1,5,11,5};
        canPartition(kn);
       int [] nums = {10,9,2,5,3,7,101,18};
       lengthOfLIS(nums);




        numDecodings("226");


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



    public static int coinChange1(int[] coin, int n) {

        if (coin == null || coin.length == 0 || n < 0) {
            return -1;
        }
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];// 记录
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1; // 初始化每个都不能找钱表示没钱可找
        }
        for (int i = 0; i < coin.length; i++) {
            if (coin[i] == n) {//如果当前面额刚好够找 直接返回
                return 1;
            }
            if (n - coin[i] > 0) { //如果需要找的钱大于当前面值的钱 那么当前面值的钱至为1，表示可以用当前硬币去找
                dp[coin[i]] = 1;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coin.length; j++) {//遍历找每个钱的最优找钱方案
                if (i - coin[j] >= 0 && dp[i - coin[j]] != -1) {//表示有钱可找
                    // 找n 可以设计为0-n的一个数组，数组中每个值表示要找dp【i】的钱的最优解  找后面的钱根据前面要找钱的值去加
                    if (dp[i] == -1 || dp[i] > (dp[i - coin[j]] + 1)) { //表示没有当前面值的钱或者当前面值的钱大于通过本面值钱找的硬币个数
                        //	dp[i] = Math.min(dp[i],dp[i - coin[j]] + 1);

                        dp[i] = dp[i - coin[j]] + 1;

                    }

                }

            }

        }
        return dp[n];

    }


    public static int coinChange(int [] coins , int n ){
        if(coins == null || coins.length == 0 || n <= 0 ){
            return 0 ;
        }
        int [] dp = new int[n+1];
        //初始化dp
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1 ;
        }
        for (int i = 0; i < coins.length; i++) {
            if(n == coins[i]){ //如果刚好有面值==n直接返回
                return -1 ;
            }
            if(n - coins[i] > 0 ){ //如果当前面值 < 小于要找的钱 把要找的钱的位置至为1
                dp[coins[i]] = 1 ;
            }

        }
        for (int i = 0; i <=n; i++) {
            for (int j = 0; j < coins.length; j++) {
                if( i - coins[j] >=0 && dp[i-coins[j]] != -1){//可找钱
                    if(dp[i] == -1 ||  dp[i] > dp[i-coins[j]] + 1 ){
                        dp[i] = dp[i-coins[j]] + 1 ;
                    }

                }


            }
        }
        return dp[n];
    }

    /**
     * 已知字母 A-Z 可以表示成数字 1-26。给定一个数字串，求有多少种不同的字符串等价于这个 数字串。
     *   输入是一个由数字组成的字符串，输出是满足条件的解码方式总数。
     *      Input: "226"
     *      Output: 3
     * 在这个样例中，有三种解码方式:BZ(2 26)、VF(22 6) 或 BBF(2 2 6)
     *
     * 思路 ：转移方程 f[i] = f[i-1] 表示当前可以独立组成要出来的数
     *              f[i] = f[i-2]  表示需要前面一个组合加上当前的数字组成
     *              f[i] +=f[i-2]  独立组成+需要前面组成的次数 就是总次数
     *
     *
     *              比如 226  f[] = 【s.length+1】 f[0] = 1 ;
     *              s[0]='2'
     *              f[1]
     *                  2   可以由 '2' 或者 ''+'2'组成（因为前面不能为空所以''+'2'不能组成 所以只有当前他自己 所以为f[1-1]）
     *              f[2]
     *                  22  可以'2' + f[1] 组成  或者 f[2-2] + '22'
     *              f[3]
     *                  226 可以由 2 2 6 (f[2])、 22 6(f[2]） 2 26 (f[0])  组成
     *
     *
     *
     *
     *
     * @param s
     * @return
     */
    public static int numDecodings1(String  s ){
        s = ' '  + s ;
        char [] chs = s.toCharArray();
        int [] dp = new int[chs.length];
        dp[0] = 1 ;
        for (int i = 1; i < dp.length; i++) {
            int a = chs[i]- '0' ;
            int b = chs[i] - '0' + (chs[i-1] - '0') * 10 ;
            if(1 <= a && a <=9){//如果小于9 说明可以由当前值独立组成 f[i-1]
                dp[i] = dp[i-1];
            }
            if(1 <=a && a <=9 && 10<=b && b<=26){ // 如果由前后两个组成 需要在当前值得基础上+ 组合成的数字 前面数字的组成方式  只需要往前考虑2位
                dp[i]  += dp[i-2];
            }
        }



        return dp[chs.length - 1] ;
    }





    public static int numDecodings(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (1 <= a && a <= 9)
                f[i] = f[i - 1];
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (10 <= b && b <= 26)
                f[i] += f[i - 2];
        }
        return f[n];
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean [] dp = new boolean[s.length() + 1 ];
        dp[0] = true ;
        for (int i = 1; i <dp.length ; i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true ;
                    break;
                }

            }

        }
        return  dp[s.length()];


    }


    /**
     *
     *
     *
     *
     * @param arr
     * @return
     */
    public static int lengthOfLIS(int [] arr ){

        int n = arr.length;
        int [] dp = new int[n]; //创建长度为n的数组
        dp[0] = 1 ;  //第一个一定是自增的所以初始值赋值为1
        int maxLength = 0 ;
        for (int i = 1; i < n; i++) {
            dp[i] = 1 ; //如果至于一个 那他自己一定是自增序列
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]){
                    //先开始没看明白为啥和dp[j]+1想了一下
                    //因为dp[j]存储的是上一步自增最长的 如 2 3 4 dp【2】 存储的是3 第四个比如是5  那就是dp【3】 + 1
                    //如果是 2 4 3 那么dp【i】 会取 dp[2] + 1
                    System.out.print("dp["+j+"]= " + dp[j] +"\t");
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            System.out.println();
            maxLength = Math.max(maxLength,dp[i]);

        }
        return maxLength;
    }


    /**
     * 两个字符串最长公共子序列
     *
     * 思路 ： 构建一个二维数组用来存储每一步的状态
     *      如果两个相等 上一步的值+1
     *      如果不相等 取[i][j-1] 和 [i-1][j]的最大值
     *
     *      [i][j-1] 其实是前一列最大长度
     *      [i-1][j] 其实是上一行最长长度
     * @param text1
     * @param text2
     * @return
     */
    public static  int longestCommonSubsequence(String text1 ,String text2){
        int m = text1.length() ;
        int n = text2.length() ;
        int [][] dp = new int[m+1][n+1];//构建二维数组存放状态
        for (int i = 1; i <= m; i++) {
            char c = text1.charAt(i-1);
            for (int j = 1; j <=n; j++) {
                char c1 = text2.charAt(j-1);
                if(c == c1){
                    dp[i][j] = dp[i-1][j-1] + 1 ;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }

            }
        }
        return  dp[m][n];

    }

    /**
     * 0-1背包问题
     * @param weights  背包所有物品重量数组
     * @param values   背包中每个物品对应的价值
     * @param N  N个物品
     * @param W  背包容量
     * @return
     */
    public static int knapsack(int [] weights, int [] values, int N, int W ,boolean fullPack){
        int [][] dp = new int[N+1][W+1];
        for (int i = 1; i <= N; i++) {
            int v = values[i-1];
            int w = weights[i-1];
            for (int j = 1; j <= W; j++) {
                if(fullPack){//完全背包问题
                    //如果当前物品重量 + 上次放入的重量超过背包大小
                    if(j < w){//dp[i][j]为上次放入的
                        dp[i][j] = dp[i-1][j];
                    }else{//0-1背包是因为 只能选择一个 用完就没了  所以需要和上一次最优的相加
                        //完全背包不存在这个问题，只需要找出当前最大的即可然后和上一次最大的作比较 哪个大要哪个
                        dp[i][j] = Math.max(dp[i-1][j],dp[i][j-w]+v);
                    }
                }else{ //0-1背包问题
                    //如果当前物品重量 + 上次放入的重量超过背包大小
                    if(j < w){//dp[i][j]为上次放入的
                        dp[i][j] = dp[i-1][j];
                    }else{
                        //否则的话取上次最大重量 和本次放入对比
                        //dp[i-1][j-w]表示 如果把当前的加入那么j代表当前背包容量 加入后需要减去当前的w容量 为j-w
                        //比如当前i= 3 j =5 w=2 那么当前的为 dp[2][5-2]+ w 只需要取dp[2][3]的价值即可  因为每次都会存当前最大放入后的价值
                        dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w]+v);
                    }
                }

            }

        }
        return dp[N][W];
    }

    /**
     * leetcode 416 0-1背包问题
     *
     *  给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *  输入：nums = [1,5,11,5]
     *  输出：true
     *  解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     *
     *  思路：求中间和数 target 这个即为最大容量 N
     *  nums 为背包
     *  nums[i] 为每个价值
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0 ;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
        }
        if(sum % 2 !=0){
            return false ;
        }
        int target = sum >> 1 ;
        int n = nums.length;

        boolean [][] dp = new boolean[n+1][target+1];
        for(int i = 0 ; i<=n ; i++){
            dp[i][0] = true ;//初始第一个都为true 表示只有一个的话 一定能组成
        }
        for(int i = 1 ; i <= n ;i++ ){
            int w = nums[i-1];//当前价值
            for(int j = 1 ; j <= target ; j++){ //计算当前价值有没有符合的
                if(j >= w){//加上这个数大于当前的价值了   j表示当前可放的容量
                    dp[i][j] = dp[i-1][j] | dp[i-1][j-w]; //相当于是如果取了这个数 是否满足和不取这个数是否满足
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][target];
    }


    /**
     * 不懂这个题
     * @param n
     * @return
     */
    public static int minSteps(int n ){
        int d = 2 ;
        int max = 0 ;
        while( n > 1 ){//n 最小应该是d的1倍
            while (n % d == 0 ){// n是d的倍数时 但有可能不是最小倍数
                max += n ;
                n /= d ;
            }
            d++;


        }
        return max ;

    }


    //leetcode 650
    //如果 n是一个质数 粘贴n次就行
    //如果n是一个合数 那么找他最大因子  比如 n = 6  最大因子是3
    //dp[3] = AAA 所以复制1次  即dp[3]+1 此时dp[3] = AAA了 所以需要粘贴1次即可 为dp[6/3]-1
    //dp[6] = dp[3]+1 + dp[2]-1 ;
    //转移方程为  dp[n] = dp[i]+1 + dp[n/i] -1
    //          dp[n] = dp[i] + dp[n/i]

    //
    public static int minStepsDp(int n) {
        int [] dp = new int[n+1];
        dp[0] = 0 ;
        dp[1] = 0 ; //只有一个不需要复制粘贴了
        for(int i = 2 ; i <=n ; i++){
            dp[i] = i ; //如果是质数 粘贴n次就行了
            for(int j = 2 ; j*j <= i ; j++ ){//为什么用j*j 或者用 i开平方 因为 当i为合数时 会有p*q = i 和  q*p = i 两种情况 这种无情况 复制粘贴其实是一样的 所以用j*j  或者 i开平方
                if(i % j == 0 )//此时说明
                    dp[i] = dp[j] + dp[i/j] ;

            }
        }
        return dp[n];

    }


    /**
     * 最大公共子串 ，和最大递增子序列很像
     * @param s1
     * @param s2
     * @return
     */
    public static  int lcs(String s1 , String s2){
        int n = s1.length() ;
        int m = s2.length() ;
        int [][] dp = new int[n+1][m+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0 ;

        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0 ;
        }
        int max = 0 ;
        for (int i = 1; i <= n ; i++) {
            char c = s1.charAt(i-1);
            for (int j = 1; j <= m; j++) {
                if(c == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1 ;
                    max = Math.max(max,dp[i][j]);
                }
            }

        }
        return max;
    }

    /**
     * leetcode 152
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int     maxi = nums[0] ,  //记录当前最大的 ， 之所以需要最大和最小 是有可能出现负数
                mini = nums[0] ,  //记录当前最小的
                max = nums[0];
        int length = nums.length ;
        for(int i = 1 ; i < length ; ++i){
            int     mx = maxi ,//记录一边计算后最大的
                    mn = mini ;//记录一遍运算后最小的
            maxi = Math.max(mx * nums[i],Math.max(nums[i], mn * nums[i]));//mx有可能*nums[i] < 0 , mn * num[i] 有可能大于0 ， nums[i]也有可能大于 两次的计算
            mini = Math.min(mn * nums[i],Math.min(nums[i],mx * nums[i]));//同上面相反
            //之所以需要mini 是因为最后有可能负负得正 踏马哒 没考虑到这里
            //所以上面两步每次都在计算最大和最小
            //最后对比当前计算的和当前的最大值哪个最大
            max = Math.max(maxi,max);
        }
        return max ;
    }

    /**
     * leetcode 85. 最大矩形 没看明白
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /** leetcode 10
     * 别人的思路就是清晰 按照别人的写就写出来了
     * 以一个例子详解动态规划转移方程：
     * S = abbbbc
     * P = ab*d*c
     * 1. 当 i, j 指向的字符均为字母（或 '.' 可以看成一个特殊的字母）时，
     *    只需判断对应位置的字符即可，
     *    若相等，只需判断 i,j 之前的字符串是否匹配即可，转化为子问题 f[i-1][j-1].
     *    若不等，则当前的 i,j 肯定不能匹配，为 false.
     *
     *        f[i-1][j-1]   i
     *             |        |
     *    S [a  b  b  b  b][c]
     *
     *    P [a  b  *  d  *][c]
     *                      |
     *                      j
     *
     *
     * 2. 如果当前 j 指向的字符为 '*'，则不妨把类似 'a*', 'b*' 等的当成整体看待。
     *    看下面的例子
     *
     *             i
     *             |
     *    S  a  b [b] b  b  c
     *
     *    P  a [b  *] d  *  c
     *             |
     *             j
     *
     *    注意到当 'b*' 匹配完 'b' 之后，它仍然可以继续发挥作用。
     *    因此可以只把 i 前移一位，而不丢弃 'b*', 转化为子问题 f[i-1][j]:
     *
     *          i
     *          | <--
     *    S  a [b] b  b  b  c
     *
     *    P  a [b  *] d  *  c
     *             |
     *             j
     *
     *    另外，也可以选择让 'b*' 不再进行匹配，把 'b*' 丢弃。
     *    转化为子问题 f[i][j-2]:
     *
     *             i
     *             |
     *    S  a  b [b] b  b  c
     *
     *    P [a] b  *  d  *  c
     *       |
     *       j <--
     *
     * 3. 冗余的状态转移不会影响答案，
     *    因为当 j 指向 'b*' 中的 'b' 时, 这个状态对于答案是没有用的,
     *    原因参见评论区 稳中求胜 的解释, 当 j 指向 '*' 时,
     *    dp[i][j]只与dp[i][j-2]有关, 跳过了 dp[i][j-1].
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s , String p ){
        int m = s.length() ;
        int n = p.length() ;
        boolean [][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n ; j++) {
                char c = p.charAt(j-1);
                if(c == '*'){
                    dp[i][j] = dp[i][j-2];
                    if(match(s,p,i,j-1)){//如果匹配上了
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }


                }else{
                    if(match(s,p,i,j)){
                        dp[i][j] =dp[i-1][j-1];
                    }
                }

            }
        }
        return dp[m][n];
    }

    private static boolean match(String s, String p , int m , int n){
        if(m == 0 )
            return false;
        if(p.charAt(m) =='*'){
            return true ;
        }

        return s.charAt(m-1) == p.charAt(n-1);
    }

    /**
     * leetcode 121
     * 股票问题
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int max = 0;
        int buy = Integer.MIN_VALUE;
        for(int i = 0 ; i < prices.length ; i++){
            buy = Math.max(buy,-prices[i]); //找最低买入
            max = Math.max(max,buy + prices[i]);
        }
        return max ;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     *
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     *
     *
     * 思路 ： 收尾相连 ， 那么偷第一间就不能偷最后一间 ，同理 偷最后一件就不能偷第一间
     *  所以分两种情况 第一次偷第一间 最后一间不偷
     *              偷最后一件，第一间不偷
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];

        int n = nums.length;
        int [] dp = new int[n + 1];
        dp[1] = nums[0];
        for(int i = 2;  i < n ; i ++){
            dp[i] = Math.max(dp[i-2] + nums[i-1],dp[i-1]);
        }

        int max = dp[n-1] ;//偷第一间的话最大值
        dp = new int[n+1];
        for(int i = 2;  i <= n ; i ++){
            dp[i] = Math.max(dp[i-2] + nums[i-1],dp[i-1]);
        }
        //dp[n] 偷最后一间的值
        return Math.max(max,dp[n]);

    }

    /**
     *
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 股票持有问题
     * @param k
     * @param nums
     * @return
     */
    public static int maxProfit(int k , int [] nums){

        int  n = nums.length ;
        int K = Math.min(k,n/2);//股票一买一卖交易为1次 所以应该取 股票最小能交易多少次
        int [][][] dp = new int [n][2][k+1];//n表示第几天 2表示两种状态 0代表不持有股票，1代表持有股票  k为交易次数
        for (int i = 0; i <= K; i++) {
            dp[0][0][i]  = 0 ; //如果不持有 说明没有买 当前value就是0
            dp[0][1][i] = -nums[i];//如果持有了说明花了钱 所以要减当前价格
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][0][j] = Math.max(dp[i-1][0][j],dp[i-1][1][j] + nums[i]);//如果第i天不持有股票时找第i-1天的值  如果第i-1也没持有那就还是原来的，如果持有了 那说明第i天把股票卖了 所以时+num【i】
                dp[i][1][j] = Math.max(dp[i-1][1][j],dp[i-1][0][j] - nums[i]);//同上
            }
        }
        return dp[n-1][0][K];
    }






    /**
     * 股票买卖 leetcode 122
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int n = prices.length;
        int [][] dp = new int[n][3];
        for(int i = 0 ; i < n ; i ++){
            dp[i][0] = 0;
            dp[i][1] = -prices[i];
            dp[i][2] = 0;
        }
        for(int i = 1 ; i < n ; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][2] - prices[i]);
            dp[i][1] =dp[i-1][0] + prices[i];
            dp[i][2] =Math.max(dp[i-1][1],dp[i-1][2]);
        }
        return dp[n-1][0];
    }



    /**
     * 股票买卖 leetcode 122
     *
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        int n = prices.length;
        int [][] dp = new int[n][2];
        for(int i = 0 ; i < n ; i ++){
            dp[i][0] = 0;
            dp[i][1] = -prices[i];
        }
        for(int i = 1 ; i < n ; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }


    /** leetcode 714
     * 如果卖了之后有手续费
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int [][] dp = new int[n][2];
        for(int i = 0 ; i < n ; i ++){
            dp[i][0] = 0;
            dp[i][1] = -prices[i];
        }
        for(int i = 1 ; i < n ; i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i] - fee );
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] - prices[i]);
        }
        return dp[n-1][0];
    }

    /**
     * leetcode 343
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/integer-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int [] dp = new int[n+1];
        for (int i = 2; i <=n; i++) {
            int max = dp[i];
            for (int j = 1; j < i; j++) {
                max = Math.max(max,Math.max( j * (i-j) , j * dp[i-j])); //后面是找出最大乘积 ，比如 i= 8 j=2  max(2 * 6 , 2 * dp[6]  ) dp[6] 最大乘积是2*2*2 ，所以会选择后面的
            }
            dp[i] = max;
        }
        return dp[n];
    }


    /**
     * 编辑距离 leetcode 583
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int w1 = word1.length();
        int w2 = word2.length();
        int [][] dp = new int[w1+1][w2+1];
        for(int i = 0 ; i<=w1 ;i ++){
            dp[i][0]  = i ;
        }
        for(int i = 0 ; i<=w2 ;i ++){
            dp[0][i]  = i ;
        }
        for(int i = 1 ; i <=w1 ;i++){
            char c1 = word1.charAt(i-1);
            for(int j=1 ; j<=w2 ;j++){
                char c2 = word2.charAt(j-1);
                if(c1 == c2){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+1;
                }
            }
        }
        return dp[w1][w2];

    }

    /**
     * 0 1 背包问题
     * leetcode 494
     *
     * 假设当前总和为 sum 需要减去的为minus  那么需要加的为 sum - minus
     * target = (sum -minus - minus )
     *  所以需要减去的为 minus = (sum -target)/2
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {//如果num >j 不选用当前的选择  neg为需要减去的，如果超过neg了越减越少了 不能用
                //如果小于j 说明可以减
                //0-1背包问题 相当于是如果选用了 那么当前价值是 上次+本次加入的价值  本次加入的价值为j-num 而j-num需要的次数前面算过了 为dp[j-num]
                //所以 dp[neg] = dp[neg-num] +dp[neg]  == dp[i-1][neg]+ dp[i-1][neg-num]
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }





}



