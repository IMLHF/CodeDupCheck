#include <iostream>

#define MAX_LEN 1300000
#define MIN_INT -9999999
using namespace std;

int counter[MAX_LEN];
int FindMaxInArray(int a[]){
    int m = MIN_INT;
    int j;
    for(int i; i < MAX_LEN ; i++){
        if (a[i] > m){
             m = a[i];
             j = i;
        }

    }
    cout << j <<endl << m << endl;
    return j;
}
int main()
{
    int N;
    int t;

    cin >> N;
    while(N--){
        cin >> t;
        counter[t] ++;
    }
    int k = FindMaxInArray(counter);
    //cout << k <<endl << counter[k];
    return 0;
}


/***************************************************
User name: zhxw150229朝鲁蒙
Result: Accepted
Take time: 464ms
Take Memory: 1188KB
Submit time: 2018-04-03 14:24:06
****************************************************/