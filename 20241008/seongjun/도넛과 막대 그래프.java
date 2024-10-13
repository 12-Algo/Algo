import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int maxNodeNumber = 0;
        
        List<List<Integer>> incomingEdges = new ArrayList<>();
        List<List<Integer>> outgoingEdges = new ArrayList<>();
        for (int i=0;i<=1000000;i++) {
            incomingEdges.add(new ArrayList<>());
            outgoingEdges.add(new ArrayList<>());
        }
        for (int i=0;i<edges.length;i++) {
            incomingEdges.get(edges[i][1]).add(edges[i][0]);
            outgoingEdges.get(edges[i][0]).add(edges[i][1]);
            maxNodeNumber = Math.max(maxNodeNumber, Math.max(edges[i][0], edges[i][1]));
        }
        
        // 생성한 점 찾기
        int createdNodeNumber = 0;
        for (int i=1;i<=maxNodeNumber;i++) {
            // 들어오는 간선 없고, 나가는 간선 2 이상 = 생성한 점
            if (incomingEdges.get(i).size() == 0 && outgoingEdges.get(i).size() >= 2) {
                createdNodeNumber = i;
                break;
            }
        }
            
        // 연결된 노드 순회하며 노드 갯수, 간선 갯수 세기
        int donutGraph = 0;
        int barGraph = 0;
        int figure8Graph = 0;
        for (int i=0;i<outgoingEdges.get(createdNodeNumber).size();i++) {
            int connectedWithCreatedNode = outgoingEdges.get(createdNodeNumber).get(i);
            boolean[] visit = new boolean[maxNodeNumber+1];
            visit[connectedWithCreatedNode] = true;
            int nodeCount = 1;
            int edgeCount = 0;
            Queue<Integer> q = new LinkedList<>();
            q.add(connectedWithCreatedNode);
            
            while (!q.isEmpty()) {
                int nowNode = q.poll();
                for (int j=0;j<outgoingEdges.get(nowNode).size();j++) {
                    edgeCount++;
                    int nextNode = outgoingEdges.get(nowNode).get(j);
                    if (!visit[nextNode]) {
                        nodeCount++;
                        visit[nextNode] = true;
                        q.add(nextNode);
                    }
                }
            }
            
            if (nodeCount == edgeCount) {
                donutGraph++;
            } else if (nodeCount-1 == edgeCount) {
                barGraph++;
            } else if ((nodeCount + 1) == edgeCount) {
                figure8Graph++;
            } 
        }
        
        int[] answer = {createdNodeNumber, donutGraph, barGraph, figure8Graph};
        return answer;
    }
}