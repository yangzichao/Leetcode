# 374 Guess-Number-Higher-or-Lower

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
<div><p>We are playing the Guess Game. The game is as follows:</p>
<p>I pick a number from <b>1</b> to <b><i>n</i></b>. You have to guess which number I picked.</p>
<p>Every time you guess wrong, I'll tell you whether the number is higher or lower.</p>
<p>You call a pre-defined API <code>guess(int num)</code> which returns 3 possible results (<code>-1</code>, <code>1</code>, or <code>0</code>):</p>
<pre>-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
</pre>
<p><strong>Example :</strong></p>
<div>
<pre><strong>Input: </strong>n = <span id="example-input-1-1">10</span>, pick = <span id="example-input-1-2">6</span>
<strong>Output: </strong><span id="example-output-1">6</span>
</pre>
</div>
</div></section>
 
 ## Method One 
 
``` Java
/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return       -1 if num is lower than the guess number
 *                1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */
​
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1;
        int right = Integer.MAX_VALUE;
        
        while(left <= right) {
            int pivot = left + (right - left)/2;
            if( guess(pivot) == 1) {
                left = pivot + 1;
            }
            if( guess(pivot) == -1 ) {
                right = pivot - 1;
            }
            if( guess(pivot) == 0) {
                return pivot;
            }
         }
        return -1;
    }
}
​
```
