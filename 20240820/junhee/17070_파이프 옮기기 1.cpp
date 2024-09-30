#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <queue>

using namespace std;

int n;
int map[16][16];
// 파이프가 이동할 수 있는 방향 지정
// idx = 0일 때는 가로 방향일 때, 1일 때는 세로 방향일 때, 2일 때는 대각선 방향일 때
vector<pair<int, int>> dir[3] = { {{1, 0}, {-1,-1}, {1,1}}, {{-1,-1}, {0, 1}, {1,1} }, {{1, 0}, {0, 1}, {1,1}} };
int answer = 0;

// 현재 위치와 현재 보고있는 방향을 parameter로 가지고 dfs 시작
void dfs(int x, int y, int direction) {
	// 끝까지 갔으면 방법++ 해주고 return
	if (x == n - 1 && y == n - 1) {
		answer++;
		return;
	}
	// 갈 수 있는 각 방향을 탐색
	for (int i = 0; i < dir[direction].size(); i++) {
		// 방향이 -1이면 가로 -> 세로 혹은 세로 -> 가로처럼 못가는 방향이므로 continue
		if (dir[direction][i].first == -1)
			continue;
		int nx = x + dir[direction][i].first;
		int ny = y + dir[direction][i].second;
		// 범위 탐색과 해당 방향에 장애물이 있는지 검사
		if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] != 1) {
			// 대각선 방향이 아니면 그대로 dfs
			if(i != 2) 
				dfs(nx, ny, i);
			// 대각선 방향이면 가고자 하는 방향 주위도 빈칸이어야 하므로 검사
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
