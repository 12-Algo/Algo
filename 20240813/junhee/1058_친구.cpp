#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>
#include <cmath>

using namespace std;

int n;
int friends[51][51];
int answer = -1;

void bfs(int start) {
	bool visit[51] = { false, };
	queue<pair<int, int>> q;
	visit[start] = true;
	q.push({ start, 0 });
	int cnt = -1;
	while (!q.empty()) {
		pair<int, int> cur = q.front();
		q.pop();
		if (cur.second == 3) break;
		cnt++;
		for (int i = 0; i < n; i++) {
			if (friends[cur.first][i] == 1 && visit[i] == false) {
				q.push({ i, cur.second + 1 });
				visit[i] = true;
			}
		}
	}
	answer = max(cnt, answer);

}

int main() {
	cin >> n;
	string temp;
	for (int i = 0; i < n; i++) {
		cin >> temp;
		for (int j = 0; j < n; j++) {
			if (temp[j] == 'Y') {
				friends[i][j] = 1;
				friends[j][i] = 1;
			}
		}
	}
	for (int i = 0; i < n; i++) {
		bfs(i);
	}
	cout << answer;
	return 0;
}