# 442 Find-All-Duplicates-in-an-Array 
 
difficulty: Medium 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>Given an array of integers, 1 ≤ a[i] ≤ <i>n</i> (<i>n</i> = size of array), some elements appear <b>twice</b> and others appear <b>once</b>.</p>
<p>Find all the elements that appear <b>twice</b> in this array.</p>
<p>Could you do it without extra space and in O(<i>n</i>) runtime?</p>
<p></p>
<p><b>Example:</b><br>
</p><pre><b>Input:</b>
[4,3,2,7,8,2,3,1]
<b>Output:</b>
[2,3]
</pre></div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        // 题目给出了一个关键信息是，每个数都不比数组的尺寸大。
        // 而且这个题还说，不会出现多于两次的。那么这个题就很无聊了。
        // 比如第一个数是4，那么我们就把nums[3]设为负就好了
        List<Integer> ans = new ArrayList<>();
        for(int n : nums){
            if( nums[Math.abs(n) - 1] < 0 ){
                ans.add(Math.abs(n));
            }
            nums[Math.abs(n) - 1] *= -1;
        }
        return ans;
    }
}
​
```