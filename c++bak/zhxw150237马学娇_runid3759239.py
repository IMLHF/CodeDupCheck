#include<bits/stdc++.h>
using namespace std;
int main(){
    int n;
    cin>>n;
    int s[n+3];
    for(int i=0;i<n;i++){
        cin>>s[i];
    }
    sort(s,s+n);
    int index=0,counts=1,temp=1;
    for(int i=1;i<n;i++)
    {
        if(s[i-1]!=s[i]){
            if(counts<temp){
                counts=temp;
                index=i-1;
            }
            temp=1;
        }
        else{temp++;}
    }
     if(counts<temp){
            counts=temp;
            index=n-1;
        }
    cout<<s[index]<<endl<<counts<<endl;
}


/***************************************************
User name: zhxw150237马学娇
Result: Accepted
Take time: 600ms
Take Memory: 2928KB
Submit time: 2018-04-03 14:48:40
****************************************************/