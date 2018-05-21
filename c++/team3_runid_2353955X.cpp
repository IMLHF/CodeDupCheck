#include <stdio.h>
#include <stdlib.h>
#define N 112345
int main()
{
    int n, m, max, a[N], i, count;
    double ave;
    while(scanf("%d%d", &n, &m) != EOF)
    {
        int sum = 0;
        for(i = 0; i < n; i++)
        {
            scanf("%d", &a[i]);
            sum += a[i];
        }
        ave = sum * 1.0 / m;
        sum = 0;
        count = 0;
        max = 0;
        i = 0;
        while(i < n)
        {
            if((sum + a[i] - ave) < (ave - sum))
            {
                sum += a[i];
            }
            else
            {
                count++;
                sum = a[i];
            }
            if(sum > max)
            {
                max = sum;
            }
            i++;
        }
        printf("%d\n", max);
    }
    return 0;
}


/***************************************************
User name: team3
Result: Wrong Answer
Take time: 0ms
Take Memory: 116KB
Submit time: 2017-02-18 10:48:31
****************************************************/