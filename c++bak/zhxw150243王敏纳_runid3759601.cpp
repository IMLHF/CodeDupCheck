#include <bits/stdc++.h>
using namespace std;
const int maxn = 100000;
const int INF = 0x3f3f3f3f;
int num[maxn],n,x;

int main(){
	scanf("%d",&n);
	memset(num,0,sizeof(num));
	int Max = 0;
	int pos = INF;
	for(int i = 0; i < n; i++){
		scanf("%d",&x);
		num[x]++;
		if(Max == num[x]){
			if(pos > x)
				pos = x;
		}
		else if(Max < num[x]){
			Max = num[x];
			pos = x;
		}
	}
	printf("%d\n",pos);
	printf("%d\n",Max);
	return 0;
}

/***************************************************
User name: zhxw150243王敏纳
Result: Accepted
Take time: 192ms
Take Memory: 592KB
Submit time: 2018-04-03 16:13:48
****************************************************/