#include <bits/stdc++.h>
using namespace std;

const int maxn = 1e6 + 7;
int a[maxn];

int main() {
    int n, x, ans = 0;
    scanf("%d", &n);
    for(int i = 1; i <= n; i ++) {
        scanf("%d", &x);
        a[x] ++;
        if(a[x] > a[ans] || (a[x] == a[ans] && ans > x)) ans = x;
    }
    printf("%d\n%d\n", ans, a[ans]);
    return 0;
}


/***************************************************
User name: zhxw150209黄衍淇
Result: Accepted
Take time: 192ms
Take Memory: 672KB
Submit time: 2018-04-03 14:17:51
****************************************************/