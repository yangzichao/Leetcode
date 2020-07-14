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

```

```
