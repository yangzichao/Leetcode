# 212 Word-Search-II 
 
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
<div><p>Given a 2D board and a list of words from the dictionary, find all words in the board.</p>
<p>Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.</p>
<p>&nbsp;</p>
<p><strong>Example:</strong></p>
<pre><strong>Input:</strong> 
<b>board </b>= [
  ['<span style="color:#d70">o</span>','<span style="color:#d70">a</span>','a','n'],
  ['e','<span style="color:#d30">t</span>','<span style="color:#d00">a</span>','<span style="color:#d00">e</span>'],
  ['i','<span style="color:#d70">h</span>','k','r'],
  ['i','f','l','v']
]
<b>words</b> = <code>["oath","pea","eat","rain"]</code>
<strong>Output:&nbsp;</strong><code>["eat","oath"]</code>
</pre>
<p>&nbsp;</p>
<p><b>Note:</b></p>
<ol>
	<li>All inputs are consist of lowercase letters <code>a-z</code>.</li>
	<li>The values of&nbsp;<code>words</code> are distinct.</li>
</ol>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    private class Node{
        Map<Character, Node> children;
        boolean isWord;
        String str;
        public Node(boolean isWord){
            this.isWord = isWord;
            this.children = new HashMap<>();
            this.str = "";
        }
    }
    private Node root;
    private char[][] board;
    private Set<String> ans;
    private final int[][] directions = new int[][]{{0,1}, {1,0}, {0, -1}, {-1, 0}};
    
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.root = new Node(false);
        this.ans = new HashSet<>();
        
        for( String word : words ){
            Node cur = root;
            for(char c : word.toCharArray()){
                if( ! cur.children.containsKey(c) ){
                    cur.children.put(c, new Node(false));
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
            cur.str = word;
        }
        

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(!root.children.containsKey(board[i][j])){
                    continue;
                }
                boolean[][] marked = new boolean[board.length][board[0].length];
                marked[i][j] = true;
                Node cur = root.children.get(board[i][j]);
                dfs(cur, marked, i, j);
            }
        }
        return new ArrayList<>(ans);
    }
    
    private void dfs(Node cur, boolean[][] marked, int row, int col){
        if( cur.isWord == true ){
            ans.add(cur.str);
        }
        for(int[] direction : directions ){
            int r = row + direction[0];
            int c = col + direction[1];
            if( r < 0 || c < 0 || r >= marked.length || c >= marked[0].length ){
                continue;
            }
            if( marked[r][c] ){
                continue;
            }
            if( !cur.children.containsKey( board[r][c] ) ){
                continue;
            }
            marked[r][c] = true;
            Node next = cur.children.get( board[r][c] );
            dfs(next, marked, r, c);
            marked[r][c] = false;
        }
    }
}
/*
这个问题 我在这个例子上翻车过。
Input:
[["a","b"],["a","a"]]
["aba","baa","bab","aaab","aaa","aaaa","aaba"]
Output:
["aaa","aba","aaba","baa"]
Expected:
["aaa","aaab","aaba","aba","baa"]
 
原因就在于这个，如果找到了某个word, 还是应当继续搜索的！
if( cur.isWord == true ){
    ans.add(cur.str);
    return;
}
        
*/
```