#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
#define MAX 10001
using namespace std;

struct road
{
    int a;
    int b;
    int cost;

    bool operator<(const road &o) const
    {
        return cost > o.cost;
    }
};

int N, M, t;
priority_queue<road> pq;
int parent[MAX];

void init()
{
    for (int i = 1; i <= N; i++)
    {
        parent[i] = i;
    }
}

int find(int x)
{
    if (x == parent[x])
    {
        return x;
    }
    return parent[x] = find(parent[x]);
}

void _union(int x, int y)
{
    int rx = find(x);
    int ry = find(y);

    if (rx < ry)
    {
        parent[ry] = rx;
    }
    else
    {
        parent[rx] = ry;
    }
}

int main()
{
    cin >> N >> M >> t;
    for (int i = 0; i < M; i++)
    {
        int a, b, c;
        cin >> a >> b >> c;

        pq.push({a, b, c});
    }

    init();

    int answer = 0;
    int plused = 0; // 더해줘야 하는 값 누적

    // 크루스칼
    while (!pq.empty())
    {
        int a = pq.top().a;
        int b = pq.top().b;
        int cost = pq.top().cost;
        pq.pop();

        if (find(a) == find(b))
        {
            continue;
        }

        _union(a, b);
        answer += (cost + plused);
        plused += t;
    }
    cout << answer << "\n";
}