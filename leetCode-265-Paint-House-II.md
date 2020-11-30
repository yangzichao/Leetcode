# 265 Paint-House-II 
 
difficulty: Hard 
 
<style>
        section pre{
          background-color: #eee;
          border: 1px solid #ddd;
          padding:10px;
          border-radius: 5px;
        }
      </style>
<section>
<div><p>There are a row of <i>n</i> houses, each house can be painted with one of the <i>k</i> colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.</p>
<p>The cost of painting each house with a certain color is represented by a <code><i>n</i> x <i>k</i></code> cost matrix. For example, <code>costs[0][0]</code> is the cost of painting house 0 with color 0; <code>costs[1][2]</code> is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.</p>
<p><b>Note:</b><br>
All costs are positive integers.</p>
<p><strong>Example:</strong></p>
<pre><strong>Input:</strong> [[1,5,3],[2,9,4]]
<strong>Output:</strong> 5
<strong>Explanation: </strong>Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
&nbsp;            Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 
</pre>
<p><b>Follow up:</b><br>
Could you solve it in <i>O</i>(<i>nk</i>) runtime?</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    private int numberOfColors;
    public int minCostII(int[][] costs) {
        if(costs.length < 1 || costs[0].length < 1 ) {
            return 0;
        }
        if(costs[0].length == 1 ) {
            return costs[0][0];
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
​
```