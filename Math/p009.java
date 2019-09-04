class Solution {
    public boolean isPalindrome(int x) {
        int dupx = x;
        if (x > 0 || x == 0){
            int rev = 0;
            while(x != 0){
                rev = 10* rev + x%10;
                x = x/10;
            }
            if(dupx == rev){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
// 这个办法是翻转数字 然后看翻转的字符是否和原来的相同。
