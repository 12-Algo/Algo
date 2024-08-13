#include <iostream>
#include <string.h>
#include <queue>
#include <algorithm>
#define MAX 51
using namespace std;

int main()
{
    int N;
    cin >> N;

    // 각 사람마다 2-친구 저장
    int twoFriends[MAX] = {
        0,
    };

    // 친구인지 저장하는 배열
    bool isFriend[MAX][MAX];

    for (int i = 1; i <= N; i++)
    {
        string s;
        cin >> s;

        for (int j = 1; j <= N; j++)
        {
            if (s[j - 1] == 'N')
            {
                isFriend[i][j] = false;
            }
            else
            {
                isFriend[i][j] = true;
            }
        }
    }

    for (int i = 1; i <= N; i++)
    {
        bool visited[MAX] = {
            false,
        };

        for (int j = 1; j <= N; j++)
        {
            if (i == j)
            {
                continue;
            }

            // 직접 친구인 케이스
            if (isFriend[i][j])
            {
                // 이미 체크하지 않았다면 친구로 저장
                if (!visited[j])
                {
                    twoFriends[i]++;
                    visited[j] = true;
                }

                // 친구의 친구 탐색
                for (int k = 1; k <= N; k++)
                {
                    // 친구의 친구가 내 자신이면 pass
                    if (k == i)
                    {
                        continue;
                    }

                    // 친구의 친구가 다른 사람이면 2-친구 저장
                    if (isFriend[j][k] && !isFriend[i][k] && !visited[k])
                    {
                        twoFriends[i]++;
                        visited[k] = true;
                    }
                }
            }
        }
    }
    // 최댓값 출력
    int answer = *max_element(twoFriends, twoFriends + N + 1);
    cout << answer << "\n";
}
