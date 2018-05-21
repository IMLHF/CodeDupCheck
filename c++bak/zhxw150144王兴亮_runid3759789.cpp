#include<iostream>
#include<algorithm>
using namespace std;

int main()
{
	int n,i,m=0,s,max=0,t,*a;
	//m标记元素，s统计该元素重数，max标记出现的最大，t表示该重数对应的元素
	scanf("%d",&n);
	
	a=new int[n];

	for(i=0;i<n;i++)
		scanf("%d",&a[i]);
	sort(a,a+n);
	for(i=0;i<n;i++)
	{
		if(m!=a[i])
			s=1; 
		else
			s++;
		m=a[i];
		if(s>max)
		{
			t=m;
			max=s;
		}
	}
	printf("%d\n%d\n",t,max);
	return 0;
}


/***************************************************
User name: zhxw150144王兴亮
Result: Accepted
Take time: 292ms
Take Memory: 2924KB
Submit time: 2018-04-03 17:24:07
****************************************************/