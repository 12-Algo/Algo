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

// 적이 남았는지 검사
bool count_e() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (copy_map[i][j] == 1) return true;
		}
	}
	return false;
}

// 각각의 궁수가 쏠 적 고르기
vector<pair<int, int>> find_enemy() {
	// 가장 가까운 적 선택을 위한 초기화
	pair<int, int> e1 = { INT_MAX, INT_MAX };
	pair<int, int> e2 = { INT_MAX, INT_MAX };
	pair<int, int> e3 = { INT_MAX, INT_MAX };
	// 현재 궁수가 서있는 위치
	pair<int, int> a1 = { n, location[0] };
	pair<int, int> a2 = { n, location[1] };
	pair<int, int> a3 = { n, location[2] };
	// 가까운 거리를 위한 초기화
	int min_dist1 = INT_MAX;
	int min_dist2 = INT_MAX;
	int min_dist3 = INT_MAX;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			// 적을 찾으면, 각 궁수가 해당 적을 쏠 것인지 검사
			if (copy_map[i][j] == 1) {
				// 각 궁수와 해당 적의 거리 계산
				int t_dist1 = abs(a1.first - i) + abs(a1.second - j);
				int t_dist2 = abs(a2.first - i) + abs(a2.second - j);
				int t_dist3 = abs(a3.first - i) + abs(a3.second - j);
				// 거리가 가장 가깝거나 같고, 사정거리 안쪽이면
				// 궁수 1
				if (t_dist1 <= min_dist1 && t_dist1 <= d) {
					// 거리가 같으면, 왼쪽 적을 쏴야 함
					if (t_dist1 == min_dist1) {
						if (e1.second > j) {
							e1.first = i; e1.second = j;
						}
					}
					// 거리가 가까우면, 거리를 초기화하고 해당 적 선택
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
	// 각 궁수별 적을 저장해서 반환
	vector<pair<int, int>> enemy_list;
	enemy_list.push_back(e1);
	enemy_list.push_back(e2);
	enemy_list.push_back(e3);
	return enemy_list;
}

// 궁수가 공격을 한 뒤, 적이 한칸 앞으로
void move_enemy() {
	vector<pair<int, int>> temp_e;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (copy_map[i][j] == 1) {
				// 여기서 바로 적을 옮기면, 다음 i + 1의 탐색에 또 적으로 인식하고 옮겨버림
				// 때문에 옮길 적을 vector에 넣고, 한번에 옮김
				// 또한, 지도 밖으로 나가는 적은 없애줌
				if (i + 1 < n) temp_e.push_back({ i + 1, j });
				// 원래 적이 있던 위치 0으로 초기화
				copy_map[i][j] = 0;
			}
		}
	}
	// 적 이동
	for (int i = 0; i < temp_e.size(); i++) {
		copy_map[temp_e[i].first][temp_e[i].second] = 1;
	}
}

// 게임 시작
void play_game() {
	int cnt = 0;
	// 적이 남아있지 않을 때 까지 실행
	while (count_e()) {
		// 각 궁수가 쏠 적 탐색
		vector<pair<int, int>> enemy_list = find_enemy();
		for (int i = 0; i < enemy_list.size(); i++) {
			// 쏠 수 있는 적인지 탐색.
			// INT_MAX라는 건, 범위에 들어온 적이 없었다는 것
			if (enemy_list[i].first != INT_MAX && enemy_list[i].second != INT_MAX) {
				// 각 궁수가 같은 적을 쏴도 한 번만 카운팅 되므로, 검사
				if (copy_map[enemy_list[i].first][enemy_list[i].second] == 1) {
					copy_map[enemy_list[i].first][enemy_list[i].second] = 0;
					cnt++;
				}
			}
		}
		// 공격 후 적 이동
		move_enemy();
	}
	// 정답 초기화
	answer = max(cnt, answer);
}

// 각 조합마다 매번 실행해야 하므로, 기존의 지도는 따로 저장 후 매번 생성해서 사용
void init_map() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			copy_map[i][j] = map[i][j];
		}
	}
}

// 궁수가 서 있을 위치의 조합
void dfs(int idx) {
	// 3명 다 위치를 정했으면, 게임 시작
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
