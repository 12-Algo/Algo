#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#define MAX 21

using namespace std;

int N, M;
int blocks[MAX][MAX];
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int point = 0;
int cx = 0, cy = 0;
int max_cnt = 0;
bool visit2[101][101];

bool inRange(int i, int j) {
    return i > 0 && i <= N && j > 0 && j <= N;
}

bool findGroup() {
    queue<pair<int, int>> q;
    bool possible = false;
    for(int i=1;i<=N;i++){
        for(int j=1;j<=N;j++){
            visit2[i][j]=false; // 이미 탐색한 그룹인지?
        }
    }

    max_cnt = 0;
    int max_rainbow = 0;
    cx = 0; cy = 0; // 삭제할 기준 블럭 위치

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {

            // 기준 블럭
            if (blocks[i][j] < 1 || visit2[i][j]) {
                continue;
            }

            visit2[i][j] = true;
            int cnt = 1;
            int rainbow = 0;
            bool visited[101][101] = {false}; // 이번에 방문헀는지?
            q.push({i, j});
            visited[i][j] = true;

            while (!q.empty()) {
                pair<int,int> cur = q.front(); 
                q.pop();
                int x = cur.first;
                int y = cur.second;

                // 상하좌우
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    // 범위 밖
                    // 이미 방문
                    // 검은색 블럭인 경우
                    // 무지개 블럭이 아니면서 블럭 번호가 다른 경우
                    if (!inRange(nx, ny) || visited[nx][ny] || blocks[nx][ny] == -1 || (blocks[nx][ny] != 0 && blocks[nx][ny] != blocks[i][j])) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    q.push({nx, ny});

                    cnt++;
                    // 무지개
                    if (blocks[nx][ny] == 0) {
                        rainbow++;
                    } else { // 무지개가 아닐 때만 -> 무지개는 여러 번 탐색 가능
                        visit2[nx][ny] = true;
                    }
                }
            }

            // 블럭 개수가 2보다 큰지
            if (cnt >= 2) {
                possible = true;
            }
            // 갱신
            if (cnt > max_cnt) {
                max_cnt = cnt;
                max_rainbow = rainbow;
                cx = i;
                cy = j;
            } else if (cnt == max_cnt) {
                if (rainbow >= max_rainbow) {
                    max_rainbow = rainbow;
                    cx = i;
                    cy = j;
                }
            }
        }
    }
    return possible;
}

// (cx,cy) 로 시작하는 블럭들을 없애면서 -2로
void removeBlock() {
    queue<pair<int, int>> q;
    bool visited[101][101] = {false};
    q.push({cx, cy});
    visited[cx][cy] = true;
    int point = 1;

    while (!q.empty()) {
        auto cur = q.front(); q.pop();
        int x = cur.first;
        int y = cur.second;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            // 범위 밖
            // 이미 방문
            // 검은색 블럭인 경우
            // 무지개 블럭이 아니면서 블럭 번호가 다른 경우
            if (!inRange(nx, ny) || visited[nx][ny] || blocks[nx][ny] == -1 ||  (blocks[nx][ny] != 0 && blocks[nx][ny] != blocks[cx][cy])) {
                continue;
            }
            
            visited[nx][ny] = true;
            q.push({nx, ny});
            blocks[nx][ny] = -2;
            point++; // 점수
        }
    }
    blocks[cx][cy] = -2;
}

void gravity() {
    for (int j = 1; j <= N; j++) {
        queue<pair<int, int>> q;

        for (int i = N; i >= 1; i--) {
            q.push({blocks[i][j], i});
        }

        int i = N;
        while (!q.empty()) {
            int n = q.front().first;
            int x = q.front().second;
            q.pop();

            // 빈공간
            if (n == -2) {
                continue;
            }
            // 검은색 블럭
            if (n == -1) {
                blocks[x][j] = -1; // 그대로
                i = x - 1; // 다음 인덱스는 X 하나 위 -> -1은 고정이니까
                continue;
            }

            // n이 내려와 저장될 위치
            blocks[i][j] = n;
            if (i != x) {
                blocks[x][j] = -2;
            }
            i--;
        }
        // 윗부분은 빈공간
        for (int x = i - 1; x >= 1; x--) {
            blocks[x][j] = -2;
        }
    }
}

void rotate() {
    int tmp[MAX][MAX];
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            tmp[i][j] = blocks[i][j];
        }
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            blocks[i][j] = tmp[j][N - i + 1];
        }
    }
}

int main() {

    cin >> N >> M;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> blocks[i][j];
        }
    }

    while (true) {
        if (!findGroup()) {
            break;
        }
        point += max_cnt * max_cnt;
        removeBlock();
        gravity();
        rotate();
        gravity();
    }

    cout << point << "\n";

    return 0;
}
