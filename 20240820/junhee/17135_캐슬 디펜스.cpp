#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int n, m, d;
int map[16][15];
int copy_map[16][15];
bool visit[15];
vector<int> location;
int answer = -1;

bool count_e() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (copy_map[i][j] == 1) return true;
		}
	}
	return false;
}

vector<pair<int, int>> find_enemy() {
	pair<int, int> e1 = { INT_MAX, INT_MAX };
	pair<int, int> e2 = { INT_MAX, INT_MAX };
	pair<int, int> e3 = { INT_MAX, INT_MAX };
	pair<int, int> a1 = { n, location[0] };
	pair<int, int> a2 = { n, location[1] };
	pair<int, int> a3 = { n, location[2] };
	int min_dist1 = INT_MAX;
	int min_dist2 = INT_MAX;
	int min_dist3 = INT_MAX;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (copy_map[i][j] == 1) {
				int t_dist1 = abs(a1.first - i) + abs(a1.second - j);
				int t_dist2 = abs(a2.first - i) + abs(a2.second - j);
				int t_dist3 = abs(a3.first - i) + abs(a3.second - j);
				// 궁수 1
				if (t_dist1 <= min_dist1 && t_dist1 <= d) {
					if (t_dist1 == min_dist1) {
						if (e1.second > j) {
							e1.first = i; e1.second = j;
						}
					}
					else {
						min_dist1 = t_dist1;
						e1.first = i; e1.second = j;
					}
				}
				// 궁수 2
				if (t_dist2 <= min_dist2 && t_dist2 <= d) {
					if (t_dist2 == min_dist2) {
						if (e2.second > j) {
							e2.first = i; e2.second = j;
						}
					}
					else {
						min_dist2 = t_dist2;
						e2.first = i; e2.second = j;
					}
				}
				// 궁수 3
				if (t_dist3 <= min_dist3 && t_dist3 <= d) {
					if (t_dist3 == min_dist3) {
						if (e3.second > j) {
							e3.first = i; e3.second = j;
						}
					}
					else {
						min_dist3 = t_dist3;
						e3.first = i; e3.second = j;
					}
				}
			}
		}
	}
	vector<pair<int, int>> enemy_list;
	enemy_list.push_back(e1);
	enemy_list.push_back(e2);
	enemy_list.push_back(e3);
	return enemy_list;
}

void move_enemy() {
	vector<pair<int, int>> temp_e;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (copy_map[i][j] == 1) {
				if (i + 1 < n) temp_e.push_back({ i + 1, j });
				copy_map[i][j] = 0;
			}
		}
	}
	for (int i = 0; i < temp_e.size(); i++) {
		copy_map[temp_e[i].first][temp_e[i].second] = 1;
	}
}

void play_game() {
	int cnt = 0;
	while (count_e()) {
		vector<pair<int, int>> enemy_list = find_enemy();
		for (int i = 0; i < enemy_list.size(); i++) {
			if (enemy_list[i].first != INT_MAX && enemy_list[i].second != INT_MAX) {
				if (copy_map[enemy_list[i].first][enemy_list[i].second] == 1) {
					copy_map[enemy_list[i].first][enemy_list[i].second] = 0;
					cnt++;
				}
			}
		}
		move_enemy();
	}
	answer = max(cnt, answer);
}

void init_map() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			copy_map[i][j] = map[i][j];
		}
	}
}

void dfs(int idx) {
	if (location.size() == 3) {
		init_map();
		play_game();
		return;
	}

	for (int i = idx; i < m; i++) {
		if (!visit[i]) {
			visit[i] = true;
			location.push_back(i);
			dfs(idx + 1);
			location.pop_back();
			visit[i] = false;
		}
	}
}

int main() {
	cin >> n >> m >> d;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int temp;
			cin >> temp;
			map[i][j] = temp;
		}
	}
	
	dfs(0);

	cout << answer;

	return 0;
}
