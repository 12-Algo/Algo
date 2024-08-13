#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <climits>
#include <string>

using namespace std;

int n, m;
int map[101][101] = { 0, };
int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };

struct cheese {
	int x, y, depth;
};

int check_cheese() {
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] == 1)
				cnt++;
		}
	}
	return cnt;
}

void bfs() {
	queue<cheese> q;
	bool visit[101][101] = { false, };
	q.push({ 0,0,0 });
	while (!q.empty()) {
		cheese now = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = now.x + dx[i];
			int ny = now.y + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (visit[nx][ny] == false && now.depth == 0) {
					if (map[nx][ny] == 0) {
						visit[nx][ny] = true;
						q.push({ nx,ny,0 });
					}
					else {
						visit[nx][ny] = true;
						map[nx][ny] = 0;
						q.push({ nx,ny,1 });
						
					}
				}
			}
		}
	}
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int temp;
			cin >> temp;
			map[i][j] = temp;
		}
	}
	int prev = 0;
	int answer = 0;
	while (check_cheese() != 0) {
		prev = check_cheese();
		bfs();
		answer++;
	}
	cout << answer << endl << prev;
	return 0;
}
