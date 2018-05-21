#include <bits/stdc++.h>

using namespace std;
map<int,int> a;
int main()
{
    int n;
    cin>>n;
    int ans=-1,s=-1;
    for(int i=0;i<n;i++)
    {
        int t;
        cin>>t;
        a[t]++;
        if(a[t]>s||(a[t]==s&&t<ans)){
            ans = t;
            s=a[t];
        }
    }
    cout<<ans<<endl<<s<<endl;
    return 0;
}


/***************************************************
User name: zhxw150102张丰彦
Result: Accepted
Take time: 1016ms
Take Memory: 3228KB
Submit time: 2018-04-03 14:27:24
****************************************************/