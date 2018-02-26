
/**
 * SciFiName gathers input from a user to generate a custom name that
 * sounds like it could be used in a science fiction book or movie.
 * 
 * @author ckinnard
 * @version 3/4/16
 */

public class SciFiName   
{
    /**
     * All input that you privide should be at least three letters long
     * or the program may crash (but feel free to test this!)
     * 
     * For best results, user lowercase letters and do not use spaces in your input
     */
    public static void main()
    {
        System.out.println("If you provide me some information I will provide a Science Fiction name for you.");
        System.out.println("Please have all responses  be at least three characters long.");
        System.out.println("For best results, user lowercase letters with no spaces.\n");

        // notice this methd (print) does not print a newline
        System.out.print("Enter your first name: ");
        String firstName = UserInput.getString();
        System.out.print("Enter your last name: ");
        String lastName = UserInput.getString();
        System.out.print("Enter the city where you or one of your parents were born: ");
        String city = UserInput.getString();
        System.out.print("Enter the name of your grammar school: ");
        String school = UserInput.getString();
        System.out.print("Enter the first name of a sibling or other relative: ");
        String relativeName1 = UserInput.getString();
        System.out.print("Enter the first name of a second sibling or relative: ");
        String relativeName2 = UserInput.getString();
        System.out.println("Your Sci-Fi name is: "+genFirstName(firstName, lastName)+" "
        +genLastName(city, school)+" of "+genBirthplace(relativeName1,relativeName2));
        // generate a sciFi name
        
   
        
    }
    public static String genBirthplace (String relative1, String relative2){
        String s1 = relative1.substring((int)(Math.random()*(relative1.length()-1)),
        relative1.length());
        String s2 = relative2.substring((int)(Math.random()*(relative2.length()-1)),
        relative2.length());
        return s1.substring(0,1).toUpperCase()+s1.substring(1,s1.length()-1)
        +s2;
    }
    public static String genLastName (String city, String school){
        String s1 = city.substring(0,2);
        String s2 = school.substring(0,3);
        return s1.substring(0,1).toUpperCase()+s1.substring(1,s1.length()-1)
        +s2;
    }    
    public static String genFirstName(String firstName, String lastName){
        String s1=firstName.substring(0,3);
        String s2 = lastName.substring(0,2);
        return s1.substring(0,1).toUpperCase()+s1.substring(1,s1.length()-1)
        +s2;
    }
}
