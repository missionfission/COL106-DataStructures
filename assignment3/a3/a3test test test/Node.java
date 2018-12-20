import java.util.*;
public class Node <Key extends Comparable<Key>,Value>
{
  
  public Node <Key,Value> [] n;
  public Vector <Key> k;
  public Vector <Value> val;
  public int keys =0;
 public Node <Key,Value> parent;
public Node(int b)
{
n = new Node [b];
k= new Vector<Key> ();
val =new Vector<Value> ();

parent = null;
}


}
