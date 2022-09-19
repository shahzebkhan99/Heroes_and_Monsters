package ham;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private Scanner scan;
	private boolean keep_playing = true, game_over = false;
	private int turn;
	private Dungeon_Chracter dc1, dc2;
	private Board board;
	private int rows = 10, columns = 10;
	private int hp = 50;
	private double base =.7, chance_to_regen = .5;
	private int min_regen = 1, max_regen = 5, min_dam = 2, max_dam = 10, ycoor = 0;//c
	private int xcoor = 0;//c
	String name = "";
	int facing = 1;//c
	
	public Game(Scanner scan) {
		this.scan = scan;
		
	}
	
	
	private void check_game_over() {
		if(!dc1.is_alive() || dc2.is_alive() == false) {
			this.game_over = true;
			if(dc1.is_alive()) {
				System.out.println(dc1.get_name() + " wins!");
			}else {
				System.out.println(dc2.get_name() + " wins!");
			}
		}	
	}
	private void combat(Dungeon_Chracter attack, Dungeon_Chracter defense) {
		int[] solution = attack.attack();
		int size = solution.length/2;
		boolean hit = false;
		int[] location = defense.get_location();
		int damage = solution[solution.length-1];
		if(damage > 0) {
			for(int i = 0; i < size;i = i+2) {
				if(location[0] == solution[i] &&
				   location[1] == solution[i+1]) {
					hit = true;
					break;
				}
			}			
		}
		if(hit) {
			System.out.println(attack.get_name() + 
					" hits and does " + damage + " damage");
			defense.hit(damage);
		}else {
			System.out.println(attack.get_name() + " misses");
		}			
	}
	private void computer_change_direction() {
		int choice = get_random_int(1, 4);
		dc2.change_direction(choice);
		System.out.println(dc2.get_name() + " changes direction and faces " + dc2.facing());
	}
	private void display_info() {
		System.out.println(dc1.toString());
		System.out.println(dc2.toString());
	}
	private void human_change_direction() {
		System.out.println("Which direction do you wish to face?");
		System.out.println("1 = North, 2 = East, 3 = South, 4 = West");
		int choice = get_user_input(scan, 1, 4);
		dc1.change_direction(choice);		
	}
	private void initialize_characters() {
		initialize_human_character();
		initialize_computer_character();
	}
	
	private void initialize_computer_character() {
		this.name = "Slag";
		this.xcoor = this.columns-1;
		this.ycoor = this.rows -1;
		int choice = get_random_int(6, 6);
		this.dc2 = instantiate_character(choice);
		
		board.mark_board(ycoor, xcoor, dc2.get_symbol());
	}
	private Dungeon_Chracter instantiate_character(int choice) {
		Dungeon_Chracter dc;
		switch(choice) {
		case 1:
			System.out.println("A warrior enters the arena!");
			dc = instantiate_warrior();
			break;
		case 2:
			System.out.println("A troll enters the arena!");
			dc = instantiate_troll();
			break;
		case 3:
			System.out.println("A cleric enters the arena!");
			dc = instantiate_cleric();
			break;
		case 4:
			System.out.println("An orc emerges from the swamp!");
			dc = instantiate_orc();
		case 5:
			System.out.println("An Super Chicken emerges from the other space!");
			dc = instantiate_super_chicken();
		case 6:
			System.out.println("Jett emerges from the graveyard!");
			dc = instantiate_jett();
			break;
			default:
				dc = null;
		}
		return dc;
	}
	private void initialize_game() {
		this.turn = 0;
		this.game_over = false;
		this.board = new Board(this.rows, this.columns);
		initialize_characters();
		board.display_board();
	}
	private void initialize_human_character() {
		System.out.println("What is your name?");
		this.name = scan.nextLine();
		this.xcoor = 0;
		this.ycoor = 0;
		System.out.println("Do you want to be a \nWarrior (1) or\nTroll (2) or\nCleric (3) or \nOrc (4) or \nSuper Chicken (5) or \nJett (6) ");
		int choice = get_user_input(scan, 1, 6);
		
		
		dc1 = instantiate_character(choice);
		
		board.mark_board(ycoor, xcoor, dc1.get_symbol());
	}
	private Dungeon_Chracter instantiate_cleric() {
		double chance_to_block = .1;
		double chance_to_heal = .3;
		int min_heal = 8;
		int max_heal = 10;
		Dungeon_Chracter dc = new Cleric(this.name,this.hp,this.ycoor,this.xcoor,
				this.base,this.min_dam,this.max_dam,this.facing,chance_to_block,
				chance_to_heal,min_heal,max_heal);
		return dc;
	}
	private Dungeon_Chracter instantiate_orc() {
		double combo_chance = 1;
		Dungeon_Chracter dc = new Orc(this.name,this.hp,this.ycoor,this.xcoor,
				this.base,this.min_dam,this.max_dam,this.facing,this.chance_to_regen,
				this.min_regen,this.max_regen,combo_chance);
		return dc;
	}
	private Dungeon_Chracter instantiate_troll() {
		double chance_to_roll = .5;
		Dungeon_Chracter dc = new Troll(this.name,this.hp,this.ycoor,this.xcoor,
				this.base,this.min_dam,this.max_dam,this.facing,this.chance_to_regen,
				this.min_regen,this.max_regen,chance_to_roll);
		return dc;
	}
	private Dungeon_Chracter instantiate_warrior() {
		double chance_to_block = .2;
		double chance_to_crit = .4;
		int max_cool_down = 3;
		Dungeon_Chracter dc = new Warrior(this.name,this.hp,this.ycoor,this.xcoor,
				this.base,this.min_dam,this.max_dam,this.facing,
				chance_to_block,chance_to_crit,max_cool_down);
		return dc;
	}
	private Dungeon_Chracter instantiate_super_chicken() {
		double chance_to_super_punch = 0.9;
		double chance_to_block = .4;
		Dungeon_Chracter dc = new SuperChicken(this.name,this.hp,this.ycoor,this.xcoor,
				this.base,this.min_dam,this.max_dam,this.facing,
				chance_to_block,chance_to_super_punch);
		return dc;
	}
	
	private Dungeon_Chracter instantiate_jett() {
		double chance_to_block = 0.1;
		double chance_to_absorb = 0.7;
		Dungeon_Chracter dc = new Jett(this.name,this.hp,this.ycoor,this.xcoor,
				this.base,this.min_dam,this.max_dam,this.facing,
				chance_to_block, chance_to_absorb);
		return dc;
	}
	
	private void collision_avoidance(Dungeon_Chracter mover,
			Dungeon_Chracter occupied) {		
		int[] o_location = occupied.get_location();
		int facing = mover.get_direction();
		int[] m_location1 = mover.get_location();
		board.mark_board(m_location1[0], m_location1[1], '~');
		mover.move();
		int[] m_location = mover.get_location();
		if(m_location[0] == o_location[0] && m_location[1] == o_location[1] ) {
			if(facing > 2) {
				mover.change_direction(facing -2);				
			}else {
				mover.change_direction(facing +2);
			}
			mover.move();
			mover.change_direction(facing);		
			System.out.println("Invalid move you have been moved back");
		}
		int[] m_location2 = mover.get_location();
		board.mark_board(m_location2[0], m_location2[1], mover.get_symbol());		
	}
	
	private void take_computer_turn() {
		int choice = get_random_int(1, 3);
		/* 1 = move
		 * 2 = combat
		 * 3 = change direction
		 * 
		 */
		if(choice == 1) {
			System.out.println("Computer moves");
			this.collision_avoidance(dc2, dc1);			
		}else if(choice == 2) {
			System.out.println("Computer attacks");
			this.combat(dc2, dc1);
		}else {
			this.computer_change_direction();
		}		
	}
	private void take_human_turn() {
		String message = "You have three options:\n" + 
	    "Move (Enter 1)\n" +
		"Combat (Enter 2)\n" +
	    "Change direction (Enter 3)\n";
		System.out.println(message);
		int choice = get_user_input(scan, 1, 3);
		if(choice == 1) {			
			this.collision_avoidance(dc1, dc2);		
		}else if(choice == 2) {			
			this.combat(dc1, dc2);
		}else {
			this.human_change_direction();
		}//display board	
	}
	private void take_turns() {
		if(!this.game_over) {
			this.display_info();
			this.board.display_board();
			if((this.turn % 2)==0) {
				this.take_human_turn();
			}else {
				this.take_computer_turn();
			}
			this.turn++;
			this.check_game_over();
			//this.board.display_board();
		}		
	}
	public void play_game() {

		this.initialize_game();
		while(!game_over) {
			take_turns();
		}

	}


	public static int get_user_input(Scanner scan, int min, int max) {

		int val = min-1;

		while (val<min || val>max) {
			try {
				val = scan.nextInt();
			}catch (Exception e) {
				scan.nextLine();
				System.out.println("Not a valid input   Try again ");
			}finally {

			}
		}


		return val;

	}

	public static int get_random_int(int min, int max) {
		Random randomnum = new Random();
		int r = Math.abs(randomnum.nextInt());
		r = ( r % (max - min + 1)) + min;
		return r;


	}
}