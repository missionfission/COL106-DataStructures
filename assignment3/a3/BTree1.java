import java.util.List;

public class BTree<Key extends Comparable<Key>,Value> implements DuplicateBTree<Key,Value> {


	Node root;
	int size,height,b;

    public BTree(int a) throws bNotEvenException {  /* Initializes an empty b-tree. Assume b is even. */
       if(a/2)
	throw new bnotEvenException("b not even");
	else
		{b=a;
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
    public boolean isleaf(Node b)
    {

    }

  
    public List<Value> search(Key key) throws IllegalKeyException {
		if (!isEmpty())
        { List<Value> l=new List();

                Node p=root;
                while(!p.isEmpty())
                {  
                    
                    if(BinarySearch(p,key,0,p.keys)==null)   // suppose key is not present in the node
                      

                  {   if (key<p.k[0]) {
                            p=p.n[0];
                            }                                               // go to the appropriate child node 
                        else if(p.k[p.keys]<key)
                           p=p.n[p.keys+1];
                           else
                           { for (i=0;i<p.keys;i++)

                        if(p.k[i]<key&&p.k[i+1]>key)
                            p=p.n[i+1];
                            }

                        }

                    else
                    {   l+=BinarySearch(p,key,0,p.keys);
                        List<Node> m= SearchChildren(p,key,0,p.keys).isEmpty();    // if key is present in this node 
                   while(!m.isEmpty())                                              //if more than one keys are present find all of them

                        {
                            
                            p=m.deque();                                                
                            l+=BinarySearch(p,key,0,p.keys);
                        
                  }
                    break;
                }

           return l;
       }

	     
    }
}

    public List<Value> BinarySearch(Node b,Key key,int start,int end)
    {
        static List<Value> l= new List();
        static int flag=0;
       if(end>=start)
       { int mid=(start+end)/2;

        if(b.k[mid]==key)
            {   flag=1;
               while(b.k[i]==key)
        {       l.addfirst(b.k[value]);
                i--;
            }
            while(b.k[i]==key)
            {
                l.addlast(b.k[value]);
                i++;
            }

        }

        else
            if(b.k[mid]<key)
                    { start=mid+1;

                        BinarySearch(b,key,start,end);
                    }
            else

                     if(b.k[mid]>key)
                     {end = mid-1;
                        BinarySearch(b,key,start,end);

                         }

       }

       if(flag==1)
        return l;
        else 
        return null;


    }

public List<Node>  SearchChildren(Node b,Key key,int start,int end)  
    {
        static List<Node> l= new List();
        
       if(end>=start)
       { int mid=(start+end)/2;
                int i=mid;
                int j=mid;
        if(b.k[mid]==key)                    // search in all the adjoining nodes if they have the same key or not
            
            while(b.k[i]==key)
        {       l.addfirst(b.k[i]);
                i--;
            }
            while(b.k[j]==key)                      // add their children to the list for searching for values
            {
                l.addlast(b.n[j]);
                j++;
            }

        else
            if(b.k[mid]<key)
                    { start=mid+1;

                        BinarySearch(b,key,start,end);
                    }
            else

                     if(b.k[mid]>key)
                     {end = mid-1;
                        BinarySearch(b,key,start,end);

                         }

       }

       return l;

    }
   


   
    public void insert(Key key, Value val)
 {

   Node p=root;

                
            if(isEmpty())
            {
                height++;                                                       
                root=new Node(b);

            }
                while(!p.isEmpty())
                {    if (p.keys==b-1)                                               // find the appropriate leaf node for the key
                     split(p);
                     if(p.n==null)
                        if(p.keys!=b-1)
                        insert(p,key,val);
                        else


                else
                  {   if (key<p.k[0]) {
                            p=p.n[0];
                            }
                        else                                    //

                       { if(p.k[p.keys]<key)
                           p=p.n[p.keys+1];
                           else
                           { for (i=0;i<p.keys;i++)
                           if(p.k[i]<=key&&p.k[i+1]>key)
                           p=p.n[i+1];
                           }

                        }
}

   }
}

public void insert(Node b,Key key,Value val)          // insert in the appropriate leaf node 
{
     int i = b.keys;  
  while ((i > 0) && (key < b.k[i-1]))
    {   
      b.k[i] = b.k[i-1];
      b.val[i]=b.val[i-1];
           i = i - 1;
    }
  b.k[i] = key; 
  b.val[i]=val;
b.keys++;  
size++;


}


public void insert(Node b,Key key,Value val,Node t1,Node t2)           // only the split function calls this method
{
     int i = b.keys;  
  while ((i > 0) && (key < b.k[i-1]))
    {   
      b.k[i] = b.k[i-1];
      b.val[i]=b.val[i-1];
      b.n[i]=b.n[i-1];
      i = i - 1;
    }
  b.k[i] = key; 
  b.val[i]=val;
  b.n[i]=t1;
  b.n[i+1]=t2;
b.keys++;  
size++;

}
   public void split(Node b)
    {

        if(b==root)b
            {height++; 
                b.parent=new Node(M);
           }
    

    Node t1,t2;
  

 for (int j = 0; j < M/2; j++)
           { t2.k[j] = b.k[M/2+j];
            t2.n[j] = b.n[M/2+j];
            t2.val[j] = b.val[M/2+j];
            t2.parent=b.parent;
            t1.parent = b.parent;
            t1.k[j] = b.k[j];
            t1.n[j] = b.n[j];
            t1.val[j] = b.val[j];
            t1.keys=M/2;
            t2.keys=M/2;

         }

       insert(b.parent,b.k[M/2],b.val[M/2],t1,t2);  

}

public void delete(Key key) throws IllegalKeyException
{
while(search(key).length!=0)
  delete(key,root);

}



   public void simpledelete (Key key, Node root) throws IllegalKeyException        
      {
    if (key==null) {
                throw new IllegalKeyException("wrong key");
                }

        Node p=root;
        while(!isEmpty())
        {   if(isunderflow(p))
              underflow(p);


        if(BinarySearch(p,key,0,p.keys)==null)   // suppose key is not present in the node

              {  if (key<p.k[0]) {
                          p=p.n[0];
                            }                                               // go to the appropriate child node 
                        else if(p.k[p.keys]<key)
                           
                           p=p.n[p.keys+1];
                           else
                           { for (i=0;i<p.keys;i++)

                        if(p.k[i]<key&&p.k[i+1]>key)
                             if (!isleaf(p))
                            p=p.n[i+1];
                      
                            }

                        }

                }
          else
           {    int i= findpos(Key key,Node p); 
          if (!isleaf(p))
      {     Node m=p.n[i+1]; 
              while(!isleaf(m))
         
        m=m.n[0];
          p.k[i]=m.k[0];
          p.val[i]=m.val[0];
           while(m!=p.parent)
           {      if(isunderflow(m))
          underflow(m);
          m=m.parent;
        }
 // then replace it with the element 
  // if merge with this same parent key then we would have to find where did the key go so 
  // the other half part we do the bottom up deletion from the inorder successor  

// swap the values and we are done 


  }                    
          else 
        {    remove(i,p);
               if(isunderflow(p))
          underflow(p);}                                                         // case2 p is an external node 
}



   }


public void remove(int a , Node p)   
{


 int i;                                                 // to remove an element from the leaf
        for (i=a; i<p.keys; i++)
            p.k[i] = p.k[i+1];
            p.val[i] = p.val[i+1];
           
  }
 
}


 

  public int findpos(Key key,Node p)  // return the first occurence of the key in the node
{
for (int i=0;i<p.keys;i++)
   if ( p.k[i]==key)
    return i;
}
  public boolean isunderflow(Node p)
  {
    return (p.keys<M/2&&p!=root);


  } 
  public void underflow (Node p)
   {


      if(sibling(p)[0].keys>M/2)         //redistribute
                  {


                  }
  else 
        if(sibling(p)[1].keys>M/2)         //redistribute                         
    {
            
                        }

         else
  
    {




    }                                          //merge p , its parent and its 1st sibling 

                           
      
  }

  public Node[] sibling(Node p)
{

Node m=p.parent;
Node s[] = new Node [2];
for (int i=0;i<m.keys;i++)
 if(m.n[i]==p)
 {
    s[0]=m.n[i-1];
    s[1]=m.n[i+1];
 }
return s;
}

  public  boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

  public  boolean equal(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
      

public String toString()
{






}

}

