package solutions.NumberofSubarrayswithBoundedMaximum;

/*
Input: 
A = [2, 1, 4, 3]
L = 2
R = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].

*/

class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int res = 0, count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= L && A[i] >= R) {
                count = i - j + 1;
                res += i - j + 1;
            } else if (A[i] < L) {
                res += count;
            } else {
                count = 0;
                j = i + 1;
            }
        }
        return res;
    }
}