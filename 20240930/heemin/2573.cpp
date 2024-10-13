#include<iostream>
#include<queue>
#define MAX 301
using namespace std;

int N,M;
int map[MAX][MAX];
int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};

bool checkRange(int i, int j){
    return i>=0 && i<N && j>=0 && j<M;
}


int bfs(){
    queue<pair<pair<int,int>,int>> q;

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            if(map[i][j]>0){
                
                int cnt=0;

                for(int k=0;k<4;k++){
                    int ni=i+dx[k];
                    int nj=j+dy[k];

                    if(!checkRange(ni,nj)){
                        continue;
                    }

                    if(map[ni][nj] <= 0){
                        cnt++;
                    }

                }

                q.push({{i,j},cnt});
            }
        }
    }

    while(!q.empty()){
        int x=q.front().first.first;
        int y=q.front().first.second;
        int cnt=q.front().second;
        q.pop();

        map[x][y]-=cnt;
    }

    int ice=0;
    bool visited[MAX][MAX];
    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            visited[i][j]=false;
        }
    }
    queue<pair<int,int>> q2;

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            if(map[i][j]<=0 || visited[i][j]){
                continue;
            }

            ice++;

            q2.push({i,j});
            visited[i][j]=true;

            while(!q2.empty()){
                int x=q2.front().first;
                int y=q2.front().second;
                q2.pop();

                for(int k=0;k<4;k++){
                    int nx=x+dx[k];
                    int ny=y+dy[k];

                    if(!checkRange(nx,ny) || visited[nx][ny] || map[nx][ny]<=0){
                        continue;
                    }

                    q2.push({nx,ny});
                    visited[nx][ny]=true;
                }
            }
        }
    }

    if(ice==0){
        return -1;
    }
    else if(ice==1){
        return 0;
    }
    else if(ice > 1){
        return 1;
    }
}

int main(){
    cin >> N >> M;

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            cin >> map[i][j];
        }
    }

    int answer=0;
    while(true){
        answer++;
        int res=bfs();
        if(res==0){
            continue;
        }
        else{
            if(res==-1){
                answer=0;
            }
            break;
        }
    }
    cout << answer << "\n";
}