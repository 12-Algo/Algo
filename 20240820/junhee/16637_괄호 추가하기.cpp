#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int n;
vector<int> num;
vector<char> oper;
bool choice[10];
int answer = INT_MIN;

int calc_oper(int a, int b, char op) {
    if (op == '+') return a + b;
    else if (op == '-') return a - b;
    else return a * b;
}

void calc() {
    vector<int> t_num;
    vector<char> op;
    for (int i = 0; i < oper.size(); i++) {
        if (!choice[i]) {
            t_num.push_back(num[i]);
            op.push_back(oper[i]);
        }
        else {
            t_num.push_back(calc_oper(num[i], num[i + 1], oper[i]));
            if(i < oper.size() - 1)
                op.push_back(oper[i + 1]);
            i++;
        }
    }
    if (!choice[oper.size() - 1]) {
        t_num.push_back(num[num.size() - 1]);
    }
    int result = t_num[0];
    for (int i = 0; i < op.size(); i++) {
        result = calc_oper(result, t_num[i + 1], op[i]);
    }
    answer = max(answer, result);
 }

void dfs(int idx) {
    if (idx >= oper.size()) {
        calc();
        return;
    }

    choice[idx] = true;
    dfs(idx + 2);
    choice[idx] = false;
    dfs(idx + 1);
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
            int temp;
            cin >> temp;
            num.push_back(temp);
        }
        else {
            char temp;
            cin >> temp;
            oper.push_back(temp);
        }
    }
    dfs(0);
    cout << answer;
    return 0;
}
