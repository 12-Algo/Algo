class Solution {
    static final int MAX = 1_000_000;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int[] in = new int[MAX+1];
        int[] out = new int[MAX+1];
        
        int N = 1;
        
        for(int i=0; i<edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            in[to] ++;
            out[from] ++;
            
            N = Math.max(N, Math.max(from, to));
        }
        
        for(int i=1; i<=N; i++) {
            if(in[i]==0 && out[i] >= 2)
                answer[0] = i;
            
            else if(in[i] > 0 && out[i] == 0)
                answer[2]++;
            
            else if(in[i] >=2 && out[i] >=2)
                answer[3]++;
        }
        
        answer[1] = out[answer[0]] - (answer[2] + answer[3]);
        
        return answer; 
    }
}
