package solutions.CircularArrayLoop;

/*

Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.

Example 2: Given the array [-1, 2], there is no loop.

*/

class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int j = i, k = getIndex(j, nums);
            while (nums[i] * nums[k] > 0 && nums[i] * nums[getIndex(k, nums)] > 0) {
                if (j == k) {
                    if (j == getIndex(j, nums)){
                        break;
                    }
                    return true;
                }
                j = getIndex(j, nums);
                k = getIndex(getIndex(k, nums), nums);
            }
        }
        return false;
    }
    
    private int getIndex(int i, int[] nums) {
        int n = nums.length;
        return (n + i + nums[i]) % n;
    }
}