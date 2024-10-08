import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // a, b가 주고 받은 선물 개수 저장하기 위해 이름에 대한 인덱스 저장
        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i=0;i<friends.length;i++) {
            nameToIndex.put(friends[i], i);
        }
        int[] giftIndex = new int[friends.length]; // 선물 지수
        // a, b가 주고 받은 선물 개수 저장
        int[][] giftCounts = new int[friends.length][friends.length];
        for (int i=0;i<gifts.length;i++) {
            String[] people = gifts[i].split(" ");
            giftCounts[nameToIndex.get(people[0])][nameToIndex.get(people[1])]++;
            giftIndex[nameToIndex.get(people[0])]++;
            giftIndex[nameToIndex.get(people[1])]--;            
        }
        

        
        int[] nextMonthGifts = new int[friends.length];
        // 두 사람간의 선물한 횟수 비교
        for (int i=0;i<giftCounts.length;i++) {
            for (int j=i+1;j<giftCounts.length;j++) {
                if (giftCounts[i][j] > giftCounts[j][i]) {
                    nextMonthGifts[i]++;
                } else if (giftCounts[i][j] < giftCounts[j][i]) {
                    nextMonthGifts[j]++;
                } else { // 선물 지수 비교
                    if (giftIndex[i] > giftIndex[j]) {
                        nextMonthGifts[i]++;
                    } else if (giftIndex[i] < giftIndex[j]) {
                        nextMonthGifts[j]++;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i=0;i<nextMonthGifts.length;i++) {
            answer = Math.max(answer, nextMonthGifts[i]);
        }
        
        return answer;
    }
}