#include <iostream>
#include <vector>
#include <algorithm>
#include <sstream>

using namespace std;

int main() {
    int n, answer = 0;
    cin >> n;
    
    vector<long long> list(n);
    for (int i = 0; i < n; i++) {
        cin >> list[i];
    }

    sort(list.begin(), list.end());

    for (int i = 0; i < n; i++) {
        int start = 0, end = n - 1;
        while (start < end) {
            if (start == i) {
                start++;
                continue;
            }
            if (end == i) {
                end--;
                continue;
            }
            if (list[start] + list[end] < list[i]) {
                start++;
            } else if (list[start] + list[end] > list[i]) {
                end--;
            } else {
                answer++;
                break;
            }
        }
    }

    cout << answer << endl;

    return 0;
}
