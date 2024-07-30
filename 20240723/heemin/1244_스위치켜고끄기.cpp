#include <iostream>
#define MAX 101
using namespace std;

int main() {
  int N;
  cin >> N;

  int arr[MAX];

  // 스위치
  for (int i = 1; i <= N; i++) {
    cin >> arr[i];
  }

  int people;
  cin >> people;

  for (int i = 1; i <= people; i++) {
    int gender;
    int swi;
    cin >> gender >> swi;

    // 성별이 남자인 경우
    if (gender == 1) {
      for (int j = 1; j <= N; j++) {
        if (j % swi != 0) continue;
        arr[j] = !arr[j];  //  토글
      }
      continue;
    }

    // 성별이 여자인 경우
    arr[swi] = !arr[swi];
    int index = 1;
    while (swi - index > 0 && swi + index <= N) {
      if (arr[swi - index] != arr[swi + index]) {  // 대칭이 다른 경우
        break;
      }

      // 양쪽 토글
      arr[swi - index] = !arr[swi - index];
      arr[swi + index] = !arr[swi + index];

      index++;
    }
  }

  // 출력
  for (int i = 1; i <= N; i++) {
    cout << arr[i] << " ";
    if (i % 20 == 0) {
      cout << "\n";
    }
  }
}
