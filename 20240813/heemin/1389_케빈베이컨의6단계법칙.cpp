#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <limits.h>
#define MAX 101
using namespace std;

int main()
{
    vector<int> friends[MAX];
    int N, M;
    cin >> N >> M;

    int minValue = INT_MAX; // 케빈 베이컨 최솟값
    int answer = 1;         // 정답 인덱스

    // 입력 인접 리스트에 저장
    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;
        friends[a].push_back(b);
        friends[b].push_back(a);
    }

    // BFS
    for (int i = 1; i <= N; i++)
    {
        int dist[MAX] = {0}; // 각 친구마다 최소 단계 저장
        queue<int> q;

        // 바로 친구인 경우 - 1단계
        for (int j = 0; j < friends[i].size(); j++)
        {
            dist[friends[i][j]] = 1;
            q.push(friends[i][j]);
        }

        while (!q.empty())
        {
            int next = q.front();   // 친구
            int aDist = dist[next]; // 해당 친구와의 단계
            q.pop();

            // 친구의 친구들 탐색
            for (int k = 0; k < friends[next].size(); k++)
            {
                // 친구의 친구가 타겟인 경우 || 이미 단계를 저장한 친구인 경우 pass
                if (friends[next][k] == i || dist[friends[next][k]] > 0)
                {
                    continue;
                }

                dist[friends[next][k]] = aDist + 1; // 이전 친구와의 단계 + 1
                q.push(friends[next][k]);
            }
        }

        // 친구 단계 합
        int cnt = 0;
        for (int j = 1; j <= N; j++)
        {
            cnt += dist[j];
        }

        if (cnt < minValue)
        {
            minValue = cnt;
            answer = i; // 최소인 경우 정답 갱신
        }
    }
    cout << answer << "\n";
}