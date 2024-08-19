#include <iostream>
using namespace std;
#define MAX 501

int n, m;
int map[MAX][MAX];
int dp[MAX][MAX];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

bool isInRange(int i, int j)
{
    return i > 0 && i <= n && j > 0 && j <= m;
}

int dfs(int a, int b)
{
    // 목적지에 도달한 경우
    if (a == n && b == m)
    {
        return 1;
    }
    // 경로가 있는 경우
    if (dp[a][b] != -1)
    {
        return dp[a][b];
    }

    dp[a][b] = 0;

    for (int k = 0; k < 4; k++)
    {
        int newa = a + dx[k];
        int newb = b + dy[k];

        // 범위안에 있고, 내리막길인 경우
        if (isInRange(newa, newb) && map[newa][newb] < map[a][b])
        {
            // 경로에 이전까지의 값 추가
            dp[a][b] += dfs(newa, newb);
        }
    }
    return dp[a][b];
}

int main()
{
    cin >> n >> m;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            cin >> map[i][j];
            dp[i][j] = -1;
        }
    }
    cout << dfs(1, 1) << "\n";

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            cout << dp[i][j] << " ";
        }
        cout << "\n";
    }
}