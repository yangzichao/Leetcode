# 174 Dungeon-Game 
 
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
<div><style type="text/css">table.dungeon, .dungeon th, .dungeon td {
  border:3px solid black;
}
 .dungeon th, .dungeon td {
    text-align: center;
    height: 70px;
    width: 70px;
}
</style>
<p>The demons had captured the princess (<strong>P</strong>) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (<strong>K</strong>) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.</p>
<p>The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.</p>
<p>Some of the rooms are guarded by demons, so the knight loses health (<em>negative</em> integers) upon entering these rooms; other rooms are either empty (<em>0's</em>) or contain magic orbs that increase the knight's health (<em>positive</em> integers).</p>
<p>In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.</p>
<p>&nbsp;</p>
<p><strong>Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.</strong></p>
<p>For example, given the dungeon below, the initial health of the knight must be at least <strong>7</strong> if he follows the optimal path <code>RIGHT-&gt; RIGHT -&gt; DOWN -&gt; DOWN</code>.</p>
<table class="dungeon">
	<tbody>
		<tr>
			<td>-2 (K)</td>
			<td>-3</td>
			<td>3</td>
		</tr>
		<tr>
			<td>-5</td>
			<td>-10</td>
			<td>1</td>
		</tr>
		<tr>
			<td>10</td>
			<td>30</td>
			<td>-5 (P)</td>
		</tr>
	</tbody>
</table>
<p>&nbsp;</p>
<p><strong>Note:</strong></p>
<ul>
	<li>The knight's health has no upper bound.</li>
	<li>Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.</li>
</ul>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][ n - 1 ] = Math.max(1, 1 - dungeon[ m - 1][n - 1]);
        for(int i = m - 2; i >= 0; i--){
            dp[i][ n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }
        for(int j = n - 2; j >= 0; j--){
            dp[m - 1][j] = Math.max(1, dp[m - 1][j + 1] - dungeon[m - 1][j]);
        }
        for(int i = m - 2; i >= 0; i-- ){
            for(int j = n - 2; j >= 0; j-- ){
                int hp = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
                dp[i][j] = Math.max(1, hp);
            }
        }
        return dp[0][0];
    }
}
​
/**
    这个问题比较反直觉的是，它只能用终点反推起点。
    为什么？因为要考虑什么才是这个问题的子问题。
    对于子问题 我们不能够改变的就是这个终点 终点变了问题就变了
    因此我们只能够从终点倒推到某个起点
    二维数组 dp[i][j] 储存的就是我们的所有子问题的答案，即
    从 i,j 出发到终点所需要的最小体力
    状态转移 dp[i][j] = Math.min(dp[i + 1][j], dp[i][ j + 1]) - dungeon[i][j];
    如果数字为负，说明我们这里是加体力的，我们只需要一个 +1 的体力走到这里就行了。
*/
​
```