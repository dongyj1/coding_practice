package solutions.MinimumGeneticMutation433;

/**
 * Created by dyj on 7/4/18.

 A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

 Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

 For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

 Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

 Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

 Note:

 Starting point is assumed to be valid, so it might not be included in the bank.
 If multiple mutations are needed, all mutations during in the sequence must be valid.
 You may assume start and end string is not the same.

 */
class Solution {
    int min = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        if (bank.length == 0) {
            return -1;
        }
        boolean[] visited = new boolean[bank.length];
        if (dfs(bank, visited, start, end, 0)) {
            return min;
        }
        return -1;
    }

    private boolean dfs(String[] bank, boolean[] visited, String start, String target, int cur) {
        if (start.equals(target)) {
            min = Math.min(min, cur);
            return true;
        }
        boolean found = false;
        for (int i = 0; i < bank.length; i++) {
            // System.out.println(!isOneDiff(bank[i], start));
            if (visited[i] || !isOneDiff(bank[i], start)) {
                continue;
            }
            visited[i] = true;
            found = dfs(bank, visited, bank[i], target, cur + 1) || found;
            visited[i] = false;
        }
        return found;
    }

    private boolean isOneDiff(String a, String b) {
        char[] chA = a.toCharArray();
        char[] chB = b.toCharArray();
        int count = 0;
        for (int i = 0; i < chA.length; i++) {
            if (chA[i] != chB[i]) {
                count++;
            }
        }
        return count == 1;
    }
}