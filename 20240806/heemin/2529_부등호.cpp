#include <algorithm>
#include <iostream>
#include <string>
#include <vector>
using namespace std;

int k;
vector<char> v;
int arr[10];
bool visited[10];
long long int minValue = 9999999999;
long long int maxValue = 0;
string minS = "";
string maxS = "";

void update() {
  for (int i = 0; i < k; i++) {
    if (v[i] == '<') {
      if (arr[i] > arr[i + 1]) return;
    } else if (v[i] == '>') {
      if (arr[i] < arr[i + 1]) return;
    }
  }

  string s = "";
  for (int i = 0; i < k + 1; i++) {
    s += (arr[i] + '0');
  }

  long long int num = stoll(s);
  if (minValue > num) {
    minValue = num;
    minS = s;
  }
  if (maxValue < num) {
    maxValue = num;
    maxS = s;
  }
}

void permutation(int index) {
  if (index == k + 1) {
    update();
    return;
  }

  for (int i = 0; i <= 9; i++) {
    if (visited[i]) continue;
    visited[i] = true;
    arr[index] = i;
    permutation(index + 1);
    visited[i] = false;
  }
}

int main() {
  cin >> k;

  for (int i = 0; i < k; i++) {
    char c;
    cin >> c;
    v.push_back(c);
  }

  permutation(0);

  cout << maxS << "\n" << minS << "\n";
}