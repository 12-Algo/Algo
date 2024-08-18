#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int score_board[50][9];  // 문제에서 최대 n은 50이므로 이를 반영
int order[9];  // 타순 배열
bool visit[9];
int answer = 0;

void play_game() {
    int score = 0;
    int curr_order = 0;

    for (int inning = 0; inning < n; inning++) {
        int out = 0;
        vector<int> base(3, 0);
        while (out < 3) {
            int curr_player = order[curr_order];
            int result = score_board[inning][curr_player];
            if (result == 0) {
                out++;
            }
            else {
                for (int i = 2; i >= 0; i--) {
                    if (base[i] == 1) {
                        if (i + result >= 3) {
                            score++;
                        }
                        else {
                            base[i + result] = 1;
                        }
                        base[i] = 0;
                    }
                }
                if (result == 4) {
                    score++;
                }
                else {
                    base[result - 1] = 1;
                }
            }
            curr_order = (curr_order + 1) % 9;
        }
    }
    answer = max(answer, score);
}

void dfs(int depth) {
    if (depth == 9) {
        play_game();
        return;
    }

    if (depth == 3) {
        order[depth] = 0;
        dfs(depth + 1);
    }
    else {
        for (int i = 1; i < 9; i++) {
            if (!visit[i]) {
                visit[i] = true;
                order[depth] = i;
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 9; j++) {
            cin >> score_board[i][j];
        }
    }

    visit[0] = true;
    dfs(0);

    cout << answer << endl;

    return 0;
}
