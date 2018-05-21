#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main()
{
   int i,n,m,k[100005];
   while (~scanf("%d %d",&n,&m))
   {
   int low=0,high=0,mid;
      for(i=0;i<n;i++)
      {
         scanf("%d",&k[i]);
         low=(k[i]>low?k[i]:low);
         high+=k[i];
      }
      while (low<=high)
      {
          mid=(low+high)/2;
          int num=1,sum=0;
          for (i=0;i<n;i++)
          {
             if (sum+k[i]<=mid)
             {
               sum+=k[i];
             }
             else
             {
             num++;
                sum=k[i];
             }
             if (num<=m)
             {
                high=mid-1;
             }
             else
             {
                 low=mid+1;
             }
          }
      }
      printf("%d\n",mid);
   }
   return 0;
}


/***************************************************
User name: 老坛酸菜
Result: Wrong Answer
Take time: 0ms
Take Memory: 108KB
Submit time: 2017-02-18 10:41:37
****************************************************/