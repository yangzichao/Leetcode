# 278 First-Bad-Version

difficulty: Easy

<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.</p>
<p>Suppose you have <code>n</code> versions <code>[1, 2, ..., n]</code> and you want to find out the first bad one, which causes all the following ones to be bad.</p>
<p>You are given an API <code>bool isBadVersion(version)</code> which will return whether <code>version</code> is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.</p>
<p><b>Example:</b></p>
<pre>Given n = 5, and version = 4 is the first bad version.
<code>call isBadVersion(3) -&gt; false
call isBadVersion(5)&nbsp;-&gt; true
call isBadVersion(4)&nbsp;-&gt; true
Then 4 is the first bad version.&nbsp;</code>
</pre>
</div></section>
 
 ## Method One 
 
``` Java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
​
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while(left <= right) {
            int pivot = left + (right - left)/2;
            boolean isCur = isBadVersion(pivot);
            if(!isCur) {
                left = pivot + 1;
            }else{
                right = pivot - 1;
            }
        }
        return left;
    }
}
​
```
