import java.util.*;

class Solution {
    static int T;
    static HashSet<String> map = new HashSet<>();
    
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};

        T = words.length;
        // System.out.print(T);
        
        String first = words[0];
        // int a = first.length();
        // System.out.print(first.charAt(first.length()-1));
        map.add(first);
        char lastWord = first.charAt(first.length()-1);
        
        for(int i = 1 ; i < T; i++){
            String cur = words[i];
            if(map.contains(cur) || lastWord != cur.charAt(0)){
                // System.out.print("Hello " + i);
                answer[0] = i%n +1;
                answer[1] = i/n +1;
                break;
            }
            
            lastWord = cur.charAt(cur.length()-1);
            map.add(cur);
            // System.out.print(cur + " ");
        }
        
        
        return answer;
    }
}