import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        ArrayList<Long> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(list);
        for(int i = 0; i < n; i++) {
            int start = 0, end = n - 1;
            while(start < end) {
                if(start == i){
                    start++;
                    continue;
                }
                if(end == i) {
                    end--;
                    continue;
                }
                if(list.get(start) + list.get(end) < list.get(i)) {
                    start++;
                }
                else if(list.get(start) + list.get(end) > list.get(i)) {
                    end--;
                }
                else if(list.get(start) + list.get(end) == list.get(i)) {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}