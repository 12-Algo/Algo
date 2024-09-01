#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#define MAX 16
using namespace std;

int N;
int M;
int D;
int map[MAX][MAX];
int cmap[MAX][MAX];
int archer[3];

int dx[3] = {0, -1, 0};
int dy[3] = {-1, 0, 1};

int answer = 0;
int killed = 0;
int totalEnemy = 0;
int enemy = 0;

bool isInRange(int i, int j)
{
    return i > 0 && j > 0 && i <= N && j <= N;
}

int checkDist(int archerNum, int i, int j)
{
    int x = N + 1;
    int y = archer[archerNum];

    return abs(x - i) + abs(y - j);
}

pair<int, int> bfs(int archerNum)
{
    queue<pair<int, int>> q;

    bool visited[MAX][MAX];
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            visited[i][j] = false;
        }
    }

    q.push({N, archer[archerNum]});

    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        // 최단거리 좌표
        if (cmap[x][y] == 1 && checkDist(archerNum, x, y))
        {
            return {x, y};
        }

        visited[x][y] = true;

        for (int k = 0; k < 3; k++)
        {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (!isInRange(nx, ny) || visited[nx][ny] || checkDist(archerNum, nx, ny) > D)
            {
                continue;
            }

            q.push({nx, ny});
        }
    }
    return {-1, -1};
}

void moveEnemy()
{
    // 적이 성이 있는 칸으로 이동
    // for (int j = 1; j <= M; j++)
    // {
    //     if (cmap[N][j] == 1)
    //     {
    //         enemy--;
    //     }
    // }
    for (int i = N; i >= 1; i--)
    {

        for (int j = 1; j <= M; j++)
        {
            if (i == N)
            {
                if (cmap[N][j] == 1)
                {
                    enemy--;
                }
            }
            cmap[i][j] = cmap[i - 1][j];
        }
    }
}
void calculate()
{

    // 각 공수와 가장 가까운 적
    queue<pair<int, int>> q;
    for (int i = 0; i < 3; i++)
    {
        q.push(bfs(i));
    }

    // 죽이는 적 수 합산 및 맵 갱신
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        if (x == -1 && y == -1)
        {
            continue;
        }

        if (cmap[x][y] == 0)
        {
            continue;
        }

        cmap[x][y] = 0;
        killed++;
        enemy--;
    }

    // 적 앞으로 한 칸 이동
    moveEnemy();
}

void getArcher(int cnt, int start, int mask)
{
    if (cnt == 3)
    {
        killed = 0;
        enemy = totalEnemy;

        // 맵 복사
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                cmap[i][j] = map[i][j];
            }
        }

        while (enemy > 0)
        {
            calculate();
        }

        answer = max(answer, killed);

        return;
    }

    for (int i = start; i <= N; i++)
    {
        if ((mask & (1 << i)) != 0)
        {
            continue;
        }

        archer[cnt] = i;
        getArcher(cnt + 1, i + 1, mask | (1 << i));
    }
}

int main()
{
    cin >> N >> M >> D;

    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            cin >> map[i][j];
            if (map[i][j] == 1)
            {
                totalEnemy++;
            }
        }
    }
    getArcher(0, 1, 0);
    cout << answer << "\n";

    // 궁수 1~N 중 3
}