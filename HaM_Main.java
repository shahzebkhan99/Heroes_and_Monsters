package ham;

import java.util.Random;
import java.util.Scanner;

public class HaM_Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Game game = new Game(scan);
		game.play_game();	
	}
	
}
