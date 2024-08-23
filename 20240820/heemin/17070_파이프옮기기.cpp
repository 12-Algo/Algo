#include <iostream>
#include <queue>
#include <algorithm>
#define MAX 17
using namespace std;

int N;
int map[MAX][MAX];
int answer = 0;

int diagX[3] = {0, 1, 1};
int diagY[3] = {1, 1, 0};

int dx[2] = {0, 1};
int dy[2] = {1, 0};

bool isInRange(int i, int j)
{
    bool range = i > 0 && j > 0 && i <= N && j <= N;
    bool valid = !map[i][j];

    return range && valid;
}

bool isDiagonalPossible(int x, int y)
{
    // 대각선 탐색
    bool possible = true;
    for (int i = 0; i < 3; i++)
    {
        int nx = x + diagX[i];
        int ny = y + diagY[i];

        if (!isInRange(nx, ny))
        {
            possible = false;
            break;
        }
    }

    return possible;
}
void solution()
{
    queue<pair<pair<int, int>, int>> q;
    // 끝점의 좌표, 방향
    // 1 : 가로
    // 2 : 세로
    // 3 : 대각선

    q.push({{1, 2}, 1});

    while (!q.empty())
    {
        int x = q.front().first.first;
        int y = q.front().first.second;
        int dir = q.front().second;
        q.pop();

        if (x == N && y == N)
        {
            answer++;
        }

        // 가로
        if (dir == 1)
        {

            // 가로 탐색
            if (isInRange(x, y + 1))
            {
                q.push({{x, y + 1}, 1});
            }

            // 대각선 탐색
            if (isDiagonalPossible(x, y))
            {
                q.push({{x + 1, y + 1}, 3});
            }

            continue;
        }
        // 세로
        if (dir == 2)
        {

            // 세로 탐색
            if (isInRange(x + 1, y))
            {
                q.push({{x + 1, y}, 2});
            }

            // 대각선 탐색
            if (isDiagonalPossible(x, y))
            {
                q.push({{x + 1, y + 1}, 3});
            }
        }
        // 대각선
        if (dir == 3)
        {

            // 가로 탐색
            if (isInRange(x, y + 1))
            {
                q.push({{x, y + 1}, 1});
            }

            // 세로 탐색
            if (isInRange(x + 1, y))
            {
                q.push({{x + 1, y}, 2});
            }

            // 대각선 탐색
            if (isDiagonalPossible(x, y))
            {
                q.push({{x + 1, y + 1}, 3});
            }
        }
    }
}

int main()
{
    cin >> N;

    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            cin >> map[i][j];
        }
    }
    solution();
    cout << answer << "\n";
}