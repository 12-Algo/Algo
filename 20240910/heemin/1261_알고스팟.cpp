#include <iostream>
#include <vector>
#include <deque>
#define MAX 101
#define INF 99999999
using namespace std;

int N, M;
int map[MAX][MAX];
int dist[MAX][MAX];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

bool isInRange(int i, int j)
{
    return i > 0 && j > 0 && i <= M && j <= N;
}

// 0-1 너비우선탐색 !
void bfs()
{
    deque<pair<int, int>> dq;
    dq.push_back({1, 1});

    while (!dq.empty())
    {
        int x = dq.front().first;
        int y = dq.front().second;
        dq.pop_front();

        if (x == M && y == N)
        {
            return;
        }

        for (int k = 0; k < 4; k++)
        {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (!isInRange(nx, ny) || dist[nx][ny] != INF)
            {
                continue;
            }

            // 1이면 덱 뒤로
            if (map[nx][ny] == 1)
            {
                dist[nx][ny] = min(dist[x][y] + 1, dist[nx][ny]);
                dq.push_back({nx, ny});
            }
            else // 0이면 덱 앞으로
            {
                dist[nx][ny] = min(dist[x][y], dist[nx][ny]);
                dq.push_front({nx, ny});
            }
        }
    }
}

int main()
{
    cin >> N >> M;

    for (int i = 1; i <= M; i++)
    {
        string s;
        cin >> s;
        for (int j = 1; j <= N; j++)
        {
            map[i][j] = s[j - 1] - '0';
            dist[i][j] = INF;
        }
    }

    dist[1][1] = 0;
    bfs();

    if (N == 1 && M == 1)
    {
        cout << map[M][N] << "\n";
        return 0;
    }
    cout << dist[M][N] << "\n";
}