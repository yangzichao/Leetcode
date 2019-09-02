class p007 {
    public int[] twoSum(int[] nums, int target) {
        int[] re = new int[2];
        for(int i = 1 ;  i < nums.length; i += 1){
            for(int j = 0; j < i; j += 1){
                if(nums[i] + nums[j] == target){
                    re[0] = j;
                    re[1] = i;
                }
            }
        }
        return re;
    }
}
