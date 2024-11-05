import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

        Arrays.sort(arr);

        int answer = 0;
        for(int i=0; i<N; i++) {
            int target = arr[i];
            int left = 0, right = N-1;

            while(left < right) {
                int sum = arr[left] + arr[right];

                if(left == i){
                    left ++;
                    continue;
                }
                else if(right == i){
                    right --;
                    continue;
                }
                
                if(sum < target) {
                    left ++;
                } else if(sum > target) {
                    right --;
                } else {
                    answer ++;
                    
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
