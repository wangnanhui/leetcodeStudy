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

###20210527 
        最长公共子序列
        两个字符串最长公共子序列个数`

#### 20210530
    `0-1背包问题
     完全背包问题
    leetcode416
     0-1背包问题`
    `给你一个 只包含正整数 的 非空 数组 nums 。
    请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
    输入：nums = [1,5,11,5]
    输出：true
    解释：数组可以分割成 [1, 5, 5] 和 [11] 。`
#### 20210530
    
 （大哥你也不陪我刷题了 唉 也许有一天你能看到这个  )
`最初在一个记事本上只有一个字符 'A'。
你每次可以对这个记事本进行两种操作Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
Paste (粘贴) : 你可以粘贴你上一次复制的字符。给定一个数字 n 。
你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。
输出能够打印出 n 个 'A' 的最少操作次数。`


#### 20210601
    最长公共子串
    乘积最大子数组
`

#### 20210602
    正则表达式匹配   leetcode 10
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素

    股票问题 121

##### 20210603

    leetcode ：213 
    你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
    给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
    输入：nums = [2,3,2]
    输出：3
    解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    思路 ： 收尾相连 ， 那么偷第一间就不能偷最后一间 ，同理 偷最后一件就不能偷第一间
    所以分两种情况 1、第一次偷第一间 最后一间不偷   2、偷最后一件，第一间不偷

   ` ps:得看看Java这块的东西了，之后每天刷一道题，看一点Java的东西 ，不知道大哥你啥时候才能开始跟二弟一起刷题啊
`
##### 20210607
    股票持有问题 
    leetcode 188

