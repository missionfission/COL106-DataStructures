public class funoverload {
	public void test (){
		System.out.println( " Function : test with no parameter " );
	}
	
	public void test ( int a ){
		System.out.println( " Function : test with one parameter " );
	}
public int test ()
 {
 System . out . println (" Function : tst with one integer parameter "); }

	public static void main ( String args [])
	{
		funoverload fun = new funoverload ();
		fun.test ();
		fun.test (3);
	}
}
