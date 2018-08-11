package solutions.NestedListWeightSum;

/*
Input: [[1,1],2,[1,1]]
Output: 10 

Input: [1,[4,[6]]]
Output: 27 
1 * 1 + 4 * 2 + 6 * 3 = 27
*/


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            sum += extract(ni, 1);
        }
        return sum;
    }

    private int extract(NestedInteger ni, int lv) {
        if (ni.isInteger()) {
            return ni.getInteger() * lv;
        } else {
            List<NestedInteger> list = ni.getList();
            int sum = 0;
            for (NestedInteger nii : list) {
                sum += extract(nii, lv + 1);
            }
            return sum;
        }
    }
}