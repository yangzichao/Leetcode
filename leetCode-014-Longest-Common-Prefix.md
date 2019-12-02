# 014J Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:  
Input: ["flower","flow","flight"]  
Output: "fl"

Example 2:  
Input: ["dog","racecar","car"]  
Output: ""  
Explanation: There is no common prefix among the input strings.

Note:

All given inputs are in lowercase letters a-z.

## Method 1: 水平 （较优解，常规解）
* 首先判断是否空
* 不为空则先第一个和第二个String对比，如果 common prefix 不是从第二个 string的头部开始，
那么prefix去掉尾部，继续比。循环，直到prefix是从第二个string的头部开始.此时得到的就是1和2
字符串的共同prefix.
* 然后拿12的共同prefix继续和第3个比，以此类推。
* 注意，一旦出现prefix为空，要立刻返回空。否则继续运行会报错。
```Java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if( strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for(int i = 0; i < strs.length; i += 1){
          while(strs[i].indexOf(prefix) != 0 ) {
              prefix = prefix.substring(0,prefix.length() - 1);
              if (prefix.isEmpty()){
                  return "";
            }
          }
        }
        return prefix;
    }
}
```

## Method 2 竖直方向 (最优解)
* 竖直法首先还是排除空数组。
* 竖直法取第一个词的第 i 个字母去和 其他词的第 i 个比较。如果相同就跳过。
* 如果发现不同，那么就返回 substring(0,i)，其实就是少返回一个字符。
* 同时如果发现 i 等于字符串的长度。由于i是从0开始的，意味着此时i已经指向字符串末尾后一位不存在的
null了。那么立刻返回substring(0,i)。
* 竖直法和水平法相比，其期望是更小的。

```Java
class Solution {
    public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
    for (int i = 0; i < strs[0].length() ; i++){
        char c = strs[0].charAt(i);
        for (int j = 1; j < strs.length; j ++) {
            if (i == strs[j].length() || strs[j].charAt(i) != c){
                return strs[0].substring(0, i);  
              }           
        }
    }
    return strs[0];
    }
}
```
第二次竟然写出了几乎一样的代码；
```Java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length < 1 || strs[0].length() < 1){
            return "";
        }
        int count = 0;
        for(int i = 0; i < strs[0].length();i++){
            char c = strs[0].charAt(i);
            for(String s : strs){
                if( i > s.length() - 1|| s.charAt(i) != c){
                    return strs[0].substring(0, count);
                }
            }
            count++;
        }

        return strs[0];
    }
}
```
