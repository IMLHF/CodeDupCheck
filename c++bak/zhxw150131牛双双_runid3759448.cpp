#include<iostream>
#include<cstdio>
#define N 100010
using namespace std;

int a[N];

int main()
{

    int n;
    scanf("%d",&n);
    int i,t;
    for(i=0; i<N; i++) a[i]=0;
    for(i=0; i<n; i++)
    {
        scanf("%d",&t);
        a[t]++;
    }
    int p,ans=0;
    for(i=0; i<N; i++)
    {
        if(a[i]>ans)
        {
            p=i;
            ans=a[i];
        }
    }
    printf("%d\n",p);
    printf("%d\n",ans);
    return 0;
}


/***************************************************
User name: zhxw150131牛双双
Result: Accepted
Take time: 192ms
Take Memory: 640KB
Submit time: 2018-04-03 15:23:52
****************************************************/