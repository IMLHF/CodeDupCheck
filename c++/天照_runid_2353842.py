#include<stdio.h>

int a[100000];

int f(int l,int n,int m)
{
    int sum = 0;
    int i,j;
    int min,max;
    if( m == 0 )
    {
        min = 0;
        for( i = l; i < n; i++ )
        min += a[i];
        return min;
    }
    for( i = l; i < n; i++ )
    {
         sum = 0;
         for( j = l; j <= i; j++ )
         {
            sum += a[j];
         }
         max = f(l+1,n,m-1);
         if(max < sum)
         max = sum;
         if(i == l)
         {
             min = max;
         }
         else if(min > max)
         min = max;
    }
    return min;
}

int main()
{
    int n,m,i;
    while(~scanf("%d%d",&n,&m))
    {
        for( i = 0; i < n; i++ )
        scanf("%d",a+i);
        int max;
        max = f(0,n,m);
        printf("%d\n",max);
    }
    return 0;
}


/***************************************************
User name: 天照
Result: Time Limit Exceeded
Take time: 1010ms
Take Memory: 0KB
Submit time: 2017-02-18 10:23:44
****************************************************/