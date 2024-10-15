import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N]; // 집들이 있는 좌표

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 집 좌표 정렬
        Arrays.sort(houses);

        // 이분 탐색을 통한 최적의 거리 찾기(팁: 적절한 거리를 찾는 것이 포인트)
        int start = 1; // 가장 짧은 거리
        int end = houses[N - 1] - houses[0]; // 최대 거리
        int ans = 0;

        while (start <= end) {
            int mid = (start + end) / 2; // 중간 거리
            
            if (canWIFI(mid)) { // C개 이상 설치했다면 거리를 좀 더 늘려보기

                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(ans);
    }

    // 이 거리로 떨어져있는 공유기를 설치할 수 있는가?
    static boolean canWIFI(int distance) {
        int cnt = 1; // 첫 번째 집에다가 공유기 설치하고 시작
        int installedHouse = houses[0]; // 공유기를 설치한 집의 위치

        for (int i = 1; i < N; i++) {
            if (houses[i] - installedHouse >= distance) { // 거리가 distance보다 떨어져있는 집이라면
                cnt++; // 일단 설치~
                installedHouse = houses[i];
            }
        }

        return cnt >= C; // C개 이상 설치했는가?
    }
}
