# Backtracking 回溯

LC489 robot room cleaner 待做啊

LC022


## 回溯模板

<pre>
基于下面两题写的
LC037
LC1307

核心部分是这样。可以写一些其他的helperfunction，以便于代码简单。
</pre>


```java
// 命可以起类似于 sodokuSolver/puzzleSolver之类的
private boolean backtracker(输入参数){
    1.边界条件，用来停止递归 一般直接 return true;
    2.特殊情况，需要以某一个规则直接进入下一层递归或者return false;
    3.
    开始迭代，迭代中暗藏 return true;
    for(以某种条件开始迭代){
        1.跳过不合适的值。
        2.尝试可能合适的值。
        3.
        if( backtracker(去到下面一层)  ){  // Guard Clause
            //如果下面一层传来 true
            return true;
        }
        4.
        如果下面一层不能传来一个合适的遍历的值，才会运行到这里。
        undo 第2步，将它复原，尝试下一个迭代的值。
    }
    4.
    通过了本层迭代，还不能找到合适的值 return true，说明真不行
    直接，
    return false;
}

```
