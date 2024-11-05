#include<iostream>
#include<vector>
#include<set>
#include<algorithm>
using namespace std;

int n;
vector<int> v;

bool find_value(int k, int index){

    int left=0;
    int right=v.size()-1;
    int sum=0;

    while(left < right){

        if(left==index){
            left++;
        }
        if(right==index){
            right--;
        }

        if(left >=right){
            return false;
        }
        sum=v[left]+v[right];

        if(sum == k){
            return true;
        }

        if(sum > k){
            right--;
        }
        else if(sum < k){
            left++;
        }
    }
    return false;
}

int main(){
    cin >> n;
    for(int i=0;i<n;i++){
        int a;
        cin >> a;
        v.push_back(a);
    }

    sort(v.begin(),v.end());

    int cnt=0;

    for(int i=0;i<v.size();i++){
        if(find_value(v[i],i)){
            cnt++;
        }
    }

    cout << cnt << "\n";
}