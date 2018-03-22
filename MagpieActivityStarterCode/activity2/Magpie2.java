/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		String lowerCaseStatement = statement.toLowerCase();
		if (statement.trim().length()<1){
			response = "Say something please.";
		}
		else if (lowerCaseStatement.indexOf("no") >= 0)
		{
			response = "Why so negative?";
		}
		else if (lowerCaseStatement.indexOf("mother") >= 0
				|| lowerCaseStatement.indexOf("father") >= 0
				|| lowerCaseStatement.indexOf("sister") >= 0
				|| lowerCaseStatement.indexOf("brother") >= 0)
		{
			response = "Tell me more about your family.";
		}
		else if(lowerCaseStatement.indexOf("cat")>=0
				|| lowerCaseStatement.indexOf("dog")>=0
				|| lowerCaseStatement.indexOf("bird")>=0
				|| lowerCaseStatement.indexOf("hamster")>=0)
		{
			response = "Tell me more about your pets.";
		}
		else if(lowerCaseStatement.indexOf("Hanson")>=0){
			response = "He sounds like a good teacher.";
		}
		else if(lowerCaseStatement.indexOf("how")>=0
				&& statement.indexOf("day")>=0){
			response = "My day is going well. How is your day?";
		}
		else if(lowerCaseStatement.indexOf(statement)>=0){
			response = "I hate loops.";
		}
		else if(statement.equals(statement.toUpperCase())){
			response = "Chill bro.";
		}
		else
		{
			response = getRandomResponse();
		}
		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 6;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm.";
		}
		else if (whichResponse == 2)
		{
			response = "Do you really think so?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}
		else if(whichResponse ==4){
			response = "I can't beleive you'd say that.";
		}
		else if(whichResponse ==5){
			response = "You rolled 6.";
		}
		return response;
	}
}
