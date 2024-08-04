#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int n;
int list[10] = {0,1,2,3,4,5,6,7,8,9};
bool visit[10] = { false, };
char is[9];
vector<int> permutation;
vector<string> answer;

void calc() {
	bool flag = false;
	string t_ans = "";
	for (int i = 0; i < n; i++) {
		if (is[i] == '<') {
			if (permutation[i] < permutation[i + 1]) {
				t_ans += permutation[i] + '0';
			}
			else {
				flag = true;
				break;
			}
		}
		else {
			if (permutation[i] > permutation[i + 1]) {
				t_ans += permutation[i] + '0';
			}
			else {
				flag = true;
				break;
			}
		}
	}
	if (!flag) {
		t_ans += permutation[permutation.size() - 1] + '0';
		answer.push_back(t_ans);
	}
	return;
}

void make_permu() {
	if (permutation.size() == n + 1) {
		calc();
		return;
	}
	for (int i = 0; i < 10; i++) {
		if (!visit[i]) {
			visit[i] = true;
			permutation.push_back(list[i]);
			make_permu();
			permutation.pop_back();
			visit[i] = false;
		}
	}
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		char temp;
		cin >> temp;
		is[i] = temp;
	}

	make_permu();

	cout << answer[answer.size() - 1] << endl << answer[0];
	return 0;
}