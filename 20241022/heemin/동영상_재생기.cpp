#include <string>
#include <vector>
#include <iostream>
using namespace std;

int split(string s){    
    return stoi(s.substr(0,2))*100 + stoi(s.substr(3));
}

int prev(int time){
    int min=time/100;
    int sec=time%100;
        
    sec-=10;
    
    if(sec<0){
        min--;
        sec+=60;
    }
    if(min<0){
        return 0;
    }
    
    return min*100+sec;
}

int next(int time ,int video){
    int min=time/100;
    int sec=time%100;
    
    sec+=10;
    
    if(sec > 60){
        min++;
        sec-=60;
    }
    
    if(min*100+sec > video){
        return video;
    }
    
    return min*100+sec;
}

int isOpening(int opening_start, int opening_end, int position){    
    if(position >= opening_start && position <= opening_end){
        return opening_end;
    }
    return position;
}

string getAnswer(int answer){
    string ret="";
    
    int min=answer/100;
    int sec=answer%100;

    min<10 ? ret=ret+"0"+to_string(min)+":" : ret=ret+to_string(min)+":";    
    sec<10 ? ret=ret+"0"+to_string(sec) : ret=ret+to_string(sec);
    
    return ret;
}

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {
    string answer = "";
    
    int video=split(video_len);
    int position=split(pos);
    int opening_start = split(op_start);
    int opening_end = split(op_end);
    
    position= isOpening(opening_start, opening_end, position);
    
    for(int i=0;i<commands.size();i++){
        string com=commands[i];
        
        if(com=="prev"){
            position = prev(position);
        }
        else if(com=="next"){
            position = next(position, video);
        }
         position = isOpening(opening_start, opening_end, position);
    }
    
    answer=getAnswer(position);
    return answer;
}