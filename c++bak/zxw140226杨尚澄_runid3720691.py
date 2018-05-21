#include <stdio.h>
#include <map>
#include <algorithm>

using namespace std;

int main()
{
    int n; scanf("%d", &n);
    map<int, int>ma;
    ma.clear(); int cur;
    while(n--){
        scanf("%d", &cur);
        ma[cur]++;
    }
    map<int, int>::iterator it;
    it = ma.begin();
    int cnt=0, ans;
    while( it!=ma.end() ){
        if(it->second > cnt){
            cnt = it->second;
            ans = it->first;
        }
        it++;
    }
    printf("%d\n%d\n", ans, cnt);
    return 0;
}

/***************************************************
User name: zxw140226杨尚澄
Result: Accepted
Take time: 496ms
Take Memory: 3184KB
Submit time: 2018-03-30 20:36:34
****************************************************/