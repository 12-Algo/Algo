#include<iostream>
#include<queue>
#include<tuple>
#define MAX 31
using namespace std;

int L,R,C;
char building[MAX][MAX][MAX];
bool visited[MAX][MAX][MAX];

int dx[6]={1,-1,0,0,0,0};
int dy[6]={0,0,1,-1,0,0};
int dz[6]={0,0,0,0,1,-1};

int sz,sx,sy;
int ez,ex,ey;

bool checkRange(int i, int j, int k){
    return i>=0 && j>=0 && k>=0 && i<L && j<R && k<C;
}

void bfs(){
    queue<tuple<int,int,int,int>> q;

    q.push(make_tuple(sz,sx,sy,0));
    visited[sz][sx][sy]=true;

    while(!q.empty()){
        tuple<int,int,int,int> t=q.front();
        q.pop();

        int z=get<0>(t);
        int x=get<1>(t);
        int y=get<2>(t);
        int depth=get<3>(t);

        if(z==ez && x==ex && y==ey){
            cout << "Escaped in " << depth << " minute(s).\n";
            return;
        }

        for(int k=0;k<6;k++){
            int nz=z+dz[k];
            int nx=x+dx[k];
            int ny=y+dy[k];
            int ndepth=depth+1;

            if(!checkRange(nz,nx,ny) || visited[nz][nx][ny] || building[nz][nx][ny]=='#'){
                continue;
            }

            visited[nz][nx][ny]=true;
            q.push(make_tuple(nz,nx,ny,ndepth));            
        }

    }
    cout << "Trapped!\n";  
}

int main(){

    while(true){
        cin >> L >> R >> C;
        if(L==0 && R==0 && C==0){
            break;
        }

        for(int i=0;i<L;i++){
            for(int j=0;j<R;j++){
                for(int k=0;k<C;k++){
                    visited[i][j][k]=false;
                }
            }
        }

        for(int i=0;i<L;i++){
            for(int j=0;j<R;j++){
                for(int k=0;k<C;k++){
                    cin >> building[i][j][k];

                    if(building[i][j][k]=='S'){
                        sz=i;
                        sx=j;
                        sy=k;
                    }
                    else if(building[i][j][k]=='E'){
                        ez=i;
                        ex=j;
                        ey=k;
                    }
                }
            }
        }
        bfs();
    }
}