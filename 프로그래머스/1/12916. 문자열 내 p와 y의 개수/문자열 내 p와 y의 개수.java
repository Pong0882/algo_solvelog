class Solution {
    boolean solution(String s) {
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'P' || c == 'p') {
                result++;
            }
            else if(c == 'Y' || c == 'y') {
                result--;
            }
        }

        return result == 0 ? true : false;
    }
}