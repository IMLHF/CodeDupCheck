#include <iostream>
#include<bits/stdc++.h>
using namespace std;

int a[100010];

int main()
{
    int n;
    cin>>n;
    int num;
    for(int i=0;i<n;i++){
        scanf("%d",&num);
        a[num]++;
    }
    int Max=a[0];
    int pos=0;
    for(int i=1;i<=99999;i++){
        if(a[i]>Max)
            Max=a[i],pos=i;
    }
    printf("%d\n%d",pos,Max);
    return 0;
}


/***************************************************
User name: zhxw150143鞠明杭
Result: Accepted
Take time: 192ms
Take Memory: 600KB
Submit time: 2018-04-01 21:47:19
****************************************************/