#include <stdio.h>
int main()
{
    int n, m, t;
    long long int f[100000];
    while(~scanf("%d %d", &n, &m))
    {
        int i, minid;
        long long int min;
        for(i = 0;i < n;i++)
        {
            scanf("%lld", &f[i]);
        }
        t = n - m;
        while(t--)
        {
            min = f[0] + f[1];
            minid = 1;
            for(i = 2;i < n;i++)
            {
                if(min > f[i - 1] + f[i])
                {
                    min = f[i - 1] + f[i];
                    minid = i;
                }
            }
            f[minid - 1] = min;
            for(i = minid;i < n - 1;i++)
            {
                f[i] = f[i + 1];
            }
            n--;
        }
        long long int max = 0;
        for(i = 0;i < n;i++)
        {
            if(max < f[i])
                max = f[i];
        }
        printf("%lld\n", max);
    }
    return 0;
}


/***************************************************
User name: 三少
Result: Wrong Answer
Take time: 0ms
Take Memory: 112KB
Submit time: 2017-02-18 10:07:00
****************************************************/