#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;



int main() {
	int n, z, m;
	vector<int> stone;
	cin >> n >> z >> m;
	// ��ֹ� �Է�
	for (int i = 0; i < m; i++) {
		int temp;
		cin >> temp;
		stone.push_back(temp - 1);
	}
	// Hop�� 1���� n-1���� �÷���
 	for (int i = 1; i < n; i++) {
		// ������
		int start = 0;
		while (true) {
			// next Hop
			start = (start + i) % n;
			bool flag = false;
			// ��ֹ� ������ break
			for (int j = 0; j < stone.size(); j++) {
				if (start == stone[j]) {
					flag = true;
					break;
				}
			}
			// ������ ������ �ѹ��� ���Ŵϱ� break
			if (start == 0) flag = true;
			if (flag) break;
			// ���ϴ� �� ã���� ��� �� return
			if (start == z - 1) {
				cout << i;
				return 0;
			}
		}
	}
	return 0;
}