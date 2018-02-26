import kareltherobot.Directions.Direction;

public interface SortableBotInterface {
	/**
	 * Turns this robot to the right.  Really, just three lefts...
	 */
	void turnRight();
	/**
	 * Turns this Robot around, duh!
	 */
	void turnAround();
	/**
	 * Moves this robot to the left the specified number of steps.  The robot
	 * will end up facing the direction that it started facing.
	 * 
	 * @param steps the distance this Robot will move to the left.  If this robot
	 * can't continue to move (obstruction, etc.) it will crash.  Please don't crash me!
	 */
	void slideLeft(int steps);
	
	/**
	 * Moves this robot to the right the specified number of steps.  The robot
	 * will end up facing the direction that it started facing.
	 * 
	 * @param steps the distance this Robot will move to the left.  If this robot
	 * can't continue to move (obstruction, etc.) it will crash.  Please don't crash me!
	 */
	void slideRight(int steps);
	
	/**
	 * Continuously drop beepers and move until out of beepers.  This robot will 
	 * drop in the direction it is facing.
	 */
	void showAllBeeps();
	
	/**
	 * Retrieves the number of beepers the Robot has.  This is necessary because the
	 * beepers method that Robot has is protected.  This allows public access to the num.
	 * @return number of beepers this robot has
	 */
	int getNumBeeps();
	
	/**
	 * This method gets the current location of the Robot.  It is returned in an int array of 
	 * length 2.  The 0th element is the street, the element in index 1 is the avenue.
	 * @return array of int that contains street and ave in that order in other words
	 *         {street, ave}
	 */
	int[] getLocation();
	
	/**
	 * Moves this robot to the specified location.  Returns old loc.
	 * @param loc  new location this Robot is moving to.  Assumes array of int has length of at 
	 * least 2.  Element at index 0 contains street, and index 1 contains avenue.
	 * @return int array that contains old street and avenue in that order.
	 */
	int[] moveToLocation(int[] loc);
	
	
	/**
	 * This method turns the robot to the sper
	 * @param dir direction to be faced
	 * 
	 * @return the old direction this robot was facing
	 */
	Direction faceDir(Direction dir);
}
