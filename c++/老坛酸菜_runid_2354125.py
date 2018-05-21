#include<stdio.h>
int low,high,mid,i;
int n,m, a;
int k[1000005];
void f(int low,int high)
{
  if (low<=high)
  {
     mid=low+(high-low)/2;
     int count=1,sum=0;
     for (i=0;i<n;i++)
     {
        if (sum+k[i]<=mid)
        {sum+=k[i];}
        else
        {count++;sum=k[i];}
     }
     if (count>m)
     f(mid+1,high);
     else
     {a=mid;
     f(low,mid-1);}
  }
}
int main()
{
   while (~scanf("%d %d",&n,&m))
   {
     low=high=0;
     for (i=0;i<n;i++)
     {
        scanf("%d",&k[i]);
        if (k[i]>low)
        low=k[i];
        high+=k[i];
     }
     f(low,high);
     printf("%d\n",a);
   }
   return 0;
}

/***************************************************
User name: 老坛酸菜
Result: Accepted
Take time: 0ms
Take Memory: 108KB
Submit time: 2017-02-18 11:25:33
****************************************************/