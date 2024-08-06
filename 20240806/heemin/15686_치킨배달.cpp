#include <limits.h>

#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

#define MAX 51
using namespace std;

int N, M;
int arr[MAX][MAX];
bool visited[MAX];
int result[MAX];
int chickenNum = 0;
int answer = INT_MAX;
vector<pair<int, int>> chicken;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

bool inRange(int x, int y) { return x > 0 && y > 0 && x <= N && y <= N; }

int getChickenDistance() {
  int visit[MAX][MAX];
  int dist[MAX][MAX];
  int ret = 0;

  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      visit[i][j] = false;
      dist[i][j] = 0;
    }
  }

  queue<pair<int, int>> q;

  for (int i = 0; i < M; i++) {
    visit[chicken[result[i]].first][chicken[result[i]].second] = true;
    q.push({chicken[result[i]].first, chicken[result[i]].second});
  }

  while (!q.empty()) {
    int x = q.front().first;
    int y = q.front().second;
    q.pop();

    for (int k = 0; k < 4; k++) {
      int nx = x + dx[k];
      int ny = y + dy[k];

      if (!inRange(nx, ny) || visit[nx][ny]) continue;

      dist[nx][ny] = dist[x][y] + 1;
      visit[nx][ny] = true;

      if (arr[nx][ny] == 1) {
        ret += dist[nx][ny];
      }

      q.push({nx, ny});
    }
  }

  return ret;
}

void combination(int index, int next) {
  if (index == M) {
    answer = min(answer, getChickenDistance());
    return;
  }

  for (int i = next; i < chickenNum; i++) {
    if (visited[i]) continue;
    visited[i] = true;
    result[index] = i;
    combination(index + 1, i);
    visited[i] = false;
  }
}

int main() {
  cin >> N >> M;

  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      cin >> arr[i][j];
      if (arr[i][j] == 2) {
        chicken.push_back({i, j});
        chickenNum++;
      }
    }
  }

  combination(0, 0);
  cout << answer << "\n";
}