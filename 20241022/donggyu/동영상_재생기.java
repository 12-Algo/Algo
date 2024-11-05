class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
		int nowPos = toSecond(pos);
		
		for(String o : commands) {
			nowPos = checkJump(op_start, op_end, nowPos);
			if("prev".equals(o)) {
				nowPos = prev(nowPos);
			} else if("next".equals(o)) {
				nowPos = next(nowPos, toSecond(video_len));
			}		
		}
        nowPos = checkJump(op_start, op_end, nowPos);
		return toMinute(nowPos);
    }
    
   static int checkJump(String op_start, String op_end, int nowPos) {
		int start = toSecond(op_start);
		int end = toSecond(op_end);
		if(start<=nowPos && nowPos <= end){
			nowPos = end;
		}
       
		return nowPos;
	}
	static int next(int time, int endTime) {
		int returnTime = time+10;
		if(returnTime>endTime)
			returnTime = endTime;
		
		return returnTime;
	}
	
	static int prev(int time) {
		int returnTime = time-10;
		if(returnTime<0)
			returnTime = 0;

		return returnTime;
	}
    
	static int toSecond(String time) {
		int second = 0;
		String[] timeList = time.split(":");
		second = Integer.parseInt(timeList[0])*60+Integer.parseInt(timeList[1]);
		return second;
	}

	static String toMinute(int time) {
		int minute = time/60;
		int second = time%60;
		return String.format("%02d:%02d", minute, second);
	}
  
}