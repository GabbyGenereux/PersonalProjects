
/**
 * Write a description of class Producer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Producer extends User
{
    // instance variables - replace the example below with your own
    private String t;
    private Simulation s;
    /**
     * Constructor for objects of class Producer
     * Creates new taste and simulation
     */
    public Producer(Simulation sim, java.lang.String taste)
    {
        super(sim, taste);
        t = taste;
        s = sim;
    }

    /**
     *act - likes documents from simulation based off users like 
     */
    public void act()
    {
       Document d = new Document(t);
       s.addDocument(d);
       super.act(); 
    }
    
    public java.lang.String toString()
    {
        return "Producer: " + super.toString();
    }
}
