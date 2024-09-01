#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#define MAX 51
#define PLAYER 9
using namespace std;

int N;
int result[MAX][PLAYER + 1];
bool visited[PLAYER + 1];
int order[PLAYER + 1];
int answer = 0;

void calculate()
{
    queue<int> q;
    for (int i = 1; i <= 9; i++)
    {
        q.push(order[i]);
    }

    int out = 0;
    int inning = 1;
    int score = 0;
    bool base[4] = {
        false,
    };

    while (inning <= N)
    {

        int player = q.front();
        int player_result = result[inning][player];
        q.pop();
        q.push(player);

        // 안타
        if (player_result == 1)
        {
            if (base[3])
            {
                base[3] = false;
                score++;
            }
            if (base[2])
            {
                base[2] = false;
                base[3] = true;
            }
            if (base[1])
            {
                base[1] = false;
                base[2] = true;
            }
            base[1] = true;
            continue;
        }
        // 2루타
        if (player_result == 2)
        {
            if (base[3])
            {
                base[3] = false;
                score++;
            }
            if (base[2])
            {
                base[2] = false;
                score++;
            }
            if (base[1])
            {
                base[1] = false;
                base[3] = true;
            }
            base[2] = true;
            continue;
        }
        // 3루타
        if (player_result == 3)
        {
            if (base[3])
            {
                base[3] = false;
                score++;
            }
            if (base[2])
            {
                base[2] = false;
                score++;
            }
            if (base[1])
            {
                base[1] = false;
                score++;
            }

            base[3] = true;
            continue;
        }
        // 홈런
        if (player_result == 4)
        {
            for (int i = 1; i <= 3; i++)
            {
                if (base[i])
                {
                    score++;
                    base[i] = false;
                }
            }
            score++;
            continue;
        }
        // 아웃
        if (player_result == 0)
        {
            out++;
        }

        // 다음 이닝
        if (out >= 3)
        {
            out = 0;
            inning++;
            for (int i = 1; i <= 3; i++)
            {
                base[i] = false;
            }
        }
    }
    answer = max(answer, score);
}

void permutation(int cnt)
{
    if (cnt == PLAYER + 1)
    {
        calculate();
        return;
    }

    if (cnt == 4)
    {
        order[cnt] = 1;
        permutation(cnt + 1);
    }

    for (int i = 1; i <= PLAYER; i++)
    {

        if (visited[i])
        {
            continue;
        }

        else
        {
            visited[i] = true;
            order[cnt] = i;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}
int main()
{
    cin >> N;

    // 1번 선수는 지정됨
    visited[1] = true;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= PLAYER; j++)
        {
            cin >> result[i][j];
        }
    }
    permutation(1);

    cout << answer << "\n";
}