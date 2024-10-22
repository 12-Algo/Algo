import java.util.*;

class Solution {
    private static int videoL;
    private static int now;
    private static int opStart;
    private static int opEnd;
    
    private static int convertToInt(String time) {
        return Integer.parseInt(time.substring(0, 2))*60 + Integer.parseInt(time.substring(3, 5));
    }
    
    private static String convertToString(int time) {
        String res = "";
        int m = time/60;
        int s = time%60;
        
        if (m < 10) {
            res += "0";
        }
        
        res += m + ":";
        
        if (s < 10) {
            res += "0";
        }
        
        res += s;
        
        return res;
    }
    
    private static void move(int value) {
        now += value;
        
        if (now < 0) {
            now = 0;
        } else if (now > videoL) {
            now = videoL;
        }
    }
    
    private static void checkOpening() {
        if (now >= opStart && now <= opEnd) {
            now = opEnd;
        }
    }
    
    private static void excuteCommands(String[] commands) {
        for (String c : commands) {
            if (c.equals("prev")) {
                move(-10);
            } else {
                move(10);
            }
            
            checkOpening();
        }
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        videoL = convertToInt(video_len);
        now = convertToInt(pos);
        opStart = convertToInt(op_start);
        opEnd = convertToInt(op_end);
        
        checkOpening();
        excuteCommands(commands);
        
        return convertToString(now);
    }
}