public class program
{
	public String[] test(String fileNames[])
	{
		/*
		Exercise 17: Java files- You have been given the list of the names
		of the files in a directory.
		You have to select Java files from them.
		A file is a Java file if it’s name ends with ”.java”.
		For e.g. ”File-Names.ejava” is a Java file, ”FileNames.java.pdf” is not.
		If the input is {”can.java”,”nca.doc”,”and.java”,”dan.txt”,”can.java”,”andjava.pdf”} 
		the expected output is {”can.java”,”and.java”,”can.java”}
		*/

                 ;
                int j=0,count=0;

		for(int i=0;i<fileNames.length;i++)
               if(fileNames[i].endsWith(".java"))
		count++;
String[] javafiles = new String[count] ;
for(int i=0;i<fileNames.length;i++)
               if(fileNames[i].endsWith(".java"))
		
                    javafiles[j++]=fileNames[i];

return javafiles;
	}
}
