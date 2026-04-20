import java.util.*;

class Solution {
    static int N;
    
    public int solution(String s) {
        int answer = 0;
        N = s.length();
        
        for (int start = 0; start < N; start++) {
            ArrayDeque<Character> q = new ArrayDeque<>();
            boolean flag = false;
            
            for (int i = 0; i < N; i++) {
                int cur = (start + i) % N;
                char c = s.charAt(cur);
                
                if (c == '[' || c == '{' || c == '(') {
                    q.push(c);
                } else {
                    if (q.size() == 0) {
                        flag = true;
                        break;
                    } else {
                        char top = q.peek();
                        
                        switch (c) {
                            case ']': {
                                if (top == '[') {
                                    q.pop();
                                } else {
                                    flag = true;
                                }
                                break;
                            }
                            case '}': {
                                if (top == '{') {
                                    q.pop();
                                } else {
                                    flag = true;
                                }
                                break;
                            }
                            case ')': {
                                if (top == '(') {
                                    q.pop();
                                } else {
                                    flag = true;
                                }
                                break;
                            }
                        }
                        
                        if (flag) {
                            break;
                        }
                    }
                }
            }
            
            if (!flag && q.isEmpty()) {
                answer++;
            }
        }
        
        return answer;
    }
}