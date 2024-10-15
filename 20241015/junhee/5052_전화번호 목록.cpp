#include <string>
#include <cstring>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int T, n;

int main() {
    cin >> T;
    for(int t = 0; t < T; t++) {
        vector<string> phone_book;
        cin >> n;
        for(int i = 0; i < n; i++) {
            string temp;
            cin >> temp;
            phone_book.push_back(temp);
        }
        sort(phone_book.begin(), phone_book.end());
        bool answer = true;
        for(int i = 0; i < phone_book.size() - 1; i++){
            string temp = phone_book[i + 1].substr(0, phone_book[i].length());
            if(phone_book[i] == temp)
                answer = false;
        }
        if(answer) cout << "YES" << "\n";
        else cout << "NO\n";
    }
    return 0;
}