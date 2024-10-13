import java.util.*;

class Solution {
    int N;
    int[][] dice;
    
    int[] A;
    int[] B;
    int[] answer;
    
    List<Integer> aSum = new ArrayList<>();
    List<Integer> bSum = new ArrayList<>();
    
    int max = 0;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        N = dice.length;
        
        A = new int[N/2];
        B = new int[N/2];
        answer = new int[N/2];
        
        comb(0, 0, 0);
        
        return answer;
    }
    
    private void comb(int idx, int a, int b) {   
        if(a==N/2 && b==N/2){
            aSum.clear();
            bSum.clear();
            
            addSum(0, 0, A, aSum);
            
            addSum(0, 0, B, bSum);
            
            battle();
            return;
        } else if(idx == N) {
           return;
        }
        
        if(a < N/2) {
            A[a] = idx;
            comb(idx+1, a+1, b);
        }
        if(b < N/2) {
            B[b] = idx;
            comb(idx+1, a, b+1);
        }
    }
    
    private void addSum(int idx, int sum, int[] select, List<Integer> list) {
        if(idx == select.length) {
            list.add(sum);
            return;
        }
    
        int d = select[idx];
        for(int i=0; i<6; i++) {
            addSum(idx+1, sum + dice[d][i], select, list);
        }
    }
    
    private void battle() {
        Collections.sort(bSum);
        
        int win = 0;
        for(int i=0; i<aSum.size(); i++) {
            win += lowerBound(aSum.get(i));
        }
    
        if(win > max) {
            max = win;
            for(int i=0; i<N/2; i++) {
                answer[i] = A[i] + 1;
            }
        }
    }
    
    private int lowerBound(int target) {
        int high = bSum.size();
        int low = -1;
        
        while(low+1 < high) {
            int mid = (low+high)/2;
            
            if(bSum.get(mid) < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        return high;
    }
}
