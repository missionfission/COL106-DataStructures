import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

class test
{
    public static void main(String[] args) throws Exception
    {
        LinkedListImage x=new LinkedListImage("x.txt");
        LinkedListImage y=new LinkedListImage("y.txt");



        /////////////////////////////////////////Check OR
        LinkedListImage or=new LinkedListImage("or.txt");
        x.performOr(y);
        String a=x.toStringCompressed();
        String b=or.toStringCompressed();
        
        if(a.equals(b))
            System.out.println("OR sahi hai.");
        else
            System.out.println("OR galat hai.");
        ////////////////////////////////////////


        
        //////////////////////////////////////////Check AND
        LinkedListImage and=new LinkedListImage("and.txt");
        x=new LinkedListImage("x.txt");
        x.performAnd(y);
        a=x.toStringCompressed();
        b=and.toStringCompressed();

        if(a.equals(b))
            System.out.println("AND sahi hai.");
        else
            System.out.println("AND galat hai.");
        /////////////////////////////////////////



        /////////////////////////////////////////////////Check INVERT
        LinkedListImage x_invert=new LinkedListImage("x_invert.txt");
        x=new LinkedListImage("x.txt");
        x.invert();
        a=x.toStringCompressed();
        b=x_invert.toStringCompressed();
        
        if(a.equals(b))
            System.out.println("INVERT sahi hai.");
        else
            System.out.println("INVERT galat hai.");
        ////////////////////////////////////////////

        

        //////////////////////////////////////////Check XOR
        LinkedListImage xor=new LinkedListImage("xor.txt");
        x=new LinkedListImage("x.txt");
        x.performXor(y);
        a=x.toStringCompressed();
        b=xor.toStringCompressed();
        
        if(a.equals(b))
            System.out.println("XOR sahi hai.");
        else
            System.out.println("XOR galat hai.");
        /////////////////////////////////////////



        /////////////////////////////////////////Check SET PIXEL
        LinkedListImage set=new LinkedListImage("SetPixel.txt");
        x=new LinkedListImage("x.txt");
        for(int i=0;i<x.height;i++)
            for(int j=0;j<x.width;j++)
                if(y.getPixelValue(i,j))
                    x.setPixelValue(i,j,false);
        a=x.toStringCompressed();
        b=set.toStringCompressed();
        
        if(a.equals(b))
            System.out.println("SET PIXEL sahi hai.");
        else
            System.out.println("SET PIXEL galat hai.");
        ///////////////////////////////////////////////



        /////////Check NUM BLACK PIXELS
        x=new LinkedListImage("x.txt");
        String[] line=null;
        boolean incorrect=false;
        try
        {
            FileReader t=new FileReader("BlackPixels.txt");
            BufferedReader input=new BufferedReader(t);
            line=input.readLine().split(" ");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        int[] c=x.numberOfBlackPixels();

        for(int i=0;i<x.height;i++)
            if(Integer.parseInt(line[i])!=c[i])
            {
                incorrect=true;
                break;
            }

        if(!incorrect)
            System.out.println("NUM BLACK PIXELS sahi hai.");
        else
            System.out.println("NUM BLACK PIXELS galat hai.");     
        //////////////////////////////////////////////////////   
    }
}