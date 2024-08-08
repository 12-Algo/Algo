#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int list[8] = {0,};
bool select[8] = { false, };
vector<int> permutation;
int answer = -1;

// 식에 따라 계산하고, 최댓값이면 저장
void calc() {
	int t_ans = 0;
	for (int i = 1; i < permutation.size(); i++) {
		t_ans += abs(permutation[i - 1] - permutation[i]);
	}
	answer = max(answer, t_ans);
 }

// 만들 수 있는 수열 전부 탐색
void make_permu() {
	// 수열 만들었으면 계산
	if (permutation.size() == n) {
		calc();
		return;
	}
	for (int i = 0; i < n; i++) {
		if (!select[i]) {
			select[i] = true;
			permutation.push_back(list[i]);
			make_permu();
			permutation.pop_back();
			select[i] = false;
		}
	}
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		int temp;
		cin >> temp;
		list[i] = temp;
	}

	make_permu();
	cout << answer;
	return 0;
}