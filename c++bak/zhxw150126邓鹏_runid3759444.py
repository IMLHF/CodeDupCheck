#include <iostream>
#include <algorithm>
using namespace std;
int main(void)
{
   int n;
   while(cin>>n)
   {
      int *array = new int [n+3];
      for(int i=0; i<n; i++)
         cin>>array[i];

      sort(array,array+n);

      int index = 0,count_max = 1,count_temp = 1;
      for(int i=1; i<n; i++)
         if( array[i-1] != array[i])
         {
	        if( count_max < count_temp )
	        {
	           count_max = count_temp;
	           index = i-1;
	        }
	        count_temp = 1;


         }
	     else
	       count_temp++;

      if( count_max < count_temp )
      {
	     count_max = count_temp;
	     index = n-1;
      }

      cout<<array[index]<<endl<<count_max<<endl;
      delete [] array;
   }
   return 0;
}


/***************************************************
User name: zhxw150126邓鹏
Result: Accepted
Take time: 576ms
Take Memory: 2932KB
Submit time: 2018-04-03 15:23:15
****************************************************/