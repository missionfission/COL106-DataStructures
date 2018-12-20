public class progr
{
	public int[][] test(int M1[][], int M2[][])
	{
		/*
		Exercise 19: Matrix addition- Given two matrices M1 and M2, the objective to
		add them. Each matrix is provided as an int[][], a 2 dimensional integer array.
		The expected output is also 2 dimensional integer array.
		*/   


                   if((M1.length==M2.length)&&(M1[0].length==M2[0].length))
		int [][]addition= new int [M1.length][M1[0].length];
               for(int i=0;i<M1.length;i++)
               for(int j=0;j<M1[0].length;j++)
                   addition[i][j]=M1[i][j]+M2[i][j];
		return addition;
	}
}
