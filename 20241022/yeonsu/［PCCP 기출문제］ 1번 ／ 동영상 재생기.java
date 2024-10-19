import java.util.*;
import java.io.*;

class Solution {
    static StringTokenizer token;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        token = new StringTokenizer(video_len, ":");
        int maxM = Integer.parseInt(token.nextToken());
        int maxS = Integer.parseInt(token.nextToken());
        int runningTime = maxM * 60 + maxS;
        token = new StringTokenizer(pos, ":");
        int posM = Integer.parseInt(token.nextToken());
        int posS = Integer.parseInt(token.nextToken());
        int posTime = posM * 60 + posS;
        token = new StringTokenizer(op_start, ":");
        int opsM = Integer.parseInt(token.nextToken());
        int opsS = Integer.parseInt(token.nextToken());
        int opsTime = opsM * 60 + opsS;
        token = new StringTokenizer(op_end, ":");
        int opeM = Integer.parseInt(token.nextToken());
        int opeS = Integer.parseInt(token.nextToken());
        int opeTime = opeM * 60 + opeS;

        if (posTime >= opsTime && posTime < opeTime) {
            posTime = opeTime;
        }
        for (int i=0;i<commands.length;i++) {
            if (commands[i].equals("next")) {
                posTime += 10;
            } else {
                posTime -= 10;
            }
             
            if (posTime < 0) {
                posTime = 0;
            } else if (posTime > runningTime) {
                posTime = runningTime;
            }
            if (posTime >= opsTime && posTime < opeTime) {
                posTime = opeTime;
            }
        }
        answer = secondsToTime(posTime);
        
        return answer;
    }
    
    private String secondsToTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}