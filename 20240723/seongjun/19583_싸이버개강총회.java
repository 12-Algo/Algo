package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int timeToInteger(String time) {
        String[] timeSplit = time.split(":");
        return Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String[][] times = new String[3][2];
        for (int i=0;i<3;i++) {
            times[i] = st.nextToken().split(":");
        }
        // 1. 시간을 분 단위로 치환 (ex. 22:00 -> 22 * 60 + 0 = 1320)
        // 2. 입력이 주어지는 동안, inputTime <= s 인 경우만 이름 저장. Set
        // 3. 입력이 주어지는 동안, e <= inputTime <= q 인 경우만 이름 저장. Set
        // 4. 두 Set에 동일한 이름이 들어있으면 count ++

        // 시간 * 60 + 분 으로 치환
        int s = Integer.parseInt(times[0][0]) * 60 + Integer.parseInt(times[0][1]);
        int e = Integer.parseInt(times[1][0]) * 60 + Integer.parseInt(times[1][1]);
        int q = Integer.parseInt(times[2][0]) * 60 + Integer.parseInt(times[2][1]);

        // 조건에 맞는 이름만 저장
        Set<String> beforeStart = new HashSet<String>();
        Set<String> afterEnd = new HashSet<String>();
        String input;
//        while (!(input = br.readLine()).isEmpty()) {
        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input, " ");
            int inputTime = timeToInteger(st.nextToken());

            if (inputTime <= s) {
                beforeStart.add(st.nextToken());
            } else if (inputTime >= e && inputTime <= q) {
                afterEnd.add(st.nextToken());
            }
        }
        Set<String> attend = new HashSet<String>(beforeStart);
        attend.retainAll(afterEnd);
        System.out.println(attend.size());
    }
}
