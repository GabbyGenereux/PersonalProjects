import java.util.ArrayList;
import java.util.Random;
/**
 * Simulates a file sharing program
 * 
 * @author Gabrielle Genereux - 100963245 
 * @version V1 12/10/2015
 */
public class Simulation
{
    // instance variables - replace the example below with your own
    private ArrayList<User> users;
    private ArrayList<Document> documents;

    /**
     * Constructor for objects of class Simulation
     * Creates a new user ArrayList
     * Creates a new documents ArrayList
     */
    public Simulation()
    {
        users = new ArrayList<User>();
        documents = new ArrayList<Document>();
    }
    
    /**
     * Constructor for objects of class Simulation
     * Creates a new consumer or producer at random
     */
    public Simulation(int nbUsers, int nbDocs)
    {
        this();
        String tagList[] = {"rock", "jazz", "rap"};
        Random n = new Random();        
        String tag = tagList[n.nextInt(tagList.length)];
        int l = n.nextInt(2);
        for(int i = 0; i < nbUsers; i++)
        {
           if(l == 1)
        {
            Consumer c = new Consumer(this, tag);
            addUser(c);
            System.out.println("A new consumer was created.");
        }
        else
        {
            Producer p = new Producer(this, tag);
            addUser(p);
            System.out.println("A new producer was created.");
        }

        }
        
        for(int j = 0; j < nbDocs; j++)
        {
          if(l == 1)
          {
            Consumer c = new Consumer(this, tag);
            addUser(c);
            System.out.println("A new consumer was created.");
          }
          else
          {
            Producer p = new Producer(this, tag);
            addUser(p);
            System.out.println("A new producer was created.");
          }
        }
        

        
    }

    /**
     * addDocument - adds the given document to the list of documents
     * @param  d   *** 
     */
    public void addDocument(Document d)
    {
        documents.add(d);
    }
      /**
     * addUser - adds the given user to the list of users
     * @param  u user
     */
    public void addUser(User u)
    {
       users.add(u); 
    }
      /**
     * run - runs a simulation consisting of picking a user and getting the user to act
     */
    public void run()
    {
       for (User u : users)
       {
           u.act();
           System.out.println(toString());
       }

    }
      /**
     * search - returns all documents
     * @return     arrayList documents
     */
    public java.util.ArrayList<Document> search()
    {
        return documents;
    }
          /**
     * toString - returns all users
     * @return    the users 
     */
    public java.lang.String toString()
    {
       String output = "";
       for( int i = 0; i < users.size(); i++)
       {
           output += users.get(i).toString() + " \n";
       }
       return output;
    }
    
    /**
     * main - makes a new simualtion and runs it
     * @para args[] string of argument inputs
     */
    public static void main(String args[])
    {   
         int firstArg = Integer.parseInt(args[0]);
         int secondArg = Integer.parseInt(args[1]);
       
         Simulation newSim = new Simulation(firstArg, secondArg);
         newSim.run();
    }
}
