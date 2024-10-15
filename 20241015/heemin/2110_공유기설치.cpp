#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int n,c;
vector<int> modem;

bool solution(int value){
    int cnt=1;

    int before=0;
    for(int i=1;i<n;i++){
        if(modem[i]-modem[before] >= value){
            before=i;
            cnt++;
        }
    }

    if(cnt >= c) {
        return true;
    }
    return false;
}

int main(){
    cin >> n >> c;
    for(int i=0;i<n;i++){
        int a;
        cin >> a;
        modem.push_back(a);
    }
    sort(modem.begin(),modem.end());

    int start=1;
    int end=modem[n-1]-modem[0];
    int mid=0;

    int answer=0;

    while(start <= end){
        mid=(start+end)/2;

        if(solution(mid)){
            answer=mid;
            start=mid+1;
        }
        else{
            end=mid-1;
        }
    }

    cout << answer << "\n";
}