import java.util.*;
import java.io.*;
public class functiontester
{
Map<String,Integer> map=new HashMap<String,Integer>();
public void add(Vertex u)
{	v.add(u);
	size++;
	map.put(u.s,size);
	percolateup(u);
}
public void percolateup(Vertex u)
{	int a = map.get(u.s);
	while(a>1)
	{if(u.d<v.get(a/2).d)
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
	map.put(u.s,-2);
	v.set(1,v.get(size));
	map.put(v.get(1).s,1);
	v.remove(size);
	size--;
	int a=1;
	while(a<size/2||(a==size/2&&size%2==1))
		{	if(v.get(a).d>v.get(2*a).d||v.get(a).d>v.get(2*a+1).d)
			{if(v.get(2*a+1).d>v.get(2*a).d)
				{swap(a,2*a);
				a=2*a;}
			else
				{swap(a,2*a+1);
				a=2*a+1;}
			}
			else
			break;
		}
	if(a==size/2&&size%2==0)
		if(v.get(a).d>v.get(2*a).d)
			swap(a,2*a);
	return u;
}
public static void main(String [] args)
{
functiontester o = new functiontester();
o.map.put("12345678G",9);
System.out.println(o.map.get(s1));

}	
}