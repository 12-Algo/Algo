#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int map[501][501];
int dp[501][501];
int n;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int dfs(int x, int y)
{
    if (dp[x][y] != 0)
    {
        return dp[x][y];
    }
    dp[x][y] = 1;
    for (int i = 0; i < 4; i++)
    {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (0 <= nx && nx < n && 0 <= ny && ny < n)
        {
            if (map[x][y] < map[nx][ny])
            {
                dp[x][y] = max(dp[x][y], dfs(nx, ny) + 1);
            }
        }
    }
    return dp[x][y];
}

int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> map[i][j];
        }
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            dfs(i, j);
        }
    }
    int answer = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            answer = max(answer, dp[i][j]);
        }
    }
    cout << answer;
    return 0;
}