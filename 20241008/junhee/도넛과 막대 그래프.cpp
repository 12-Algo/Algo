#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<vector<int>> outE(1000005);
vector<vector<int>> inE(1000005);
vector<vector<int>> path(1000005);

int calc(int start) {
    queue<int> q;
    if(outE[start].size() >= 2) return 3;
    for(int i = 0; i < outE[start].size(); i++) {
        q.push(outE[start][i]);
    }
    while(!q.empty()) {
        int cur = q.front();
        q.pop();
        if(cur == start) return 1;
        if(outE[cur].size() >= 2) return 3;
        if(outE[cur].size() == 0) return 2;
        for(int i = 0; i < outE[cur].size(); i++) {
            q.push(outE[cur][i]);
        }
    }
    return 2;
}

vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer;
    for(int i = 0; i < edges.size(); i++) {
        outE[edges[i][0]].push_back(edges[i][1]);
        inE[edges[i][1]].push_back(edges[i][0]);
        
    }
    int start = -1;
    for(int i = 0; i < 1000005; i++) {
        if(outE[i].size() >= 2 && inE[i].size() == 0) {
            answer.push_back(i);
            start = i;
            break;
        }
    }
    int cnt[4] = {0,};
    for(int i = 0; i < outE[start].size(); i++) {
        int num = calc(outE[start][i]);
        cnt[num]++;
    }
    answer.push_back(cnt[1]);
    answer.push_back(cnt[2]);
    answer.push_back(cnt[3]);
    return answer;
}
