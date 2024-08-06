#include <cmath>
#include <iostream>
#include <queue>
using namespace std;

int N, M;
int map[8][8];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
int answer = 0;
int walls = 0;
int virus = 0;

bool isInRange(int i, int j) { return i >= 0 && i < N && j >= 0 && j < M; }

int bfs() {
  int ret = 0;

  bool visit[8][8] = {false};

  queue<pair<int, int>> q;

  int count = 0;

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      if (map[i][j] == 2 && !visit[i][j]) {
        q.push({i, j});

        visit[i][j] = true;

        while (!q.empty()) {
          int a = q.front().first;
          int b = q.front().second;
          q.pop();

          for (int k = 0; k < 4; k++) {
            int na = a + dx[k];
            int nb = b + dy[k];

            if (isInRange(na, nb) && !visit[na][nb] && map[na][nb] == 0) {
              visit[na][nb] = true;
              q.push({na, nb});
              count++;
            }
          }
        }
      }
    }
  }
  ret = N * M - walls - virus - count;
  return ret;
}
int main() {
  cin >> N >> M;

  vector<pair<int, int>> empty;

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> map[i][j];
      if (map[i][j] == 0) {
        empty.push_back({i, j});
      } else if (map[i][j] == 1) {
        walls++;
      } else {
        virus++;
      }
    }
  }

  walls += 3;

  for (int i = 0; i < empty.size() - 2; i++) {
    for (int j = i + 1; j < empty.size() - 1; j++) {
      for (int k = j + 1; k < empty.size(); k++) {
        // map 변경
        map[empty[i].first][empty[i].second] = 1;
        map[empty[j].first][empty[j].second] = 1;
        map[empty[k].first][empty[k].second] = 1;

        answer = max(answer, bfs());

        map[empty[i].first][empty[i].second] = 0;
        map[empty[j].first][empty[j].second] = 0;
        map[empty[k].first][empty[k].second] = 0;
      }
    }
  }
  cout << answer << "\n";
}