import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	//It was all good till I tried to make the 
	public static void main(String[] args) throws FileNotFoundException {
		
		String filePath = "\\data\\puzzle.txt";
		//String path = args[0];
		Sudoku.readPuzzle(filePath);
		Sudoku sudoku = new Sudoku(filePath);
		System.out.println("Sudoku grid to solve");
		sudoku.display();
		
		//If the backtracking in the "solve" method results in an answer, display the found solution. Otherwise, let the user know that there is no solution.
		if (sudoku.solve()) {
			System.out.println("Solution: ");
			sudoku.display();
		} else {
			System.out.println("No Solution."); //I know that there is an actual solution, however it would be helpful to keep this in case the puzzle itself has a mistake in it.
		}
	}
	
}
