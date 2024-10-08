#include <string>
#include <vector>
#include <unordered_map>
#include <iostream>
#include <algorithm>

using namespace std;

unordered_map<string, int> name_map;
int map[50][50];
int gift_rate[50];
int solution(vector<string> friends, vector<string> gifts) {
    int answer = 0;
    for(int i = 0; i < friends.size(); i++) {
        string temp = friends[i];
        name_map[temp] = i;
    }
    for(int i = 0; i < gifts.size(); i++) {
        string temp = gifts[i];
        string give_friend = "";
        string receive_friend = "";
        int idx = 0;
        for(int j = 0; j < temp.size(); j++) {
            if(temp[j] == ' ') {
            idx = j + 1;
            break;
            }
            give_friend += temp[j];
        }
        for(int j = idx; j < temp.size(); j++) {
            receive_friend += temp[j];
        }
        //cout << give_friend << " " << receive_friend << "\n";
        map[name_map[give_friend]][name_map[receive_friend]]++;
        gift_rate[name_map[give_friend]]++;
        gift_rate[name_map[receive_friend]]--;
    }
    for(int i = 0; i < friends.size(); i++) {
        int cnt = 0;
        for(int j = 0; j < friends.size(); j++) {
            if(i == j) continue;
            if(map[i][j] > map[j][i]) cnt++;
            if(map[i][j] == map[j][i]) {
                if(gift_rate[i] > gift_rate[j]) cnt++;
            }
        }
        answer = max(answer, cnt);
    }
    
    
    
    return answer;
}
