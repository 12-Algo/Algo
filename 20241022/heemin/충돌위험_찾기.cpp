#include <string>
#include <vector>
#include <iostream>
#include <map>
using namespace std;

struct Robot{
    int x;
    int y;
    int next;
};

int solution(vector<vector<int>> points, vector<vector<int>> routes) {
    int answer = 0;
    vector<Robot> robots;
    map<pair<int,int> , int> m;
    
    for(int i=0;i<routes.size();i++){
        int first_x=points[routes[i][0]-1][0];
        int first_y=points[routes[i][0]-1][1];
        
        robots.push_back({first_x,first_y, 1});
        m[{first_x,first_y}]++;
    }
    
     for(map<pair<int,int>,int>::iterator iter = m.begin(); iter!=m.end(); iter++){
        if(iter->second > 1){
            answer++;
        }
    }
    
    int total=routes.size();
    
    while(true){
        
        if(total <= 0) break;
        
        m.clear();
        for(int i=0;i<routes.size();i++){
            
            if(robots[i].next == -1){
                continue;
            }
            
            int tx=points[routes[i][robots[i].next]-1][0];
            int ty=points[routes[i][robots[i].next]-1][1];
            
            if(robots[i].x != tx){
                robots[i].x < tx ? robots[i].x++ : robots[i].x--;
            }
            else if(robots[i].y != ty){
                robots[i].y < ty ? robots[i].y++ : robots[i].y--;
            }
            
            if(robots[i].x == tx && robots[i].y == ty){
                robots[i].next++;
                
                if(robots[i].next >= routes[i].size()){
                    robots[i].next=-1;
                    total--;
                }
            }            
            m[{robots[i].x, robots[i].y}]++;            
        }
        
        for(map<pair<int,int>,int>::iterator iter = m.begin(); iter!=m.end(); iter++){
            if(iter->second > 1){
                answer++;
            }
        }
    }
    return answer;
}