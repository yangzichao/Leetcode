# 259 3Sum-Smaller 
 
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
<div><p>Given an array of <i>n</i> integers <i>nums</i> and a <i>target</i>, find the number of index triplets <code>i, j, k</code> with <code>0 &lt;= i &lt; j &lt; k &lt; n</code> that satisfy the condition <code>nums[i] + nums[j] + nums[k] &lt; target</code>.</p>
<p><strong>Example:</strong></p>
<pre><strong>Input:</strong> <i>nums</i> = <code>[-2,0,1,3]</code>, and <i>target</i> = 2
<strong>Output:</strong> 2 
<strong>Explanation:</strong>&nbsp;Because there are two triplets which sums are less than 2:
&nbsp;            [-2,0,1]
             [-2,0,3]
</pre>
<p><b style="font-family: sans-serif, Arial, Verdana, &quot;Trebuchet MS&quot;;">Follow up:</b> Could you solve it in <i>O</i>(<i>n</i><sup>2</sup>) runtime?</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        
        for(int i = 0; i < nums.length - 2; i++ ) {
            int aTarget = target - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            // 这里我们不需要考虑重复的了，因为只要的是不同的index组合。
            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum < aTarget){
                    // 这里是知道，一旦比 target 小，那么right一路向下到 left + 1 就有 right - left 种了。
                    // 因此直接 += right - left; 非常机智
                    count += right - left;
                    left++;
                }else{
                    right--;
                }
            }
        }
        
        return count;
    }
}
​
```