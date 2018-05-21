#include <iostream>
#include <algorithm>
using namespace std;
int main(void)
{
   int n;
   while(cin>>n)//数据的个数
   {
      int *array = new int [n+3];//多分配3个空间，避免数组越界
      for(int i=0; i<n; i++)
         cin>>array[i];//输入数据

      sort(array,array+n);//升序排列

      int index = 0,count_max = 1,count_temp = 1;//众数下标，重数，临时重数
      for(int i=1; i<n; i++)
         if( array[i-1] != array[i])
         {
            if( count_max < count_temp )
            {
               count_max = count_temp;//记录重数
               index = i-1;//记录众数的下标
            }
            count_temp = 1;//临时重数清零
         }
         else
           count_temp++;

      if( count_max < count_temp )//若众数出现在最后面，则上面无法判断，所以要增加一次判断
      {
         count_max = count_temp;
         index = n-1;//若众数出现在最后面，那么其下标就是n-1
      }
      
      cout<<array[index]<<endl<<count_max<<endl;
      delete [] array;
   }
   return 0;
}


/***************************************************
User name: zhxw150106张鹏
Result: Accepted
Take time: 652ms
Take Memory: 2932KB
Submit time: 2018-04-03 15:35:49
****************************************************/