#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#define MAX 1001
#define INF 999999;
using namespace std;

int N, M;
vector<pair<int, int>> v[MAX];
priority_queue<pair<int, int>> pq;
int dist[MAX];
int answer[MAX];

int main()
{
    cin >> N >> M;
    for (int i = 0; i < M; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;

        v[a].push_back({b, c});
        v[b].push_back({a, c});
    }

    for (int i = 1; i <= N; i++)
    {
        dist[i] = INF;
    }

    pq.push({0, 1});
    dist[1] = 0;

    while (!pq.empty())
    {
        int d = -pq.top().first;
        int node = pq.top().second;
        pq.pop();

        for (int i = 0; i < v[node].size(); i++)
        {
            int cost = d + v[node][i].second;

            // 거리 최솟값 갱신
            if (cost < dist[v[node][i].first])
            {
                dist[v[node][i].first] = cost;
                pq.push({-cost, v[node][i].first});
                // 다음 노드값과 이어지는 노드 갱신
                answer[v[node][i].first] = node;
            }
        }
    }

    cout << N - 1 << "\n";
    for (int i = 2; i <= N; i++)
    {
        cout << i << " " << answer[i] << "\n";
    }
    cout << "\n";
}