import java.io.*;
import java.util.*;
class Solution {
    static int[] outDegree, inDegree;
    
    public int[] solution(int[][] edges) {
        int[] answer = {};
        int nodeCnt = findNodeCnt(edges);
        outDegree = new int[nodeCnt+1];
        inDegree = new int[nodeCnt+1];
        setDegree(edges);
        answer = solve(nodeCnt);
        return answer;
    }
    
    private static int[] solve(int nodeCnt){
        int[] answer = new int[4];
        int addNode = 0;
        for(int i=1;i<nodeCnt+1;i++){
            //추가 노드
            if(inDegree[i]==0 && outDegree[i]>=2){
                answer[0] = i;
                addNode = i;
            //막대 노드
            } 
            if(outDegree[i]==0){
                answer[2]++;
            }
            //8자 그래프
            if(inDegree[i]>=2 && outDegree[i]==2){
                answer[3]++;
            }
        }
        //도넛그래프
        answer[1] = outDegree[addNode] - answer[2] - answer[3];
        return answer;
    }
    
    private static void setDegree(int[][] edges){
        for(int i=0;i<edges.length;i++){
            outDegree[edges[i][0]]++;
            inDegree[edges[i][1]]++;
        }
    }
    
    private static int findNodeCnt(int[][] edges){
        int nodeCnt = 0;
        for(int i=0;i<edges.length;i++){
            for(int j=0;j<edges[i].length;j++){
                nodeCnt = Math.max(nodeCnt, edges[i][j]);
            }
        }
        return nodeCnt;
    }
}