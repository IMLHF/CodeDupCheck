#include<iostream>
#include<algorithm>
using namespace std;
int main(void)
{
    int n;
    while(cin>>n)
    {
        int *ar = new int[n+3];
        for(int i = 0; i< n; i++)
        {
            cin >> ar[i];
        }
        sort(ar,ar+n);

        int id = 0, ct = 1, tp = 1;
        for(int i = 1; i < n; i++)
        {
            if(ar[i-1] != ar[i])
            {
                if(ct < tp)
                {
                    ct = tp;
                    id = i - 1;
                }
                tp = 1;
            }
            else
                tp++;
        }
        if(ct < tp)
        {
            ct = tp;
            id =  n-1;
        }
        cout<<ar[id]<<endl<<ct<<endl;
        delete [] ar;

    }
    return 0;

}


/***************************************************
User name: zhxw150227袁顺杰
Result: Accepted
Take time: 572ms
Take Memory: 2932KB
Submit time: 2018-04-03 14:31:26
****************************************************/