#include <iostream>
#include <set>
#include <string>
using namespace std;

// 문자열 분리 함수
pair<string, string> split(string s, char c) {
  string ret1 = "";
  string ret2 = "";

  int i = 0;
  for (i = 0; i < s.length(); i++) {
    if (s[i] != c) {
      ret1 += s[i];
      continue;
    }
    break;
  }

  ret2 = s.substr(i + 1, s.length() - i);
  return {ret1, ret2};
}

// a가 b보다 빠르거나 같은 시간이면 true, 아니라면 false 반환
bool isBetween(string a, string b) {
  pair<string, string> aSplit = split(a, ':');
  pair<string, string> bSplit = split(b, ':');

  if (stoi(aSplit.first) > stoi(bSplit.first)) {
    return false;
  } else if (stoi(aSplit.first) < stoi(bSplit.first)) {
    return true;
  } else {
    if (stoi(aSplit.second) > stoi(bSplit.second)) {
      return false;
    } else {
      return true;
    }
  }
}

int main() {
  string S, E, Q;
  cin >> S >> E >> Q;

  set<string> check;

  int count = 0;

  while (true) {
    string time, name;
    cin >> time >> name;

    if (time == "") {
      break;
    }

    // 개강총회를 시작하기 전 채팅을 한 사람
    if (isBetween("00:00", time) && isBetween(time, S)) {
      check.insert(name);
      continue;
    }

    // 개강총회가 끝나고 채팅을 한 사람
    if (isBetween(E, time) && isBetween(time, Q)) {
      if (check.find(name) != check.end()) {
        check.erase(name);
        count++;
      }
    }
  }
  cout << count << "\n";
}