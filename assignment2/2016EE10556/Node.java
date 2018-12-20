public class Node 
{
	Node  next;
	Node prev;
	int start,end ;

public Node ()
{ this(0,0,null,null); 


}
public Node (int s,int e,Node n,Node p)
{
start=s;
end =e;
next=n;
prev=p;

}
public Node next(){return next;}
public Node prev(){return prev;}
public int getstart(){return start;}
public int getend(){return end;}
public void setstart(int a){start=a;}
public void setend(int a){end=a;}
public void setnext(Node n){next =n;}
public  void setprev(Node p){prev =p;}
}
