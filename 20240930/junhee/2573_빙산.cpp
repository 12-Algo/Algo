#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <string.h>

using namespace std;

int n, m;
int map[300][300];
int melt_map[300][300];
bool visit[300][300];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int count_sea(int x, int y)
{
    int cnt = 0;
    for (int i = 0; i < 4; i++)
    {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < m)
        {
            if (map[nx][ny] == 0)
                cnt++;
        }
    }
    return cnt;
}

void bfs(int sx, int sy)
{
    queue<pair<int, int>> q;
    q.push({sx, sy});
    visit[sx][sy] = true;
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < n && 0 <= ny && ny < m && !visit[nx][ny] && map[nx][ny] != 0)
            {
                visit[nx][ny] = true;
                q.push({nx, ny});
            }
        }
    }
}

int count_ice()
{
    int cnt = 0;
    memset(visit, false, sizeof(visit));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (map[i][j] != 0 && !visit[i][j])
            {
                // cout << "x : " << i << " y : " << j << "\n";
                // for (int i = 0; i < n; i++)
                // {
                //     for (int j = 0; j < m; j++)
                //     {
                //         cout << map[i][j] << " ";
                //     }
                //     cout << "\n";
                // }
                // cout << "\n";
                bfs(i, j);
                cnt++;
            }
        }
    }
    return cnt;
}

void melt()
{
    memset(melt_map, 0, sizeof(melt_map));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            melt_map[i][j] = count_sea(i, j);
        }
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            map[i][j] -= melt_map[i][j];
            if (map[i][j] < 0)
                map[i][j] = 0;
        }
    }
}

int main()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> map[i][j];
        }
    }
    int year = 0;
    int cnt = 0;
    while (true)
    {

        cnt = count_ice();
        // cout << cnt << " , " << year << "\n";
        if (cnt >= 2)
            break;
        if (cnt == 0)
        {
            year = 0;
            break;
        }
        melt();
        year++;
    }
    cout << year;
    return 0;
}
