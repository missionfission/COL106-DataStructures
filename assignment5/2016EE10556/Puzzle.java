import java.util.*;
import java.io.*;

public class Puzzle 
{
Map<String,Integer> map;
Map<String,Integer> defaultmap=new HashMap<String,Integer>();
Writer out;
int cost [] = new int [8];
String output,input;
int size;
Vector<Vertex> v;
private class Vertex
{	int d;
	String s;
	String prev;
	Vertex previous;
	int backnodes;
public Vertex(int d1,String s1,String prevv,Vertex u,int backnodes1)
	{ d=d1;
	  s=s1;
	  prev=prevv;
	  previous=u;
	  backnodes=backnodes1;
	}
}
public void add(Vertex u)
{	v.add(u);
	size++;
	map.put(u.s,size);
	percolateup(u);
}
public void percolateup(Vertex u)
{	int a = map.get(u.s);
	while(a>1)
	{	Vertex u1=v.get(a/2);
		if((u.d<u1.d)||((u.d==u1.d)&&(u.backnodes<u1.backnodes)))
		{ swap(a,a/2);
			a=a/2;
		}
	else
		break;
	}
}
public void swap(int i,int j)
{	Vertex temp=v.get(i);
	v.set(i,v.get(j));
	v.set(j,temp);
	map.put(v.get(i).s,i);
	map.put(v.get(j).s,j);
}
public Vertex min()
{	Vertex u=v.get(1);
	v.set(1,v.get(size));
	map.put(v.get(1).s,1);
	map.put(u.s,-2);
	v.remove(size);
	size--;
	int a=1;
	while(a<size/2||(a==size/2&&size%2==1))
		{	Vertex a1=v.get(a);
			Vertex u1=v.get(2*a);
			Vertex u2=v.get(2*a+1);
			if(a1.d>u1.d||a1.d>u2.d)
			{if(u2.d>u1.d||((u1.d==u2.d)&&(u2.backnodes>u1.backnodes)))
				{swap(a,2*a);
				a=2*a;}
			else
				{swap(a,2*a+1);
				a=2*a+1;}
			}
			else
			if(a1.d==u1.d||a1.d==u2.d)
				{if((u2.d==u1.d)&&((a1.backnodes>u1.backnodes)||(a1.backnodes>u2.backnodes)))
					{if(u2.backnodes>u1.backnodes)
						{swap(a,2*a);
							a=2*a;}
					else
						{swap(a,2*a+1);
							a=2*a+1;}}
				else
					{if(a1.d==u1.d&&a1.backnodes>u1.backnodes)
						swap(a,2*a);

					else
						if(a1.d==u2.d&&a1.backnodes>u2.backnodes)
						swap(a,2*a+1);

					else
						break;}

				}
			
			else
				break;
	}
	if(a==size/2&&size%2==0)
		{if(v.get(a).d>v.get(2*a).d)
			swap(a,2*a);
		else
			if((v.get(a).d==v.get(2*a).d)&&v.get(a).backnodes>v.get(2*a).backnodes)
				swap(a,2*a);}	

	return u;
}
public boolean empty()
{return size==0; }
public void solvePuzzle(String [] s,Writer writer) throws Exception
{	out=writer;
	map=new HashMap<String,Integer>(defaultmap);
	size=0;
	v=new Vector<Vertex>();
	input=s[0];
	output=s[1];
	algorithm();
}
public void permute(String str, int l, int r)
{    if (l == r)
    defaultmap.put(str,-1);
    else
    {
      for (int i = l; i <=r; i++)
        {
            str = swap(l,i,str);
            permute(str,l+1,r);
            str = swap(l,i,str);
        }
    }
}
public void algorithm() throws Exception
{	v.add(new Vertex(-1,output,null,null,0));
	add(new Vertex(0,input,null,null,0));
	if(inversions(input)!=inversions(output))
	{out.write(-1+" "+-1);
	out.write("\n");
		return;}
	while(!empty())
		{	Vertex u = min();
			if(u.s.equals(output))
			{print(u);break;}
			addneighbours(u);
			
		}
	
}
public int inversions(String s)
{
int k=s.indexOf("G");
char [] c=s.toCharArray();
int count=0;
for(int i=0;i<8;i++)
	for(int j=i+1;j<9;j++)
		if(c[j]<c[i]) count++;
return (count-k)%2;
}
public void addneighbours(Vertex u)
{
	String s1,prev;
	int d;
	int i= u.s.indexOf("G");
	char[] c =u.s.toCharArray();
	if(i/3<2)
		{	s1=swap(i,i+3,u.s);
			 d=cost[c[i+3]-49];
			prev=c[i+3]+"U ";
			if(map.get(s1)!=-2)  // if it is in the cloud
			{addneighbours2(s1,u,d,prev);}
		}
	if(i/3>0)
		{	s1=swap(i,i-3,u.s);	
			 d=cost[c[i-3]-49];
			 prev=c[i-3]+"D ";
			if(map.get(s1)!=-2)  // if it not in the cloud
				{addneighbours2(s1,u,d,prev);}
		}			
	if(i%3!=2)
		{	s1=swap(i,i+1,u.s);
			d=cost[c[i+1]-49];
			prev=c[i+1]+"L ";
			if(map.get(s1)!=-2)  // if it not in the cloud
				{addneighbours2(s1,u,d,prev);}
		}
	if(i%3!=0)
		{	s1=swap(i,i-1,u.s);
			d=cost[c[i-1]-49];
			prev=c[i-1]+"R ";
			if(map.get(s1)!=-2)  // if it not in the cloud
				{addneighbours2(s1,u,d,prev);}	
		}
}
public void addneighbours2(String s1,Vertex u,int d,String prev)
{Vertex u1;
if(map.get(s1)!=-1)   // present in the heap
	{u1=v.get(map.get(s1));		
		if((u1.d>u.d+d)||((u1.d==u.d+d)&&(u1.backnodes-u.backnodes>1)))				
		{u1.d=u.d+d;
		u1.prev=prev;
		u1.backnodes=u.backnodes+1;
		u1.previous=u;
		percolateup(u1);}
			}
else	
	{u1=new Vertex(u.d+d,s1,prev,u,u.backnodes+1);
	add(u1);}

}
public String swap(int i,int j,String s)
{	char[] c =s.toCharArray();
	char temp = c[i];
	c[i] = c[j];
	c[j] = temp;
	String swappedString = new String(c);
	return swappedString;
}
public void print(Vertex u) throws Exception
{	Vertex u1=u;
	int count =0;
	StringBuilder answer=new StringBuilder();	
	while(u1.previous!=null)
		{
		answer.insert(0,u1.prev);
		u1=u1.previous;
		count++;
		}
	out.write(count+" "+u.d);out.write("\n");
	out.write(answer.toString());
	
}
public static void main(String [] args)
{
Puzzle p=new Puzzle();
p.permute("12345678G",0,8);
BufferedReader s;
File inFile = null;

if (0 < args.length) {
  		 inFile = new File(args[0]);
			} 
	else {
  			return;
			}
try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(args[1]), "utf-8"))) {
   


try{
	s = new BufferedReader(new FileReader(inFile));
	String line=s.readLine();
	int n =Integer.parseInt(line);
	for(int i=0;i<n;i++)
		{	line=s.readLine();
			String words[]=line.split(" ");
			line =s.readLine();
			String[] strs = line.trim().split("\\s+");
			for(int j=0;j<8;j++)
			p.cost[j]=Integer.parseInt(strs[j]);
			p.solvePuzzle(words,writer);writer.write("\n");}
}
catch(Exception o){}
 }catch(Exception o){ }
}
}