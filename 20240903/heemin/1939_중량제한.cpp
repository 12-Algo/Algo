#include <iostream>
#include <queue>
#include <algorithm>
#define MAX 10001
using namespace std;

int N, M;
int f1, f2;
vector<pair<int, int>> v[MAX];
bool visited[MAX];

bool bfs(int mid)
{
    queue<int> q;

    // 공장 시작
    q.push(f1);
    visited[f1] = true;

    while (!q.empty())
    {
        int island = q.front();
        q.pop();
        if (island == f2) // 다른 공장 도달
        {
            return true;
        }

        for (int i = 0; i < v[island].size(); i++)
        {
            int next = v[island][i].first;
            int cost = v[island][i].second;

            // 이미 방문했거나, 다리의 중량제한이 물품의 무게보다 작은 경우 이동 불가능
            if (cost < mid || visited[next])
            {
                continue;
            }

            visited[next] = true;
            q.push(next);
        }
    }
    return false;
}

int main()
{
    cin >> N >> M;

    int maxW = 0;

    for (int i = 0; i < M; i++)
    {
        int s, d, w;
        cin >> s >> d >> w;
        maxW = max(maxW, w);
        v[s].push_back({d, w});
        v[d].push_back({s, w});
    }

    cin >> f1 >> f2;

    // 이분탐색

    int start = 0;
    int end = maxW;

    int answer = 0;

    while (start <= end)
    {
        // 물품의 중량
        int mid = (start + end) / 2;
        fill_n(visited, N + 1, false);

        if (bfs(mid)) // mid 중량으로 이동 가능
        {
            answer = mid;
            start = mid + 1; // 더 큰 값 탐색
        }
        else // mid 중량으로 이동 불가능
        {
            end = mid - 1; // 더 작은 값 탐색
        }
    }
    cout << answer << "\n";
}