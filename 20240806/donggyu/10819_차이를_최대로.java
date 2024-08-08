package algo;

import java.util.*;
import java.io.*;
public class Difference{
    public static int n;
    public static int[] inputArr;
    public static int[] ans;
    public static boolean[] visit;
    public static int result;
    
    public static void dfs(int cnt){
        if(cnt == n) {
            int sum = 0;
            for(int i = 0; i < n-1; i++) {
                sum += Math.abs(ans[i] - ans[i+1]);
            }
            result = Math.max(result, sum);
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visit[i]){
                ans[cnt] = inputArr[i];
                visit[i] = true;
                dfs(cnt + 1);
                visit[i] = false;
            }
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        inputArr = new int[n];
        ans = new int[n];
        visit = new boolean[n];
        for(int i = 0; i < n; i++) {
        	inputArr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0);
        System.out.println(result);
    }
}