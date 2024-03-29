/**
 * This stores the directions for the users selection
 * @author Jacob Valdiviez
 * @see Direction
 *
 */
public class DirectionBoard {
	/**
	 * Contains all the directions in the direction board
	 * @see Direction
	 */
	private Direction[] directions;
	/**
	 * Instantiates the Direction Board with the number of directions
	 * @param numOfDirections
	 * @see Direction
	 */
	public DirectionBoard(int numOfDirections){
		this.directions = new Direction[numOfDirections];
	}
	/**
	 * retrieves all the directions
	 * @return Direction[]
	 * @see Direction
	 */
	public Direction[] getDirections(){
		return this.directions;
	}
	/**
	 * Returns a direction given the index
	 * @param i
	 * @return Direction
	 * @see Direction
	 */
	public Direction getDirection(int i){
		return this.directions[i];
	}
	/**
	 * Sets a direction to the direction board;
	 * @param direction
	 * @param i
	 */
	public void setDirection(Direction direction, int i){
		this.directions[i] = direction;
	}
}
