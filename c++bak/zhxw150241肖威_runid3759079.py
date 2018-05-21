#include<bits/stdc++.h>
using namespace std;
int a[1300000];
int main()
{
    int n;

    memset(a,0,sizeof(a));
    scanf("%d",&n);
    for(int i=0;i<n;i++)
    {
        int k;
        scanf("%d",&k);
        a[k]++;
    }
    int m1=-1,m2;
    for(int i=0;i<1300000;i++)
    {
        if(a[i]>m1)
        {
            m1=a[i];
            m2=i;
        }
    }
    printf("%d\n%d\n",m2,m1);
    return 0;
}












/***************************************************
User name: zhxw150241肖威
Result: Accepted
Take time: 224ms
Take Memory: 1192KB
Submit time: 2018-04-03 14:19:33
****************************************************/