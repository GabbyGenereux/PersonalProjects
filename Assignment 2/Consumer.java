
/**
 * Write a description of class Consumer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Consumer extends User
{
    // instance variables - replace the example below with your own
    private String t;
    private Simulation s;
    /**
     * Constructor for objects of class Consumer
     */
    public Consumer(Simulation sim, java.lang.String taste)
    {
       super(sim, taste);
    }

    /**
     * toString - returns the toString from User
     * @return    a formatted string
     */
    public java.lang.String toString()
    {
        return "Consumer: " + super.toString();
    }
    
}
