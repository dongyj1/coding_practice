package solutions.RepeatedStringMatch;

/*
A = "abcd" and B = "cdabcdabc". -> 3

abcdabcd
*/

class Solution {
    public int repeatedStringMatch(String A, String B) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.toString().indexOf(B) != -1) {
            return count;
        }
        sb.append(A);
        count++;
        if (sb.toString().indexOf(B) != -1) {
            return count;
        }
        return -1;
    }
}