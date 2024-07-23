#include <algorithm>
#include <climits>
#include <iostream>
#include <vector>
#define MAX 1000001
using namespace std;

int main() {
  int N;
  cin >> N;

  // int dp[MAX];
  vector<int> dp(MAX);

  for (int i = 1; i <= N; i++) {
    dp[i] = INT_MAX;
  }

  dp[N] = 0;

  for (int i = N; i >= 1; i--) {
    // 값은 i
    dp[i] = min(dp[i], dp[i + 1] + 1);

    if (i % 3 == 0) {
      dp[i / 3] = min(dp[i / 3], dp[i] + 1);
    }
    if (i % 2 == 0) {
      dp[i / 2] = min(dp[i / 2], dp[i] + 1);
    }
    dp[i - 1] = min(dp[i - 1], dp[i] + 1);
  }
  cout << dp[1] << "\n";
}
