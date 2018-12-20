class EmptyListException extends RuntimeException{
 EmptyListException(String err)
 {
 	super(err);
 }


}



public class LinkedList{
Node head;
Node tail;
int size;
public LinkedList()
{head=null;
tail=null;
size=0;

}
public void addfirst(int start,int end){
	if(head !=null)
	{	head=new Node(start,end,head,null);
	head.next().setprev(head);
	size++;
}
else
{head=new Node(start,end,null,null);
	
	size++;
	tail=head;
}

}
public void removelast() throws EmptyListException{
	if(size==0)
		throw new EmptyListException("khali hai");
	if(size==1)
		head=null;
	Node temp=tail;
	tail=tail.prev();
	tail.setnext(null);
	size--;
	
	

}
public void addafter(Node current, int start, int end){
	if (current!=null)
	{ Node temp = new Node(start,end,current.next(),current);
		if(current.next()!=null )
			current.next().setprev(temp);
                     else
                       tail=temp;   
		current.setnext(temp);
		size++;
	}
else 
addfirst(start,end);

}
public void removeafter(Node current) throws EmptyListException{
	if(current==null)
	{
removefirst(); return;
	}
	if(current.next()==tail)
	{removelast(); return;}
	if(current==tail)
		throw new EmptyListException("kuch nahi hai aage");
	else
			{ 
		current.setnext(current.next().next());
		current.next().setprev(current);
		size--;
		

	}

}
public void removefirst () throws EmptyListException{
if(size==0)
	throw new EmptyListException("khali hai");
if (size==1)
	tail=null;
head=head.next();
if(head!=null)head.setprev(null);
size--;

}
public void addlast(int start,int end)
{
          if(head!=null)
        {Node temp=new Node(start,end,tail.next(),tail);
	tail.setnext(temp);
	tail=temp;
          size++;
           }
     else
      addfirst(start,end);
}

public boolean isempty(){
if(head==null)
	return true;
else
	return false;

}

public int size ()
{
return size;

}
public Node head()
{return head;

}
public Node tail()
{return tail;
}

public void print ()
{Node p1=head;
if(head==null)
System.out.println("empty");
while(p1!=null)
{System.out.println(p1.getstart()+" "+p1.getend());
p1=p1.next();

}

}




}
