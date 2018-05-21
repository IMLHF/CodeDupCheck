#include<bits/stdc++.h>
using namespace std;
const int N=13e5+10;
const int inf=1e9;
int n;
int num[N];
int ans=0;
int sum=0;
int count_mid(int mid,int l,int r){//统计中间数的重数

   int cnt=0;
   for(int i=l;i<=r;i++)
     if(num[i]==mid)
       cnt++;
   return cnt;
}
int get_start(int mid,int l,int r){///找到开始的头
   return lower_bound(num+1,num+r+1,mid)-num;

}
void get_ans(int l,int r){
   if(l==r){
    if(1>sum || (1==sum&&num[l]<ans)){
        sum=1;
        ans=num[l];
    }
    return;
   }
   int mid=(l+r)>>1;
   int key=num[mid];
   int tsum=count_mid(key,l,r);
   
   int pos=get_start(key,l,r);
   
   if(tsum>sum || ( tsum==sum && key < ans)){
      sum=tsum;
      ans=key;
   }
   if(pos >= sum) get_ans(l,pos);///左边找。
   if(r- (pos+tsum-1) > sum)
    get_ans(pos+tsum,r);///右边找。

}

int main(){
      cin>>n;
      for(int i=0;i<n;i++)
        scanf("%d",&num[i]);
      sort(num,num+n);
      get_ans(0,n-1);
      printf("%d\n%d\n",ans,sum);
      return 0;
}


/***************************************************
User name: zhxw150143鞠明杭
Result: Accepted
Take time: 312ms
Take Memory: 896KB
Submit time: 2018-04-08 21:16:42
****************************************************/