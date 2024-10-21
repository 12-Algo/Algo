#include <string>
#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int dx[4]={0,0,1,-1};
int dy[4]={1,-1,0,0};

bool checkRange(int i, int j){
    return i>=0 && j>=0 && i<5 && j<5;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    
    for(int t=0;t<5;t++){
        
        bool possible=true;
        
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(places[t][i][j]=='P'){

                    bool visited[5][5] = {false, };

                    queue<pair<pair<int,int>,int>> q;
                    q.push({{i,j},0});
                    visited[i][j]=true;

                    while(!q.empty()){
                        int x=q.front().first.first;
                        int y=q.front().first.second;
                        int d=q.front().second;
                        q.pop();

                        if(d==2){
                            continue;
                        }

                        for(int k=0;k<4;k++){
                            int nx=x+dx[k];
                            int ny=y+dy[k];

                            if(!checkRange(nx,ny) ||visited[nx][ny]){
                                continue;
                            }

                            if(places[t][nx][ny]!='X'){
                                q.push({{nx,ny},d+1});
                                visited[nx][ny]=true;
                                if(places[t][nx][ny] == 'P'){
                                     possible=false;
                                }
                            }
                        }
                    }
                }
            }
        }
        possible ? answer.push_back(1) : answer.push_back(0);
    }
    return answer;
}