#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = 0;
    int a=0;
    int N=attacks[attacks.size()-1][0];                  
    int hp=health;
    int inRow=0;
    
    for(int i=1;i<=N;i++){
        // 공격!
        if(attacks[a][0] == i){
            hp-=attacks[a][1];
            inRow=0;
            a++;
            
            if(hp<=0){
                return -1;
            }
            continue;
        }
        
        int plused=bandage[1]; // x
        inRow++;
        if(inRow == bandage[0]){ // t
            inRow=0;
            plused+=bandage[2]; // y
        }
        
        hp+=plused;
        if(hp > health){
            hp=health;
        }
    }
    answer=hp;
    return answer;
}