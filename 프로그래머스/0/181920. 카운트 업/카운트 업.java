class Solution {
    public int[] solution(int start_num, int end_num) {
        int num = end_num - start_num + 1;
        int[] answer = new int[num];
        
        for(int i = 0 ; i < num ; i++){
            answer[i] = start_num + i;
        } 
        
        return answer;
    }
}