# 编程小贴士


## 变量命名

boolean 函数命名 应当以 is/has/should + 词 命名
例如 isValid hasNext 

## Guard Clause
形如
```java
if( 条件 ){
    一大坨代码；
}else{
    return；
}
```
可读性较差，尤其是那一大坨代码非常长的时候。
此时不如
```java
if(!条件){
    return;
}
一大坨代码;
```
这样的可读性就好很多。
[See also](https://stackoverflow.com/questions/4887212/shall-i-use-guard-clause-and-try-to-avoid-else-clause)