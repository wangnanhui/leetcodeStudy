# leetcode 刷题  每日至少一题
    开始时间 20210523
    @倩男 又成了我一个人刷题 终究还是我一个人抗下了所有 

`先刷动态规划吧`

### 20210523
* 题目描述给定一个 m × n 大小的非负整数矩阵，求从左上角开始到右下角结束的、经过的数字的和最 小的路径。每次只能向右或者向下移动
  
* 输入输出样例
    输入是一个二维数组，输出是最优路径的数字和。
  

*转移方程 
dp[i][j] = min(dp[i-1][j] , dp[i][j-1]) + path[i][j]




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



/**
* 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
*leetcode 221
* 转移方程 ：
*  当当前位置为1时 找到当前位置所在的其他三个位置中最小的值 加上1就是当前最小的面积
*  当前如果刚好在边上 dp[i][j] = 1
*  dp[i][j] = min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1
*
*  找到边长后  平方就是最大面积
* @param matrix
* @return
*


    `20210524   组成n的最小平平方个数`
    leetcode 279
* 最大平方数 和 零钱很像
*
* 思路
*   dp[i] 表示当前这个数 如dpp[12] = 12 ；
*   当前数最差由 n个1组成 所以第一次dp[i] = i
*   内层 i-j*j >= 0 表示当前j的平方能否组成<=i
*   Math.min(dp[i],dp[i - j*j ] + 1 )表示
*   比如10 可以由 3*3+1 或者说由dp[6]+2*2  也就是dp[1] + 3*3 后面+1表示当前j*j是1次
*   dp[6]中存储这组成6的最小平方数的个数




    20210525 

    找零钱为题 
    、首先将原问题拆分为子问题
    
    已知什么？显而易见，钞票的金额都只需要其本身1张即可
    
    如何在已知钞票的情况下构造出 金额X需要的最少钞票组合
    
    2、确认状态
    
    　　DP[0] - DP[amount] 表示构造金额amount需要的最小钞票数
    
    3、确认边界状态（初试条件）
    
    DP[coin] = 1 其他的都未知初始值设为 -1
    
    例如coins = [1, 2, 5], amount = 11 已知 dp[1]、dp[2]、dp[5] =1
    
    现在已知 DP[coin] 需要求出每一个DP[amount]
    
    4、状态转移方程
    
    　　dp[i] = min(dp[i-1], dp[i-2], dp[i-5]) + 1

##### 20210526 
    leetcode 91 

###### 已知字母 A-Z 可以表示成数字 1-26。给定一个数字串，求有多少种不同的字符串等价于这个 数字串。 输入是一个由数字组成的字符串，输出是满足条件的解码方式总数。 Input: "226"
    Output: 3在这个样例中，有三种解码方式:BZ(2 26)、VF(22 6) 或 BBF(2 2 6)思路 ：转移方程 f[i] = f[i-1] 表示当前可以独立组成要出来的数
              f[i] = f[i-2]  表示需要前面一个组合加上当前的数字组成 
              f[i] +=f[i-2]  独立组成+需要前面组成的次数 就是总次数
              比如 226  f[] = 【s.length+1】 f[0] = 1 ;
              s[0]='2'
              f[1]
                  2   可以由 '2' 或者 ''+'2'组成（因为前面不能为空所以''+'2'不能组成 所以只有当前他自己 所以为f[1-1]）
              f[2]
                  22  可以'2' + f[1] 组成  或者 f[2-2] + '22'
              f[3]
                  226 可以由 2 2 6 (f[2])、 22 6(f[2]） 2 26 (f[0])  组成
                对着答案看了很久才看明白，一个人刷题真的是很难坚持，加油啊 加油啊 

`

    20210527 
        最长公共子序列
        两个字符串最长公共子序列个数
    
