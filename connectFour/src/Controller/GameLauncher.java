package Controller;

import Exceptions.InvalidColumnException;
import Model.Game;
import View.GameView;
import Exceptions.ColumnIsFullException;

public class GameLauncher {
	GameView view;
	Game game;
	
	public GameLauncher() {
		view = new GameView();
		game = view.makeGame();
		boolean didGameEnd = false;
		while(true) {
			
			/* ends loop if game has ended */
			if(getGame().checkIfGameEnded()) {
				didGameEnd = true;
				break;
			} 
			
			view.printBoard(getGame());
			int columnToInsertIn = -1;
			
			/* checks if player inputted number in the correct format */
			try {
				columnToInsertIn = view.playTurn(game.getTurnPlayer().getName());
			} catch (NumberFormatException el) {
				System.out.println("PLEASE ENTER A NUMBER");
			}
			/* if insertDisc was succesful continue on with the game */
			try {
				if (getGame().insertDisc(columnToInsertIn)) {
					break;
				}
				getGame().endTurn();
			}	catch (ColumnIsFullException e) {
					System.out.println("THIS COLUMN IS FULL");
				}
			/* if user inputted a valid column continue on with the game */
			catch (InvalidColumnException e2) {
				System.out.println("PLEASE ENTER A VALID COLUMN.");
			}
			
			if(didGameEnd) {
				System.out.println("GAME ENDED, THERE IS NO WINNER");
			} else {
				view.printBoard(getGame());
				System.out.println("************************************");
				System.out.println("************************************");
				System.out.println(getGame().getTurnPlayer().getName() + " HAS WON!!!!!!!!");
				System.out.println("************************************");
				System.out.println("************************************");
			}
		}
	}
	
	public static void main(String[] args) {
		new GameLauncher();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
