# 399 Evaluate-Division

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
<div><p>Equations are given in the format <code>A / B = k</code>, where <code>A</code> and <code>B</code> are variables represented as strings, and <code>k</code> is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return <code>-1.0</code>.</p>
<p><b>Example:</b><br>
Given <code> a / b = 2.0, b / c = 3.0.</code><br>
queries are: <code> a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .</code><br>
return <code> [6.0, 0.5, -1.0, 1.0, -1.0 ].</code></p>
<p>The input is: <code> vector&lt;pair&lt;string, string&gt;&gt; equations, vector&lt;double&gt;&amp; values, vector&lt;pair&lt;string, string&gt;&gt; queries </code>, where <code>equations.size() == values.size()</code>, and the values are positive. This represents the equations. Return <code> vector&lt;double&gt;</code>.</p>
<p>According to the example above:</p>
<pre>equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. </pre>
<p>&nbsp;</p>
<p>The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.</p>
</div></section>
 
 ## Method One 
 
``` Java
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 卧槽真是个难题
        // 破题的关键是建加权图 A/B = 2， B/C = 3 即 A -- 2 --> B -- 3 --> C 
        // 反之有  C -- 1/3 --> B -- 1/2 --> A, 因此 A/C = 6, C/A = 1/6 .
        
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        for(int i = 0; i < values.length; i++) {
            String variableA = equations.get(i).get(0);
            String variableB = equations.get(i).get(1);
            graph.putIfAbsent(variableA, new HashMap<String, Double>());
            graph.putIfAbsent(variableB, new HashMap<String, Double>());
            graph.get(variableA).put(variableB, values[i]);
            graph.get(variableB).put(variableA, 1.0/values[i]);
        }
        
        double[] ans = new double[queries.size()];
        int curProblem = 0;
        for(List<String> query : queries) {
            String numerator = query.get(0);
            String denominator = query.get(1);
            
            if(!graph.containsKey(numerator)) {
                ans[curProblem++] = -1;
                continue;
            }
            
            Set<String> marked = new HashSet<>();
            Stack<String> onStack = new Stack<>();
            Stack<Double> valStack = new Stack<>();
            
            onStack.push(numerator);
            valStack.push(1.0);
            
            while(!onStack.isEmpty()) {
                String curVar = onStack.pop();
                Double curVal = valStack.pop();

                marked.add(curVar);
                if(curVar.compareTo(denominator) == 0) {
                    ans[curProblem] = curVal;
                    break;
                }
                for(String neighbor : graph.get(curVar).keySet()) {
                    if(marked.contains(neighbor)) {
                        continue;
                    }
                    double nextVal = graph.get(curVar).get(neighbor);
                    onStack.push(neighbor);
                    valStack.push( curVal * nextVal );
                }
            }
            if( ans[curProblem] < 0.001 && ans[curProblem] > -0.001) {
                ans[curProblem] = -1;
            }
            curProblem += 1;
        }

        return ans;
    }

}

````

## follow up

* You know that variables can only be represented by lower letters of English alphabet (up to 26 letters). How would you optimize to make it faster then HashSet/Map?
Use 26x26 matrix and fill diagonal with 1.0 (a --1-->a)
* People in Mathlandia like to have long variable names, where variable name can be up to 10,000 letters. Your program consumes to much memory and long names slow down implementation. How would you optimize it?
Convert variable names to indices. Graph will be represented by indices instead of variables.
Keep nameToIdx which maps name of variable to index, names which 'maps' index to names


## Union Find is the Best Method:

```java
class Solution {
    /*
        Union Find 这个题的想法就是:
        以只做过除数而没有被除的数当作root 节点，即参考点，令他的值为1.
        即直接反应一个倍数关系，a/b = 2, b 是 1倍， a 是 2 倍。
        如
        x1/x2 = 2, x2/x3 = 3, x3/x4= 4
        x1 -> x2 -> x3 -> x4
        24     12    4    1
        储存的方式是
        x4:1 -> x1:24
             -> x2:12
             -> x3:4
        如果我们找 x2/x3, 那就是 x2: 4 / x3:12 = 3；

        y1/y2 = 3, y2/y3 = 5
        y1 -> y2 -> y3
        15     5     1

        y3:1 -> y1:15
             -> y2:5

        如果此时我们求 x1/y2, 由于 root 不一样，我们回复一个  -1.
        如果另加一个
        x1/y1 = 2,

        union x1, y1, 那么我们直接 union 他们的 root, x4 和 y3 就好了, 我们令 y3 是 x4 的 root。
        那么怎么放缩数字的倍数呢？我们令 x4/y3 = 15/24 * 2 就是这两个树的倍数之比了！
        所以我们把 x4 的倍数由 1 调整至 15/24 * 2 即可
        证明：  \frac{x4}{y3} = \frac{x4 x1 y1 }{y3 x1 y1} = \frac{x1}{y1} \frac{y1}{y3} \frac{x4}{x1} = 2 * 15 * 1/24
        下次 path compression 的时候，这个倍数就会被乘给它的子节点。
        y3:1 -> y1:15
             -> y2:5
             -> x4:15/24 * 2 -> x1:24
                             -> x2:12
                             -> x3:4
    */
    Map<String, String>roots = new HashMap<>();
    Map<String, Double>numbers = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] answers = new double[queries.size()];

        for (int i = 0; i < values.length ; i++ ) {
            List<String> equation = equations.get(i);
            String numerator = equation.get(0);
            String denominator = equation.get(1);
            double quotient = values[i];
            union(numerator, denominator, quotient);
        }
        for (int i = 0; i < queries.size(); ++i) {
            List<String> query = queries.get(i);
            String numerator = query.get(0);
            String denominator = query.get(1);
            answers[i] = (roots.containsKey(numerator) && roots.containsKey(denominator) && findRoot(numerator) == findRoot(denominator) )
                ? numbers.get(numerator) / numbers.get(denominator) : -1.0;
        }
        return answers;
    }

    public void addNode(String curNode) {
        if (roots.containsKey( curNode )) {
            return;
        }
        roots.put( curNode,  curNode);
        numbers.put( curNode, 1.0);
    }
    public String findRoot(String curNode) {
        String parentNode = roots.getOrDefault(curNode, curNode);
        if (curNode == parentNode) {
            return parentNode;
        }
        /* 这里的 PostOrder 是找到根节点之后开始向上撤回递归，因为根节点的值是1，
         这里我们一层一层把树直接捋直了。
         我们有两个指针，一个指根节点，一个指父节点。
         过程如下
         Step 0 : 1 -> 3 -> 2 -> 3
         Step 1 : 1 -> 3
                    -> 6 -> 3
         Step 2 : 1 -> 3
                    -> 6
                    -> 18
        */
        String rootNode = findRoot(parentNode);
        numbers.put(curNode, numbers.get(curNode) * numbers.get(parentNode));
        roots.put(curNode, rootNode);

        return roots.getOrDefault(curNode, curNode);
    }

    public void union(String numerator, String denominator, double quotient) {
        addNode(numerator);
        addNode( denominator);
        String numeratorRoot = findRoot(numerator);
        String denomitorRoot = findRoot( denominator);
        roots.put(numeratorRoot, denomitorRoot);
        numbers.put(numeratorRoot, quotient * numbers.get( denominator) / numbers.get(numerator));
    }
}
````
