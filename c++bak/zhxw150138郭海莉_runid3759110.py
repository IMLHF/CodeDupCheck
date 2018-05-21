#include <iostream>
#include <stdio.h>
#include <string.h>
using namespace std;

int a[100001];
int main()
{
    long long int n,i;
    memset(a,0,sizeof(a));
    scanf("%lld",&n);
    int x,y=-1;
    for(i =0; i<n; i++)
    {
        int m;
        scanf("%d",&m);
        a[m]++;
        if(y<a[m]||y==a[m]&&x>m)
        {
            y = a[m];
            x = m;
        }
    }
    printf("%d\n%d\n",x,y);
    return 0;
}


/***************************************************
User name: zhxw150138郭海莉
Result: Accepted
Take time: 240ms
Take Memory: 584KB
Submit time: 2018-04-03 14:27:28
****************************************************/