#include <iostream>
#define MAX 1001
using namespace std;

int C, R;

// 범위
bool isInRange(int i, int j) { return i >= 1 && i <= C && j >= 1 && j <= R; }

int main() {
  cin >> C >> R;

  bool map[MAX][MAX] = {
      false,
  };

  int k;
  cin >> k;

  // Column 은 1~7
  // ROW 는 1~6

  int maxCol = C;
  int maxRow = R;
  int minCol = 2;
  int minRow = 1;

  int col = 1;
  int row = 1;

  int flag = 1;

  // 배정이 불가능한 경우
  if (R * C < k) {
    cout << 0 << "\n";
    return 0;
  }

  // i 부터 k 번 까지
  for (int i = 1; i <= k; i++) {
    if (i == k) break;

    // 열, 위쪽 방향
    if (flag == 1) {
      // 최대 범위를 벗어나거나, 이미 좌석 배정이 된 상태면 옆으로
      if (row + 1 > maxRow || map[col][row + 1]) {
        flag = 2;
        col++;
        maxRow--;
        continue;
      }

      row++;
      map[col][row] = true;
      continue;
    }

    // 행, 오른쪽
    if (flag == 2) {
      if (col + 1 > maxCol || map[col + 1][row]) {
        flag = 3;
        row--;
        maxCol--;
        continue;
      }
      col++;
      map[col][row] = true;
      continue;
    }

    // 열, 아래쪽
    if (flag == 3) {
      if (row - 1 < minRow || map[col][row - 1]) {
        flag = 4;
        col--;
        minRow++;
        continue;
      }
      row--;
      map[col][row] = true;
      continue;
    }

    // 행, 왼쪽
    if (flag == 4) {
      if (col - 1 < minCol || map[col - 1][row]) {
        flag = 1;
        row++;
        minCol++;
        continue;
      }
      col--;
      map[col][row] = true;
    }
  }

  cout << col << " " << row << "\n";
}