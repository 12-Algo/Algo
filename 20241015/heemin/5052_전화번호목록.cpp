#include<iostream>
#include<algorithm>
#include<vector>
#include<string>
using namespace std;

int n;

int main(){
    int t;
    cin >> t;

    for(int T=0;T<t;T++){
        cin >> n;

        vector<string> list;
        for(int i=0;i<n;i++){
            string s;
            cin >> s;
            list.push_back(s);
        }

        sort(list.begin(),list.end());

        bool possible=true;

        for(int i=0;i<n-1;i++){
            string s=list[i];
            string s2=list[i+1];

            if(s.length() >= s2.length()){
                continue;
            }

            if(s2.substr(0,s.length()) == s){
                possible=false;
                break;
            }

        }
        possible ? cout << "YES\n" : cout << "NO\n";
    }
}