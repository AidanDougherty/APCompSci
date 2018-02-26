
/**
 * Write a description of class BreakTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BreakTest
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class BreakTest
     */
    public BreakTest()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        for(int i =0; i<5;i++){
            System.out.print("x");
           for(int a =0; a<3; a++){
               System.out.print("y");
               break;
            }
    }return x + y;}
}
