#include <algorithm>
#include <iostream>
#include <set>
#include <vector>
#define MAX 1001
using namespace std;

int main() {
  int N, Z, M;
  cin >> N >> Z >> M;

  vector<int> v;
  for (int i = 0; i < M; i++) {
    int a;
    cin >> a;
    v.push_back(a);
  }

  bool visited[MAX];
  bool flag = false;

  for (int i = 1; i <= 1000; i++) {
    if (flag) break;
    int nowField = 1;
    bool visited[MAX] = {false};

    while (nowField < 1000) {
      if (nowField == Z) {
        cout << i << "\n";
        flag = true;
        break;
      }

      if (find(v.begin(), v.end(), nowField) != v.end()) {
        break;
      }

      if (visited[nowField]) break;
      visited[nowField] = true;

      nowField += i;
      if (nowField > N) {
        nowField -= N;
      }
    }
  }
  if (!flag) {
    cout << "-1\n";
  }
}