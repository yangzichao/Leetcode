class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        for i in range(len(nums)): # 遍历数组
            for j in range(len(nums)): #遍历数组
                if (i < j) and (nums[i]+nums[j] == target): # 确保只输出一次，比如 [1,2] = [2,1]， 但是要先输出小的
                    pos = [i,j] #建立一个列表储存找到的位置
                    return pos #返回位置
'''
这是我第一次提交
方法很蠢 就是暴力循环
缺点是费时费力，这就是官方提供的解答之一
'''
