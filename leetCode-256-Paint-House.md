# 256 Paint-House 
 
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
<div><p>There are a row of <i>n</i> houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.</p>
<p>The cost of painting each house with a certain color is represented by a <code><i>n</i> x <i>3</i></code> cost matrix. For example, <code>costs[0][0]</code> is the cost of painting house 0 with color red; <code>costs[1][2]</code> is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.</p>
<p><b>Note:</b><br>
All costs are positive integers.</p>
<p><strong>Example:</strong></p>
<pre><strong>Input:</strong> [[17,2,17],[16,16,5],[14,3,19]]
<strong>Output:</strong> 10
<strong>Explanation: </strong>Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
&nbsp;            Minimum cost: 2 + 5 + 3 = 10.
</pre>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    private int numberOfColors;
    public int minCost(int[][] costs) {
        if(costs.length < 1 ) {
            return 0;
        }
        this.numberOfColors = costs[0].length;
        int[] prevMinCostByColor = new int[ numberOfColors ];
        
        for(int i = 0; i < costs.length; i++ ) {
            
            int[] curMinCostByColor = new int[ numberOfColors ];
            
            for(int c1 = 0; c1 < numberOfColors; c1++ ) {
                int min = Integer.MAX_VALUE;
                for(int c2 = 0; c2 < numberOfColors; c2++ ) {
                    if( c1 == c2 ) {
                        continue;
                    }
                    min = Math.min(min, prevMinCostByColor[c2]);
                }
                curMinCostByColor[c1] = min + costs[i][c1];
            }
            prevMinCostByColor = curMinCostByColor;
        } 
        
        int minCost = Integer.MAX_VALUE; 
        for(int c = 0; c < numberOfColors; c++ ) {
            minCost = Math.min(minCost, prevMinCostByColor[c] );
        }
        return minCost;
    }
}
​
​
/**
对于 第 N 个房子的 第 c 个颜色来说。
它可以选择和上个房子不同的颜色。因此而 dp 的。
*/
        for(int i = 0; i < 3; i++ ) {
​
```