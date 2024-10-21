#include <string>
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>
#define MAX 501
using namespace std;

int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};

int n,m;

bool checkRange(int i, int j){
    return i>=0 && i<n && j>=0 && j<m;
}

int solution(vector<vector<int>> land) {
    int answer = 0;
    
    n=land.size();
    m=land[0].size();
    
    bool visited[MAX][MAX]={false,};
    int res[MAX]={0,};
    
    for(int i=0;i<land.size();i++){
        for(int j=0;j<land[i].size();j++){
            if(visited[i][j] || land[i][j]==0){
                continue;
            }        
            
            set<int> s;
            s.insert(j);
            
            queue<pair<int,int>> q;
            q.push({i,j});
            visited[i][j]=true;
            int cnt=1;
            
            while(!q.empty()){
                int x=q.front().first;
                int y=q.front().second;
                q.pop();
                
                for(int k=0;k<4;k++){
                    int nx=x+dx[k];
                    int ny=y+dy[k];
                    
                    if(!checkRange(nx,ny) || visited[nx][ny] ){
                        continue;
                    }
                    
                    if(land[nx][ny]==0){
                        continue;
                    }
                    visited[nx][ny]=true;
                    q.push({nx,ny});
                    s.insert(ny);
                    cnt++;
                }
            }
            for(set<int>::iterator iter=s.begin();iter!=s.end();iter++){
                res[*iter]+=cnt;
                answer=max(answer,res[*iter]);
            }
        }
    }
    return answer;
}