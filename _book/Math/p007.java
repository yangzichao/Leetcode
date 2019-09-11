public class p007 {
  // Wrong answer
    // public static int reverse(int x) {
    //     short counter = 1;
    //     int output = 0;
    //     int sub = x;
    //     while(sub/10 != 0){
    //        sub = sub/10;
    //        counter += 1;
    //     }
    //     if((counter == 10) && (Math.abs((x%10)) > 1)){
    //       return 0;
    //     }
    //     else{
    //       for(short i = 1; i <= counter; i += 1){
    //         output += (x%10)* Math.pow(10,(counter - i));
    //         x = x/10;
    //       }
    //       return output;
    //     }
    // }

    public static int reverse(int x ){
      int rev = 0;
      int remain = 0;

        while(x != 0){
          remain = x%10;
          if(rev > Integer.MAX_VALUE/10 || rev == Integer.MAX_VALUE/10 && remain > 7){
            return 0;
          }
          if(rev < Integer.MIN_VALUE/10 || rev == Integer.MIN_VALUE/10 && remain < -8){
            return 0;
          }
          rev = rev*10 + remain;
          x = x/10;
        }
        return rev;

    }
    public static void main(String[] args){
      int x = 12345;
      System.out.println(reverse(x));
    }
}
