#include<iostream>
#include<stdio.h>
#include<bits/stdc++.h>
using namespace std;

int a[1000001];


int main()
{
    int n;
    int x;
    int i;
    scanf("%d",&n);
    memset(a,0,sizeof(a));
    while(n--)
    {
        scanf("%d",&x);
        a[x]++;//桶排
    }
    int ant = 0;

    for(i =0;i<1000001;i++)
    {
        if(a[ant]<a[i])
            ant = i;

    }

    printf("%d\n%d\n",ant,a[ant]);
        return 0;

}


/***************************************************
User name: zhxw150112苏金库
Result: Accepted
Take time: 192ms
Take Memory: 2064KB
Submit time: 2018-04-07 15:01:42
****************************************************/