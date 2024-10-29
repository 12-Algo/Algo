#include <string>
#include <vector>
#include <iostream>


using namespace std;
int len = 0;
int o_pos = 0;
int o_start = 0;
int o_end = 0;

int parseTime(string time) {
    string hour = time.substr(0, 2);
    string minute = time.substr(3, 2);
    int t = stoi(hour) * 60 + stoi(minute);
    return t;
}

string parseString(int time) {
    int h = time / 60;
    int m = time % 60;
    string t = "";
    if(h < 10) t += "0";
    t += to_string(h);
    t += ":";
    if(m < 10) t += "0";
    t += to_string(m);
    return t;
}

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {
    string answer = "";
    len = parseTime(video_len);
    o_pos = parseTime(pos);
    o_start = parseTime(op_start);
    o_end = parseTime(op_end);
    for(int i = 0; i < commands.size(); i++) {
        if(o_start <= o_pos && o_pos <= o_end) o_pos = o_end;
        if(commands[i] == "prev") {
            o_pos -= 10;
            if(o_pos < 0) o_pos = 0;
        }
        if(commands[i] == "next") {
            o_pos += 10;
            if(o_pos > len) o_pos = len;
        }
        if(o_start <= o_pos && o_pos <= o_end) o_pos = o_end;
    }
    return parseString(o_pos);
}