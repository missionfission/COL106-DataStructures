

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    public static void main1(String[] a) {
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < 10; i++)
            l.add(2 * i);
        ListIterator<Integer> it = l.listIterator();
        while (it.hasNext()) {
            int t = it.next();
            if (t > 5)
                it.add(5);
            System.out.println(t);
        }
        System.out.println(l);
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        DuplicateBTree<String, String> tree = new BTree<>(n);
          for(int i=0;i<1000;i++)
      {  tree.insert("3","a");
    StringFormatChecker sf = new StringFormatChecker(tree.toString(), n);
                        if (!sf.verify())
                            {System.out.println(sf.getMessage());System.out.println(tree.toString());break;}}

        //for(int i=0;i<1000;i++)tree.insert("9","b");
          //   System.out.println(tree.height());
            //tree.delete("9");
		// tree.delete("3");
         //System.out.println(tree.height());
	 //for(int i=0;i<1000;i++)tree.insert("9","b");tree.delete("9");
         //System.out.println(tree.search("9"));
         //if(tree.search("9").size()==200)
           // System.out.println("ok");
        //tree.delete("3");
       // System.out.println(tree);
      /*while (true) {
            String command = s.next();
            try {
                switch (command) {
                    case "exit":
                        System.exit(0);
                    case "insert":
                        tree.insert(s.next(), s.next());
                        break;
                    case "delete":
                        tree.delete(s.next());
                        break;
                    case "print":
                        StringFormatChecker sf = new StringFormatChecker(tree.toString(), n);
                        if (!sf.verify())
                            System.out.println(sf.getMessage());
                        else
                            System.out.print("Verified:");
                        System.out.println(sf.getArgument());
                        break;
                    case "size":
                        System.out.println(tree.size());
                    case "height":
                        System.out.println(tree.height());
                        break;
                    case "empty":
                        System.out.println(tree.isEmpty());
                        break;
                    case "search":
                        System.out.println(tree.search(s.next()));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }*/
    }
}
