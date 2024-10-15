#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
    int answer = 0;
    vector<int> q;
    long long sum1 = 0;
    long long sum2 = 0;
    for(int i = 0; i < queue1.size(); i++) {
        q.push_back(queue1[i]);
        sum1 += queue1[i];
    }
    for(int i = 0; i < queue2.size(); i++) {
        q.push_back(queue2[i]);
        sum2 += queue2[i];
    }
    if((sum1 + sum2) % 2 == 1) return -1;
    int start1 = 0;
    int end1 = (q.size() / 2) - 1;
    int start2 = q.size() / 2;
    int end2 = q.size() - 1;
    while(true) {
        if(sum1 == sum2) break;
        else if(sum1 > sum2) {
            sum1 -= q[start1];
            sum2 += q[start1];
            start1++;
            end2++;
        }
        else if(sum1 < sum2) {
            sum1 += q[start2];
            sum2 -= q[start2];
            start2++;
            end1++;
        }
        answer++;
        start1 %= q.size();
        start2 %= q.size();
        end1 %= q.size();
        end2 %= q.size();
        if(answer > q.size() * 2) return -1;
    }
    
    return answer;
}