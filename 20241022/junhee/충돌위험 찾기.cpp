#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<vector<int>> points, vector<vector<int>> routes) {
    int answer = 0;
    int cnt = 0;
    int max_len = 0;
    pair<int, int> robot;
    vector<pair<int,int>> robot_route[101];
    for(int i = 0; i < routes.size(); i++) {
        for(int j = 0; j < routes[i].size() - 1; j++) {
            int start = routes[i][j] - 1;
            int end = routes[i][j + 1] - 1;
            robot = {points[start][0], points[start][1]};
            while(robot.first != points[end][0]) {
                robot_route[cnt].push_back(robot);
                if(robot.first < points[end][0]) {
                    robot.first++;
                }
                else {
                    robot.first--;
                }
            }
            while(robot.second != points[end][1]) {
                robot_route[cnt].push_back(robot);
                if(robot.second < points[end][1]) {
                    robot.second++;
                }
                else {
                    robot.second--;
                }
            }
        }
        robot_route[cnt].push_back(robot);
        max_len = max(max_len,  static_cast<int>(robot_route[cnt].size()));
        cnt++;
    }
    for(int i = 0; i < max_len; i++) {
        int map[101][101] = {0,};
        for(int j = 0; j < routes.size(); j++) {
            if(robot_route[j].size() <= i) continue;
            map[robot_route[j][i].first][robot_route[j][i].second]++;
        }
        cout << endl;
        bool flag = false;
        for(int x = 0; x < 101; x++) {
            for(int y = 0; y < 101; y++) {
                if(map[x][y] > 1) {
                    answer++;
                }
            }
        }
     }
    return answer;
}