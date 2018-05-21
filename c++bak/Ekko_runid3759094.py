#include <iostream>
#include <cstring>
using namespace std;
int main()
{
    int m, n, x, y = -1, a[100001];
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
User name: Ekko
Result: Accepted
Take time: 472ms
Take Memory: 580KB
Submit time: 2018-04-03 14:24:38
****************************************************/