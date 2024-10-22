#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> diffs, vector<int> times, long long limit) {
    int answer = 0;
    int start = 1;
    int end = 100000;
    while(start <= end) {
        int mid = (start + end) / 2;
        long long time = times[0];
        for(int i = 1; i < diffs.size(); i++) {
            if(diffs[i] <= mid) time += times[i];
            else {
                int temp = diffs[i] - mid;
                time += (times[i - 1] + times[i]) * temp + times[i];
            }
        }
        if(time <= limit) {
            end = mid - 1;
        }
        else {
            start = mid + 1;
        }
        
    }
    return start;
}