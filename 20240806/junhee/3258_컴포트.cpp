#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;



int main() {
	int n, z, m;
	vector<int> stone;
	cin >> n >> z >> m;
	for (int i = 0; i < m; i++) {
		int temp;
		cin >> temp;
		stone.push_back(temp - 1);
	}
 	for (int i = 1; i < n; i++) {
		int start = 0;
		while (true) {
			start = (start + i) % n;
			bool flag = false;
			for (int j = 0; j < stone.size(); j++) {
				if (start == stone[j]) {
					flag = true;
					break;
				}
			}
			if (start == 0) flag = true;
			if (flag) break;
			if (start == z - 1) {
				cout << i;
				return 0;
			}
		}
	}
	return 0;
}