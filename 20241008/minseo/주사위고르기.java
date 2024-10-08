import java.util.*;

class Solution {
    static int N, max, diceA[], answer[], diceB[];
    static int[][] dices;

    public static int[] solution(int[][] dice) {
        N = dice.length;
        dices = dice;

        max = 0;
        diceA = new int[N/2]; // A가 선택한 주사위
        diceB = new int[N/2]; // B가 선택한 주사위
        answer = new int[N/2];

        comb(0, 0);

        return answer;
    }

    // A와 B 그룹으로 나누기 위한 조합 생성
    public static void comb(int cnt, int start){
        if(cnt == N/2){
            int bIndex = 0;

            for (int i = 0; i < N; i++) {
                // A가 가져가지 않은 주사위는 B의 주사위
                boolean flag = false; // A에 포함되었는지 여부
                for (int a : diceA) {
                    if (i == a) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    diceB[bIndex++] = i;
                }
            }

            // 승리 횟수 계산하기
            int winRate = play();

            if(winRate > max){
                // 승률이 더 높으면 갱신
                max = winRate;

                for(int i = 0; i < N/2; i++){
                    answer[i] = diceA[i] + 1; // 주사위는 1부터 시작하기 때문에 보정!
                }
            }

        } else {
            for(int i = start; i < N; i++){
                diceA[cnt] = i;
                comb(cnt+1, i+1);
                diceA[cnt] = 0;
            }
        }
    }

    static int play(){
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();

        // A와 B의 각 조합에 대한 합을 계산
        for (int[] order : product()) {
            int sumA = 0, sumB = 0;
            for (int j = 0; j < N/2; j++) {
                sumA += dices[diceA[j]][order[j]]; // A 그룹 주사위 합
                sumB += dices[diceB[j]][order[j]]; // B 그룹 주사위 합
            }
            A.add(sumA);
            B.add(sumB);
        }
        

        Collections.sort(B);  // B 배열 정렬

        // A가 B보다 큰 경우의 수 계산
        int wins = 0;
        for (int numA : A) {
            wins += bisectLeft(B, numA);
        }
        return wins;
    }

    static int bisectLeft(List<Integer> B, int target) {
        int low = 0, high = B.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (B.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    static List<int[]> product() {
        List<int[]> result = new ArrayList<>();
        int[] selected = new int[N/2];
        productHelper(0, selected, result);
        return result;
    }

    static void productHelper(int cnt, int[] selected, List<int[]> result) {
        if (cnt == N/2) {
            result.add(selected.clone());
            return;
        }
        for (int i = 0; i < 6; i++) {
            selected[cnt] = i;
            productHelper(cnt+1, selected, result);
        }
    }
}
