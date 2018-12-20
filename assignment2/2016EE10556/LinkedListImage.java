import java.io.*;
import java.util.*;
public class LinkedListImage implements CompressedImageInterface {

    int height , width;LinkedList l[]; // array of linked list
               public int height()
           {        return height;           
                     } 

            public int width()
            { return width;
                     } 
 public LinkedList[] list()
    {
            return l;


    }

    public LinkedListImage(LinkedList[] a,int h, int w)
    {

            height=h;width=w;l=a;

    }

	public LinkedListImage(String filename)   
	{   try{ Scanner s=new Scanner(System.in);
              File inFile = new File(filename); //read from file
              s=new Scanner(inFile);
                 width =s.nextInt();
                 height=s.nextInt();int start;
                   l = new LinkedList [height];    // array of linked list each row has its own linked list

        for (int i=0;i<height;i++)
                   {   l[i]=new LinkedList();
                    for(int j=0;j<width;j++)
                      {  if(s.nextInt()==0)
                      
                       { start =j;j++;
                        if(j<width)
                      while(s.nextInt()==0)
                            {j++;
                            if(j>=width)
                               break;}
                    l[i].addlast(start,j-1);}
                      } 




            }

        }
        catch(FileNotFoundException e)
        {
           System.out.println("file not found");

        }
                                                    


}
public LinkedListImage(boolean[][] grid, int w, int h)
    {  
                height =h;
                width=w;
        l = new LinkedList [height]; // array of linked list each row has its own linked list

        for(int i=0;i<height;i++)
                   {   l[i]=new LinkedList();

                        for (int j=0;j<width;j++)
                         
                         { if(grid[i][j]==false)
                             { int start=j;
                                 while(grid[i][j]==false)
                                        { j++;
                                           if(j>=width)break;}             
                            l[i].addlast(start,j-1);}} }// store elements to the linked list
         
    }


    
    public boolean getPixelValue(int x, int y) throws PixelOutOfBoundException
    {   if( !((x>=0&&x<height)&&(y>=0&&y<width)))
        throw new PixelOutOfBoundException (" invalid values");
		Node p=l[x].head();  // search within linked list of row x 
        while(p!=null) 
       { if(p.getstart()<=y&&p.getend()>=y)
        return false;
        p=p.next();
         }
        return true;
    }

    public void setPixelValue(int x, int y, boolean val) throws PixelOutOfBoundException
    { if( !((x>=0&&x<height)&&(y>=0&&y<width)))
           throw new PixelOutOfBoundException (" invalid values");


      if(val==getPixelValue(x,y))  // if the value set is same as earlier return
         return ;
       else                                               // there is a flip
        {  Node p1=l[x].head(); 
          if(val==false)                     // a black element add the element in the compressed list
        
          {        if(p1==null)
                 { l[x].addlast(y,y); return ;}          
 
            while(p1.next()!=null)
            {   
			    if(p1.getstart()>y+1)
			    {l[x].addafter(p1.prev(),y,y);                // if the element is to be added at the beginning
			     break;}
			if(p1.getstart()==y+1)
			     {p1.setstart(y);
				 break;}                               // if the element can be merged into the starting of an existing node
			if (p1.getend()<y)
				    
			   { if(p1.getend()==y-1)                                       // it is just after the node end 

			       {if(p1.getend()==p1.next().getstart()-2)        // it is in between 2 nodes which have a gap of 1
				 
				       { p1.setend(p1.next.getend());
					  l[x].removeafter(p1); 
					  break;
				       }
			       else 
				  {p1.setend(y);                                  
				    break;                                     // merge after node end
				    }}
			

			     else

			       p1=p1.next(); }    
		
			     }    
         
        
                               
                                    
                    if(p1.next()==null)
                      {  if(p1.getstart()>y+1)
			              l[x].addafter(p1.prev(),y,y);                // if the element is to be added at the beginning
			                       
                       if(p1.getstart()==y+1)
			               p1.setstart(y);
                        if (p1.getend()<y)
				                  if(p1.getend()==y-1) 
                           p1.setend(y);
							                else
                           l[x].addlast(y,y);

                }




            }

			else
				{if(val==true)                           // a black element is set to a white element
				      
            while(p1!=null)                                        //remove that element from the list 
				 {  if(p1.getstart()<=y&&p1.getend()>=y)                  // element is present in between this node
				     { 
             if(p1.getstart()==p1.getend())                              // a standalone element
				      {

                l[x].removeafter(p1.prev());
                
				      break; }

				      else
				      {  if(p1.getstart()==y)             // element is at the front of the node
                 { p1.setstart(y+1);
                 break;}
                                                                
                else if(p1.getend()==y)             //element is at the last of the node
                          {p1.setend(y-1);break;}   
                      else                                            // split the node into two nodes
					          { l[x].addafter(p1,y+1,p1.getend());
					             p1.setend(y-1);break;}
						   }
				 
				}
        p1=p1.next();
				  }

     }
 }
}

    public int[] numberOfBlackPixels()
    {
        int count []=new int [height] ;
	      for(int i=0;i<height;i++)
          {  
                count[i]=0;
            Node p1= l[i].head();
             while(p1!=null)
             {   
                count[i]+=p1.getend()-p1.getstart()+1;                            // count the no of black nodes
                 p1=p1.next();


             }

          }


		return count;
			}

 public void invert()
 {
          LinkedList l1[] = new LinkedList [height];                  // a new linkedlist array
		for (int i=0;i<height;i++)                                        
        {   l1[i] =new LinkedList();
            Node p1=l[i].head();
           if(p1!=null)
            {   if(p1.getstart()>0)
                    {l1[i].addlast(0,p1.getstart()-1);                                     // add new nodes with values not of the original values
                    }

               while(p1.next()!=null)
                 {    l1[i].addlast(p1.getend()+1,p1.next().getstart()-1);         
                      p1=p1.next();

                      }

                      if(p1.getend()<width-1)
            l1[i].addlast(p1.getend()+1,width-1);                                     // code all cases

                   }
         else 
            l1[i].addlast(0,width-1);

            

            }

              l=l1;

    }
    



    public void performAnd(CompressedImageInterface img) throws BoundsMismatchException
  { if( height==img.height()&&width==img.width())
           { invert();
              img.invert();
              performOr(img);
              invert();    
              img.invert();   



                }
          else 
                    throw new BoundsMismatchException("not matching");

     

         
        
    }


   
    public void performOr(CompressedImageInterface img) throws BoundsMismatchException 
    { if( height==img.height()&&width==img.width())
           {     LinkedList [] l1=img.list();
                     LinkedList [] l3 = new LinkedList [height];
                    for (int i=0;i<height;i++)
                   {     Node p1=l[i].head();
                    Node p2=l1[i].head(); l3[i]=new LinkedList();
                           
                           while (p1!=null&&p2!=null)
                       {       
                                if(p1.getstart()<p2.getstart())        //  the one with the larger start number should be p1
                                { Node temp=p1;
                                    p1=p2;
                                    p2=temp;
                                }

                                if(p1.getend()<p2.getend())          
                                    {l3[i].addlast(p1.getstart(),p1.getend());
                                    p1=p1.next();continue;}
                                else  
                                {  if(p1.getstart()<=p2.getend())
                                   { l3[i].addlast(p1.getstart(),p2.getend());
                                    p2=p2.next();continue;}
                                    else 
                                      if(p1.getend()>=p2.getend()&&p1.getstart()>=p2.getend())
                                      {p2=p2.next();
                                        continue;
                                      }
                                  }

                            

                     }

                }


                  l=l3;

            }
          else 
                    throw new BoundsMismatchException("not matching");

            

         
		
    }
    
    public void performXor(CompressedImageInterface img) throws BoundsMismatchException 
    {    
if( height==img.height()&&width==img.width())
           {     
                 CompressedImageInterface img1=img;
                 CompressedImageInterface img2=new LinkedListImage(l,height,width);
                img1.invert();
                img2.performAnd(img1);
                invert();
                img1.invert();
                performAnd(img);
                performOr(img2);
                
                }
          else 
                    throw new BoundsMismatchException("not matching");

            
      }
		      


    
    public String toStringUnCompressed()
      {  StringBuilder s=new StringBuilder();
            s.append(width+" "+height+",");
            for(int i=0;i<height;i++)                          // check each element and add corresponding value to the string
            {  
                try{for(int j=0;j<width;j++)
                 { if (getPixelValue(i,j)==false)
               
                    s.append(" 0");
                    else
                        s.append(" 1");

                    }}
                     catch(PixelOutOfBoundException e)
            {    

            }   
           if(i!=height-1) s.append(",");

            }
            

            return s.toString();	
           
       }
    
    public String toStringCompressed()
    { StringBuilder s=new StringBuilder();                                          // compressed form
         s.append(height+" "+width+",");
    for(int i=0;i<height;i++)
    { Node p1;
        if(l[i].head()!=null)

            p1= l[i].head();
      else
             p1=null; 
        while(p1!=null)
        {
              s.append(" ");
             s.append(p1.getstart());
             s.append(" ");
             s.append(p1.getend());

            p1=p1.next();
        }
       s.append(" -1");
      if(i!=height-1) s.append(",");


    }


return s.toString();   


      }

    public static void main(String[] args) {
      // testing all methods here :
      boolean success = true;

      // check constructor from file
      CompressedImageInterface img1 = new LinkedListImage("sampleInputFile.txt");

      // check toStringCompressed
      String img1_compressed = img1.toStringCompressed();
     
      String img_ans = "16 16, -1, 5 7 -1, 3 7 -1, 2 7 -1, 2 2 6 7 -1, 6 7 -1, 6 7 -1, 4 6 -1, 2 4 -1, 2 3 14 15 -1, 2 2 13 15 -1, 11 13 -1, 11 12 -1, 10 11 -1, 9 10 -1, 7 9 -1";
      success = success && (img_ans.equals(img1_compressed));

      if (!success)
      {
        System.out.println("Constructor (file) or toStringCompressed ERROR");
        return;
      }

      // check getPixelValue
      boolean[][] grid = new boolean[16][16];
      for (int i = 0; i < 16; i++)
        for (int j = 0; j < 16; j++)
        {
                try
                {
              grid[i][j] = img1.getPixelValue(i, j);                
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
        }

      // check constructor from grid
      CompressedImageInterface img2 = new LinkedListImage(grid, 16, 16);
      String img2_compressed = img2.toStringCompressed();
     
      success = success && (img2_compressed.equals(img_ans));

      if (!success)
      {
        System.out.println("Constructor (array) or toStringCompressed ERROR");
        return;
      }

      // check Xor
        try
        {
          img1.performXor(img2);      

        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
      for (int i = 0; i < 16; i++)
        for (int j = 0; j < 16; j++)
        {
                try
                {
              success = success && (!img1.getPixelValue(i,j));  

                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
        }

      if (!success)
      {
        System.out.println("performXor or getPixelValue ERROR");
        return;
      }

      // check setPixelValue
      for (int i = 0; i < 16; i++)
        {
            try
            {
            img1.setPixelValue(i, 0, true);            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }


      // check numberOfBlackPixels
      int[] img1_black = img1.numberOfBlackPixels();
      success = success && (img1_black.length == 16);
      for (int i = 0; i < 16 && success; i++)
        success = success && (img1_black[i] == 15);
      if (!success)
      {
        System.out.println("setPixelValue or numberOfBlackPixels ERROR");
        return;
      }

      // check invert
        img1.invert();
        for (int i = 0; i < 16; i++)
        {
            try
            {
                success = success && !(img1.getPixelValue(i, 0));            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }
        if (!success)
        {
            System.out.println("invert or getPixelValue ERROR");
            return;
        }

      // check Or
        try
        {      
            img1.performOr(img2);  
                  
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && img1.getPixelValue(i,j);

                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }
        if (!success)
        {
            System.out.println("performOr or getPixelValue ERROR");
            return;
        }

        // check And
        try
        {
            img1.performAnd(img2);    
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && (img1.getPixelValue(i,j) == img2.getPixelValue(i,j));             
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }
        if (!success)
        {
            System.out.println("performAnd or getPixelValue ERROR");
            return;
        }

      // check toStringUnCompressed
        String img_ans_uncomp = "16 16, 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1, 1 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1, 1 1 1 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1 1, 1 1 0 0 0 1 1 1 1 1 1 1 1 1 1 1, 1 1 0 0 1 1 1 1 1 1 1 1 1 1 0 0, 1 1 0 1 1 1 1 1 1 1 1 1 1 0 0 0, 1 1 1 1 1 1 1 1 1 1 1 0 0 0 1 1, 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1, 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1, 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1 1, 1 1 1 1 1 1 1 0 0 0 1 1 1 1 1 1";
        success = success && (img1.toStringUnCompressed().equals(img_ans_uncomp)) && (img2.toStringUnCompressed().equals(img_ans_uncomp));
         
        if (!success)
        {
            System.out.println("toStringUnCompressed ERROR");
            return;
        }
        else
            System.out.println("ALL TESTS SUCCESSFUL! YAYY!");
    }
}
