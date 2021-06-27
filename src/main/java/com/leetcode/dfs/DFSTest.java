package com.leetcode.dfs;

import java.util.*;

public class DFSTest {
    public static void main(String[] args) {
        DFSTest dfs = new DFSTest();

        dfs.subsets(new int []{1,2,3});

        dfs.combinationSum(new int [] {2,3,5},7);

        char [][] board =    {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String  word = "ABCCED";
        dfs.exist(board,word);




       // combine(5,3);
       // permute(new int[]{1,2,3});
    }


    /**
     * leetcode 47
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> p =new ArrayList<>();

        dfs(p,nums,0);
        System.out.println("nums = " +p);
        return p ;
    }

    /**
     *
     *
     *
     *
     * @param permute
     * @param nums
     * @param level
     */
    static void dfs(List<List<Integer>> permute , int [] nums , int level){
        System.out.println("\t");
        if(level == nums.length - 1 ){
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                t.add(nums[i]);
            }
            permute.add(t);
            return;
        }
        for (int i = level; i < nums.length; i++) {
            swap(nums,i,level);
            dfs(permute,nums,level+1);
            swap(nums,level,i);
        }
    }
    static void swap(int [] num , int a , int b){
        if(a!= b){
            int temp = num[a];
            num[a] = num[b];
            num[b] = temp;
        }
    }

    public List<String> letterCombinations(String digits) {
        Map<Character,List<Character>> chars = new HashMap<>();
        chars.put('2', Arrays.asList('a','b','c'));
        chars.put('3', Arrays.asList('d','e','f'));
        chars.put('4', Arrays.asList('g','h','i'));
        chars.put('5', Arrays.asList('j','k','l'));
        chars.put('6', Arrays.asList('m','n','o'));
        chars.put('7', Arrays.asList('p','q','r','s'));
        chars.put('8', Arrays.asList('t','u','v'));
        chars.put('9', Arrays.asList('w','x','y','z'));

        List<String> list = new ArrayList<>();
        dfs1(digits,0,chars,new StringBuilder(),list);
        return list;
    }

    void dfs1(String dig , int start ,  Map<Character,List<Character>>  map ,StringBuilder stringBuilder ,List<String> combain){
        if(start == dig.length() ){
            combain.add(stringBuilder.toString()) ;
        }else{
            Character c = dig.charAt(start);
            List<Character> cs = map.get(c);
            for (int i = 0; i < cs.size(); i++) {
                stringBuilder.append((cs.get(i)));
                dfs1(dig,start+1,map,stringBuilder,combain);
                stringBuilder.deleteCharAt(start);
            }
        }
    }



    public String[] permutation(String s) {
        Set<String> list = new HashSet<>();
        if(s != null && s.length() >0 )
            dfs(list,s.toCharArray(),0);
        return list.toArray(new String[0]);
    }


    void dfs(Set<String> list , char [] chs , int start ){
        if(start == chs.length){
            list.add(new String(chs));
            return ;
        }
        for(int i = start ; i<chs.length;i++){
            swap(start,i,chs);
            dfs(list,chs,start+1);
            swap(i,start,chs);
        }
    }

    void swap(int i , int j , char [] chs ){
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp ;

    }

    public static List<List<Integer>> combine(int n , int k ){
        List<List<Integer>> list = new ArrayList<>();
        Set<Set<Integer>>  hSet = new HashSet<>();
        backtracking(hSet, list,1,n,k,new ArrayList<>());

        System.out.println(list);
        return list;
        
        
    }

    //本来是写了一个for 但超时了

    /**
     * 看了一下答案
     *  比如 5 3
     *      第一次  1 2 3  跳出remove掉3 此时在第二次递归中 start为3 进入第二个递归 start 4 因为时先判断再加入 所以每次都会执行后面的两个递归
     *      加入4后 1 2 4  此时start为4 加入list1后 满足list.size = 3 return 第一个递归跳出 remove掉4 进入第二个递归 start+1 = 5
     *      加入5后 1 2 5  此时start为4 加入list1后 满足list.size = 3 return 第一个递归跳出 remove掉5 进入第二个递归 start+1 = 6
     *
     *      第一轮过去后 进入第二个进去栈的方法 此时start = 2 ，从2开始继续循环上面的不步骤
     * @param hset
     * @param list
     * @param start
     * @param n
     * @param count
     * @param list1
     */
    static void  backtracking(  Set<Set<Integer>>  hset , List<List<Integer>> list , int start ,int n , int count ,List<Integer> list1 ){
        if (list1.size() + (n - start + 1) < count) {
            return;
        }
        if(count == list1.size()){
//            Set<Integer> list2 = new HashSet<>(list1);
//            if(!hset.contains(list2) &&list2.size() == count){
//                hset.add(list2);
                list.add(new ArrayList<>(list1));
       //     }
            return ;
        }

        list1.add(start);
        backtracking(hset,list,start+1,n,count,list1);
        list1.remove(list1.size() -1 );
        backtracking(hset,list,start+1,n,count,list1);
        
    }

    /**
     * leetcode 79
     * 我只想到70% 😁
     *
     * 解题思路 ：
     *      先往右边（i+1,j）扫描遇到不等于当前的字符时需要回退一个即（i-1,j）
     *      再往下面扫描（i，j+1)当当前的不满足 回退一个即（i，j-1） ，每次当前的pos+1
     *      如果pos和要匹配的串相等 说明匹配完全了返回true
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
               if(board[i][j] == word.charAt(0)){
                  if(check(board,i,j,word,0)){ //要先找到满足的开始的位置
                      return true ;
                   }
               }


            }
        }
        return false ;
    }

    boolean check(char [][] board , int i , int j , String word , int start  ){
       if(start == word.length() ){
           return true;
       }
        if(i <0 || j < 0 ){
            return false;
        }
        if( i >= board.length || j >=board[i].length){//如果start超过当前的return ；
            return false;
        }
        if(board[i][j] != word.charAt(start)){

            return false;
        }
        char t = board[i][j];
        board[i][j] = '0';
        boolean res =   check(board,i+1,j,word,start+1) ||  //只要里面有一个满足 说明可以匹配上
                        check(board,i-1,j,word,start+1)||
                        check(board,i,j+1,word,start+1)||
                        check(board,i,j-1,word,start+1) ;
        board[i][j] = t ; //防止匹配过了又重新匹配  没太明白
        return res ;
    }




    public List<List<String>>  solveNQueens(int N ){
        List<List<String>> list = new ArrayList<>();//存储最后的结果
        int [] column = new int[N] ;//存储皇后所在的列数
        Set<Integer>  row = new HashSet<>();//存储皇后所放在的行数
        Set<Integer>  left = new HashSet<>();//左对角线
        Set<Integer>  right = new HashSet<>();//右对角线
        nQueueDfs(list,N,row,left,right,0,column);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));

        }
        System.out.println(list);
        return list;
    }
    void nQueueDfs(List<List<String>> list , int n ,Set<Integer>  row,Set<Integer>  left,Set<Integer>  right , int i , int [] column ){
        if( i == n){ //走到最后一行了
            List<String>  temp = createQueue(column,n);
            list.add(new ArrayList<>(temp));
            return ;
        }
        for (int j = 0; j < n; j++) {
            //如果当前行的第j列已经放了一个皇后了那这一行一定不能再放其他的了
            //如果左对角线有了或者有右角线也有了也不能放了
            //j代表的是 第i行的第j列
            //有个规律 就是 每一个位置对应的左斜线上的每一个位置 行下标 + 列下标之和相等  右对角线的 行下标 - 列下标 之差相等 ，所以下面判断是是这样
            if(right.contains(j+i) || left.contains(j - i ) || row.contains(j)){
                continue;
            }
            column[i] =j ; //如果满足的话 先放入
            row.add(j);
            right.add(j+i);
            left.add(j-i);
            nQueueDfs(list,n,row,left,right,i+1,column);//遍历下一个位置
            row.remove(j);//因为还可能右其他方案 需要把上一次的移走
            right.remove(j+i);
            left.remove(j-i);


        }

    }


    List<String> createQueue(int [] column , int N){
        List<String> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            char [] row=new char[N];
            Arrays.fill(row,'.');
            row[column[i]]='Q';
            list.add(new String(row));
        }
        return list;
    }

    /**
     * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     *
     * candidates中的数字可以无限制重复被选取。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list= new ArrayList<>();
       Set<Set<Integer>> set = new HashSet<>();
      //  for (int i = 0; i < candidates.length; i++) {
        combinationDfs(list, new ArrayList<>(), candidates, target, 0, 0);
      //  }
        System.out.println(list);
        return list;

    }

    /**
     *
     * @param list
     * @param c
     * @param candidates
     * @param target
     * @param pos
     * @param sum
     */
    void combinationDfs( List<List<Integer>> list , List<Integer> c,  int[] candidates , int target , int pos , int sum){

        if(sum > target || pos == candidates.length  ){//大于说明不能用这个数
            return ;
        }

        if(sum == target){//如果相加之和等于要求得数 说明满足
            List<Integer>  temp= new ArrayList<>(c);
            Set<Integer> s = new HashSet<>(temp);
            list.add(temp);


            return ;
        }
        combinationDfs(list,c,candidates,target,pos+1,sum);//判断某个位置的值就是target
        if(sum + candidates[pos]<= target) {
            c.add(candidates[pos]);
            combinationDfs(list,c,candidates,target,pos,sum + candidates[pos]);
            c.remove(c.size() - 1 ) ;
        }

    }


    /**
     * leetcode 78 子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        subsetsDfs(new ArrayList<>(),list,nums,nums.length,0);
        System.out.println(list);
        return list;
    }


    void subsetsDfs( List<Integer> c ,List<List<Integer>> list , int [] nums , int n , int pos){
        if(pos == n){
            list.add(new ArrayList<>(c));
            return;
        }
        if(pos > n){
            return;
        }
        c.add(nums[pos]);
        subsetsDfs(c,list,nums,n,pos+1);
        c.remove(c.size() -1);
        subsetsDfs(c,list,nums,n,pos+1);




    }





}
