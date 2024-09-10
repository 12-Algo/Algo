#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Node
{
    int s, e, w;
};

bool compare1(Node a, Node b)
{
    return a.w > b.w;
}

bool compare2(Node a, Node b)
{
    return a.w < b.w;
}

int n, m;
vector<Node> list;
int unf1[1001];
int unf2[1001];
int answer[2];

int Find1(int a)
{
    if (unf1[a] == a)
        return a;
    return unf1[a] = Find1(unf1[a]);
}

void Union1(int a, int b)
{
    int fa = Find1(a);
    int fb = Find1(b);
    if (fa != fb)
    {
        unf1[fa] = fb;
    }
}

int Find2(int a)
{
    if (unf2[a] == a)
        return a;
    return unf2[a] = Find2(unf2[a]);
}

void Union2(int a, int b)
{
    int fa = Find2(a);
    int fb = Find2(b);
    if (fa != fb)
    {
        unf2[fa] = fb;
    }
}

void kruskal1()
{
    for (int i = 0; i < list.size(); i++)
    {
        if (Find1(list[i].s) != Find1(list[i].e))
        {
            Union1(list[i].s, list[i].e);
            // cout << list[i].s << " " << list[i].e << "\n";
            if (list[i].w == 0)
            {
                answer[0] += 1;
            }
        }
    }
}

void kruskal2()
{
    for (int i = 0; i < list.size(); i++)
    {
        if (Find2(list[i].s) != Find2(list[i].e))
        {
            Union2(list[i].s, list[i].e);
            // cout << list[i].s << " " << list[i].e << "\n";
            if (list[i].w == 0)
            {
                answer[1] += 1;
            }
        }
    }
}

// 왜 c로 하시져 ?-?
int main()
{
    cin >> n >> m;
    for (int i = 0; i <= m; i++)
    {
        int s, e, w;
        cin >> s >> e >> w;
        list.push_back({s, e, w});
    }
    for (int i = 0; i <= n; i++)
    {
        unf1[i] = i;
        unf2[i] = i;
    }
    sort(list.begin(), list.end(), compare1);
    kruskal1();
    sort(list.begin(), list.end(), compare2);
    // for (int i = 0; i < list.size(); i++)
    // {
    //     cout << list[i].s << " " << list[i].e << " " << list[i].w << "\n";
    // }
    // cout << "================\n";
    kruskal2();
    cout << answer[1] * answer[1] - answer[0] * answer[0];
}
