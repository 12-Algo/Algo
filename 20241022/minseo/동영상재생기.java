class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int max = getTime(video_len);
        int start = getTime(op_start);
        int end = getTime(op_end);
        int current = getTime(pos);
        
        // 재생 중인 경우
        if(start <= current && current <= end){
            current = end;
        }
        
        for(String command : commands){
            if(command.equals("next")){
                current += 10;
            }
            else{
                current -= 10;
            }
            
            if(current < 0){
                current = 0;
            }
            
            else if(current > max){
                current = max;
            }
            
            if(start <= current && current <= end){
                current = end;
            }
        }
        // gpt 참고함 ㅎ..
        return String.format("%02d:%02d", current / 60 , current % 60);
    }
    
    int getTime(String time){
        // 분으로 바꿔주기
        String[] arr = time.split(":");        
        return Integer.parseInt(arr[1]) + Integer.parseInt(arr[0]) * 60;
    }
}