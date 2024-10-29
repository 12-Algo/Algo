#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 100001
using namespace std;

vector<int> v;
int dp[5][5][MAX];

int getEnergy(int now, int next){
    if(next==now){
        return 1;
    }
    if(now == 0){
        return 2;
    }
    if(abs(next-now) == 2){
        return 4;
    }
    else{
        return 3;
    }
}

int solution(int cnt, int left, int right){
    if(cnt==v.size()){
        return 0;
    }

    if(dp[left][right][cnt]!=-1){
        return dp[left][right][cnt];
    }

    dp[left][right][cnt]=min(solution(cnt+1,v[cnt],right)+getEnergy(left,v[cnt]),solution(cnt+1,left,v[cnt])+getEnergy(right,v[cnt]));

    return dp[left][right][cnt];
}


int main(){
    while(true){
        int num;
        cin >> num;

        if(num==0){
            break;
        }

        v.push_back(num);
    }

    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            for(int k=0;k<MAX;k++){
                dp[i][j][k]=-1;
            }
        }
    }

    int answer = solution(0,0,0);
    cout << answer << "\n";
}