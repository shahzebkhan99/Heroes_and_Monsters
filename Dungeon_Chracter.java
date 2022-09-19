package ham;

import java.util.Random;

public abstract class Dungeon_Chracter implements iDC {
	private String name;
	private char symbol;
	private int max_hp;
	private int current_hp;
	private int x_coord;
	private int y_coord;
	private double base_chance;
	private int do_damage_max, do_damage_min;
	private int direction;
	
	public Dungeon_Chracter() {
		this.name = "Ralph";
		this.symbol = this.name.charAt(0);
		this.max_hp = 20;
		this.current_hp = this.max_hp;
		this.y_coord = 0;
		this.x_coord = 0;
		this.base_chance = .6;
		this.do_damage_min = 9;
		this.do_damage_max = 10;
		this.direction = 3; //south
	}
	public Dungeon_Chracter(String name, int max_hp, int y_coord, int x_coord,
			double base_chance, int do_damage_min, int do_damage_max, int direction) {
		this.name = name;
		this.symbol = this.name.charAt(0);
		this.max_hp = max_hp;
		this.current_hp = this.max_hp;
		this.y_coord = y_coord;
		this.x_coord = x_coord;
		this.base_chance = base_chance;
		this.do_damage_min = do_damage_min;
		this.do_damage_max = do_damage_max;
		this.direction = direction; //south
	}

	@Override
	public void move() {
		int [] new_increment =this.new_increment();
		this.y_coord = new_increment [0];
		this.x_coord = new_increment [1];
	}

	@Override
	public int[] attack() {
		int [] fire_solution = new int [3];
		int [] new_increment = this.new_increment();
		fire_solution[0] = new_increment[0];
		fire_solution[1] = new_increment[1];
		fire_solution[2] =0;
		if (this.successful_action(base_chance)) {
			
			fire_solution[2] = get_random_int(this.do_damage_min, this.do_damage_max);			
		}
		
		System.out.println(this.name + " attacks for " + fire_solution[2]);
		return fire_solution;
	}

	@Override
	public void change_direction(int direction) {
		this.direction = this.set_vars(1, 4, direction);		
	}

	@Override
	public int[] get_location() {
		int[] location = {this.y_coord, this.x_coord};		
		return location;
	}

	@Override
	public void hit(int damage) {
		this.current_hp = set_vars(0, this.max_hp, this.current_hp - damage);
		
	}

	@Override
	public boolean is_alive() {
		return this.current_hp > 0;
	}

	@Override
	public int set_vars(int min, int max, int var) {
		if (var < min) {
			var = min;
		}else if (var > max) {
			var = max;
		}
		return var;
	}

	@Override
	public boolean successful_action(double chance) {
		Random rand = new Random();
		
		return rand.nextDouble() < chance;
	}
	
	public String get_name(){
		return this.name;
	}
	public char get_symbol() {
		return this.symbol;
	}
	public int get_direction() {
		return this.direction;
	}
	
	public int get_current_hp() {
		return this.current_hp;
	}
	
	public int get_max_hp() {
		return this.max_hp;
	}
	
	@Override
	public String toString() {
		String read_out = this.name + "\n";
		read_out += "Facing " + facing() + "\n";
		read_out += "Has " + this.current_hp + " hp\n";
		return read_out;
	}
	public String facing() {
		switch(this.direction) {
		case 1:
			return "North";
			
		case 2:
			return "East";
			
		case 3:
			return "South";
		
		case 4:
			return "West";
			
		default:
			return "Error";
		}
	}
	private int[] increment_east() {
		int x = set_vars(0,9,this.x_coord+1);
		int[] new_increment = {this.y_coord, x};
		return new_increment;
	}
	
	private int[] increment_west() {
		int x = set_vars(0,9,this.x_coord-1);
		int[] new_increment = {this.y_coord, x};
		return new_increment;
	}
	
	private int[] increment_south() {
		int y = set_vars(0,9,this.y_coord+1);
		int[] new_increment = { y, this.x_coord};
		return new_increment;
	}
	
	private int[] increment_north() {
		int y = set_vars(0,9,this.y_coord-1);
		int[] new_increment = { y, this.x_coord};
		return new_increment;
	}
	
	private int[] new_increment() {
		int [] increment_array = new int [2];
		switch(this.direction) {
		case 1:
			increment_array = this.increment_north();
			break;
		case 2:
			increment_array = this.increment_east();
			break;
		case 3:
			increment_array = this.increment_south();
			break;
		case 4:
			increment_array = this.increment_west();
			break;
			
			default:
				increment_array [0] = this.y_coord;
				increment_array [1] = this.x_coord;
		}
		return increment_array;
	}

	public static int get_random_int(int min, int max) {
		Random randomnum = new Random();
		int r = Math.abs(randomnum.nextInt());
		r = ( r % (max - min + 1)) + min;
		return r;
	}

}
