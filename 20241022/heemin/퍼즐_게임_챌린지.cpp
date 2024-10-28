#include <string>
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

bool solution(int level, vector<int> diffs, vector<int> times, long long limit){
    
    long long total=0;

    for(int i=0;i<diffs.size();i++){
        int diff=diffs[i];
        int time=times[i];
        
        if( diff <= level ){
            total += time;
        }
        else{
            int cnt = diff-level;
            total += (time+times[i-1])*cnt;
            total += time;
        }
        
        if(total > limit){
            return false;
        }
    }
    return true;
}

int solution(vector<int> diffs, vector<int> times, long long limit) {
    int answer = 0;
    
    int start=1;
    int end = 100000;
    int mid=0;
    
    while(start <= end ){
        mid=(start+end)/2;
        
        if(solution(mid,diffs,times,limit)){
            answer=mid;
            end=mid-1;
        }
        else{
            start=mid+1;
        }
    }
    
    return answer;
}