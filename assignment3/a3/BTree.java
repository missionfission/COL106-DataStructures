
import java.util.*;
public class BTree<Key extends Comparable<Key>,Value> implements DuplicateBTree<Key,Value> {

	Node <Key,Value> root;
	int size,height,b,M;
  StringBuilder s;

    public BTree(int a) throws bNotEvenException {  /* Initializes an empty b-tree. Assume b is even. */
       if(a%2!=0)
	throw new bNotEvenException();
	else
		{b=a;
      M=a;
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
    {
     return b.n.get(0)==null;
     
   }

  
 public List<Value> search(Key key) throws IllegalKeyException {
		if (!isEmpty())
        { List<Value> l=new ArrayList<Value>();

                Node <Key,Value> p=root;
                while(p.keys>0)
                {  
                    
                    if(BinarySearch(p,key,0,p.keys-1,0)==null)   // suppose key is not present  the node
                      

                  {if(!isleaf(p)){   if (less(key,p.k.get(0))) {
                            p=p.n.get(0);
                            }                                               // go to the appropriate child Node <Key,Value>
                        else if(less(p.k.get(p.keys-1),key))
                           p=p.n.get(p.keys);
                           else
                           { for (int i=0;i<p.keys-1;i++)

                        if(less(p.k.get(i),key)&&less(key,p.k.get(i+1)))
                            p=p.n.get(i+1);
                            }

                        }
                      else
                        throw new IllegalKeyException();}

                    else
                    {   if(l.addAll(BinarySearch(p,key,0,p.keys-1,0)));
                        if(!isleaf(p)) 
                 {       List<Node> m= SearchChildren(p,key,0,p.keys-1);    // if key is present in this Node <Key,Value>
                   while(m.size()!=0)                                              //if more than one keys are present find all of them

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
        if(equal(b.k.get(mid),key))
            {   flag=1;
               while(i>=0)
        {      if(equal(b.k.get(i),key))
          {l.add(0,b.val.get(i));
                i--;}
                else
                  break;
            }
            j++;
            while(j<b.keys)
            {    if(equal(b.k.get(j),key))
                {l.add(l.size(),b.val.get(j));
                j++;}
                else
                  break;
            }

        }

        else
           { if(less(b.k.get(mid),key))
                    { start=mid+1;

                       return BinarySearch(b,key,start,end,flag);
                    }
            else

                     if(less(key,b.k.get(mid)))
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
        if(equal(b.k.get(mid),key))                    // search in all the adjoining nodes if they have the same key or not
         {                
            while(i>=0)
        {    if(equal(b.k.get(i),key))  
            { l.add(0,b.n.get(i));
                i--;}
                else
                  break;
            }
            
            while(j<b.keys)                      // add their children to the list for searching for values
            {     if(equal(b.k.get(j),key))
                {l.add(l.size(),b.n.get(j+1));
                j++;}
                else
                  break;
            }
          }
        else
           { if(less(b.k.get(mid),key))
                    { start=mid+1;

                        return SearchChildren(b,key,start,end);
                    }
            else

                     if(less(b.k.get(mid),key))
                     {end = mid-1;
                        return SearchChildren(b,key,start,end);

              }
                         }

       }
       if(l.size()==0)
       { if(less(key,b.k.get(0)))
        l.add(0,b.n.get(0));
        else
          l.add(0,b.n.get(b.keys));

       }

       return l;


      
    }
   


   
    public void insert(Key key, Value val)
 {

   

                
            if(isEmpty())
            {
             height++;                                                       
                root=new Node(b);
                insert(root,key,val);
                return;
            }
            Node <Key,Value> p=root;
                while(p!=null)
                {    

                    if(isleaf(p))
                     {if(p.keys==M-1)
                       {
                        split(p);
                         p=p.parent;}
                         else   
                       {insert(p,key,val);
                       return ;}
                       }    
                    
                      if(!isleaf(p)) {  
                             if (p.keys==M-1)                          // find the appropriate leaf Node <Key,Value>for the key
                                {  split(p);
                                p=p.parent;}

                       if (less(key,p.k.get(0))) {
                            p=p.n.get(0);
                            }
                        else                                    //

                       { if(less(p.k.get(p.keys-1),key)||equal(p.k.get(p.keys-1),key))
                           {if(p.keys<M)p=p.n.get(p.keys);}
                           else
                           { for (int i=0;i<p.keys-1;i++)
                           if((less(p.k.get(i),key)||equal(p.k.get(i),key))&&less(key,p.k.get(i+1)))
                           p=p.n.get(i+1);
                           }

                        }
                    }

                   }
}

public void insert(Node <Key,Value>  b,Key key,Value val)          // insert in the appropriate leaf Node <Key,Value>
{
     int i = b.keys;  
    while(i>0)
    {   
      if(less(key,b.k.get(i-1)))
      {b.k.set(i,b.k.get(i-1));
      b.val.set(i,b.val.get(i-1));
           i = i - 1;}
           else
            break;
    }
  b.k.set(i,key); 
  b.val.set(i,val);
b.keys++;  
size++;


}


public void insert(Node <Key,Value> b,Key key,Value val,Node <Key,Value> t1,Node <Key,Value> t2,Node <Key,Value> c)           
{
     int i = b.keys;  
     
     while ((i > 0) && b.n.get(i)!=c)
    {   
      b.k.set(i,b.k.get(i-1));
      b.val.set(i,b.val.get(i-1));
      b.n.set(i+1,b.n.get(i));
      i = i - 1;
    }                                                       // only the split function calls this method
  b.k.set(i, key);
  b.val.set(i,val);
 
    b.n.set(i+1,t2);
   b.n.set(i,t1);
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
            t1.k.set(j,b.k.get(j));
            t1.n.set(j,b.n.get(j));
            t1.val.set(j,b.val.get(j));
              t2.k.set(j,b.k.get(M/2+j));
            t2.n.set(j, b.n.get(M/2+j));
            t2.val.set(j,b.val.get(M/2+j));
                    }
           t1.n.set(j,b.n.get(j));
           t2.n.set(j,b.n.get(M/2+j));
        if(!isleaf(t1)) for ( j= 0; j < M/2; j++)
         {
          t1.n.get(j).parent=t1;
         }
         
         if(!isleaf(t2)) for ( j= 0; j < M/2; j++)
         {
          t2.n.get(j).parent=t2;
         }
           t1.keys=M/2-1;
            t2.keys=M/2-1;
             t2.parent=b.parent; 
                                   
         insert(b.parent,b.k.get(M/2-1),b.val.get(M/2-1),t1,t2,b); 
      
}

public void delete(Key key) throws IllegalKeyException
{ if(search(key).size()==0)
throw new IllegalKeyException();

while(search(key).size()>1)
  {
   delete(key,root);
   System.out.println(toString());
  }
  delete(key,root);
  
}



   public void delete (Key key, Node <Key,Value> root) throws IllegalKeyException        
      {
    if (key==null) {
                throw new IllegalKeyException();
                }

        Node <Key,Value> p = root;
        while(!isEmpty())
        {   if(isunderflow(p))
              underflow(p);


        if(BinarySearch(p,key,0,p.keys,0)==null)   // suppose key is not present in the node

              {  if (less(key,p.k.get(0))) {
                          p=p.n.get(0);
                            }                                               // go to the appropriate child Node <Key,Value>
                        else if(less(p.k.get(p.keys-1),key))
                           
                           p=p.n.get(p.keys);
                           else
                           { for (int i=0;i<p.keys-1;i++)

                           if(less(p.k.get(i),key)&&less(key,p.k.get(i+1)))
                             if (!isleaf(p))
                            p=p.n.get(i+1);
                      
                            }

                        }

                
          else
           {    int i= findpos(key, p); 
               if (!isleaf(p))
            { Node <Key,Value> m= p.n.get(i+1); 
              while(!isleaf(m))
                m=m.n.get(0);
            p.k.set(i,m.k.get(0));
          p.val.set(i,m.val.get(0));
          remove(0,m);
          
           while(m!=p.parent)
           {        if(isunderflow(m))
                   { underflow(m);
                    m=m.parent;}
                    else
                      break;
             }
             return ;
                                    
          }                    
         else
            { remove(i,p);
              return ;} // case2 p is an external Node <Key,Value>
        }

                                 // then replace it with the element 
      // if merge with this same parent key then we would have to find where did the key go so 
            // the other half part we do the bottom up deletion from the inorder successor  

                    // swap the values and we are done 


   }

}
public void remove(int a , Node <Key,Value> p)   
{

// This fucntion removes a single element from the leaf
 int i;                   
                                                        // to remove an element from the leaf
        for (i=a; i<p.keys; i++)
           { p.k.set(i,p.k.get(i+1));
            p.val.set(i,p.val.get(i+1));}
        p.keys--;
        size--;
           
  }
 

  public void remove(Node <Key,Value> p, int index, Node <Key,Value> newchild)
  {
    // the merge of underflow calls this fucntion and it removes the parent key and its two children and replaces them with the merged key
        if(p==root&&p.k.size()==1)
    {     height--;
      root =newchild;
      root.parent=null;
       }
      else
      {  p.n.set(index,newchild);
      for(int i=index;i<p.keys-1;i++)
      {     p.k.set(i,p.k.get(i+1)); 
            p.val.set(i,p.val.get(i+1));
            p.n.set(i+1,p.n.get(i+2));
        }

          }
      p.keys--;
  }

  public int findpos(Key key,Node <Key,Value> p)  // return the first occurence of the key in the node
{
for (int i=0;i<p.keys;i++)
  { if ( equal(p.k.get(i),key))
    return i;}
  return -1;
}

public int parentpos(Node <Key,Value>p)
{
Node <Key,Value> m=p.parent;
for(int i=0;i<=m.keys;i++)
{if(m.n.get(i)==p)
return i;}
return -1;

}
  public boolean isunderflow(Node <Key,Value>p)
  {
    return (p.keys<(M/2-1)&&p!=root);

  } 
  public void underflow (Node <Key,Value>p)
   { int i;
// In both the rightsibling and left sibling part we remove from their list and replace with parent

    Node <Key,Value> m1= leftsibling(p);
    Node <Key,Value> m2 =rightsibling(p);
            if(m1!=null&&m1.keys>=M/2)                                                 //redistribute
        {
          for( i=p.keys;i>0;i--)
          {p.k.set(i,p.k.get(i-1));
          p.val.set(i,p.val.get(i-1));
          p.n.set(i+1,p.n.get(i));}
          p.n.set(1,p.n.get(0));                                          
          p.n.set(0,m1.n.get(m1.keys));
          p.n.get(0).parent=p;
         p.k.set(0,p.parent.k.get(parentpos(p)));
           p.val.set(0,p.parent.val.get(parentpos(p)-1));                                     // parent with the leftsibling
            replace((Key)m1.k.get(m1.keys-1),(Value)m1.val.get(m1.keys-1),p.parent,parentpos(p)-1);

         m1.keys--;
          p.keys++;
           return ;
     }
  
      if(m2!=null&&m2.keys>=M/2)
    
          {  p.val.set(p.keys,p.parent.val.get(parentpos(p)));
            p.k.set(p.keys,p.parent.k.get(parentpos(p)));
            p.n.set(p.keys+1,m2.n.get(0));
            p.n.get(p.keys+1).parent =p;
            replace((Key)m2.k.get(0),(Value)m2.val.get(0),p.parent,parentpos(p));
            for( i=0;i<m2.keys-1;i++)
            {m2.k.set(i,m2.k.get(i+1));
            m2.val.set(i,m2.val.get(i+1));
            m2.n.set(i,m2.n.get(i+1));
            }
            m2.n.set(i,m2.n.get(i+1));
            //
            m2.keys--;
            p.keys++;
           return ;
     }
// The merge part of the function starts here
   Node <Key,Value> merge = new Node(M);int flag=0;
             System.out.println("here");
          if(m1!=null) 
            // else merge with the left or right sibling which is available
          {for( i=0;i<M/2-2;i++)

             { System.out.println("here");
              merge.k.set(i,m1.k.get(i));
              merge.val.set(i,m1.val.get(i));
              merge.k.set(i+M/2,p.k.get(i));
              merge.val.set(i+M/2,p.val.get(i));
              merge.n.set(i,m1.n.get(i));
                merge.n.set(i+M/2,p.n.get(i)); }
              merge.k.set(i,m1.k.get(i));
              merge.val.set(i,m1.val.get(i));
              merge.n.set(i,m1.n.get(i));
              merge.n.set(i+1,m1.n.get(i))
              merge.n.set(i+M/2,p.n.get(i));
              merge.k.set(M/2-1,p.parent.k.get(parentpos(p)-1));
              merge.val.set(M/2-1,p.parent.val.get(parentpos(p)-1));
              if(!isleaf(merge)) for(i=0;i<M-1;i++)
                  merge.n.get(i).parent=merge;
              remove(p.parent,parentpos(p)-1,merge);
                flag=1;
          }


          if(m2!=null&&flag==0)
          {     //merge p , its parent and its 1st sibling 
            for( i=0;i<M/2-2;i++)

             { merge.k.set(i,p.k.get(i));
              merge.val.set(i,p.val.get(i));
              merge.k.set(i+M/2,m2.k.get(i));
              merge.val.set(i+M/2,m2.val.get(i));
              merge.n.set(i,p.n.get(i));
                merge.n.set(i+M/2,m2.n.get(i)); }
              merge.n.set(i,p.n.get(i));
              merge.k.set(i+1,m2.k.get(i));
              merge.val.set(i+1,m2.val.get(i));
              merge.n.set(M/2-1,m2.n.get(0));
              merge.n.set(i+M/2,m2.n.get(i));
              merge.k.set(M/2-2,p.parent.k.get(parentpos(p)));
              merge.val.set(M/2-2,p.parent.val.get(parentpos(p)));
                 for(i=0;i<M-1;i++)
                  if(!isleaf(merge)) merge.n.get(i).parent=merge;
              remove(p.parent,parentpos(p),merge);



          }
            merge.keys=M-2;
            merge.parent = p.parent;
                                         
                         
      
  }

  public void replace(Key key,Value val,Node p,int index)
  {
      p.k.set(index,key);
      p.val.set(index,val);

  }

  public Node <Key,Value> rightsibling(Node <Key,Value> p)
{

Node <Key,Value> m=p.parent;


if(m.n.get(m.keys)==p)
return null;

for (int i=0;i<m.keys;i++)
 if(m.n.get(i)==p)
 return  m.n.get(i+1); 
return null;
}
public Node <Key,Value> leftsibling(Node <Key,Value> p)
{Node <Key,Value> m=p.parent;
if(m.n.get(0)==p)
return null;
 for (int i=1;i<=m.keys;i++)
 {if(m.n.get(i)==p)
  return m.n.get(i-1);}
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
  if(!isEmpty())
  {if(!isleaf(p))
{ for(i=0;i<p.keys;i++)
 {output(p.n.get(i));
 s.append(", ");
  s.append(p.k.get(i));s.append("=");s.append(p.val.get(i));
  s.append(", ");
}
output(p.n.get(i));}
else 
{for(i=0;i<p.keys-1;i++)
{
s.append(p.k.get(i));s.append("=");s.append(p.val.get(i));
s.append(", ");
}
s.append(p.k.get(i));s.append("=");s.append(p.val.get(i));}}
s.append("]");
return s.toString();



}

}

