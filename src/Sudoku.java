import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
	
	//The .txt file written as a 2D array. (I couldn't find a way to make the .txt file be written as an array like I was able to with my tron project's world loading methods.)
	static int[][] Grid = new int [9][9];
	int gridGenerated = 0;
	
	int[][] UnsolvedGrid = readPuzzle("data\\puzzle.txt");
	
	public static int[][] readPuzzle(String filePath)throws FileNotFoundException {
		
		Scanner keyboard = new Scanner(new File(filePath));
		
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if(keyboard.hasNextInt()) {
					
					grid[row][col] = keyboard.nextInt();
				}
			}
		}
		keyboard.close();
		
		return grid;
	}
    

	private static int[][] grid;
	//Empty spaces as an int
	public static final int EMPTY = 0;
	//Size of the Sudoku grid
	public static final int SIZE = 9;

	//Makes the grid using a nested for loop that goes through each number in the given array.
	public Sudoku(int[][] grid) {
		this.grid = new int[SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.grid[i][j] = grid[i][j];
			}
		}
	}
	
	//Check for duplicates in a row
	private boolean InRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (grid[row][i] == number)
				return true;
		
		return false;
	}
	
	//Check for duplicates in a Column
	private boolean InCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (grid[i][col] == number)
				return true;
		
		return false;
	}
	
	//Check for duplicates in each of the 9 3x3 boxes
	private boolean InBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (grid[i][j] == number)
					return true;
		
		return false;
	}
	
	//Verifies that the number does not have a duplicate in the same row, column, nor box.
	private boolean ValidityCheck(int row, int col, int number) {
		return !InRow(row, number)  &&  !InCol(col, number)  &&  !InBox(row, col, number);
	}
	
	//Method for solving the sudoku grid using backtracking
       public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
          //Scan for any empty indexes that have been filled in with zeroes
          if (grid[row][col] == EMPTY) {
            //Test different numbers that fit where there are zeroes
            for (int number = 1; number <= SIZE; number++) {
            	if (ValidityCheck(row, col, number)) {
            		grid[row][col] = number;
            	//See whether or not the solution works. If it doesn't, backtrack and try a different solution.
                if (solve()) {
                  return true;
                } else {
                  grid[row][col] = EMPTY;
                }
             }
            }

            return false;
           }
          }
         }

         return true;
	}
	
    //Method for displaying the sudoku grid on the console
	public void display() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + grid[i][j]);
			}
		
			System.out.println();
		}
		
		System.out.println();
	}


}
