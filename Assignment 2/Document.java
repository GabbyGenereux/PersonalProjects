
/**
 * Provides an implementation of a document
 * 
 * @author Gabrielle Genereux - 100963245 
 * @version V1
 */
public class Document
{
    // instance variables - replace the example below with your own
    private int id;
    private String tag;
    private static int ID_COUNT = 0;
    
    /**
     * Constructor for objects of class Document
     * creates a new tag from the tag passed by the user
     * Creates a new Id based off the ID COUNT
     */
    public Document(java.lang.String t)
    {
        tag = t;
        id = ID_COUNT;
        ID_COUNT++;
    }

    /**
     * getId - gets the users ID
     * @return     the user id 
     */
   public int getId()
   {
       return id;
   }
     /**
     * getTag - gets the documents tag
     * @return     the document tag 
     */
   public java.lang.String getTag()
   {
       return tag;
   }
   
   public java.lang.String toString()
   {
       return "this documents id is: " + id + " and the tag is: " + tag;
   }
}
