import java.util.*;

public class Solution {

    private int[][] dice;
    private int[] combi;
    private ArrayList<int[]> diceCombination;
    private ArrayList<Integer> score;

    public int[] solution(int[][] dice) {
        int[] answer = {};
        int diceCnt = dice.length;
        int maxWinCnt = 0;

        this.dice = dice;
        this.diceCombination = new ArrayList<>();
        this.score = new ArrayList<>();

        combi = new int[diceCnt / 2];
       //모든 점수 조합 구하기
        calculateCombination(0, 0, diceCnt / 2);

        for (int i = 0; i < diceCombination.size() / 2; i++) {
            int myIdx = i;
            int oppIdx = diceCombination.size() - i - 1;

            int[] myCombination = diceCombination.get(myIdx);
            int[] oppCombination = diceCombination.get(oppIdx);

		//점수 조합 압축
            HashMap<Integer, Integer> myScoreCnt = calculateScoreCnt(myCombination, 0, diceCnt / 2);
            HashMap<Integer, Integer> oppScoreCnt = calculateScoreCnt(oppCombination, 0, diceCnt / 2);

            int winCnt = 0;
            int loseCnt = 0;
            for (int m : myScoreCnt.keySet()) {
                for (int o : oppScoreCnt.keySet()) {
                    if (m > o) {
                        winCnt += (myScoreCnt.get(m) * oppScoreCnt.get(o));
                    } else if (m < o) {
                        loseCnt += (myScoreCnt.get(m) * oppScoreCnt.get(o));
                    }
                }
            }
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                answer = myCombination;
            }
            if (maxWinCnt < loseCnt) {
                maxWinCnt = loseCnt;
                answer = oppCombination;
            }
        }
        // index 0 주사위 > 1번 주사위 변경 작업
        for (int i = 0; i < answer.length; i++) {
            answer[i]++;
        }
        return answer;
    }

    private void calculateCombination(int start, int curDepth, int maxDepth) {
        if (curDepth == maxDepth) {
            diceCombination.add(arrayDeepCopy(combi));
            return;
        }
        for (int i = start; i < dice.length; i++) {
            combi[curDepth] = i;
            calculateCombination(i + 1, curDepth + 1, maxDepth);
        }
    }

    private int[] arrayDeepCopy(int[] arr) {
        return Arrays.stream(arr).toArray();
    }

    private HashMap<Integer, Integer> calculateScoreCnt(int[] combi, int curDepth, int maxDepth) {
        if (curDepth == maxDepth) {
            HashMap<Integer, Integer> scoreCnt = new HashMap<>();
            for (int s : score) {
                if (!scoreCnt.containsKey(s)) {
                    scoreCnt.put(s, 1);
                } else {
                    scoreCnt.put(s, scoreCnt.get(s) + 1);
                }
            }
            score.clear();
            return scoreCnt;
        }
        int idx = combi[curDepth];
        if (score.size() == 0) {
            for (int i = 0; i < 6; i++) {
                score.add(dice[idx][i]);
            }
        } else {
            int size = score.size();
            for (int i = 0; i < size; i++) {
                int s = score.remove(0);
                for (int j = 0; j < 6; j++) {
                    score.add(dice[idx][j] + s);
                }
            }
        }
        return calculateScoreCnt(combi, curDepth + 1, maxDepth);
    }
}