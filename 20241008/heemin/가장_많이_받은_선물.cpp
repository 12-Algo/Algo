#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

vector<string> split(string s){
    vector<string> ret(2);
    
    int index=0;
    for(int i=0;i<s.size();i++){
        if(s[i]==' '){
            index++;
            continue;
        }
        ret[index]+=s[i];
    }
    
    return ret;
}

int solution(vector<string> friends, vector<string> gifts) {
    int answer = 0;
    
    int n=friends.size();
    
    map<string, int> name;
    map<int, vector<int>> m;

    for(int i=0;i<n;i++){
        name.insert({friends[i],i});
        m[i]=vector<int>(n+2,0);
    }
    
    for(int i=0;i<gifts.size();i++){
        vector<string> v=split(gifts[i]);
        
        string from=v[0];
        string to=v[1];
        
        int from_index=name[from];
        int to_index=name[to];

        m[from_index][to_index]++;
        m[from_index][n]++; // 준 것 총합
        m[to_index][n+1]++; // 받은 것 총합
    }
    
    for(int i=0;i<n;i++){
        int get=0;
        
        for(int j=0;j<n;j++){
            if(i==j){
                continue;
            }
            
            // 준거 > 받은거
            if(m[i][j] > m[j][i]){
                get++;
            }
            else if(m[i][j]==m[j][i]){ // 선물 지수 비교
                int index1=m[i][n]-m[i][n+1];
                int index2=m[j][n]-m[j][n+1];
                
                if(index1 > index2){
                    get++;
                }
            }
        }
        answer=max(answer,get);
    }
    return answer;
}