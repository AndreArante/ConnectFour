package View;

import java.util.Scanner;
import java.util.StringJoiner;

import Model.Game;
import Model.Player;
import Model.PlayerType;
import Model.Disc;

public class GameView {
	Scanner sc;
	public GameView() {
		sc = new Scanner(System.in);
	}
	
	public Game makeGame() {
		System.out.println("HELLO AND WELCOME TO CONSOLE CONNECT FOUR");
		System.out.println("***************************************************************");
		System.out.println("FIRST PLAYER PLEASE WRITE YOUR NAME");
		String firstPlayerName = sc.nextLine();
		System.out.println("SECOND PLAYER PLEASE WRITE YOUR NAME");
		String secondPlayerName = sc.nextLine();
		Player player1 = new Player(firstPlayerName.toUpperCase(), PlayerType.PlayerOne),
				player2 = new Player(secondPlayerName.toUpperCase(), PlayerType.PlayerTwo);
		Game game = new Game(player1, player2);
		return game;
	}
	
	public void printBoard(Game game) {
		System.out.println("************************************************************");
		for (Disc[] row : game.getBoard()) {
			StringJoiner sj = new StringJoiner(" | ");
			for (Disc col : row) {
				if (col == null) {
					sj.add(" ");
				} else {
					sj.add(col.toString());
				}
			}
			System.out.println(sj.toString());
		}
	}
	
	public int playTurn(String playerName) throws NumberFormatException {
		System.out.println(
				"IT IS " + playerName + "'S TURN, PLEASE WRITE THE COLUMN'S NUMBER");
		int columnToInsertIn;
		String input=sc.nextLine();
		try {
			columnToInsertIn = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("PLEASE ENTER A NUMBER");
		}
		return columnToInsertIn;
	}


}
