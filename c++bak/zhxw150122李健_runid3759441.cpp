#include<bits/stdc++.h>
using namespace std;
int a[1000100];
int main()
{
    int n,x;
    scanf("%d",&n);
    memset(a,0,sizeof(a));
    while(n--)
    {
        scanf("%d",&x);
        a[x]++;
    }
    int idx=0;
    for(int i=0;i<1000001;i++)
        if(a[idx]<a[i])
        idx=i;
    printf("%d\n%d\n",idx,a[idx]);
    return 0;
}

/***************************************************
User name: zhxw150122李健
Result: Accepted
Take time: 156ms
Take Memory: 2024KB
Submit time: 2018-04-03 15:23:06
****************************************************/