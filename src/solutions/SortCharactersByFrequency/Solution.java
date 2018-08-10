package solutions.SortCharactersByFrequency;

/*

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

class Solution {
    public String frequencySort(String s) {
        int[] f = new int[256];
        for (char c : s.toCharArray()) {
            f[c]++;
        }
        
        List<Character>[] buckets = new List[s.length() + 1];
        
        for (int i = 0; i < 256; i++) {
            if (f[i] == 0) continue;
            char c = (char)(i);
            int fre = f[i];
            if (buckets[fre] == null) {
                buckets[fre] = new ArrayList<Character>();
            }
            buckets[fre].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++)
                        sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}