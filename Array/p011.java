class Solution {
    public int maxArea(int[] height) {
        int largest_volume = 0;
        for ( int i = 0; i < height.length; i++ ){
            for (int j = i + 1; j < height.length; j++ ) {
                int volume = 0;
                if (height[i] > height[j]){
                    volume = height[j] * (j - i);
                }else{
                    volume = height[i] * (j - i);
                }

                if (largest_volume < volume){
                    largest_volume = volume;
                }
            }
        }
        return largest_volume;
    }
}

/**
以上是暴力解法，穷举；
*/
