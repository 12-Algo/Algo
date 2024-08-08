#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;



int main() {
	int n, z, m;
	vector<int> stone;
	cin >> n >> z >> m;
	// 장애물 입력
	for (int i = 0; i < m; i++) {
		int temp;
		cin >> temp;
		stone.push_back(temp - 1);
	}
	// Hop을 1부터 n-1까지 늘려감
 	for (int i = 1; i < n; i++) {
		// 시작점
		int start = 0;
		while (true) {
			// next Hop
			start = (start + i) % n;
			bool flag = false;
			// 장애물 만나면 break
			for (int j = 0; j < stone.size(); j++) {
				if (start == stone[j]) {
					flag = true;
					break;
				}
			}
			// 시작점 만나면 한바퀴 돈거니까 break
			if (start == 0) flag = true;
			if (flag) break;
			// 원하는 곳 찾으면 출력 후 return
			if (start == z - 1) {
				cout << i;
				return 0;
			}
		}
	}
	return 0;
}