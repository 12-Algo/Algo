#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> ddr;
int dp[5][5][100000];

int move(int cur, int next)
{
    if (cur == 0)
        return 2;
    if (abs(cur - next) == 0)
        return 1;
    if (abs(cur - next) == 2)
        return 4;
    if (abs(cur - next) == 1 || abs(cur - next) == 3)
        return 3;
}

int solve(int left, int right, int depth)
{
    if (depth == ddr.size())
        return 0;
    if (dp[left][right][depth] != -1)
        return dp[left][right][depth];
    dp[left][right][depth] = min(solve(ddr[depth], right, depth + 1) + move(left, ddr[depth]), solve(left, ddr[depth], depth + 1) + move(right, ddr[depth]));
    return dp[left][right][depth];
}

int main()
{
    int temp;
    while (true)
    {
        cin >> temp;
        if (temp == 0)
            break;
        ddr.push_back(temp);
    }
    for (int i = 0; i < 5; i++)
    {
        for (int j = 0; j < 5; j++)
        {
            for (int k = 0; k < 100000; k++)
            {
                dp[i][j][k] = -1;
            }
        }
    }
    cout << solve(0, 0, 0);
}
