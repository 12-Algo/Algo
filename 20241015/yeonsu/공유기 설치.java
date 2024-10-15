import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer token;
    private static int maxDistance;
    private static int minDistance;
    private static int Size;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        arr = new int[N];
        for (int i=0;i<N;i++) {
            int temp = Integer.parseInt(br.readLine());
            arr[i] = temp;
        }
        getMinDistance();
        getMaxDistance();
        Size = maxDistance - minDistance;
        Arrays.sort(arr);

        System.out.println(binarySearch(0, Size));
    }

    private static void getMaxDistance() {
        maxDistance = 0;
        for (int i=0;i<N;i++) {
            if (arr[i] > maxDistance) {
                maxDistance = arr[i];
            }
        }
    }

    private static void getMinDistance() {
        minDistance = Integer.MAX_VALUE;
        for (int i=0;i<N;i++) {
            if (arr[i] < minDistance) {
                minDistance = arr[i];
            }
        }
    }

    //가능한 한 공유기 사이를 크게하여 설치.
    //공유기 사이 거리가 모두 동일할 것. -> 거리를 기준으로 이분 탐색.
    static int binarySearch(int left, int right) {
        if (left > right) {
            return right;
        }
        int mid = (left + right) / 2;
        //mid 크기만큼 공유기 사이 거리가 있을 때, -> 공유기가 다 들어가는가?
        //다 들어간다면 더 거리를 늘릴 수 있는 지 검사. -> 최대 거리를 찾아내보자.
        int prev = Integer.MIN_VALUE;
        int count = 0;
        for (int i=0;i<N;i++) {
            if (arr[i] >= prev + mid) {
                prev = arr[i];
                count++;
            }
        }
        //count가 많으면 mid가 작은것.
        if (count >= M) {
            return binarySearch(mid + 1, right);
        } else {
            return binarySearch(left, mid - 1);
        }
    }
}