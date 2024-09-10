#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n, m, t;
vector<pair<int, int>> c_list[10001];
bool visit[10001];
int answer = 0;
int cnt = 0;

void prim(int start)
{
    priority_queue<pair<int, int>> pq;
    for (int i = 0; i < c_list[start].size(); i++)
    {
        int node = c_list[start][i].second;
        int cost = c_list[start][i].first;
        pq.push({-cost, node});
    }
    visit[start] = true;

    while (!pq.empty())
    {
        int cur = pq.top().second;
        int n_cost = -pq.top().first;
        // cout << cur << "\n";
        pq.pop();
        if (visit[cur])
            continue;
        visit[cur] = true;
        answer += n_cost;
        answer += t * cnt;
        cnt++;
        for (int i = 0; i < c_list[cur].size(); i++)
        {
            int next = c_list[cur][i].second;
            int cost = c_list[cur][i].first;
            if (!visit[next])
                pq.push({-cost, next});
        }
    }
}

int main()
{
    cin >> n >> m >> t;
    for (int i = 0; i < m; i++)
    {
        int s, e, w;
        cin >> s >> e >> w;

        c_list[s].push_back({w, e});
        c_list[e].push_back({w, s});
    }
    prim(1);

    cout << answer;
}
