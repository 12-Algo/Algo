class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = Integer.parseInt(video_len.substring(0, 2)) * 60 + Integer.parseInt(video_len.substring(3, 5));
        int nowPos = Integer.parseInt(pos.substring(0, 2)) * 60 + Integer.parseInt(pos.substring(3, 5));
        int opStart = Integer.parseInt(op_start.substring(0, 2)) * 60 + Integer.parseInt(op_start.substring(3, 5));
        int opEnd = Integer.parseInt(op_end.substring(0, 2)) * 60 + Integer.parseInt(op_end.substring(3, 5));
        
        for (int i=0;i<commands.length;i++) {
            if (commands[i].equals("next")) {
                if (nowPos >= opStart && nowPos <= opEnd) {
                    nowPos = opEnd;
                    nowPos = Math.min(nowPos+10, videoLen);
                } else {
                    nowPos = Math.min(nowPos+10, videoLen);    
                    if (nowPos >= opStart && nowPos <= opEnd) {
                        nowPos = opEnd;  
                    }
                }
            } else if (commands[i].equals("prev")) {
                nowPos = Math.max(nowPos-10, 0);
                if (nowPos >= opStart && nowPos <= opEnd) {
                    nowPos = opEnd;  
                }
            }
        }
        
        int minute = nowPos / 60;
        int second = nowPos % 60;
        String answer = "";
        if (minute < 10) {
            answer += "0" + minute;
        } else {
            answer += minute;
        }
        answer += ":";
        if (second < 10) {
            answer += "0" + second;
        } else {
            answer += second;
        }
        return answer;
    }
}