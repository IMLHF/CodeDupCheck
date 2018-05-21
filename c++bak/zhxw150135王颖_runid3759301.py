#include <iostream>

using namespace std;
    int num[300000];
int main()
{

    int n, i, cnum=0, znum=0, a;

    cin>>n;
    for(i=0; i<n; i++){
        cin>>a;
        num[a]++;

        if(num[a]>cnum ||(num[a] == cnum && znum>a)){
            cnum = num[a];
            znum = a;
        }
    }
    cout << znum << endl << cnum << endl;
    return 0;
}


/***************************************************
User name: zhxw150135王颖
Result: Accepted
Take time: 488ms
Take Memory: 672KB
Submit time: 2018-04-03 14:57:54
****************************************************/