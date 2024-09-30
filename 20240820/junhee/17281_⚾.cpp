#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int score_board[50][9];
int order[9];
bool visit[9];
int answer = 0;

// 게임 시작
void play_game() {
    int score = 0;
    int curr_order = 0;
    // inning이 끝날 때까지
    for (int inning = 0; inning < n; inning++) {
        int out = 0;
        vector<int> base(3, 0);
        // out카운트를 3개 잡을 때 까지
        while (out < 3) {
            // 현재 타석의 타자
            int curr_player = order[curr_order];
            // 현재 타석의 타자가 친 타구의 결과
            int result = score_board[inning][curr_player];
            // 아웃이면 아웃카운트++
            if (result == 0) {
                out++;
            }
            else {
                // base를 전부 탐색
                for (int i = 2; i >= 0; i--) {
                    // 만약 base가 1(사람이 서있음)이고, 해당 base와 현재 타자의 타구가 3 이상(홈을 밟았음)일 때, 점수++
                    if (base[i] == 1) {
                        if (i + result >= 3) {
                            score++;
                        }
                        // 아니면 알맞은 base로 전진
                        else {
                            base[i + result] = 1;
                        }
                        //원래 있던 base는 사람이 없어졌으므로 0
                        base[i] = 0;
                    }
                }
                // 만약 홈런이면 점수++
                if (result == 4) {
                    score++;
                }
                // 아니면 현재 타자가 알맞은 base로 전진
                else {
                    base[result - 1] = 1;
                }
            }
            // 현재 타순++ 
            curr_order = (curr_order + 1) % 9;
        }
    }
    answer = max(answer, score);
}

// 타순을 정하는 순열 생성
void dfs(int depth) {
    // 타순을 전부 정했으면, 게임 시작
    if (depth == 9) {
        play_game();
        return;
    }
    // 4번 타자는 idx 0의 타자로 고정
    if (depth == 3) {
        order[depth] = 0;
        dfs(depth + 1);
    }
    // 4번이 아니면, 수열 생성
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
