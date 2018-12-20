import java.util.*;
public class functiontester
{String s1="yahajfkslkhushaliamkingokbyetata";
int N = 67121;
int length =9;
//public List <ArrayList<Integer>> mylist = new ArrayList<ArrayList<Integer>>();
public ArrayList<Integer> complement(ArrayList <Integer> a)
{ ArrayList<Integer> b= new ArrayList();
int n=a.size();
for(int i=0;i<length;i++)
	if(!a.contains(i))
		b.add(i);	
return b;
}
public String sort(String s )
{
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
	return sorted;
}
public boolean presenthash(String b,String c)
{
	return sort(s1).contains(sort(b+c));


}
public int hash(String s)
{ int hash=1;
	for(int i=0;i<s.length();i++)
hash=(hash*s.charAt(i))%N;
return hash;
}
public int hash(ArrayList b)
{ int hash=1;
	for(int i=0;i<b.size();i++)
hash=(hash*(int)b.get(i))%N;
return hash;
}
public void findCombinations(int n,ArrayList<Integer> sol,int length,int nn,List <ArrayList<Integer>> mylist)
  { if (sol.size() == nn) //stop condition for the recursion
      mylist.add(new ArrayList(sol));
      
   else{ for (int i=maxelement(sol);i<length;i++)
      {sol.add(i);
      findCombinations(n-1,sol,length,nn,mylist);
      sol.remove(sol.size()-1); //recursive call
      }
      }
  }  public int maxelement(List<Integer> a)
{
if(a.size()==0)
	return 0;
else
	return
a.get(a.size()-1)+1;
}
public void findtwo ()
{
	if(length<6)
	return;
int i,j;
i=3;
j=length-3;
while(j>=i)
	{choose(i,j);
		i++;
		j--;}
}

public ArrayList<Integer> reallist(ArrayList<Integer> a,ArrayList<Integer> b)
{
ArrayList<Integer> c=new ArrayList<>();;
for(int i=0;i<length;i++)
c.add(i,i);
for(int i=0;i<a.size();i++)
c.remove(a.get(i));
for(int i=0;i<b.size();i++)
b.set(i,c.get((int)b.get(i)));
return b;}
public void choose(int i,int j)
{

List <ArrayList<Integer>>mylist = new ArrayList<ArrayList<Integer>>();
ArrayList<Integer> sol1=new ArrayList<> ();
ArrayList<Integer> sol2;
findCombinations(i,sol1,length,i,mylist);
for(int m=0;i<mylist.size();i++)
	{sol1=mylist.remove(0);
	sol2=complement(sol1);
	System.out.println(sol1);System.out.println(sol2);
	//add(sol1,sol2);
}
}
public static void main(String [] args)
{ArrayList <Integer> a= new ArrayList();
	functiontester c=new functiontester();
	//ArrayList<Integer> a = new ArrayList<>();
	/*ArrayList<Integer> b = new ArrayList<>();
	for(int i=0;i<2;i++)
		a.add(i,i);
	for(int i=0;i<3;i++)
		b.add(i,i);
	System.out.println(c.reallist(a,b));
	*/
	List <ArrayList<Integer>>mylist = new ArrayList<ArrayList<Integer>>();
	c.findCombinations(3,a,8,3,mylist);	
	System.out.println(mylist.get(0));
	System.out.println(mylist.get(0));
	
}


}
