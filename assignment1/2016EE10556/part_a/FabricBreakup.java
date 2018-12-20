import java.util.*;
import java.io.File;

public class FabricBreakup{
int S[]=new int [100];
int top=-1;
int n=100;
int maxelement;
int maxelementpos;
public int pop()
{
	if(size()==0)
		{
			return -1;
           
		}
	else
	{
int temp=top-maxelementpos;
		top=maxelementpos-1;
if(size()!=0)
newmax();
return temp;

	}
}
public void newmax()
{maxelement=S[0];
for(int i=0;i<=top;i++)
if(maxelement<=S[i])
{	maxelementpos=i;
maxelement=S[i];
}
}
public void push(int element)
{

	if(size()<n-1)
{
S[++top]=element;
if(size()>1)
{if(element>=maxelement)
{
	maxelement=element;
	maxelementpos=top;
}}
else
{maxelement=element;
maxelementpos=top;
}

}
else
{int B[]=new int [2*n];
for(int i=0;i<n;i++)
B[i]=S[i];
S=B;
n=2*n;
push(element);
}
}

public int size()
{
	return top+1;
}

public static void main(String [] args)
{
try
{ int opcode;
FabricBreakup o= new FabricBreakup();

Scanner s=new Scanner(System.in);
File inFile = null;
if (0 < args.length) {
   inFile = new File(args[0]);
} else {
   throw new Exception();
}
s=new Scanner(inFile);
int n,i,value;
n=s.nextInt();
for(int j=1; j<=n;j++)
{i=s.nextInt();
	if(i!=j)
		{System.out.println("input incorrect");

break;
}
opcode=s.nextInt();

if(opcode==1)
	{value=s.nextInt();o.push(value);}
else if(opcode==2)
	System.out.println(i +" "+o.pop());
else
	System.out.println("Enter a valid operation");
}

}
catch(Exception ex)
{
ex.printStackTrace();
}
}
}


