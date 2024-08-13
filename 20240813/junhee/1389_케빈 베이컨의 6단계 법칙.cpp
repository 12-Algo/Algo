#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

int n, m;

int friends[101][101] = { 0, };
int b_cnt = INT_MAX;
int answer = 0;

void bfs(int start) {
	queue<pair<int,int>> q;
	bool visit[101] = { false, };
	int bacon = 0;
	q.push({ start, 0 });
	while (!q.empty()) {
		pair<int, int> now = q.front();
		bacon += now.second;
		q.pop();
		for (int i = 1; i < n + 1; i++) {
			if (friends[now.first][i] == 1 && visit[i] == false) {
				visit[i] = true;
				q.push({ i, now.second + 1 });
			}
		}
	}
	//cout << start << " " << bacon << endl;
	if (b_cnt > bacon) {
		b_cnt = bacon;
		answer = start;
	}
}

int main() {
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		friends[a][b] = 1;
		friends[b][a] = 1;
	}
	for (int i = 1; i < n + 1; i++) {
		bfs(i);
	}
	cout << answer;
	return 0;
}
