import java.util.*;
import java.io.*;

public class Anagram
{		int N = 257311;
	List<String> str[]=new ArrayList[N];
	public List<String> anagram;
	String s1;
	String s2;
    int []a;
    int length;
	List <ArrayList<Integer>>mylist;
	List <ArrayList<Integer>>mylist2;
	ArrayList<Integer> sol1;
	ArrayList<Integer> sol2;
	ArrayList<Integer> sol3;
public String sort(String s )
{
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
	return sorted;
}
public void Hashfunction(String s)
{
	if(str[hash(s)]==null)
	str[hash(s)]=new ArrayList<>();
	str[hash(s)].add(s);
}
public int hash(ArrayList b)
{ int hash=1;
	for(int i=0;i<b.size();i++)
hash=(hash*s1.charAt((int)b.get(i)))%N;

return hash;
}
public int hash(String s)
{ int hash=1;
	for(int i=0;i<s.length();i++)
	hash=(hash*s.charAt(i))%N;
	return hash;
}
public boolean presenthash(String b,String c)
{ // this function checks if the two strings present will combined match the given string
	return s2.equals(sort(b+c));
}
public boolean presenthash(String b)
{
	return s2.equals(sort(b));
}
public boolean presenthash(String b,String c,String d)
{
	return s2.equals(sort(b+c+d));
}
public void findAnagram(String s)
{// this function is the one which is called for  finding the anagrams of a given string s
length=s.length();
if(length>12)   // no string of size more than 12 is processed
return;
s1=s;
s2=sort(s1);
anagram=new ArrayList<String>();
permute();
Set<String> set= new HashSet<String>(anagram); // this method will remove any duplicates present in the list anagram
anagram.clear();
anagram.addAll(set);
Collections.sort(anagram);
for(int j=0;j<anagram.size();j++)
		System.out.println(anagram.get(j));
	System.out.println(-1);
		
}
public void permute ()
{// this function just adds the one word , two word and three word anagram of a string
if(str[hash(s1)]!=null)
{  int a1=hash(s1);
	for(int i=0;i<str[a1].size();i++)
if(presenthash(str[a1].get(i)))
	anagram.add(str[a1].get(i));}
findtwo();
findthree();
}

public void findtwo ()
{
	if(length<6)
	return;
int i,j;
i=3;
j=length-3;
while(j>=i)
	{choose(i,j);  //choose different i numbers from 3 to length of string
		i++;
		j--;}
}
public void findthree ()
{if (length==12)
{
choose(3,3,6);
choose(3,4,5);
choose(4,4,4);
}
if(length==11)
{choose(3,3,5);
choose(4,4,3);
}
if(length==10)
choose(3,3,4);
if(length==9)
choose(3,3,3);
		
}



public void choose(int i,int j)
{
// this function finds all possible anagrams of string where the two words consituting it are of length i and j
mylist = new ArrayList<ArrayList<Integer>>();
sol1=new ArrayList<> ();
findCombinations(i,sol1,length,i,mylist);
for(int m=0;m<mylist.size();m++)
	{sol1=mylist.get(m);
	sol2=complement(sol1);
	if(str[hash(sol1)]!=null&&str[hash(sol2)]!=null)
		add(sol1,sol2);
		}
}
public void choose(int i,int j,int k)
{	// this function finds all possible anagrams of string where the three words consituting it are of length i,j and k
		sol1=new ArrayList<>();
		sol2=new ArrayList<>();
		
		mylist = new ArrayList<ArrayList<Integer>>();
	mylist2 = new ArrayList<ArrayList<Integer>>();

	findCombinations(i,sol1,length,i,mylist);
	findCombinations(j,sol2,length-i,j,mylist2);
	

{for(int m=0;m<mylist.size();m++)
		for(int n=0;n<mylist2.size();n++)
		{		sol3=new ArrayList<>();
				sol1= mylist.get(m);
				sol2=reallist(sol1,mylist2.get(n));
				sol3.addAll(sol1);sol3.addAll(sol2);
				
				if(str[hash(sol1)]!=null&&str[hash(sol2)]!=null&&str[hash(complement(sol3))]!=null)
						add(sol1,sol2,complement(sol3));

		}}

}

public void add(ArrayList<Integer> t1,ArrayList<Integer> t2)
{// this function takes an two arraylist as an argument and if their corresponding strings will form the given string then they are added

		int a1=hash(t1); int a2=hash(t2);
for(int i=0;i<str[a1].size();i++)
	for(int j=0;j<str[a2].size();j++)
		{if(presenthash(str[a1].get(i),str[a2].get(j)))
			{anagram.add(str[a1].get(i)+" "+str[a2].get(j));
		if(t1.size()!=t2.size()){anagram.add(str[a2].get(j)+" "+str[a1].get(i));}
			
			}
	}
}
public void add(ArrayList<Integer> t1,ArrayList<Integer> t2,ArrayList<Integer> t3)
{
	int a1=hash(t1); int a2=hash(t2); int a3=hash(t3);
	for(int i=0;i<str[a1].size();i++)
	for(int j=0;j<str[a2].size();j++)
		for(int k=0;k<str[a3].size();k++)
		{if(presenthash(str[a1].get(i),str[a2].get(j),str[a3].get(k)))
			{anagram.add(str[a1].get(i)+" "+str[a2].get(j)+" "+str[a3].get(k));
					if(t2.size()!=t3.size()){anagram.add(str[a3].get(k)+" "+str[a1].get(i)+" "+str[a2].get(j));
					anagram.add(str[a2].get(j)+" "+str[a3].get(k)+" "+str[a1].get(i));
					if(t1.size()!=t2.size()){anagram.add(str[a2].get(j)+" "+str[a1].get(i)+" "+str[a3].get(k));
					anagram.add(str[a3].get(k)+" "+str[a2].get(j)+" "+str[a1].get(i));
					anagram.add(str[a1].get(i)+" "+str[a3].get(k)+" "+str[a2].get(j));}}

					}

	}
}

public void findCombinations(int n,ArrayList<Integer> sol,int length,int nn,List <ArrayList<Integer>>mylist)
  { 
  // this function computes different combinations of sets of length n of an array of given length (say 12)
  if (sol.size() == nn) //stop condition for the recursion
      mylist.add(new ArrayList(sol));
      
   else{ 
   	for (int i=maxelement(sol);i<length;i++)				
      {sol.add(i);
      findCombinations(n-1,sol,length,nn,mylist);
      sol.remove(sol.size()-1); //recursive call
      }
      }
  }

public int maxelement(ArrayList<Integer> a)
{
if(a.size()==0)											// maxelement present in a given arraylist
	return 0;
else
	return
a.get(a.size()-1)+1;
}
public ArrayList<Integer> complement(ArrayList <Integer> a)
{ ArrayList<Integer> b= new ArrayList();				// creates a complement of the arraylist 
int n=a.size();
for(int i=0;i<length;i++)
	if(!a.contains(i))
		b.add(i);
return b;
}
public ArrayList<Integer> reallist(ArrayList<Integer> a,ArrayList<Integer> b)
{
ArrayList<Integer> c=complement(a);						// this takes an arraylist a  and complements it
ArrayList<Integer> d = new ArrayList<>();				
d.addAll(b);
for(int i=0;i<b.size();i++)
d.set(i,c.get((int)b.get(i)));								//sets a new arraylist to the values of c with indexes of values of b
return d;
}
public Anagram(String filename) 
{Scanner s=new Scanner(System.in);
  try{File inFile = new File(filename);           //read from the vocabulary file
       s=new Scanner(inFile);
   }catch(Exception o){System.out.println(o);} 
   	int n = s.nextInt();
	for(int i=0;i<n;i++)
	Hashfunction(s.next());
					
	s.close();
					
}
public static void main(String[] args) {
	Anagram a=new Anagram(args[0]);
Scanner s= new Scanner(System.in);
File inFile = null;

if (0 < args.length) {
  		 inFile = new File(args[1]);
			} 
	else {
  			return;
			}

try{s=new Scanner(inFile);}
catch(Exception o){System.out.println(o);}
int n = s.nextInt();
for(int i=0;i<n;i++)
	{a.findAnagram(s.next());
	}
 
  
}
}
