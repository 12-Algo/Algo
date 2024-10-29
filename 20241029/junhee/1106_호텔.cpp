#include <iostream>
#include <algorithm>

using namespace std;

int c, n;
int dp[1000001];

int main() {
	cin >> c >> n;
	for (int i = 0; i < n; i++) {
		int cost, cus;
		cin >> cost >> cus;
		for (int j = cost; j < 1000001; j++) {
			dp[j] = max(dp[j], dp[j - cost] + cus);
		}
	}

	for (int i = 0; i < 1000001; i++) {
		if (dp[i] >= c) { 
			cout << i;
			break;
		}
	}
	return 0;
}
