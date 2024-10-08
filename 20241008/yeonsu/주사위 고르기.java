import java.util.*;

class Solution {
    private static int[][] dice;
    private static int n;
    private static int half;
    private static int[] bestCombination;
    private static long maxWins;
    private static int[] sumCounts;
    private static int maxSum = 501;

    public int[] solution(int[][] inputDice) {
        dice = inputDice;
        n = dice.length;
        half = n / 2;
        maxWins = -1;
        bestCombination = new int[half];

        generateCombinations(0, 0, 0, new int[half], new int[half]);

        return Arrays.stream(bestCombination).map(i -> i + 1).toArray();
    }

    private static void generateCombinations(int adepth, int bdepth, int index, int[] aCombination, int[] bCombination) {
        if (adepth == half && bdepth == half) {
            long win = calculateWins(aCombination, bCombination);
            if (win > maxWins) {
                maxWins = win;
                for (int i=0;i<half;i++) {
                    bestCombination[i] = aCombination[i];
                }
            }
            return;
        }
        
        //index값을 combination에 넣을것인가 말 것인가
        if (adepth < half) {
            aCombination[adepth] = index;
            generateCombinations(adepth + 1, bdepth, index + 1, aCombination, bCombination);
        }
        if (bdepth < half) {
            bCombination[bdepth] = index;
            generateCombinations(adepth, bdepth + 1, index + 1, aCombination, bCombination);
        }
        return;
    }

    private static long calculateWins(int[] aCombination, int[] bCombination) {
        int[] aSelectedDice = new int[half];
        int[] bSelectedDice = new int[half];
        for (int i = 0; i < half; i++) {
            aSelectedDice[i] = aCombination[i];
            bSelectedDice[i] = bCombination[i];
        }

        int[] aLocalSumCounts = new int[maxSum + 1];
        int[] bLocalSumCounts = new int[maxSum + 1];
        dfsSelected(0, 0, aSelectedDice, aLocalSumCounts);
        dfsSelected(0, 0, bSelectedDice, bLocalSumCounts);

        long wins = 0;
        for (int i = 0; i <= maxSum; i++) {
            for (int j = 0; j < i; j++) {
                wins += (long) aLocalSumCounts[i] * bLocalSumCounts[j];
            }
        }

        return wins;
    }

    private static void dfsSelected(int index, int sum, int[] selectedDice, int[] localSumCounts) {
        if (index == half) {
            localSumCounts[sum]++;
            return;
        }

        for (int num : dice[selectedDice[index]]) {
            dfsSelected(index + 1, sum + num, selectedDice, localSumCounts);
        }
    }
}