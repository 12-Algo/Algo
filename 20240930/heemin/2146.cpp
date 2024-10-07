#include<iostream>
#include<queue>
#define MAX 200
using namespace std;

int N;
int map[MAX][MAX];
int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};
int total=0;

bool checkRange(int i, int j){
    return i>=0 && i<N && j>=0 && j<N;
}

void divide(){
    queue<pair<int,int>> q;
    bool visited[MAX][MAX];

    int num=0;

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(visited[i][j] || map[i][j]==0){
                continue;
            }

            num++;

            q.push({i,j});
            visited[i][j]=true;
            map[i][j]=num;

            while(!q.empty()){
                int x=q.front().first;
                int y=q.front().second;
                q.pop();

                for(int k=0;k<4;k++){
                    int nx=x+dx[k];
                    int ny=y+dy[k];

                    if(!checkRange(nx,ny)){
                        continue;
                    }

                    if(visited[nx][ny]){
                        continue;
                    }

                    if(map[nx][ny]==0){
                        continue;
                    }

                    map[nx][ny]=num;
                    visited[nx][ny]=true;
                    q.push({nx,ny});
                }
            }
        }
    }

    // for(int i=0;i<N;i++){
    //     for(int j=0;j<N;j++){
    //         cout << map[i][j] << " ";
    //     }
    //     cout << "\n";
    // }

    total=num;
}

int find(int x, int y){

   queue<pair<pair<int,int>,int>> q;
   bool visited[MAX][MAX];

   int res=999999;

   for(int i=0;i<N;i++){
    for(int j=0;j<N;j++){
        if(map[i][j]==map[x][y]){
            q.push({{i,j},0});
            visited[i][j]=true;
        }
    }
   }

bool flag=false;
   while(!q.empty()){
     int i=q.front().first.first;
     int j=q.front().first.second;
     int d=q.front().second;
     q.pop();

    
     for(int k=0;k<4;k++){
    
        int ni=i+dx[k];
        int nj=j+dy[k];

        if(!checkRange(ni,nj) || visited[ni][nj]){
            continue;
        }

        if(map[ni][nj] !=0  && map[ni][nj]!=map[x][y]){
            res=d;
            flag=true;
            break;
        }
    

        visited[ni][nj]=true;
        q.push({{ni,nj},d+1});

     }
     if(flag){
        break;
    }
   }
    return res;
}

int main(){
    cin >> N;
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            cin >> map[i][j];
        }
    }

    int answer=99999999;
    divide();
    int flag=1;

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            if(map[i][j]==flag){
                answer=min(answer,find(i,j));
                flag++;
            }
        }
    }
//    // cout << "\n\n";
//     for(int i=1;i<=total;i++){
//         //answer=min(answer,find(i));
//     }

    cout << answer << "\n";
}