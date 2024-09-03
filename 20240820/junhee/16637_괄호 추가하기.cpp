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

// 숫자 계산 함수
int calc_oper(int a, int b, char op) {
    if (op == '+') return a + b;
    else if (op == '-') return a - b;
    else return a * b;
}

void calc() {
    vector<int> t_num;
    vector<char> op;
    // 괄호로 묶인 부분 먼저 계산 후 식 다시 만들어주기
    for (int i = 0; i < oper.size(); i++) {
        // 괄호로 묶지 않기로 정한 연산자
        if (!choice[i]) {
            앞쪽 숫자와 해당 연산자를 새로운 식에 저장
            t_num.push_back(num[i]);
            op.push_back(oper[i]);
        }
        // 괄호로 묶기로 한 연산자
        else {
            // 1차로 계산 후 새로운 식에 넣어주기
            t_num.push_back(calc_oper(num[i], num[i + 1], oper[i]));
            // 마지막 연산자가 아닌 경우, 연산자도 식에 넣어주기
            if(i < oper.size() - 1)
                op.push_back(oper[i + 1]);
            // 괄호로 묶었으니까 한 칸 건너 뛰기
            i++;
        }
    }
    // 마지막 연산자가 괄호로 묶인 연산자가 아닌 경우
    if (!choice[oper.size() - 1]) {
        // 마지막에 남은 숫자를 새로운 식에 넣어주기
        t_num.push_back(num[num.size() - 1]);
    }
    int result = t_num[0];
    // 새로운 식 계산
    for (int i = 0; i < op.size(); i++) {
        result = calc_oper(result, t_num[i + 1], op[i]);
    }
    answer = max(answer, result);
 }

// 모든 경우 탐색
void dfs(int idx) {
    // 다 돌았으면 계산
    if (idx >= oper.size()) {
        calc();
        return;
    }

    // 해당 operator를 괄호로 묶기로 결정한 경우
    choice[idx] = true;
    // 다음 operator는 고를 수 없으니까 건너뛰어줌
    dfs(idx + 2);
    choice[idx] = false;
    // 해당 operator를 고르지 않기로 한 경우
    dfs(idx + 1);
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        // 정수는 num에 저장
        if (i % 2 == 0) {
            int temp;
            cin >> temp;
            num.push_back(temp);
        }
        else {
            // operator는 oper에 저장
            char temp;
            cin >> temp;
            oper.push_back(temp);
        }
    }
    dfs(0);
    cout << answer;
    return 0;
}
