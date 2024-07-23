#include <algorithm>
#include <iostream>
using namespace std;

pair<int, int> position;  //
int head = 1;
// 1=북 2=동 3=남 4=서

// 전진 함수
void F() {
  if (head == 1) {
    position.second++;
    return;
  }
  if (head == 2) {
    position.first++;
    return;
  }
  if (head == 3) {
    position.second--;
    return;
  }
  if (head == 4) {
    position.first--;
    return;
  }
  return;
}

// 후진 함수
void B() {
  if (head == 1) {
    position.second--;
    return;
  }
  if (head == 2) {
    position.first--;
    return;
  }
  if (head == 3) {
    position.second++;
    return;
  }
  if (head == 4) {
    position.first++;
    return;
  }
  return;
}

// 전환
void L() {
  head--;
  if (head == 0) {
    head = 4;
  }
}

// 전환
void R() {
  head++;
  if (head == 5) {
    head = 1;
  }
}

int main() {
  int t;
  cin >> t;

  for (int i = 0; i < t; i++) {
    string s;
    cin >> s;

    int l = 0;
    int r = 0;
    int u = 0;
    int d = 0;

    position = {0, 0};
    head = 1;
    for (int j = 0; j < s.length(); j++) {
      char op = s[j];

      if (op == 'F') {
        F();
      } else if (op == 'B') {
        B();
      } else if (op == 'L') {
        L();
      } else if (op == 'R') {
        R();
      }

      // 상하좌우 최대.최솟값
      l = min(l, position.first);
      r = max(r, position.first);
      u = max(u, position.second);
      d = min(d, position.second);
    }
    cout << (r - l) * (u - d) << "\n";
  }
}