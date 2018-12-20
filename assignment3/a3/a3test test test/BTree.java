
import java.util.*;
public class BTree<Key extends Comparable<Key>,Value> implements DuplicateBTree<Key,Value> {

	Node <Key,Value> root;
	int size,height,b,M;
  StringBuilder s;

    public BTree(int a) throws bNotEvenException {  /* Initializes an empty b-tree. Assume b is even. */
       if(a%2!=0)
	throw new bNotEvenException();
	else
		{M=b=a;
           size=0;
           height=-1;}
    }

   
    public boolean isEmpty() {

	return size()==0;
        
    }

   
    public int size() {
	return size;
       
    }

 
    public int height() {
        return height;
        
    }
    public boolean isleaf(Node <Key,Value>  b)
    { return b.n[0]==null;

    }

  
 public List<Value> search(Key key) throws IllegalKeyException {
		if (!isEmpty())
        { List<Value> l=new ArrayList<Value>();

                Node <Key,Value> p=root;
                while(p!=null)
                {  
                    
                    if(BinarySearch(p,key,0,p.keys-1,0)==null)   // suppose key is not present  the node
                      

                  {   if (less(key,p.k.a[0])) {
                            p=p.n[0];
                            }                                               // go to the appropriate child Node <Key,Value>
                        else if(less(p.k.a[p.keys-1],key))
                           p=p.n[p.keys];
                           else
                           { for (int i=0;i<p.keys-1;i++)

                        if(less(p.k.a[i],key)&&less(key,p.k.a[i+1]))
                            p=p.n[i+1];
                            }

                        }

                    else
                    {   l.addAll(BinarySearch(p,key,0,p.keys-1,0));
                        if(!isleaf(p)) 
                 {       List<Node> m= SearchChildren(p,key,0,p.keys-1);    // if key is present in this Node <Key,Value>
                   while(m!=null)                                              //if more than one keys are present find all of them

                        {
                            
                            p=m.remove(0);  
                            if(BinarySearch(p,key,0,p.keys-1,0)!=null)
                            l.addAll(BinarySearch(p,key,0,p.keys-1,0));
                             if(!isleaf(p)) m.addAll(SearchChildren(p,key,0,p.keys-1));
                  }
                
                }

           return l;
       }

	     }
       throw new IllegalKeyException();
    }
    throw new IllegalKeyException();
}

        

    public List<Value> BinarySearch(Node <Key,Value> b,Key key,int start,int end,int flag)
    {
        List<Value> l= new ArrayList<Value>();

       if(end>=start)
       { int mid=(start+end)/2;
              int i,j;
              i=j=mid;
        if(b.k.a[mid]==key)
            {   flag=1;
               while(b.k.a[i]==key&&i>=0)
        {       l.add(0,(Value)b.val.a[i]);
                i--;
            }
            j++;
            while(b.k.a[j]==key&&j<b.keys)
            {
                l.add(l.size(),(Value)b.val.a[j]);
                j++;
            }

        }

        else
           { if(less(b.k.a[mid],key))
                    { start=mid+1;

                       return BinarySearch(b,key,start,end,flag);
                    }
            else

                     if(less(key,b.k.a[mid]))
                     {end = mid-1;
                       return BinarySearch(b,key,start,end,flag);

                         }}

       }

       if(flag==1)
        return l;
        else 
        return null;


    }

public List<Node>  SearchChildren(Node <Key,Value>  b,Key key,int start,int end)  
    {
         List<Node> l= new ArrayList<Node>();
       if(end>=start)
       { int mid=(start+end)/2;
                int i=mid;
                int j=mid;
        if(b.k.a[mid]==key)                    // search in all the adjoining nodes if they have the same key or not
         {                
            while(b.k.a[i]==key&&i>=0)
        {       l.add(0,b.n[i]);
                i--;
            }
            j++;
            while(b.k.a[j]==key&&j<b.keys)                      // add their children to the list for searching for values
            {
                l.add(l.size(),b.n[j+1]);
                j++;
            }
          }
        else
           { if(less(b.k.a[mid],key))
                    { start=mid+1;

                        return SearchChildren(b,key,start,end);
                    }
            else

                     if(less(b.k.a[mid],key))
                     {end = mid-1;
                        return SearchChildren(b,key,start,end);

              }
                         }

       }

       if(less(key,b.k.a[0]))
        l.add(0,b.n[0]);
        if(less(b.k.a[b.keys-1],key))
          l.add(l.size(),b.n[b.keys]);
       return l;

    }
   


   
    public void insert(Key key, Value val)
 {

   Node <Key,Value> p=root;

                
            if(isEmpty())
            {
                height++;                                                       
                root=new Node(b);
                insert(root,key,val);
                return;
            }
                while(p!=null)
                {    

                    if(isleaf(p))
                     {if(p.keys==M-1)
                       { split(p);
                        p=p.parent;
                       }   
                       insert(p,key,val);
                       return ;
                       }    
                    
                      if(!isleaf(p)) {  
                             if (p.keys==M-1)                          // find the appropriate leaf Node <Key,Value>for the key
                                split(p);

                       if (less(key,p.k.a[0])) {
                            p=p.n[0];
                            }
                        else                                    //

                       { if(less(p.k.a[p.keys-1],key)||equal(p.k.a[p.keys-1],key))
                           p=p.n[p.keys];
                           else
                           { for (int i=0;i<p.keys-1;i++)
                           if((less(p.k.a[i],key)||equal(p.k.a[i],key))&&less(key,p.k.a[i+1]))
                           p=p.n[i+1];
                           }

                        }
                    }

                   }
}

public void insert(Node <Key,Value>  b,Key key,Value val)          // insert in the appropriate leaf Node <Key,Value>
{
     int i = b.keys;  
  while ((i > 0) && less(key,b.k.a[i-1]))
    {   
      b.k.a[i] = b.k.a[i-1];
      b.val.a[i]=b.val.a[i-1];
           i = i - 1;
    }
  b.k.a[i] = key; 
  b.val.a[i]=val;
b.keys++;  
size++;


}


public void insert(Node <Key,Value> b,Key key,Value val,Node <Key,Value> t1,Node <Key,Value> t2)           
{
     int i = b.keys;  
  while ((i > 0) && b.n[i]!=t1)
    {   
      b.k.a[i] = b.k.a[i-1];
      b.val.a[i]=b.val.a[i-1];
      b.n[i]=b.n[i-1];
      i = i - 1;
    }                                                       // only the split function calls this method
  b.k.a[i] = key; 
  b.val.a[i]=val;
   b.n[i+1]=t2;
b.keys++;  


}
   public void split(Node <Key,Value>  b)
    {

        if(b==root)
            {height++; 
                b.parent=new Node(M);
                 root=b.parent;
           }
    

    Node <Key,Value> t1,t2;
            t1=new Node(M);
            t2=new Node(M);
              int j;
 for ( j= 0; j < M/2-1; j++)
           { 
            t1.parent = b.parent;
            t1.k.a[j] = b.k.a[j];
            t1.n[j] = b.n[j];
            t1.val.a[j] = b.val.a[j];
            
         }
         t1.n[j]=b.n[j];
          for (j = 0; j < M/2-1; j++)

            {t2.k.a[j] = b.k.a[M/2+j];
            t2.n[j] = b.n[M/2+j];
            t2.val.a[j] = b.val.a[M/2+j];
            t2.parent=b.parent;}
             t1.n[j]=b.n[M/2+j];
             t1.keys=M/2-1;
            t2.keys=M/2-1;
            b=t1;
       insert(b.parent,(Key)b.k.a[M/2-1],(Value)b.val.a[M/2-1],t1,t2);  
      

}

public void delete(Key key) throws IllegalKeyException
{ if(search(key).size()==0)
throw new IllegalKeyException();
while(search(key).size()!=0)
  delete(key,root);
 

}



   public void delete (Key key, Node <Key,Value> root) throws IllegalKeyException        
      {
    if (key==null) {
                throw new IllegalKeyException();
                }

        Node <Key,Value>p=root;
        while(!isEmpty())
        {   if(isunderflow(p))
              underflow(p);


        if(BinarySearch(p,key,0,p.keys,0)==null)   // suppose key is not present in the node

              {  if (less(key,p.k.a[0])) {
                          p=p.n[0];
                            }                                               // go to the appropriate child Node <Key,Value>
                        else if(less(p.k.a[p.keys-1],key))
                           
                           p=p.n[p.keys];
                           else
                           { for (int i=0;i<p.keys-1;i++)

                           if(less(p.k.a[i],key)&&less(key,p.k.a[i+1]))
                             if (!isleaf(p))
                            p=p.n[i+1];
                      
                            }

                        }

                
          else
           {    int i= findpos(key, p); 
            if (!isleaf(p))
            { Node <Key,Value> m= p.n[i+1]; 
              while(!isleaf(m))
                m=m.n[0];
            p.k.a[i]=m.k.a[0];
          p.val.a[i]=m.val.a[0];
          remove(0,m);
           while(m!=p.parent)
           {        if(isunderflow(m))
             underflow(m);
              m=m.parent;
             }
             return ;
                                    
          }                    
         else
              remove(i,p);
              return ; // case2 p is an external Node <Key,Value>
        }

                                 // then replace it with the element 
      // if merge with this same parent key then we would have to find where did the key go so 
            // the other half part we do the bottom up deletion from the inorder successor  

                    // swap the values and we are done 


   }

}
public void remove(int a , Node <Key,Value> p)   
{


 int i;                                                 // to remove an element from the leaf
        for (i=a; i<p.keys-1; i++)
           { p.k.a[i] = p.k.a[i+1];
            p.val.a[i] = p.val.a[i+1];}
        p.keys--;
        size--;
           
  }
 

  public void remove(Node <Key,Value> p, int index, Node <Key,Value> newchild)
  {
    p.n[index]=newchild;
      for(int i=index;i<p.keys-1;i++)
      {     p.k.a[i]=p.k.a[i+1]; 
            p.val.a[i]=p.val.a[i+1];
            p.n[i+1]=p.n[i+2];
        }


      p.keys--;
  }

  public int findpos(Key key,Node <Key,Value> p)  // return the first occurence of the key in the node
{
for (int i=0;i<p.keys;i++)
  { if ( p.k.a[i]==key)
    return i;}
  return -1;
}

public int parentpos(Node <Key,Value>p)
{
Node <Key,Value> m=p.parent;
for(int i=0;i<=m.keys;i++)
{if(m.n[i]==p)
return i;}
return -1;

}
  public boolean isunderflow(Node <Key,Value>p)
  {
    return (p.keys<M/2&&p!=root);

  } 
  public void underflow (Node <Key,Value>p)
   { int i;

    Node <Key,Value> m1= leftsibling(p);
    Node <Key,Value> m2 =rightsibling(p);
            if(m1!=null&&m1.keys>=M/2)                                                 //redistribute
        {
          for( i=p.keys;i>0;i--)
          {p.k.a[i]=p.k.a[i-1];
          p.val.a[i]=p.val.a[i-1];
          p.n[i+1]=p.n[i];}
          p.n[1]=p.n[0];
          p.n[0]=m1.n[m1.keys];
         p.k.a[0]=p.parent.k.a[parentpos(p)];
           p.val.a[0]=p.parent.val.a[parentpos(p)-1];
            replace((Key)m1.k.a[m1.keys-1],(Value)m1.val.a[m1.keys-1],p.parent,parentpos(p)-1);
         m1.keys--;
          p.keys++;
           return ;
     }
  
      if(m2!=null&&m2.keys>=M/2)
    
          {  p.val.a[p.keys]=p.parent.val.a[parentpos(p)];
            p.k.a[p.keys]=p.parent.k.a[parentpos(p)];
            p.n[p.keys+1]=m2.n[0];
            replace((Key)m2.k.a[0],(Value)m2.val.a[0],p.parent,parentpos(p));
            for( i=0;i<m2.keys-1;i++)
            {m2.k.a[i]=m2.k.a[i+1];
            m2.val.a[i]=m2.val.a[i+1];
            m2.n[i]=m2.n[i+1];
            }
            m2.n[i]=m2.n[i+1];
            m2.keys--;
            p.keys++;
           return ;
     }

   Node <Key,Value> merge = new Node(M);

          if(m1!=null) 
            // else merge with the left or right sibling which is available
          {for( i=0;i<M/2-1;i++)

             { merge.k.a[i]=m1.k.a[i];
              merge.val.a[i]=m1.val.a[i];
              merge.k.a[i+M/2]=p.k.a[i];
              merge.val.a[i+M/2]=p.val.a[i];
              merge.n[i]=m1.n[i];
                merge.n[i+M/2]=p.n[i]; }
              merge.n[i]=m1.n[i];
              merge.n[i+M/2]=p.n[i];
              merge.k.a[M/2-1]=p.parent.k.a[parentpos(p)-1];
              merge.val.a[M/2-1]=p.parent.val.a[parentpos(p)-1];
              remove(p.parent,parentpos(p)-1,merge);
          }


          if(m2!=null)
          {                                  //merge p , its parent and its 1st sibling 
            for( i=0;i<M/2-1;i++)

             { merge.k.a[i]=p.k.a[i];
              merge.val.a[i]=p.val.a[i];
              merge.k.a[i+M/2]=m2.k.a[i];
              merge.val.a[i+M/2]=m2.val.a[i];
              merge.n[i]=p.n[i];
                merge.n[i+M/2]=m2.n[i]; }
              merge.n[i]=p.n[i];
              merge.n[i+M/2]=m2.n[i];
              merge.k.a[M/2-1]=p.parent.k.a[parentpos(p)];
              merge.val.a[M/2-1]=p.parent.val.a[parentpos(p)];
              remove(p.parent,parentpos(p),merge);



          }
            merge.keys=M-1;
            merge.parent = p.parent;
                                         
                         
      
  }

  public void replace(Key key,Value val,Node p,int index)
  {
      p.k.a[index]=key;
      p.val.a[index]=val;

  }

  public Node <Key,Value> rightsibling(Node <Key,Value> p)
{

Node <Key,Value> m=p.parent;


if(m.n[m.keys]==p)
return null;

for (int i=0;i<m.keys;i++)
 if(m.n[i]==p)
 return  m.n[i+1]; 
return null;
}
public Node <Key,Value> leftsibling(Node <Key,Value> p)
{Node <Key,Value> m=p.parent;
if(m.n[0]==p)
return null;
 for (int i=1;i<=m.keys;i++)
 {if(m.n[i]==p)
  return m.n[i-1];}
  return null;

}


  public  boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

  public  boolean equal(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
       public  boolean greater(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) > 0;
    }


public String toString()
{
Node <Key,Value> p=root;
 s=new StringBuilder();
return output(p);

}

public String  output(Node <Key,Value>p)
{ 
 int i;
  s.append("[");
  if(!isleaf(p))
{ for(i=0;i<p.keys;i++)
 {output(p.n[i]);
 s.append(", ");
  s.append(p.k.a[i]);
  s.append(", ");
}
output(p.n[i]);}
else 
{for(i=0;i<p.keys-1;i++)
{
s.append(p.k.a[i]);
s.append(", ");
}
s.append(p.k.a[i]);}
s.append("]");
return s.toString();

}

}

