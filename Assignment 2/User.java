import java.util.ArrayList;
/**
 * Implements a user
 * 
 * @author Gabrielle Genereux   
 * @version V1
 */
public class User
{
    // instance variables - replace the example below with your own
    protected int id;
    protected String taste;
    protected Simulation sim;
    protected ArrayList<Document> likes;
    private static int ID_COUNT = 0;
    /**
     * Constructor for objects of class User
     * Creates a new ArrayList of likes
     * Creates a new simulation
     * Creates a new user taste
     * Creates a new Id based off of ID COUNT
     */
    public User(Simulation sim, java.lang.String s)
    {
        likes = new ArrayList<Document>();
        this.sim = sim;
        this.taste = s;
        this.id = ID_COUNT;
        ID_COUNT++;
    }

    /**
     * Act - likes documents from simulation based off users like
     */
    public void act()
    {
        ArrayList<Document> funStuff = sim.search();
        
        for (Document thing : funStuff)
        {
           if (taste.equals(thing.getTag()))
           {
               this.like(thing);
           }
        }
    }
    
    /**
     * getID - gets the users ID number
     * @return     returns the user id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * getTaste - gets the users taste
     * @return     the users taste
     */
    public java.lang.String getTaste()
    {
      return taste;
    }
    
    /**
     * Like - likes the supplied document
     * @param  d type Document
     */
    public void like(Document d)
    {
       likes.add(d); 
    }
    
    /**
     * Checks if a certain document d is in the likes ArrayList
     * 
     * @param  d type Document
     * @return     true if the supplied document is in the users likes
     *             returns false if the supplied document is not in the users likes
     */
    public boolean likes(Document d)
    {
        if (likes.contains(d))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public java.lang.String toString()
    {
        String output = "";
        
        for(int i = 0; i <likes.size(); i++)
        {
            output += likes.get(i).getTag();
        }
        
        return "User number: " + id + " is into: " + taste + " and likes: " + output;
    }
}
