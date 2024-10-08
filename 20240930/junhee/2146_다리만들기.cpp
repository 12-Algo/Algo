#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n;
int map[100][100];
int dx[4] = {0,1,0,-1};
int dy[4] = {1,0,-1,0};
int cnt = -1;
int answer = 10001;

struct coord{
    int x,y,cost;
};

void change_map(int x, int y) {
    queue<pair<int, int>> q;
    q.push({x, y});
    map[x][y] = cnt;
    while(!q.empty()) {
        int cx = q.front().first;
        int cy = q.front().second;
        q.pop();
        for(int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];
            if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                if(map[nx][ny] == 1) {
                    map[nx][ny] = cnt;
                    q.push({nx, ny});
                }
            }
        }
    }
}

void bfs(int cx, int cy) {
    int c = map[cx][cy];
    int temp_map[100][100];   
    copy(&map[0][0], &map[0][0]+10000, &temp_map[0][0]);
    queue<coord> q;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(map[i][j] == c) {
                q.push({i, j, 0});
            }
        }
    }
    bool flag = false;
    while(!q.empty()) {
        coord cur = q.front();
        q.pop();
        for(int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                if(temp_map[nx][ny] == 0) {
                    temp_map[nx][ny] = cur.cost + 1;
                    q.push({nx, ny, cur.cost + 1});
                }
                else if(temp_map[nx][ny] != c && temp_map[nx][ny] < 0) {
                    answer = min(answer, cur.cost);
                    flag = true;
                    break;
                }
            }
        }
        if(flag) break;
    }
    
    
}

int main() {
    cin >> n;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cin >> map[i][j];
        }
    }
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(map[i][j] == 1){
                change_map(i, j);
                cnt--;
            }
        }
    }
    cnt = -1;
    
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            if(map[i][j] == cnt){
                bfs(i, j);
                cnt--;
            }
        }
    }
    cout << answer;
    return 0;
}
