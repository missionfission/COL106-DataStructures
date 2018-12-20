public class program
{
	public int test(int n, int m)
	{
		int i,hcf=1;
if(m<n)
for (i=1;i<=m;i++)
{
if(m%i==0&&n%i==0)
hcf=i;

}
else
for (i=1;i<=n;i++)
{
if(m%i==0&&n%i==0)
hcf=i;
}

int p=m*n/hcf;
return p;
	}
}
