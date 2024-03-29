import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is the class that contains the winning moves for every game
 * @author Jacob Valdiviez
 *
 */
public class AnswerKey {
	/**
	 * This holds all the answer keys
	 */
	Object[] answers;
	/**
	 * This is a path to win
	 */
	int[][] ans1 = {{0,0,0,0,1},
			        {0,0,0,0,1},
			        {0,0,0,0,1},
			        {0,0,0,0,1},
			        {0,0,0,0,1},
			        {0,0,0,0,1},
			        {1,1,1,1,1}};
	/**
	 * This is a path to win
	 */
	/**int[][] ans2 = {{1,1,1,1,1},
	         		{1,0,0,0,0},
	         		{1,0,0,0,0},
	         		{1,0,0,0,0},
	         		{1,0,0,0,0},
	         		{1,0,0,0,0},
	         		{1,0,0,0,0}};*/
	/**
	 * Instantiates the class
	 */
	public AnswerKey(){
		answers = new Object[1];
		answers[0] = convertToCellArray(ans1);
		//answers[1] = convertToCellArray(ans2);
	}
	/**
	 * Randomly chooses an answer key;
	 * @return Cell[]
	 * @see Cell
	 */
	public Cell[] getRandomKey(){
		Random rand = new Random();
		int number = rand.nextInt(answers.length);
		return (Cell[]) answers[number];
	}
	/**
	 * Converts an array of integers into an array of cells with directions
	 * @param intAns
	 * @return Cell[]
	 * @see Cell
	 */
	private Cell[] convertToCellArray(int[][] intAns){
		List<Cell> cellList = new ArrayList<Cell>();
		int tempX = intAns.length - 1;
		int x = 0;
		int tempY = 0;
		while((tempX != 0) && (tempY != 5)){
			if(((tempX - 1) >= 0) && (intAns[tempX - 1][tempY] == 1)){
				cellList.add(new Cell(new Direction("up"),x,tempY));
				tempX--;
				x++;
			}
			else if(((tempY + 1) < intAns[0].length) && (intAns[tempX][tempY + 1] == 1)){
				cellList.add(new Cell(new Direction("right"),x,tempY));
				tempY++;
			}
			System.out.println("Stuck in here: X:"+tempX + " Y:"+tempY);
		}
		Cell[] answer = new Cell[cellList.size()];
		for(int i = 0; i < cellList.size(); i ++)
			answer[i] = cellList.get(i);
		return answer;
	}

}
