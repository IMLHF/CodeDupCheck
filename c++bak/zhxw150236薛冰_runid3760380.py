#include <bits/stdc++.h>
#include<iostream>
using namespace std;
int a[1300001];
int sum = 0;
int num = 0;
void QuickSort(int a[], int left, int right)
{
    int i, j, t, key;
    if(left>right)
        return;
    key=a[left];
    i=left;
    j=right;
    while(i!=j)
    {
        while(a[j]>=key&&i<j)
            j--;
        while(a[i]<=key&&i<j)
            i++;
        if(i<j)
        {
            t=a[i];
            a[i]=a[j];
            a[j]=t;
        }
    }
    a[left]=a[i];
    a[i]=key;
    QuickSort(a, left, i-1);
    QuickSort(a, i+1, right);

}

void DAC(int l, int r)
{
    if(l<r)
    {
        int m = (l+r)/2;
        int count1 = 0;
        int count2 = 0;
        int count = 0;
        int k = a[m];
        int ss = sum;
        int nn = num;
        for(int i=m+1; i<=r; i++)
        {
            if(a[i]==a[m]) count1++;
            else break;
        }
        for(int i=m-1; i>=l; i--)
        {
            if(a[i]==a[m]) count2++;
            else break;
        }
        count=count1+count2+1;
        if(count>sum||(count==sum&&a[m]<num)) {
            sum = count;
            num = a[m];
        }

        if(r-m-count1>=count)
            DAC(m+count1+1, r);
        if(m-l-count2>=count)
            DAC(l, m-count2-1);
    }


}

int main()
{
    int n;
    cin>>n;
    for(int i=0; i<n; i++)
        cin>>a[i];
    QuickSort(a, 0, n-1);

    DAC(0, n-1);
    cout<<num<<endl;
    cout<<sum;
}



/***************************************************
User name: zhxw150236薛冰
Result: Accepted
Take time: 600ms
Take Memory: 896KB
Submit time: 2018-04-03 19:23:01
****************************************************/