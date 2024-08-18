#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <queue>

using namespace std;

int n;
int map[16][16];

vector<pair<int, int>> dir[3] = { {{1, 0}, {-1,-1}, {1,1}}, {{-1,-1}, {0, 1}, {1,1} }, {{1, 0}, {0, 1}, {1,1}} };
int answer = 0;

void dfs(int x, int y, int direction) {
	if (x == n - 1 && y == n - 1) {
		answer++;
		return;
	}
	for (int i = 0; i < dir[direction].size(); i++) {
		if (dir[direction][i].first == -1)
			continue;
		int nx = x + dir[direction][i].first;
		int ny = y + dir[direction][i].second;
		if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] != 1) {
			if(i != 2) 
				dfs(nx, ny, i);
			else {
				if(map[nx - 1][ny] != 1 && map[nx][ny - 1] != 1)
					dfs(nx, ny, i);
			}
		}
	}
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int temp;
			cin >> temp;
			map[i][j] = temp;
		}
	}
	dfs(0, 1, 1);
	cout << answer;
	return 0;
}
