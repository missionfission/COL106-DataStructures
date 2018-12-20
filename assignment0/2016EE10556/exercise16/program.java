public class program
{



	public String test(String hex)
	{
		/*
		Exercise 16: Hex to binary- Given a string representing a number in hexadecimal
		format, convert it into its equivalent binary string. For e.g. if the input if ”1F1”
		then its binary equivalent is ”111110001”. If the input is ”13AFFFF”, the output
		should be ”1001110101111111111111111”.*/

char c ;String result="";
for(int i=0;i<hex.length();i++)

{int binary =0;

c=hex.charAt(i);
int value=0;
if(c>='0'&&c<='9')
value=(c-48);
else
value=(c-64+9);
 



for(int j=0;value!=0 ;j++)
{binary+=(value%2)*Math.pow(10,j);

value/=2;

}

String alpha=Integer.toString(binary);
for(int k=0;k+alpha.length()<4&&i!=0;k++)
result+="0";
result+=binary;
}
return result;
}


}




