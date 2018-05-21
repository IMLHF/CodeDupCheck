#include <iostream>
#include <stdlib.h>
using namespace std;
int num[1300000];
int compare(const void *a, const void *b)
{

    return *(int *)a-*(int *)b;
}

int main()
{
   int n;

   cin>>n;
    for(int i=n-1;i>=0;i--)
    {
        cin>>num[i];
    }

    qsort(num,n,sizeof(int),compare);
    int tp=num[0];
    int tm=1;
    int m=1;
    int p=num[0];

    for(int i=1;i<n;i++)
    {

        if(num[i]==tp)
        {
            tm++;
        }
        else if(tm>m)
        {

            m=tm;
            tm=1;
            p=tp;
            tp=num[i];
        }
        else
        {
            tm=1;
            tp=num[i];
        }
    }
    cout<<p<<endl<<m;
    return 0;

}



/***************************************************
User name: zhxw150142王健
Result: Accepted
Take time: 668ms
Take Memory: 3676KB
Submit time: 2018-04-03 14:27:27
****************************************************/