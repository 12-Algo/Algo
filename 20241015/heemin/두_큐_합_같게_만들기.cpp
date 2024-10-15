#include <string>
#include<iostream>
#include <vector>

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
    int answer = -2;
    
    long long sum1=0;
    long long sum2=0;
    
    for(int i=0;i<queue1.size();i++){
        sum1+=queue1[i];
    }
    for(int i=0;i<queue2.size();i++){
        sum2+=queue2[i];
    }
    
    int sum=sum1+sum2;
    
    if(sum % 2 !=0){
        return -1;
    }
    
    int cnt=0;
    int index1=0;
    int index2=0;
    
    int size=queue1.size()+queue2.size();
    while(cnt <= size*2){
        if(sum1 > sum2){
            int num=queue1[index1];
            sum1-=num;
            sum2+=num;
            queue2.push_back(num);
            index1++;
            cnt++;
        }
        else if(sum1 < sum2 ){
            int num=queue2[index2];
            sum1+=num;
            sum2-=num;
            queue1.push_back(num);
            index2++;
            cnt++;
        }
        else{
            break;
        }
    }
    
    sum1==sum2 ? answer=cnt : answer=-1;

    return answer;
}