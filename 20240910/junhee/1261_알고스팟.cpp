#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
#include <string>

using namespace std;

int n, m;
int map[101][101];
bool visit[101][101];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};
int cnt = 1;

void bfs()
{
    deque<pair<int, int>> q;
    q.push_back({0, 0});
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop_front();
        bool flag = false;
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < n && 0 <= ny && ny < m)
            {
                if (map[nx][ny] == 1 && !visit[nx][ny])
                {
                    visit[nx][ny] = true;
                    flag = true;
                    map[nx][ny] = map[x][y] + 1;
                    q.push_back({nx, ny});
                }
                else if (map[nx][ny] == 0 && !visit[nx][ny])
                {
                    visit[nx][ny] = true;
                    map[nx][ny] = map[x][y];
                    q.push_front({nx, ny});
                }
            }
        }
        if (flag)
            cnt++;
    }
}

int main()
{
    cin >> m >> n;
    for (int i = 0; i < n; i++)
    {
        string temp;
        cin >> temp;
        for (int j = 0; j < m; j++)
        {
            map[i][j] = temp[j] - '0';
        }
    }
    bfs();

    cout << map[n - 1][m - 1];
}
