#include <iostream>
#include <cstring>
using namespace std;
int main()
{
    int m,n,x,y=-1,a[100005];
    cin>>n;
    memset(a,0,sizeof(a));
    for(int i=0;i<n;i++)
    {
        cin>>m;
        a[m]++;
        if(y<a[m]||(y==a[m]&&x>m))
        {
            y=a[m];
            x=m;
        }
    }
    cout<<x<<endl<<y<<endl;
    return 0;
}


/***************************************************
User name: zhxw150140滕腾
Result: Accepted
Take time: 464ms
Take Memory: 616KB
Submit time: 2018-04-03 14:41:08
****************************************************/