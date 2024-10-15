#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

int map[5][5];
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

bool bfs(int sx, int sy) {
    bool flag = true;
    int visit[5][5];
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 5; j++) {
            visit[i][j] = -1;
        }
    }
    queue<pair<int,int>> q;
    q.push({sx, sy});
    visit[sx][sy] = 0;
    while(!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        if((x != sx || y != sy) && map[x][y] == 1 && visit[x][y] <= 2) {
            flag = false;
            break;
        }
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                if(map[nx][ny] == 2) continue;
                if(visit[nx][ny] == -1) {
                    visit[nx][ny] = visit[x][y] + 1;
                    q.push({nx, ny});
                }
            }
        }
    }
    return flag;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    for(int t = 0; t < 5; t++) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(places[t][i][j] == 'P') {
                    map[i][j] = 1;
                }
                if(places[t][i][j] == 'O') {
                    map[i][j] = 0;
                }
                if(places[t][i][j] == 'X') {
                    map[i][j] = 2;
                }
            }
        }
        bool flag = true;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(map[i][j] == 1) {
                    if (!bfs(i, j)) {
                        flag = false;
                        break;
                    }
                }
            }
            if(!flag) break;
        }
        answer.push_back(flag ? 1 : 0);
    }
    
    return answer;
}