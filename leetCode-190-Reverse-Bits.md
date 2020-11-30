# 190 Reverse-Bits 
 
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
<div><p>Reverse bits of a given 32 bits unsigned integer.</p>
<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> 00000010100101000001111010011100
<strong>Output:</strong> 00111001011110000010100101000000
<strong>Explanation: </strong>The input binary string <b>00000010100101000001111010011100</b> represents the unsigned integer 43261596, so return 964176192 which its binary representation is <b>00111001011110000010100101000000</b>.
</pre>
<p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> 11111111111111111111111111111101
<strong>Output:</strong> 10111111111111111111111111111111
<strong>Explanation: </strong>The input binary string <strong>11111111111111111111111111111101</strong> represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is <strong>10111111111111111111111111111111</strong>.</pre>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<ul>
	<li>Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.</li>
	<li>In Java,&nbsp;the compiler represents the signed integers using <a href="https://en.wikipedia.org/wiki/Two%27s_complement" target="_blank">2's complement notation</a>. Therefore, in <strong>Example 2</strong>&nbsp;above the input represents the signed integer <code>-3</code>&nbsp;and the output represents the signed integer <code>-1073741825</code>.</li>
</ul>
<p>&nbsp;</p>
<p><b>Follow up</b>:</p>
<p>If this function is called many times, how would you optimize it?</p>
</div></section>
 
 ## Method One 
 
``` Java
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // 有个很简洁的写法
        int ans = 0;
        // int 一共 32位
        for(int i = 0; i < 32; i++ ) {
            // ans << 1 是直接进一位，最低位留下0.
            // n&1 是直接判断 n 最低位是 1 还是 0.
            // 然后一个 ｜ 或，就把 ans 搞好了。
            ans = ans << 1 | ( n & 1 );
            // n >>> 1 相当于 n /2;
            n >>>= 1;
        }
        return ans;
    }
}
​
​
​
```