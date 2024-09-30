#include <iostream>
#include <algorithm>

using namespace std;

int map[10][10];
bool chk[10][10];
int color[6] = { 0, };
int answer = 30;

// 색종이를 붙일 수 있는지 확인
bool is_ok(int x, int y, int size) {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (!map[x + i][y + j]) {
                return false;
            }
        }
    }
    return true;
}

// 색종이로 가리기
void update(int x, int y, int size, int t) {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            map[x + i][y + j] = t;
        }
    }
}

void dfs(int x, int y, int cnt) {
    // x, y가 1인 지점을 찾음
    while (map[x][y] == 0) {
        if (++y >= 10) {
            if (++x >= 10) {
                // 끝까지 갔는데도 못찾았으니 return;(1을 전부 색종이로 가렸다는 뜻)
                answer = min(answer, cnt);
                return;
            }
            y = 0;
        }
    }
    // 이미 최소 색종이보다 많이 붙였으면 가지치기
    if (answer <= cnt) return;

    // 1인 지점을 찾았으니 로직 시작 (큰 것부터 작은 것 까지 전부 붙여봄)
    for (int s = 5; s > 0; s--) {
        // 범위 넘거나 이미 색종이를 다 썼으면 continue;
        if (x + s > 10 || y + s > 10 || color[s] == 5) continue;
        // 붙여도 되는지 확인
        if (is_ok(x, y, s)) {
            // 붙인 다음 해당 색종이 썼다는 의미의 cnt++
            update(x, y, s, 0);
            color[s]++;
            dfs(x, y, cnt + 1);
            // 다시 떼 주기
            update(x, y, s, 1);
            color[s]--;
        }
    }
}

int main() {
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            cin >> map[i][j];
        }
    }

    dfs(0, 0, 0);
    if (answer == 30) answer = -1;
    cout << answer;
    return 0;
}
