#include<iostream>
#include<vector>
#include<algorithm>
#define MAX 501
using namespace std;

int n;
int board[MAX][MAX];
int dp[MAX][MAX];

int dx[4]={1,-1,0,0};
int dy[4]={0,0,1,-1};

bool checkRange(int i, int j){
    return i>=0 && i<n && j>=0 && j<n;
}

int dfs(int i, int j){

    if(dp[i][j]!=0){
        return dp[i][j];
    }

    dp[i][j]=1;
    int cnt=0;

    for(int k=0;k<4;k++){
        int ni=i+dx[k];
        int nj=j+dy[k];

        if(!checkRange(ni,nj)){
            continue;
        }

        if(board[i][j] < board[ni][nj]){
            dp[i][j]=max(dp[i][j],dfs(ni,nj)+1);
        }
    }

    return dp[i][j];
}

int main(){
    cin >> n;

    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin >> board[i][j];
        }
    }
    
    int answer=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            answer=max(answer,dfs(i,j));
        }
    }
    cout << answer << "\n";
}
