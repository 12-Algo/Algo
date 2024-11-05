#include<iostream>
#include<algorithm>
#define MAX 1000001
using namespace std;

int n,c;
int arr[21][2];
int dp[MAX];

int main(){
    cin >> c >> n;

    for(int i=0;i<n;i++){
        int cost = 0;
        int people = 0;

        cin >> cost >> people;

        for(int j=cost;j<=MAX;j++){
            dp[j]=max(dp[j],dp[j-cost]+people);   
        }
    }

    for(int i=0;i<MAX;i++){
        if(dp[i] >= c){
            cout << i << "\n";
            break;
        }
    }
}