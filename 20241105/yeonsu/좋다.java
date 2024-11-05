import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        int answer = 0;

        // 각 수에 대해 검사
        for(int i = 0; i < N; i++) {
            int left = 0;
            int right = N-1;

            // 투 포인터로 합이 되는 두 수를 찾음
            while(left < right) {
                // 현재 검사하는 수(i)는 제외
                if(left == i) {
                    left++;
                    continue;
                }
                if(right == i) {
                    right--;
                    continue;
                }

                long sum = arr[left] + arr[right];

                if(sum == arr[i]) {
                    answer++;
                    break;
                } else if(sum < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}