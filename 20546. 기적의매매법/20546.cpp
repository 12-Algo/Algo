#include<iostream>
#define MAX 14
using namespace std;

int main(){
    int cash;
    cin >> cash;

    int price[14];
    for(int i=0;i<14;i++){
        cin >> price[i];
    }

    // BNP
    int A_cash=cash; 
    int A_stock_num=0;
    
    for(int i=0;i<13;i++){

        // 못 사는 경우
        if(A_cash < price[i]){
            continue;
        }

        int count= A_cash/price[i]; // 매수 가능 최대 개수
        A_stock_num+=count; 
        A_cash-=count*price[i];
    }

    int A_final=A_cash + A_stock_num*price[13] ;

    // TIMING
    int B_cash=cash;
    int B_stock_num=0;
    int continued[14]={0,};

    for(int i=3;i<13;i++){

        // 계속 하락한 경우 & 계속 상승한 경우
        if(price[i-3] > price[i-2] && price[i-2]>price[i-1] && price[i-1] > price[i] ){
            continued[i]=-1;
        }
        else if(price[i-3] < price[i-2] && price[i-2] < price[i-1] && price[i-1] < price[i]){
            continued[i]=1;
        }
    }

    for(int i=0;i<13;i++){
        if(continued[i]==0){
            continue;
        }

        // 매수 타이밍
        if(continued[i]==-1){
            // 매수 불가
            if(B_cash < price[i]){
                continue;
            }

            // 풀매수
            int count=B_cash/price[i]; // 매수 가능 최대 개수
            B_cash-=count*price[i]; 
            B_stock_num+=count;

            continue;
        }

        // 매도 타이밍
        if(continued[i]==1){

            // 풀매도
            B_cash+=B_stock_num*price[i];
            B_stock_num=0;
        }
    }

    int B_final=B_cash+B_stock_num*price[13];

    if(A_final > B_final){
        cout << "BNP\n";
    }
    else if(A_final < B_final){
        cout << "TIMING\n";
    }
    else{
        cout << "SAMESAME\n";
    }
}