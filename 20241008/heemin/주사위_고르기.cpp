#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int n;
bool visited[11];
bool seperate[11];
int tmp[6];
vector<vector<int>> d;
vector<int> a;
vector<int> b;
int max_value=0;
vector<int> an;

void calc(){
    sort(a.begin(),a.end());
    sort(b.begin(),b.end());
    
    int total=0;
        
    for(int i=0;i<a.size();i++){
        int num=a[i];
        
        // num 보다 작은 b의 개수 
        int lower=lower_bound(b.begin(), b.end(), num) - b.begin();
        total+=lower; // 승리 수
    }
    
    // 최댓값 갱신
    if(total > max_value){
        an.clear();
        max_value=total;
        for(int i=0;i<n;i++){
            if(seperate[i]){
                an.push_back(i+1);
            }
        }
    }
}

void getB(int index, int sum, int cnt){
    if(cnt >= n/2){
        b.push_back(sum);
        return;
    }
    if(seperate[index]){
        getB(index+1,sum,cnt);
    }
    else{
        for(int i=0;i<6;i++){
            getB(index+1,sum+d[index][i],cnt+1);
        }
    }
}


void getA(int index, int sum, int cnt){
    if(cnt >= n/2){
        a.push_back(sum);
        return;
    }
    if(!seperate[index]){
        getA(index+1,sum,cnt);
    }
    else{
        for(int i=0;i<6;i++){
            getA(index+1,sum+d[index][i],cnt+1);
        }
    }
}

void combination(int cnt, int start){
    if(cnt==n/2){
        for(int i=0;i<n;i++){
            seperate[i]=false;
        }
        for(int i=0;i<n/2;i++){
            seperate[tmp[i]]=true;
        }
        
        a.clear();
        b.clear();

        getA(0,0,0);
        getB(0,0,0);
        calc();
        
        return;
    }
    
    for(int i=start;i<n;i++){
        if(visited[i]){
            continue;
        }
        
        visited[i]=true;
        tmp[cnt]=i;
        combination(cnt+1,i+1);
        visited[i]=false;
    }
}

vector<int> solution(vector<vector<int>> dice) {
    vector<int> answer;
    
    n=dice.size();
    for(int i=0;i<dice.size();i++){
        vector<int> t;
        for(int j=0;j<6;j++){
            t.push_back(dice[i][j]);
        }
        d.push_back(t);
    }
    
    combination(0,0);

    answer=an;
    return answer;
}