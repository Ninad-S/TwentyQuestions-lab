import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
public class GameTree
{
    /**
     * Constructor needed to create the game.
     *
     * @param fileName
     *          this is the name of the file we need to import the game questions
     *          and answers from.
     */
     public String fileN;
    private Node root,current;
    private class Node {
        public String val;
        public Node left, right;
        public boolean question;
        public Node(String v,boolean q) {
            this.val = v;
            question = q;
            left = right = null;
        }

    }
    //Name: 
    public GameTree(String fileName)
    {
        //TODO
        fileN = fileName;
        try 

        {
            Scanner console = new Scanner(new File(fileName));
            root = new Node(console.nextLine(),true);
            gameTreeHelper(root,console);
            current = root;
        }
        catch(FileNotFoundException e){}
    }
    private void gameTreeHelper(Node cur, Scanner console)
    {
      if (!cur.question)
      {
        return;
      }
      else
      {
        String add = console.nextLine();
        cur.right = new Node(add,add.charAt(add.length() - 1) == '?');
        gameTreeHelper(cur.right,console);
        add = console.nextLine();
        cur.left = new Node(add,add.charAt(add.length() - 1 ) == '?');
        gameTreeHelper(cur.left,console);

      }
    }

    /*
     * Add a new question and answer to the currentNode. If the current node has
     * the answer chicken, theGame.add("Does it swim?", "goose"); should change
     * that node like this:
     */
    // -----------Feathers?-----------------Feathers?------
    // -------------/----\------------------/-------\------
    // ------- chicken  horse-----Does it swim?-----horse--
    // -----------------------------/------\---------------
    // --------------------------goose--chicken-----------
    /**
     * @param newQ
     *          The question to add where the old answer was.
     * @param newA
     *          The new Yes answer for the new question.
     */
    public void add(String newQ, String newA)
    {
        String holder = current.val;
        current.val = newQ;
        current.left = new Node(holder,true);
        current.right = new Node(newA,true);//TODO
    }

    /**
     * True if getCurrent() returns an answer rather than a question.
     *
     * @return False if the current node is an internal node rather than an answer
     *         at a leaf.
     */
    public boolean foundAnswer()
    {
        //TODO
        if (!current.question)
        {
          return true;
        }
        return false; //replace
    }

    /**
     * Return the data for the current node, which could be a question or an
     * answer.  Current will change based on the users progress through the game.
     *
     * @return The current question or answer.
     */
    public String getCurrent()
    {
        //TODO

        return current.val; //replace
    }

    /**
     * Ask the game to update the current node by going left for Choice.yes or
     * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
     *
     * @param yesOrNo
     */
    public void playerSelected(Choice yesOrNo)
    {
      if (yesOrNo == Choice.Yes)
      {
         current = current.right;
      }
      else
      {
        current = current.left;
      } //TODO
    }

    /**
     * Begin a game at the root of the tree. getCurrent should return the question
     * at the root of this GameTree.
     */
    public void reStart()
    {
       current = root; //TODO
    }

    @Override
    public String toString()
    {
        //TODO

        return toString(root,0);
    }
    public String toString(Node cur, int level)
    {
      if (cur == null)
      {
        return "";
      }
      else
      {
        String ret = "";
        for (int i = 0; i < level; i++)
        {
           ret += "- ";
        }
        ret += cur.val + "\n";
        return toString(cur.left, level+1) + ret + toString(cur.right,level+1);
      }
    }
    /**
     * Overwrite the old file for this gameTree with the current state that may
     * have new questions added since the game started.
     *
     */
    public void saveGame()
    {
        String outputFileName = fileN;
        PrintWriter diskFile = null;
	try { 
    diskFile = new PrintWriter(new File(outputFileName)); 
}
	catch (FileNotFoundException io) { 
    System.out.println("Could not create file: " + outputFileName); 
}
saveGameHelp(root,diskFile);
diskFile.close();//TODO
    }
    public void saveGameHelp(Node cur, PrintWriter writer)
    {
      if (cur == null)
      {
        return;
      }
      else
      {
        writer.println(cur.val);
        saveGameHelp(cur.right,writer);
        
        saveGameHelp(cur.left,writer);
      }
    }
}
