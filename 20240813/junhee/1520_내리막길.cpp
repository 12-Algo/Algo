#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <climits>
#include <string>

using namespace std;

int map[501][501];
int dp[501][501];
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };
int n, m;

int dfs(int x, int y) {
	if (x == n - 1 && y == m - 1) {
		return 1;
	}
	if (dp[x][y] == -1) {
		dp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (map[x][y] > map[nx][ny]) {
					dp[x][y] += dfs(nx, ny);
				}
			}
		}
	}
	return dp[x][y];
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
			dp[i][j] = -1;
		}
	}

	cout << dfs(0, 0) << endl;
	
	return 0;
}
