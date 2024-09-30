#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int N;
vector<int> v[10001];
int workTime[10001];
int indegree[10001];

int main()
{
    cin >> N;

    int answer[10001] = {0};

    for (int i = 1; i <= N; i++)
    {

        int cnt;
        cin >> workTime[i] >> cnt;

        for (int j = 0; j < cnt; j++)
        {
            int a;
            cin >> a;

            v[a].push_back(i);
            indegree[i]++;
        }

        answer[i] = workTime[i];
    }

    queue<int> pq;

    for (int i = 1; i <= N; i++)
    {
        if (indegree[i] == 0)
        {
            pq.push(i);
        }
    }

    int now;

    while (!pq.empty())
    {
        now = pq.front();
        pq.pop();

        for (int i = 0; i < v[now].size(); i++)
        {
            int next = v[now][i];

            // 동시에 진행되는 경우 기존값 , 이전값 + 현재 걸리는 시간 중 큰 값
            answer[next] = max(answer[now] + workTime[next], answer[next]);

            // 진입 차수가 0이되면 우선순위 큐에 넣어줌
            if (--indegree[next] == 0)
            {
                pq.push(next);
            }
        }
    }

    cout << *max_element(answer, answer + N + 1);
}