import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        int count = 0;
        for(int t = 0; t < N; t++){
            int target = input[t];
            int left = 0;
            int right = N-1;
            while(left < right){
                if(left == t){
                    left++;
                    continue;
                }
                if(right == t){
                    right--;
                    continue;
                }
                int sum = input[left]+input[right];
                if(target == sum) {
                    count++;
                    break;
                }
                if(target < sum){
                    right--;
                }
                else left++;
            }
        }
        
            
        
        System.out.println(count);
    }
}