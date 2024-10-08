#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int hdx[8] = {-2,-1,1,2,2,1,-1,-2};
int hdy[8] = {1,2,2,1,-1,-2,-2,-1};
int map[200][200];
bool visit[200][200][30];
int k, w, h;
int answer = 40001;

struct node {
    int x, y, depth, horse;
};

int bfs() {
    queue<node> q;
    q.push({0, 0, 0, 0});
    visit[0][0][0] = true;
    while(!q.empty()) {
        node cur = q.front();
        q.pop();
        int x = cur.x;
        int y = cur.y;
        int depth = cur.depth;
        if(x == h - 1 && y == w - 1) return depth;
        if(cur.horse < k) {
            for(int i = 0; i < 8; i++) {
                int nx = x + hdx[i];
                int ny = y + hdy[i];
                if(0 <= nx && nx < h && 0 <= ny && ny < w) {
                    if(map[nx][ny] == 1) continue;
                    if(!visit[nx][ny][cur.horse + 1]) {
                        visit[nx][ny][cur.horse + 1] = true;
                        q.push({nx, ny, depth + 1, cur.horse + 1});
                    }
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0 <= nx && nx < h && 0 <= ny && ny < w) {
                if(map[nx][ny] == 1) continue;
                if(!visit[nx][ny][cur.horse]) {
                    visit[nx][ny][cur.horse] = true;
                    q.push({nx, ny, depth + 1, cur.horse});
                }
            }
        }
    }
    return -1;
}

int main() {
    cin >> k >> w >> h;
    for(int i = 0; i < h; i++) {
        for(int j = 0; j < w; j++) {
            cin >> map[i][j];
        }
    }
    int answer = 0;
    answer = bfs();
    cout << answer;
    return 0;
}
