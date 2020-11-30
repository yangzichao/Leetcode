# 209 Minimum-Size-Subarray-Sum

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
<div><p>Given an array of <strong>n</strong> positive integers and a positive integer <strong>s</strong>, find the minimal length of a <b>contiguous</b> subarray of which the sum ≥ <strong>s</strong>. If there isn't one, return 0 instead.</p>
<p><strong>Example:&nbsp;</strong></p>
<pre><strong>Input:</strong> <code>s = 7, nums = [2,3,1,2,4,3]</code>
<strong>Output:</strong> 2
<strong>Explanation: </strong>the subarray <code>[4,3]</code> has the minimal length under the problem constraint.</pre>
<div class="spoilers"><b>Follow up:</b></div>
<div class="spoilers">If you have figured out the <i>O</i>(<i>n</i>) solution, try coding another solution of which the time complexity is <i>O</i>(<i>n</i> log <i>n</i>).&nbsp;</div>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minimum = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
​
        for(int i = 0; i < nums.length; i++ ) {
            int right = i;
            sum += nums[i];
            while(sum >= s) {
                minimum = Math.min( minimum, (right - left + 1) );
                sum -= nums[left];
                left += 1;
            }
        }
        
        return minimum == Integer.MAX_VALUE  ?  0 : minimum;
    }
}
​
```

第二次写，先写的是这样的

```java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minSize = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for(int right = 0; right < nums.length; right++) {
            if(sum < s) {
                sum += nums[right];
            }
            while(sum >= s) {
                minSize = Math.min(minSize, right - left + 1);
                sum -= nums[left];
                left +=1;
            }
        }

        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }
}
```

后面发现可以把 if(sum < s ) 拿掉，因为 while 保证了下一次一定 sum < s
