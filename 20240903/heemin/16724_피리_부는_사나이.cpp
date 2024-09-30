#include <iostream>
#include <stack>
using namespace std;

int N, M;
char map[1000][1000];
bool visited[1000][1000];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
char ch[] = {'U', 'D', 'L', 'R'};

bool isInRange(int x, int y)
{
    return x >= 0 && y >= 0 && x < N && y < M;
}

int findDir(char c)
{
    for (int i = 0; i < 4; i++)
    {
        if (c == ch[i])
        {
            return i;
        }
    }
}

void dfs(int x, int y)
{
    stack<pair<int, int>> s;

    s.push({x, y});

    visited[x][y] = true;

    while (!s.empty())
    {
        int i = s.top().first;
        int j = s.top().second;
        s.pop();

        char c = map[i][j];

        int dir = findDir(c);

        /// (i,j)가 가르키는 방향으로 계속 진행하는 경우
        int ni = i + dx[dir];
        int nj = j + dy[dir];

        if (isInRange(ni, nj) && !visited[ni][nj])
        {
            s.push({ni, nj});
            visited[ni][nj] = true;
        }

        // (i,j)의 상하좌우에서 (i,j)로 이동할 수 있는 경우
        for (int k = 0; k < 4; k++)
        {
            ni = i + dx[k];
            nj = j + dy[k];

            if (!isInRange(ni, nj) || visited[ni][nj])
            {
                continue;
            }

            if ((k == 1 && map[ni][nj] == 'U') || (k == 0 && map[ni][nj] == 'D') || (k == 3 && map[ni][nj] == 'L') || (k == 2 && map[ni][nj] == 'R'))
            {
                s.push({ni, nj});
                visited[ni][nj] = true;
            }
        }
    }
}

int main()
{
    cin >> N >> M;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> map[i][j];
        }
    }

    int answer = 0;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (!visited[i][j])
            {
                visited[i][j] = true;
                dfs(i, j);
                answer++; // 집합의 개수
            }
        }
    }
    cout << answer << "\n";
}