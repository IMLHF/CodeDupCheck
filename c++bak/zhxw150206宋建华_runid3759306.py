#include <bits/stdc++.h>
using namespace std;
int main()
{
	int n;
	int i = 0;
	int mct = 1;
	int ix = 0;
	cin >> n;
	int* a = new int[n];
	for (int i = 0; i < n; i++)
	{
		cin >> a[i];
	}
	sort(a, a + n);
	while (i < n - 1)
	{
		int ct = 1;
		int j;
		for (j = i; j < n - 1; j++)
		{
			if (a[j] == a[j + 1])
			{
				ct++;
			}
			else
			{
				break;
			}
		}
		if (mct < ct)
		{
			mct = ct;
			ix = j;
		}
		++j;
		i = j;
	}
	cout << a[ix] << "\n" << mct << endl;
	return 0;
}


/***************************************************
User name: zhxw150206宋建华
Result: Accepted
Take time: 608ms
Take Memory: 4060KB
Submit time: 2018-04-03 14:59:04
****************************************************/