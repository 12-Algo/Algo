#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, c;
vector<int> house;

int main()
{
    cin >> n >> c;
    for (int i = 0; i < n; i++)
    {
        int temp;
        cin >> temp;
        house.push_back(temp);
    }
    sort(house.begin(), house.end());
    int start = 1;
    int end = house[house.size() - 1];
    while (start <= end)
    {
        int mid = (start + end) / 2;
        int cnt = 1;
        int prev = 0;
        for (int i = 1; i < house.size(); i++)
        {
            if (house[i] - house[prev] >= mid)
            {
                cnt++;
                prev = i;
            }
        }
        if (cnt >= c)
        {
            start = mid + 1;
        }
        else
        {
            end = mid - 1;
        }
    }
    cout << end;
}