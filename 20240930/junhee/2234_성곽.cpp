#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <string.h>

using namespace std;

int map[51][51];
int n, m;
int dx[4] = {0, -1, 0, 1};
int dy[4] = {-1, 0, 1, 0};
bool visit[51][51];
int answer1 = 0, answer2 = 0, answer3 = 0;

int bfs(int sx, int sy)
{
    int cnt = 0;
    queue<pair<int, int>> q;
    q.push({sx, sy});
    visit[sx][sy] = true;
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        // cout << "x : " << x << " y : " << y << "\n";
        q.pop();
        cnt++;
        int flag = 1;
        for (int i = 0; i < 4; i++)
        {
            if ((map[x][y] & flag) == flag)
            {
                flag *= 2;
                continue;
            }
            flag *= 2;
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < m && 0 <= ny && ny < n && !visit[nx][ny])
            {
                visit[nx][ny] = true;
                q.push({nx, ny});
            }
        }
    }
    return cnt;
}

int main()
{
    cin >> n >> m;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> map[i][j];
        }
    }
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (!visit[i][j])
            {
                // cout << " start (" << i << " , " << j << ")" << "\n";
                int cnt = bfs(i, j);
                answer2 = max(cnt, answer2);
                answer1++;
            }
        }
    }
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int flag = 1;
            for (int k = 0; k < 4; k++)
            {
                if ((map[i][j] & flag) == flag)
                {
                    map[i][j] -= flag;
                    memset(visit, false, sizeof(visit));
                    int cnt = bfs(i, j);
                    answer3 = max(answer3, cnt);
                    map[i][j] += flag;
                }
                flag *= 2;
            }
        }
    }
    cout << answer1 << "\n"
         << answer2 << "\n"
         << answer3;
}
