#include <bits/stdc++.h>
using namespace std;
int main()
{
    int a[100000];
    for(int i=0;i<100000;i++)
        a[i]=0;
    int n;
    cin>>n;
    int m;
    while(n--)
    {
        cin>>m;
        a[m]++;
    }
        int max=a[0],ans=1;
    for(int i=1;i<100000;i++)
        if(a[i]>max)
    {
        max=a[i];
        ans=i;
    }
    cout<<ans<<endl<<max;
}


/***************************************************
User name: zhxw150130居自强
Result: Accepted
Take time: 472ms
Take Memory: 588KB
Submit time: 2018-04-03 15:34:20
****************************************************/