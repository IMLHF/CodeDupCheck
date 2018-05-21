#include <bits/stdc++.h>
using namespace std;
int a[111111],x,n,m,ans;
int main() {
    cin>>n;
    while(n--) {
        cin>>x;
        a[x]++;
    }
    for(int i=0; i<100000; i++)
        if(a[i]>m)
            m=a[i],ans=i;
    cout<<ans<<endl<<m<<endl;
    return 0;
}


/***************************************************
User name: zhxw150202彭柏皓
Result: Accepted
Take time: 492ms
Take Memory: 592KB
Submit time: 2018-04-03 14:11:50
****************************************************/