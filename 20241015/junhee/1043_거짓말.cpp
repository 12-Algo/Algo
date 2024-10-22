#include <iostream>
#include <vector>

using namespace std;

int n, m;
int cnt;
vector<int> lier;
vector<int> party[50];
int unf[50];

int find(int num) {
    if(unf[num] == num) return num;
    return num = find(unf[num]);
}
void Union(int x, int y) {
    int fx = find(x);
    int fy = find(y);
    unf[fx] = fy;
}
int main() {
    cin >> n >> m;
    cin >> cnt;
    for(int i = 1; i <= n; i++) {
        unf[i] = i;
    }
    for(int i = 0; i < cnt; i++) {
        int temp;
        cin >> temp;
        lier.push_back(temp);
    }
    for(int i = 0; i < m; i++) {
        cin >> cnt;
        for(int j = 0; j < cnt; j++) {
            int temp;
            cin >> temp;
            party[i].push_back(temp);
            if(j > 0) {
                Union(party[i][j - 1], party[i][j]);
            }
        }
    }
    int answer = 0;
    for(int i = 0; i < m; i++) {
        bool flag = false;
        for(int j = 0; j < party[i].size(); j++) {
            if(flag) break;
            for(int k = 0; k < lier.size(); k++) {
                if(find(party[i][j]) == find(lier[k])) {
                    flag = true;
                    break;
                }
            }
        }
        if(!flag) answer++;
    }
    cout << answer;
    return 0;
}