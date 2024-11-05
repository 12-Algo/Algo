import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int start = 0;
        int end = N-1;
        int cnt = 0;
        for(int i = 0; i < N; i++){
            start = 0;
            end = N-1;
            while(start < end){
                if(arr[start] + arr[end] == arr[i]){
                    if(i == start){
                        start++;
                    }
                    else if(i == end){
                        end--;
                    }
                    else{
                        cnt++;
                        break;
                    }
                }
                else if(arr[start] + arr[end] < arr[i]){
                    start++;
                }
                else{
                    end--;
                }
            }
        }

        System.out.println(cnt);

    }
}