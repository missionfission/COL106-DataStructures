import java.util.*;
public class Node <Key extends Comparable<Key>,Value>
{
  
  public ArrayList<Node> n;
  public ArrayList<Key> k ;
  public  ArrayList<Value> val;
  public int keys ;
 public Node <Key,Value> parent;
public Node(int b)
{
n=new ArrayList<Node> (b);
k=new ArrayList<Key> (b);
val=new ArrayList<Value> (b);
for(int i=0;i<b;i++)
{
n.add(null);
k.add(null);
val.add(null);

}
parent = null;
keys=0;
}


}
