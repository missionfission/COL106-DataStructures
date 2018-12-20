import java.util.EmptyQueueException;
 
public interface queue 
{
public void enqueue (Object elem) throws FullQueueException;
public Object dequeue () throws EmptyQueueException;
public Object front () throws EmptyQueueException;
public int size();
public boolean isempty();

}
