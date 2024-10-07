#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#define MAX 1000001
using namespace std;

vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer(4);
    
    int n=0;
    vector<int> in(MAX);
    vector<int> out(MAX);
    vector<int> head(MAX, 0);
    
    for(int i=0;i<edges.size();i++){
        int from=edges[i][0];
        int to=edges[i][1];
        
        out[from]++;
        in[to]++;
        
        if(head[from]==0){
            head[from]=to;
        }
        else{
            head[from]=-1;
        }
        
        n=max(n,max(from,to));
    }
    
    
    int start=0;
    for(int i=1;i<=n;i++){
        if(in[i] == 0 && (out[i] > 1)){
            start=i;
            break;
        }
    }
    
    answer[0]=start;
        
    for(int i=0;i<edges.size();i++){
       if(edges[i][0]!=start){
           continue;
       }
       int node=edges[i][1];
       int next=node;

        while(true){
           if(out[next]==0 || in[next]==0){
               answer[2]++;
               break;
           }
           if(out[next]>1){
               answer[3]++;
               break;
           }
           next=head[next];
           if(node==next){
               answer[1]++;
               break;
           }
       }
    }
    return answer;
}