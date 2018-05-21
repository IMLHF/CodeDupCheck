#include"stdio.h"
#include <iostream>
#include <cstring>
using namespace std;
int main()
{
    int m, n, x, y = -1, a[1300001];
    cin>>n;
    memset(a, 0, sizeof(a));
    for (int i = 0; i < n; i++)
    {
        cin>>m;
        a[m]++;
        if (y < a[m] || (y == a[m] && x > m))
        {
            y = a[m];
            x = m;
        }
    }
    cout<<x<<endl<<y<<endl;
    return 0;
}


/***************************************************
User name: zhxw150139许雅丽
Result: Accepted
Take time: 508ms
Take Memory: 3232KB
Submit time: 2018-04-03 14:29:28
****************************************************/