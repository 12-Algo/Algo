// #include <iostream>
// #include <algorithm>
// #include <string>
// #define MAX 19
// using namespace std;

// int N;
// string s;

// int answer = 0;
// int cal(int a, int b, char oper)
// {
//     int result = a;
//     switch (oper)
//     {
//     case '+':
//         result += b;
//         break;
//     case '*':
//         result *= b;
//         break;
//     case '-':
//         result -= b;
//         break;
//     }
//     return result;
// }
// void solution(int cnt, int value)
// {
//     if (cnt > N - 1)
//     {
//         answer = max(answer, value);
//         return;
//     }

//     char op = (cnt == 0) ? '+' : s[cnt - 1];

//     if (cnt + 2 < N)
//     {
//         int bracket = cal(s[cnt] - '0', s[cnt + 2] - '0', s[cnt + 1]);
//         solution(cnt + 2, cal(value, bracket, op));
//     }
//     solution(cnt + 2, cal(value, s[cnt] - '0', op));
// }

// int main()
// {
//     cin >> N;
//     cin >> s;

//     int bracket = N / 2;

//     int index = 0;
//     solution(0, 0);
//     cout << answer << "\n";
// }