#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int main(){
    int n;
    cin >> n;

    vector<pair<int,int>> v;

    for(int i=0;i<n;i++){
        int a,b;
        cin >> a >> b;
        v.push_back({a,b});
    }

    sort(v.begin(),v.end());

    vector<int> list;
    for(int i=0;i<n;i++){
        list.push_back(v[i].second);
    }

    int dp[101];
    int answer=0;

    for(int i=0;i<101;i++){
        dp[i]=1;
    }
    for(int i=0;i<n;i++){
        for(int k=0;k<i;k++){
            if(list[i] > list[k]){
                dp[i]=max(dp[i], dp[k]+1);
                answer=max(answer,dp[i]);
            }
        }
    }
    cout << n-answer << "\n";
}