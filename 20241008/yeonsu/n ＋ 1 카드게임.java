import java.util.*;
class Solution {
    private static int N;   //전체크기
    private static int inHandLength;    //손에 있는 카드 크기
    private static Set<Integer> inHand;    //손에 있는 카드들
    private static Set<Integer> candidate;     //뽑을 수 있는 카드들
    public int solution(int coin, int[] cards) {
        int answer = 1;
        N = cards.length;
        inHandLength = N / 3;
        inHand = new HashSet<>();
        candidate = new HashSet<>();
        //n/3장을 뽑아 가짐
        for (int i=0;i<inHandLength;i++) {
            inHand.add(cards[i]);
        }
        //2개씩 카드를 뽑아
        for (int i=inHandLength;i < N;i+=2) {
            //후보군에 카드 추가
            candidate.add(cards[i]);
            candidate.add(cards[i+1]);
            //라운드 증가   
            //원래 카드 패에서 n + 1을 만들 수 있는 지 확인
            if (getFromHand()) {
                answer++;
                continue;
            }
            
            //뽑은 카드 중 한 개를 사용해 n + 1을 만들 수 있는 지 확인
            if (coin > 0 && getOneFromCandidate()) {
                coin -= 1;
                answer++;
                continue;   
            }
                
            
            //2개를 모두 사용해 n + 1을 만들 수 있는 지 확인
            if (coin > 1 && getTwoFromCandidate()) {
                coin -= 2;
                answer++;
                continue;
            }
            
            //없다면 종료, 있다면 계속
            return answer;
        }
        return answer;
    }
    
    private static boolean getFromHand() {
        for (int card : inHand) {
            if (inHand.contains(N + 1 - card)) {
                inHand.remove(card);
                inHand.remove(N + 1 - card);
                return true;
            }
        }
        return false;
    }
    
    private static boolean getOneFromCandidate() {
        for (int card : candidate) {
            if (inHand.contains(N + 1 - card)) {
                inHand.remove(N + 1 - card);
                candidate.remove(card);
                return true;
            }
        }  
        return false;
    }
    
    private static boolean getTwoFromCandidate() {
        for (int card : candidate) {
            if (candidate.contains(N + 1 - card)) {
                candidate.remove(N + 1 - card);
                candidate.remove(card);
                return true;
            }
        }
        return false;
    }
}