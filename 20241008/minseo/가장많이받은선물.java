import java.util.*;
import java.lang.*;
class Solution {
    static int giftMatrix[][], giftNum[];
    static StringTokenizer st;
    static Map<String, Integer> map;

    public int solution(String[] friends, String[] gifts) {
        int length = friends.length;
        giftMatrix = new int[length][length];
        map = new HashMap<>();
        giftNum = new int[length];

        for(int i = 0; i < length; i++){
            map.put(friends[i], i);
        }

        for(String history : gifts){
            st = new StringTokenizer(history);
            giftMatrix[map.get(st.nextToken())][map.get(st.nextToken())]++;
        }

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                System.out.print(giftMatrix[i][j]+" ");
            }
            System.out.println();
        }
        for(int i = 0; i < length; i++){
            for(int j = i+1; j < length; j++){

                // 더 많이 준 사람이 이번에 받는다.
                if(giftMatrix[i][j] > giftMatrix[j][i]){
                    giftNum[i]++;
                }
                else if(giftMatrix[i][j] < giftMatrix[j][i]){
                    giftNum[j]++;
                }
                else{
                    // 주고받은 수가 같다면, 선물 지수를 계산한다.
                    int iIndex = 0;
                    int jIndex = 0;
                    for(int a=0; a<length; a++){
                        iIndex += giftMatrix[i][a];
                        iIndex -= giftMatrix[a][i];

                        jIndex += giftMatrix[j][a];
                        jIndex -= giftMatrix[a][j];
                    }

                    if(iIndex > jIndex){
                        // i의 선물지수가 더 크므로 i가 받음
                        giftNum[i]++;
                    }
                    else if(iIndex < jIndex){
                        giftNum[j]++;
                    }
                    else{
                        // 같으면 주고받지 않음.
                    }
                }

            }
        }

        int answer = 0;
        for(int i = 0; i < length; i++){
            System.out.print(giftNum[i]+" ");
            answer = Math.max(answer, giftNum[i]);
        }
        return answer;
    }
}