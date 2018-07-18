package solutions.CompareVersionNumbers;

/**
 * Created by dyj on 7/17/18.
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int i = 0, j = 0;

        while ((i < v1.length) || (j < v2.length)) {
            int val1 = (i < v1.length) ? Integer.parseInt(v1[i++]) : 0;
            int val2 = (j < v2.length) ? Integer.parseInt(v2[j++]) : 0;

            if (val1 != val2) {
                return (val1 < val2) ? -1 : 1;
            }

        }

        return 0;
    }
}
