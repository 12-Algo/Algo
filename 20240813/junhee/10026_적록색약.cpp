#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <climits>
#include <string>

using namespace std;

int n;

int normal[101][101] = { 0, };
int disable[101][101] = { 0, };
int n_cnt = 0;
int d_cnt = 0;
int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };

void bfs(int x, int y, int color, int type) {
	queue<pair<int, int>> q;
	q.push({ x,y });
	while (!q.empty()) {
		pair<int, int> now = q.front();
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = now.first + dx[i];
			int ny = now.second + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < n) {
				if (type == 1) {
					if (normal[nx][ny] == color) {
						normal[nx][ny] = -1;
						q.push({ nx, ny });
					}
				}
				else {
					if (disable[nx][ny] == color) {
						disable[nx][ny] = -1;
						q.push({ nx, ny });
					}
				}
			}
		}
	}
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		string temp;
		cin >> temp;
		for (int j = 0; j < n; j++) {
			if (temp[j] == 'R') {
				normal[i][j] = 1;
				disable[i][j] = 1;
			}
			else if (temp[j] == 'G') {
				normal[i][j] = 2;
				disable[i][j] = 1;
			}
			else {
				normal[i][j] = 3;
				disable[i][j] = 2;
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (normal[i][j] != -1) {
				bfs(i, j, normal[i][j], 1);
				n_cnt++;
			}
			if (disable[i][j] != -1) {
				bfs(i, j, disable[i][j], 2);
				d_cnt++;
			}
		}
	}
	cout << n_cnt << " " << d_cnt;
	return 0;
}
