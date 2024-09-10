#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <climits>

using namespace std;

int n, m;
vector<pair<int, int>> v[1001];
int dist[1001];
int answer[1001];
int cnt = -1;

void dijkstra(int start)
{
    priority_queue<pair<int, int>> pq;
    dist[start] = 0;
    pq.push({0, start});
    while (!pq.empty())
    {
        int cur = pq.top().second;
        int cost = -pq.top().first;
        pq.pop();
        for (int i = 0; i < v[cur].size(); i++)
        {
            int next = v[cur][i].first;
            int next_cost = v[cur][i].second;
            if (dist[next] > cost + next_cost)
            {
                dist[next] = cost + next_cost;
                answer[next] = cur;
                pq.push({-dist[next], next});
            }
        }
    }
}

int main()
{
    cin >> n >> m;
    for (int i = 0; i < m; i++)
    {
        int s, e, w;
        cin >> s >> e >> w;
        v[s].push_back({e, w});
        v[e].push_back({s, w});
    }
    for (int i = 1; i <= n; i++)
    {
        dist[i] = INT_MAX;
    }
    dijkstra(1);
    cout << n - 1 << "\n";
    for (int i = 2; i <= n; i++)
    {
        // cout << dist[i] << " " << "\n";
        cout << answer[i] << " " << i << "\n";
    }
}
