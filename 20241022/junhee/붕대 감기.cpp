#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = 0;
    int prev = 0;
    int hp = health;
    for(int i = 0; i < attacks.size(); i++) {
        int cur = attacks[i][0];
        int temp = cur - prev - 1;
        prev = cur;
        int heal = ((temp / bandage[0]) * ((bandage[0] * bandage[1]) + bandage[2])) + ((temp % bandage[0]) * bandage[1]);
        hp += heal;
        if(hp > health) hp = health;
        hp -= attacks[i][1];
        //cout << hp << " \n"; 
        if(hp <= 0) return -1;
    }
    answer = hp;
    return answer;
}