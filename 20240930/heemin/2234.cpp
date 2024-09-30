#include<iostream>
#include<queue>
#include<algorithm>
#define MAX 51
using namespace std;

int N,M;
int board[MAX][MAX];
bool visited[MAX][MAX];
int cnt=0;
int max_size=0;
bool visit[MAX][MAX];

bool checkRange(int i, int j){
    return i>=0 && i<N && j>=0 && j<M;
}

int bfs(int i, int j){

    queue<pair<int,int>> q;
    q.push({i,j});
    visit[i][j]=true;

    int cnt=0;
    while(!q.empty()){
        int x=q.front().first;
        int y=q.front().second;
        q.pop();

        int num=board[x][y];
        cnt++;

        if((num & 1)!=1){
            if( checkRange(x,y-1) && !visit[x][y-1]){
            visit[x][y-1]=true;
            q.push({x,y-1});
            }
        }
        if((num & 2)!=2){
            if(checkRange(x-1,y) && !visit[x-1][y]){
            visit[x-1][y]=true;
            q.push({x-1,y});
            }
        }
        if(( num & 4)!=4){
            if(checkRange(x,y+1) && !visit[x][y+1]){
            visit[x][y+1]=true;
            q.push({x,y+1});
            }
        }
        if((num & 8)!=8){
            if(checkRange(x+1,y) && !visit[x+1][y]){
            visit[x+1][y]=true;
            q.push({x+1,y});
            }
        }
    }
    return cnt;
}
void remove(){
    int ans=max_size;

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    visit[i][j]=false;
                }
            }
            int num=board[i][j];

            if((num & 1)==1){
                if(checkRange(i,j-1)){
                board[i][j]-=1;
                ans=max(ans,bfs(i,j));
                board[i][j]+=1;
                }
            }
            if((num & 2)==2){
                if(checkRange(i-1,j)){
                board[i][j]-=2;
                ans=max(ans,bfs(i,j));
                board[i][j]+=2;
                }
            }
            if((num & 4)==4){
                if(checkRange(i,j+1)){
                board[i][j]-=4;
                ans=max(ans,bfs(i,j));
                board[i][j]+=4;
                }
            }
            if((num & 8)==8){
                if(checkRange(i+1,j)){
                board[i][j]-=8;
                ans=max(ans,bfs(i,j));
                board[i][j]+=8;
                }
            }
        }
    }
    cout << ans << "\n";
}

int main(){
    cin >> M >> N;

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            cin >> board[i][j];
        }
    }

    for(int i=0;i<N;i++){
        for(int j=0;j<M;j++){
            if(!visit[i][j]){
                max_size=max(max_size,bfs(i,j));
                cnt++;
            }
            //max_size=max(max_size,bfs(i,j));
        }
    }
    cout << cnt << "\n";
    cout << max_size << "\n";
    remove();

}