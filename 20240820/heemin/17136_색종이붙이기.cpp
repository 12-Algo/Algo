// #include <iostream>
// #include <algorithm>
// using namespace std;

// int map[11][11];
// int paper[6] = {0, 5, 5, 5, 5, 5};

// int dx[3] = {0, 1, 1};
// int dy[3] = {1, 0, 1};

// int answer = 100;

// bool isInRange(int i, int j)
// {
//     return i > 0 && j > 0 && i <= 10 && j <= 10;
// }

// bool possible(int r, int c, int size)
// {
//     for (int i = 0; i < size; i++)
//     {
//         for (int j = 0; j < size; j++)
//         {
//             if (map[r + i][c + j] == 0)
//             {
//                 return false;
//             }
//         }
//     }
//     return true;
// }

// void find(int startI, int startJ, int cnt)
// {
//     while (map[startI][startJ] == 0)
//     {
//         startJ++;
//         if (startJ > 10)
//         {
//             startI++;
//             if (startI > 10)
//             {
//                 answer = min(answer, cnt);
//                 return;
//             }
//             startJ = 1;
//         }
//     }

//     if (cnt > answer)
//     {
//         return;
//     }

//     for (int count = 5; count >= 1; count--)
//     {
//         if (startI + count - 1 > 10 || startJ + count - 1 > 10)
//         {
//             continue;
//         }
//         if (paper[count] < 1)
//         {
//             continue;
//         }

//         if (!possible(startI, startJ, count))
//         {
//             continue;
//         }

//         paper[count]--;
//         for (int a = 0; a < count; a++)
//         {
//             for (int b = 0; b < count; b++)
//             {
//                 map[startI + a][startJ + b] = 0;
//             }
//         }
//         find(startI, startJ, cnt + 1);

//         paper[count]++;
//         for (int a = 0; a < count; a++)
//         {
//             for (int b = 0; b < count; b++)
//             {
//                 map[startI + a][startJ + b] = 1;
//             }
//         }
//     }
// }
// int main()
// {

//     for (int i = 1; i <= 10; i++)
//     {
//         for (int j = 1; j <= 10; j++)
//         {
//             cin >> map[i][j];
//         }
//     }

//     find(0, 0, 0);
//     if (answer == 100)
//     {
//         answer = -1;
//     }
//     cout << answer << "\n";
// }