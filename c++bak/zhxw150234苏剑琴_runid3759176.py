#include<bits/stdc++.h>
using namespace std;
int main(void)
{
    int n;
       while(cin>>n){
        int *a = new int[n+3];
        for(int i=0;i<n;i++)
            cin>>a[i];
        sort(a,a+n);
        int index=0,count_max=1,count_temp=1;
        for(int i=1;i<n;i++)
            if(a[i-1]!=a[i])
        {
            if(count_max<count_temp)
            {
                count_max=count_temp;
                index=i-1;
            }
            count_temp=1;
        }
        else
            count_temp++;
        if(count_max<count_temp)
        {
            count_max=count_temp;
            index=n-1;
        }
        cout<<a[index]<<endl<<count_max<<endl;
        delete [] a;
       }
       return 0;
}


/***************************************************
User name: zhxw150234苏剑琴
Result: Accepted
Take time: 476ms
Take Memory: 2936KB
Submit time: 2018-04-03 14:40:36
****************************************************/