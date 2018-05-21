#include <stdio.h>
#include <stdlib.h>
int largest,element,a[1300010];


int cmp(const void *a,const void *b)
{
    return *(int *)a - *(int *)b;
}
int median(int a[],int left,int right)//求中位数
{
    return a[(left + right)/2];
}
void sqlit(int a[],int middle,int left,int right,int *ll,int *rr)//计算中位数个数最左端和最右端
{
    int i,j;
    i = j = (left + right) / 2;
    while(a[--i] == middle);//寻找左端与中位数第一个相等的位置
    *ll = i + 1;//上面的i已经超出中位数，即后退一步
    while(a[++j] == middle);//寻找右端与中位数最后一个相等的位置
    *rr = j - 1;//上面的j已经超出中位数，即后退一步
}
void mode(int left,int right)
{
    int ll,rr;
    int middle = median(a,left,right);//求中位数
    sqlit(a,middle,left,right,&ll,&rr);//分割数组
    if(largest < rr - ll + 1)//寻找最大重数
        {
            largest = rr - ll + 1;
            element = middle;
        }
    if(ll - left > largest)//左边的个数大于中位数的个数从左边再寻找
        mode(left,ll - 1);
    if(right - rr > largest)//右边的个数大于中位数的个数从右边再寻找
        mode(rr + 1,right);
}
int main()
{
    int n,m,i;
    /*
    scanf("%d",&n);
    while(n--)
    {  */
        scanf("%d",&m);
        for(i=0;i<m;i++)
            scanf("%d",&a[i]);
        qsort(a,m,sizeof(a[0]),cmp);
        mode(0,m-1);
        printf("%d\n%d\n",element,largest);

    //}
    return 0;
}


/***************************************************
User name: zxw140226杨尚澄
Result: Accepted
Take time: 396ms
Take Memory: 5672KB
Submit time: 2018-04-03 19:37:25
****************************************************/