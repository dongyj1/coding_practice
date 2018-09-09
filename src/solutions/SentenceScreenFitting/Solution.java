package solutions.SentenceScreenFitting;


// simple simulation
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int sum = 0, ans = 0, which = 0;
        for (String s : sentence) sum += s.length();
        for (int i = 0; i < rows; i++) {
            int remaining = cols + 1; // reserve an extra space for the dummy space to the left of the first letter
            while (which < sentence.length && sentence[which].length() + 1 <= remaining) {
                remaining -= sentence[which++].length() + 1;
            }
            if(which >= sentence.length){
                which = 0;
                ans = ans + 1 + remaining / (sum + sentence.length);
                remaining %= sum + sentence.length;
                while (which < sentence.length && sentence[which].length() + 1 <= remaining) {
                    remaining -= sentence[which++].length() + 1;
                }
            }
        }
        return ans;
    }
}