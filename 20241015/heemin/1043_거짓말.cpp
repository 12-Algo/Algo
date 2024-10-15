#include<iostream>
#include<vector>
#define MAX 51
using namespace std;

int n,m;
vector<int> truth;
vector<vector<int>> party;
int parent[MAX];

void init(){
    for(int i=1;i<=n;i++){
        parent[i]=i;
    }
}

int find(int x){
    if(x==parent[x]){
        return x;
    }
    return parent[x]=find(parent[x]);
}

void _union(int x, int y){
    int rx=find(x);
    int ry=find(y);

    if(rx <= ry){
        parent[ry]=rx;
    }
    else{
        parent[rx]=ry;
    }
}

int main(){
    cin >> n >> m;
    int total;
    cin >> total;

    init();

    if(total==0){
        cout << m << "\n";
        return 0;
    }

    int person;
    cin >> person;
    truth.push_back(person);
    
    for(int i=0;i<total-1;i++){
        int num;
        cin >> num;
        truth.push_back(num);
        _union(person,num);
    }

    for(int i=0;i<m;i++){
        int num;
        cin >> num;

        vector<int> tmp;
        for(int j=0;j<num;j++){
            int a;
            cin >> a;
            tmp.push_back(a);
        }
        party.push_back(tmp);
    }  

    for(int i=0;i<party.size();i++){
        int first=party[i][0];

        for(int j=1;j<party[i].size();j++){
            _union(first,party[i][j]);
        }
    }

    int cnt=0;
    for(int i=0;i<party.size();i++){
        if(find(party[i][0]) != find(truth[0])){
            cnt++;
        }
    }

    cout << cnt << "\n";
}
