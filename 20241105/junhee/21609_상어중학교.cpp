#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int n, m;
vector<vector<int>> map;
vector<int> dx = { 0, 1, 0, -1 };
vector<int> dy = { 1, 0, -1, 0 };
vector<vector<bool>> visit;
int max_cnt = 0;
int max_rainbow_cnt = 0;
int delete_x = -1, delete_y = -1;
int score = 0;

void find_block(int sx, int sy) {
    int cnt = 0;
    int rainbow_cnt = 0;
    queue<pair<int, int>> q;
    q.push({sx, sy});
    visit[sx][sy] = true;
    cnt++;
    
    while (!q.empty()) {
        auto cur = q.front(); q.pop();
        int x = cur.first;
        int y = cur.second;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (!visit[nx][ny] && map[nx][ny] == map[sx][sy]) {
                    visit[nx][ny] = true;
                    cnt++;
                    q.push({nx, ny});
                } else if (!visit[nx][ny] && map[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    cnt++;
                    rainbow_cnt++;
                    q.push({nx, ny});
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (map[i][j] == 0)
                visit[i][j] = false;
        }
    }

    if (cnt > max_cnt || (cnt == max_cnt && rainbow_cnt > max_rainbow_cnt) ||
        (cnt == max_cnt && rainbow_cnt == max_rainbow_cnt && (sx > delete_x || (sx == delete_x && sy > delete_y)))) {
        max_cnt = cnt;
        max_rainbow_cnt = rainbow_cnt;
        delete_x = sx;
        delete_y = sy;
    }
}

void delete_block(int sx, int sy) {
    vector<vector<bool>> visit(n, vector<bool>(n, false));
    queue<pair<int, int>> q;
    q.push({sx, sy});
    int color = map[sx][sy];
    visit[sx][sy] = true;
    map[sx][sy] = -2;

    while (!q.empty()) {
        auto cur = q.front(); q.pop();
        int x = cur.first;
        int y = cur.second;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (!visit[nx][ny] && (map[nx][ny] == color || map[nx][ny] == 0)) {
                    visit[nx][ny] = true;
                    map[nx][ny] = -2;
                    q.push({nx, ny});
                }
            }
        }
    }
    score += max_cnt * max_cnt;
}

void swap_down(int x, int y) {
    for (int i = x + 1; i < n; i++) {
        if (map[i][y] == -2) {
            swap(map[i][y], map[i - 1][y]);
        } else {
            break;
        }
    }
}

void gravity() {
    for (int i = n - 2; i >= 0; i--) {
        for (int j = 0; j < n; j++) {
            if (map[i][j] >= 0) {
                swap_down(i, j);
            }
        }
    }
}

void turn() {
    vector<vector<int>> tmp_arr(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            tmp_arr[n - 1 - j][i] = map[i][j];
        }
    }
    map = tmp_arr;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> n >> m;
    map.resize(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> map[i][j];
        }
    }

    while (true) {
        visit.assign(n, vector<bool>(n, false));
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0)
                    find_block(i, j);
            }
        }

        if (max_cnt < 2) {
            cout << score << '\n';
            break;
        }

        delete_block(delete_x, delete_y);
        gravity();
        turn();
        gravity();

        max_cnt = 0;
        max_rainbow_cnt = 0;
        delete_x = -1;
        delete_y = -1;
    }

    return 0;
}
