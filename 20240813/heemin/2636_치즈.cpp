#include <iostream>
#include <queue>
#include <vector>
#define MAX 101
using namespace std;

int R, C;
int map[MAX][MAX];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int totalCheese = 0;
int lastCheese = 0;

/**
 * 범위 파악 함수
 */
bool isInRange(int i, int j)
{
    return i >= 0 && i < R && j >= 0 && j < C;
}

/**
 * 공기와 접촉된 칸을 2로 표시
 */
void fillOutside()
{
    bool visited[MAX][MAX] = {
        0,
    };

    queue<pair<int, int>> q;
    q.push({0, 0});
    visited[0][0] = true;
    map[0][0] = 2;

    while (!q.empty())
    {
        int a = q.front().first;
        int b = q.front().second;
        q.pop();

        for (int k = 0; k < 4; k++)
        {
            int na = a + dx[k];
            int nb = b + dy[k];

            if (!isInRange(na, nb) || visited[na][nb])
            {
                continue;
            }

            if (map[na][nb] == 1)
            {
                continue;
            }

            visited[na][nb] = true;
            map[na][nb] = 2;
            q.push({na, nb});
        }
    }
}

/**
 * 공기와 접촉된 치즈를 0으로 변환
 */
void deleteCheese()
{
    queue<pair<int, int>> q;
    queue<pair<int, int>> meltedCheese;
    bool visited[MAX][MAX];

    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            if (map[i][j] != 1)
            {
                continue;
            }

            bool flag = false;

            for (int k = 0; k < 4; k++)
            {
                int ni = i + dx[k];
                int nj = j + dy[k];

                if (!isInRange(ni, nj) || map[ni][nj] != 2)
                {
                    continue;
                }

                flag = true;
            }

            if (flag)
            {
                // 치즈가 녹아 없어져야 함
                meltedCheese.push({i, j});
            }
        }
    }

    lastCheese = meltedCheese.size();
    totalCheese -= lastCheese;

    while (!meltedCheese.empty())
    {
        int x = meltedCheese.front().first;
        int y = meltedCheese.front().second;
        meltedCheese.pop();

        map[x][y] = 0;
    }
}

int main()
{
    cin >> R >> C;

    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            cin >> map[i][j];
            if (map[i][j] == 1)
            {
                totalCheese++;
            }
        }
    }

    int cnt = 0;

    while (totalCheese > 0)
    {
        lastCheese = 0;
        fillOutside();
        deleteCheese();
        cnt++;
    }

    cout << cnt << "\n";
    cout << lastCheese << "\n";
}