import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    StringTokenizer token;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int N = friends.length;
        int[] giftsIndex = new int[N];
        HashMap<String, Integer> nameToIndex = new HashMap<>();
        int[][] infoTable = new int[N][N];
        
        //이름 인덱스 매핑
        for (int i=0;i<N;i++) {
            nameToIndex.put(friends[i], i);
        }

        //gifts정보를 바탕으로 선물 주고 받은 정보를 저장.
        for (int i=0;i<gifts.length;i++) {
            token = new StringTokenizer(gifts[i]);
            String giver = token.nextToken();
            String receiver = token.nextToken();
            infoTable[nameToIndex.get(giver)][nameToIndex.get(receiver)]++;
            giftsIndex[nameToIndex.get(giver)]++;
            giftsIndex[nameToIndex.get(receiver)]--;
        }
        
        //infoTable을 바탕으로 각 친구마다 선물을 얼마나 받는 지를 계산.
        for (int i=0;i<N;i++) {
            //i번째 친구는 j번째 친구에게 선물을 받을까?
            int received = 0;
            for (int j=0;j<N;j++) {
                if (infoTable[i][j] > infoTable[j][i]) {
                    received++;
                } else if (infoTable[i][j] == infoTable[j][i]) {
                    if (giftsIndex[i] > giftsIndex[j]) {
                        received++;
                    }
                }
            }
            answer = Math.max(answer, received);
        }
        
        return answer;
    }
}