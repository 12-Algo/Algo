import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
   private static void makeCombi(int m, int l, int idx, int before, ArrayList<int[]> combiArr, int[] arr, boolean[] check) {
      if (idx == m) {
         combiArr.add(Arrays.copyOf(arr, m));
         return;
      }

      for (int i = before+1; i < l; i++) {
         if (!check[i]) {
            check[i] = true;
            arr[idx] = i;
            makeCombi(m, l, idx+1, i, combiArr, arr, check);
            check[i] = false;
         }
      }
   }
   
   // 해당 집의 치킨거리
   private static int calcChickenDistance(int y, int x, ArrayList<int[]> chickenStores, int[] chickenCombi) {
	   int res = (int) 1e9;
	   
	   for (int i : chickenCombi) {
		   int[] position = chickenStores.get(i);
		   res = Math.min(res, Math.abs(y-position[0]) + Math.abs(x-position[1]));
	   }
	   
	   return res;
   }

    // 모든 집의 치킨 거리 합
   private static int getMinDistance(int[][] city, ArrayList<int[]> houses, ArrayList<int[]> chickenStores, int[] chickenCombi) {
      int res = 0;
      
      for (int[] house : houses) {
    	  res += calcChickenDistance(house[0], house[1], chickenStores, chickenCombi);
      }
      
      return res;
   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      StringTokenizer nums = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(nums.nextToken());
      int M = Integer.parseInt(nums.nextToken());
      int[][] city = new int[N][N];
      ArrayList<int[]> houses = new ArrayList<>();  // 집 좌표 리스트
      ArrayList<int[]> chickenStores = new ArrayList<>();  // 치킨가게 좌표 리스트

      for (int i = 0; i < N; i++) {
         StringTokenizer line = new StringTokenizer(br.readLine());

         for (int j = 0; j < N; j++) {
            int temp = Integer.parseInt(line.nextToken());
            city[i][j] = temp;

            if (temp == 1) {
               int[] house = {i, j};
               houses.add(house);
            } else if (temp == 2) {
            	int[] chicken = {i, j};
            	chickenStores.add(chicken);
            }
         }
      }
            
      ArrayList<int[]> chickenCombi = new ArrayList<>();
      int[] arr = new int[M];
      boolean[] check = new boolean[chickenStores.size()];

    // 살아남은 치킨 집 조합 구하기
      makeCombi(M, chickenStores.size(), 0, -1, chickenCombi, arr, check);
     
     // 치킨 거리 합 구하기
      int ans = (int) 1e9;
      for (int[] combi : chickenCombi) {
    	  ans = Math.min(ans, getMinDistance(city, houses, chickenStores, combi));
      }

      System.out.println(ans);
   }

}