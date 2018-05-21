#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int f[100010];

int main()
{
    int n; scanf("%d", &n);
    memset(f, 0, sizeof(f));

    int cur;
    while(n--){
        scanf("%d", &cur);
        f[cur]++;
    }
    int cnt=0;
    int ans;
    for(int i=0; i<=99999; i++){
        if(f[i] > cnt){
            cnt = f[i];
            ans = i;
        }
    }
    printf("%d\n%d\n", ans, cnt);
    return 0;
}

/***************************************************
User name: zxw140226杨尚澄
Result: Accepted
Take time: 200ms
Take Memory: 536KB
Submit time: 2018-03-30 20:41:08
****************************************************/