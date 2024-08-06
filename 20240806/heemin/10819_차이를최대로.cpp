#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

int N;
int arr[8];
bool visited[8];
vector<int> v;
int answer;

void getAnswer() {
  int cnt = 0;
  for (int i = 0; i < N - 1; i++) {
    int first = arr[i];
    int second = arr[i + 1];

    if (first > second)
      cnt += (first - second);
    else
      cnt += (second - first);
  }

  answer = max(answer, cnt);
}

void dfs(int index) {
  if (index == N) {
    getAnswer();
    return;
  }

  for (int i = 0; i < N; i++) {
    if (visited[i]) continue;
    visited[i] = true;
    arr[index] = v[i];
    dfs(index + 1);
    visited[i] = false;
  }
}

int main() {
  cin >> N;

  for (int i = 0; i < N; i++) {
    int num;
    cin >> num;

    v.push_back(num);
  }

  dfs(0);

  cout << answer << "\n";
}