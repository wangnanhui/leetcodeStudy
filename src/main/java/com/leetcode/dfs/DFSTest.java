package com.leetcode.dfs;

import java.util.*;

public class DFSTest {
    public static void main(String[] args) {
        combine(5,3);
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
     *      第一次  1 2 3  跳出remove掉3 此时在第二次递归中 start为3 进入第二个递归 start 4 满足后加入list中 然后return   相当于两次return 返回加入2时的方法栈
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


}
