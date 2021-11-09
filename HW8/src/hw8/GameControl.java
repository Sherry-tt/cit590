package hw8;


import java.util.Random;
import java.util.Scanner;

public class GameControl {
	
	/**
	 * Computer player
	 */
	Computer computer;
	
	/**
	 * Human player
	 */
	Human human;
	
	/**
	 * A random number generator to be used for returning random dice result (1-6) for both computer and human player
	 */
	Random random = new Random();

	
	/**
	 * The main method just creates a GameControl object and calls its run method
	 * @param args not used
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println("Welcome to Pig!");
		System.out.println();
		GameControl gc = new GameControl();
		while (true) {
			gc.run(sc);
			System.out.println("--------------------");
			System.out.println("Do you want to play again?");
			
			boolean check = gc.askYesNo(sc);
			if (!check) {
				System.out.println("Goodbye!");
				sc.close();
				break;
			}
		}
	}

	
	/**
     * Calls methods to perform each of the following actions:
     * - Ask user to input the name for human player, and print welcome with the name
     * - Create the players (one human and one computer)
     * - Control the play of the game
     * - Print the final results
	 * @param sc to use for getting user input
	 */
	public void run(Scanner sc) {

		String name = printWelcome(sc);
		createPlayers(name);

		boolean gameComplete = false;
		while (!gameComplete) {
			int computerScore = this.computer.move(this.human, random);
			System.out.println("Computer's score in this round: " + computerScore);
			System.out.println("Computer's total score is: " + this.computer.getScore());
			System.out.println();
			int humanScore = this.human.move(this.computer, random, sc);
			System.out.println(name + "'s score in this round: " + humanScore);
			System.out.println("Human's total score is: " + this.human.getScore());
			System.out.println();
			//if complete, then true
			gameComplete = checkWinningStatus();
		}
		printResults();
		printWinner();
	}

	/**
	 * Ask user to input the name for human player
	 * @param sc
	 * @return the name
	 */
	public String printWelcome(Scanner sc) {
		System.out.println("Please input your name");
		String name = sc.next();
		System.out.println("Welcome " + name);
		return name;
	}


	/**
     * Creates one human player with the given humanName, and one computer player with a name.
     * @param humanName for human player
     */
	public void createPlayers(String humanName) {
		this.human = new Human(humanName);
		this.computer = new Computer();
	}
	
	/**
     * Checks if a winning status has been achieved
     * @return true if one player has won the game
     */
	public boolean checkWinningStatus() {
		int computerS = this.computer.getScore();
		int humanS = this.human.getScore();
		if (computerS < 50 && humanS < 50) return false;
		else if (computerS < 50 && humanS >= 50) return true;
		else if (computerS >= 50 && humanS < 50) return true;
		else{
			// both of computer and human are greater than 50
			if (computerS == humanS ) return false;
			else return true;
		}
	}
	
	/**
	 * Prints the final scores of the human player and computer player
	 */
	public void printResults() {
		System.out.println("Human gets " + this.human.getScore());
		System.out.println("Computer gets " + this.computer.getScore());
	}
	
	/**
     * Determines who won the game, and prints the results
     */
	public void printWinner() {
		String winner = this.computer.getScore() > this.human.getScore() ? "Computer" : "Human";
		System.out.println(winner + " wins!");
	}
	
	/**
	 * If the user responds with a string "y" or "Y", the function returns True. 
	 * If the  user responds with a string "n" or "N", the function returns False. 
	 * Any other response will cause the question to be repeated until the user provides an acceptable response. 
	 * @param sc to use for getting user input
	 * @return true if user responds with "y" or "Y"
	 */
	public boolean askYesNo(Scanner sc) {
		String answer = sc.next();
		while (!answer.startsWith("y") && !answer.startsWith("Y") && !answer.startsWith("n") && !answer.startsWith("N")){
			System.out.println("Do you want to play again?");

			answer= sc.next();
		}
		if (answer.startsWith("y") || answer.startsWith("Y")) return true;
		else return false;
	}
}
