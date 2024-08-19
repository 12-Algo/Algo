#include <iostream>
#include <queue>
#include <string>
#define MAX 101
using namespace std;

int N;
char map[MAX][MAX];

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

/**
 * 범위 파악 함수
 */
bool isInRange(int i, int j)
{
    return i >= 0 && i < N && j >= 0 && j < N;
}

/**
 * 색맹인지 아닌지에 따라, 같은 색으로 보는지 다른 색으로 보는지 boolean 값 리턴
 */
bool isSame(char c, char d, bool isColorBlind)
{
    if (c == d)
    {
        return true;
    }

    if (isColorBlind)
    {
        if ((c == 'R' && d == 'G') || (c == 'G' && d == 'R'))
        {
            return true;
        }
    }

    return false;
}

/**
 * 적록색맹 여부에 따라 BFS로 순회 후 구역 개수 탐색
 */
int bfs(bool isColorBlind)
{
    // 서로 다른 구역의 개수
    int area = 0;

    queue<pair<int, int>> q;

    bool visited[MAX][MAX] = {
        0,
    };

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {

            if (visited[i][j])
            {
                continue;
            }

            char c = map[i][j];

            // 탐색하지 않은 칸이면 구역 개수 추가
            area++;

            q.push({i, j});

            while (!q.empty())
            {
                int a = q.front().first;
                int b = q.front().second;
                q.pop();

                visited[a][b] = true;

                for (int k = 0; k < 4; k++)
                {
                    int na = a + dx[k];
                    int nb = b + dy[k];

                    if (!isInRange(na, nb))
                    {
                        continue;
                    }

                    if (!isInRange(na, nb) || visited[na][nb] || !isSame(c, map[na][nb], isColorBlind))
                    {
                        continue;
                    }

                    visited[na][nb] = true;
                    q.push({na, nb});
                }
            }
        }
    }
    return area;
}

int main()
{
    cin >> N;

    for (int i = 0; i < N; i++)
    {
        string s;
        cin >> s;
        for (int j = 0; j < N; j++)
        {
            map[i][j] = s[j];
        }
    }

    int notBlind = bfs(false); // 적록색맹이 아닌 경우
    int blind = bfs(true);     // 적록색맹인 경우
    cout << notBlind << " " << blind << "\n";
}