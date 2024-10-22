import java.util.*;

class Solution {
    private final class Time {
        int mm, ss;
        
        public Time(int mm, int ss) {
            this.mm = mm;
            this.ss = ss;
        }
        
        public boolean greaterEquals(Time other) {
            if(this.mm > other.mm)
                return true;
            
            if(this.mm == other.mm && this.ss >= other.ss)
                return true;
            
            return false;
        }
        
        public boolean lessEquals(Time other) {
            if(this.mm < other.mm)
                return true;
            
            if(this.mm == other.mm && this.ss <= other.ss)
                return true;
            
            return false;
        }
        
        public void next() {
            ss += 10;
            
            if(ss >= 60) {
                ss -= 60;
                mm ++;
            }
        }
        
        public void prev() {
            ss -= 10;
            
            if(ss >= 0)
                return;
            
            if(mm == 0) {
                ss = 0;
                return;
            }
            
            mm --;
            ss += 60;
        }
        
        @Override
        public String toString() {
            return String.format("%02d:%02d", mm, ss);
        }
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        StringTokenizer st;
        Time videoLen, now, opStart, opEnd;
        
        st = new StringTokenizer(video_len, ":");
        int mm = Integer.parseInt(st.nextToken());
        int ss = Integer.parseInt(st.nextToken());
        videoLen = new Time(mm, ss);
        
        st = new StringTokenizer(pos, ":");
        mm = Integer.parseInt(st.nextToken());
        ss = Integer.parseInt(st.nextToken());
        now = new Time(mm, ss);
        
        st = new StringTokenizer(op_start, ":");
        mm = Integer.parseInt(st.nextToken());
        ss = Integer.parseInt(st.nextToken());
        opStart = new Time(mm, ss);
        
        st = new StringTokenizer(op_end, ":");
        mm = Integer.parseInt(st.nextToken());
        ss = Integer.parseInt(st.nextToken());
        opEnd = new Time(mm, ss);
        
        for(String command : commands) {
            if(now.greaterEquals(opStart) && now.lessEquals(opEnd)) {
                now.mm = opEnd.mm;
                now.ss = opEnd.ss;
            }
                
            switch(command) {
                case "prev":
                    now.prev();
                    break;
                case "next":
                    now.next();
                    if(now.greaterEquals(videoLen)) {
                        now.mm = videoLen.mm;
                        now.ss = videoLen.ss;
                    }
                    break;
            }
            
            //System.out.println(now);
        }
        
        if(now.greaterEquals(opStart) && now.lessEquals(opEnd))
            return opEnd.toString();
        
        return now.toString();
    }
}
