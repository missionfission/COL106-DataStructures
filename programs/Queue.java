
public class Queue implements queue
{

int front=-1,rear=0;
int n=100;
Object S[]=new Object [100];
public void enqueue (Object elem) throws FullQueueException
{
if(front-rear==0)
throw new FullQueueException("queue is full");
if(front==-1)
front =rear=0;
S[rear]= elem;
rear++;

}

public Object dequeue () throws EmptyQueueException
{
if(front==-1)
	throw new EmptyQueueException("queue is empty");
else 
	{int temp=front;
	if (rear==(front+1)%n)
	    {front=-1;rear=0;}
   	else
	front =(front+1)%n;
	return S[temp];}
}

public Object front() throws EmptyQueueException{
if(front==-1)
	throw new EmptyQueueException("queue is empty");
else 
	return S[front];
}
public int size()
{
return ((rear-front+n)%n);
}
public boolean isempty()
{
if (front==-1)
	return true;
else 
	return false;

}
}