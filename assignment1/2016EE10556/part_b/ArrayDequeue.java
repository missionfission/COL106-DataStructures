
public class ArrayDequeue implements DequeInterface {
private Object S[]=new Object[1];
int n=1;int front=-1;
int rear=0;
  public void insertFirst(Object o){
  if(n==1)
{Object B[]=new Object[2*n];
  S=B;
front=0;rear=1;
S[front]=o;
n=2*n;
}
else if(front-rear==0)
{Object B[]=new Object[2*n];
for(int i=0;i<n;i++)
B[i]=S[(front+i)%n];
S=B;
front=2*n-1;rear=n;
S[2*n-1]=o;
n=2*n;
}
else
if(front==-1)
{front=0;rear=1;}
else
{front=(front-1+n)%n;}
S[front]=o;
   
  
}
  
public void insertLast(Object o){
if(front==rear)
{Object B[]=new Object[2*n];
for(int i=0;i<n;i++)
B[i]=S[(front+i)%n];
S=B;
rear=n;front=0;
S[rear]=o;
rear++;
n=2*n;}
else
if(front==-1)
front++;
S[rear]=o;
rear=(rear+1)%n;   
    
  }
  
  public Object removeFirst() throws EmptyDequeException{
if(isEmpty())
throw new EmptyDequeException("queue is empty");
else
{int temp=front;
if(size()==1)
{front=-1;rear=0;}
else
front=(front+1)%n;
return S[temp];}
     }
  
  public Object removeLast() throws EmptyDequeException{
if(isEmpty())
throw new EmptyDequeException("queue is empty");
else
{int temp=rear;
if(size()==1)
{front=-1;rear=0;}
else
rear=(rear-1+n)%n;
return S[temp-1];
  }
}
  public Object first() throws EmptyDequeException{
if(isEmpty())
throw new EmptyDequeException("queue is empty");
else 
return S[front];
    
  }
  
  public Object last() throws EmptyDequeException{
if(isEmpty())
throw new EmptyDequeException("queue is empty");
else return S[rear-1];

    
  }
  
  public int size(){
if(!isEmpty())
 { 
return (rear-front+n)%n;
}
else 
return 0;
 }
  public boolean isEmpty(){
if(front==-1)
return true ;
else return false;
   
  }
  public String toString(){
  String queue="[";
  int i;
for(i=0;i<size()-1;i++)
queue+=S[(front+i)%n]+", ";
if(!isEmpty)
queue+=S[(front+i)%n];
queue+="]";
return queue;
  }
  
  
  public static void main(String[] args){
    int  N = 100;
    DequeInterface myDeque = new ArrayDequeue();
    for(int i = 0; i < N; i++) {
      myDeque.insertFirst(i);
      
    }
   
    int size1 = myDeque.size();
    System.out.println("Size: " + size1);
    System.out.println(myDeque.toString());
for(int i = 0; i < N; i++) {
      myDeque.removeFirst();
      
    }

  System.out.println(myDeque.toString());
   
    
  }
}
