# Binary Search


### case 11:
<pre>
left <= right 找 1
if(P < target) L = P + 1
else(P >= target) R = P - 1

0 1 2
L P R // P = 1， R = P - 1;
0   1 2  // P < 1, L = R, L = P + 1;
LPR
0  1 2 // L > R, return L;
PR L

把等号给R
return L;
P 停在 target 左侧
</pre>
### case 12:
<pre>
left <= right 找 1
if(P > target) R = P - 1
else (P <= target) L = P + 1

0 1 2
L P R // P = 1, L = P + 1
0 1  2
    LPR // P > 1, R = P - 1;
0 1 2   // L > R, return R.
  R LP

把等号给L
return R;
P 停在 target 右侧
</pre>

### case 21:

<pre>
left < right 找 1
if(P > target)  R = P - 1;
else (P <= target) L = P + 1;

0 1 2
L P R  // P = 1, L = P + 1;
0 1  2
  P LR // L == R, return L - 1;

等号给L
LR都在target右侧
返回P;
</pre>


### case 22:

<pre>
left < right 找 1
if (P < target) L = P + 1
else (P >= target) R = P - 1;

0 1 2
L P R // P = 1, R = P - 1
0   1  2
LR  P // L == R, return L + 1;

等号给R
LR都在target左侧
返回P;
</pre>
