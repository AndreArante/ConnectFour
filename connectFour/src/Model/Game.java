package Model;

import Exceptions.ColumnIsFullException;
import Exceptions.InvalidColumnException;

public class Game {
	
	/* initialize variables */
	Disc[] [] board;
	Player player1;
	Player player2;
	final int numberOfRows = 6;
	final  int numberOfColumns = 7;
	
	/*
	 * turn = false => player 1's turn
	 * turn = true =>  player 2's turn
	*/
	boolean turn; 
	
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		randomizePlayers();
		board = new Disc[numberOfRows][numberOfColumns];
	}

	/*
	 * if any numerical values(empty spaces) are found, game has not ended
	 * otherwise all spaces are full and game has ended
	*/
	public boolean checkIfGameEnded() {
		for (int i = 0; i < numberOfColumns; i++) {
			if (board[0][i] == null) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * 50% chance of swapping players 
	*/
	public void randomizePlayers() {
		double randomNumber = Math.random();
		Player tempPlayer;
		if (randomNumber >= 0 && randomNumber < 0.5) {
			tempPlayer = player1;
			player1 = player2;
			player2 = tempPlayer;
		}
	}
	
	/*
	 * set turn to opposite of current value
	*/
	public void endTurn() {
		turn = !turn;
	}
	
	/*
	 *  if turn == true its player 2's turn
	 *  else its player 1's turn
	*/
	public Player getTurnPlayer() {
		if (turn) {
			return player2;
		} else {
			return player1;
		}
	}
	
	/*
	 * accepts row and column number to check, along with the turn player 
	*/
	public boolean checkWin (int rowNumber, int columnNumber, Player turnPlayer) {
		int counter = 0;
		
		/* Check for a Vertical Win Upwards */
		for (int i = rowNumber; i < numberOfRows; i++) {
			/* adds 1 to the counter for the turn player */
			if(board[i][columnNumber].getPlayer() == turnPlayer) {
				counter++;
				/* if counter reaches 4 that player has connected 4 and won*/
				if(counter == 4) {
					return true;
				} else {
					/* if a disk for opposing player is encountered the player's streak is ended */
					break;
				}
			}
			
		}
		
		/* Check for a Vertical Win Downwards */
		counter = 0;
		for(int i = rowNumber; i < numberOfRows; i++) {
			if (board[rowNumber][i] == null) break; // breaks next value is empty or nonexsistient
			if (board[rowNumber][i] == null) {
				break;
			}
			if(board[rowNumber][i].getPlayer() == turnPlayer) {
				counter++;
				if(counter == 4) {
					return true;
				} else break;
			}
		}
		
		/* Check for a Horizontal Win to the Right */
		counter = 0;
		for(int i = columnNumber; i < numberOfColumns; i++) {
			if (board[rowNumber][i] == null) break; // breaks next value is empty or nonexsistient
			/* checks all values to the right of user placement for matches */
			if (board[rowNumber][i].getPlayer() == turnPlayer) {
				counter++;
				if (counter == 4) return true;
				return false;
			}
			
		}
		
		/* Check for a horizontal Win to the Left */
		counter = 0;
		for(int i = columnNumber; i < numberOfColumns; i--) {
			if (board[rowNumber][i] == null) break; // breaks next value is empty or nonexsistient
			/* checks all values to the right of user placement for matches */
			if (board[rowNumber][i] == null) break; 
			if (board[rowNumber][i].getPlayer() == turnPlayer) counter++;
			if (counter == 4) return true;
			return false;
		}
		
		/* Check for Downright Win */
		counter = 0;
		for(int i = rowNumber, j = columnNumber; i < numberOfRows && j < numberOfColumns; i++, j++) {
			if(board[i][j] == null) {
				break;
			}
			if(board[i][j].getPlayer() == turnPlayer) {
				counter++;
				if (counter == 4) return true;
				return false;
			}
		}
		
		/* Check for a Downleft Win */
		counter = 0;
		for(int i = rowNumber, j = columnNumber; i < numberOfRows && j < numberOfColumns; i++, j--) {
			if(board[i][j] == null) {
				break;
			}
			if(board[i][j].getPlayer() == turnPlayer) {
				counter++;
				if (counter == 4) return true;
				return false;
			}
		}
		
		/* Check for a UpRight Win */
		counter = 0;
		for(int i = rowNumber, j = columnNumber; i < numberOfRows && j < numberOfColumns; i--, j++) {
			if(board[i][j] == null) {
				break;
			}
			if(board[i][j].getPlayer() == turnPlayer) {
				counter++;
				if (counter == 4) return true;
				return false;
			}
		}
		
		/* Check for a UpLeft Win */
		counter = 0;
		for(int i = rowNumber, j = columnNumber; i < numberOfRows && j < numberOfColumns; i--, j--) {
			if(board[i][j] == null) {
				break;
			}
			if(board[i][j].getPlayer() == turnPlayer) {
				counter++;
				if (counter == 4) return true;
				return false;
			}
		}
		
		return false;
	}
	
	/*
	 * accepts one parameter column
	 * throws exception if that column is full 
	 * otherwise checks for win
	 * 		
	*/
	public boolean insertDisc (int columnNumber) throws ColumnIsFullException, InvalidColumnException {
		
		/* create a disc object based on the current player's turn */
		Disc disc;
		if(getTurnPlayer().getType() == PlayerType.PlayerOne) {
			disc = new PlayerOneDisc(getTurnPlayer());
		} else {
			disc = new PlayerTwoDisc(getTurnPlayer());
		}
		
		/* check if column is full */
		if(board[0][columnNumber] != null) {
			throw new ColumnIsFullException ();
		}
		
		/* check for valid input */
		if(columnNumber < 0 || columnNumber > numberOfColumns) {
			throw new InvalidColumnException ();
		}
		
		/* place in the selected column */
		for(int i = 0; i < numberOfRows - 1; i++) {
			if(board[i + 1][columnNumber] != null) {
				// there is an empty place to place the disc
				board[i][columnNumber] = disc;
				return checkWin(i, columnNumber, getTurnPlayer());
			}
		}
		
		/* above loop is skipped if row is empty, places disc at the lowest slot */
		board[numberOfRows - 1][columnNumber] = disc;
		return checkWin(numberOfRows-1, columnNumber, getTurnPlayer());

	}
	
	public Disc[][] getBoard() {
		return board;
	}
	
}
