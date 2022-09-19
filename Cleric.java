package ham;

import java.util.Random;

public class Cleric extends Hero {

	private double chance_to_heal;
	private int min_heal;
	private int max_heal;
	
	public Cleric() {
		this.chance_to_heal = 1;
		this.min_heal = 2;
		this.max_heal = 10;
	}

	public Cleric(String name, int max_hp, int y_coord, int x_coord, double base_chance,
			int do_damage_min,int do_damage_max, int direction,double chance_to_block, 
			double chance_to_heal, int min_heal,int max_heal) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_block);
		this.chance_to_heal = chance_to_heal;
		this.max_heal = max_heal;
		this.min_heal = min_heal;
	}

	@Override
	public String get_data() {		
		return null;
	}
	
	@Override
	public int[] attack() {
		int[] solution = super.attack();
		int max = solution.length;
		if (super.is_alive()) {
			int max_hp = super.get_max_hp();
			int cur_hp = super.get_current_hp();
			if((cur_hp/max_hp) < .5 && super.successful_action(chance_to_heal)) {
				System.out.println(super.get_name() + " heals");
				int heal = get_random_int (min_heal, max_heal);
				super.hit(heal*-1);
				solution[max] = 0;
			}
		}
		return solution;
	}
	
	public static int get_random_int(int min, int max) {
		Random randomnum = new Random();
		int r = Math.abs(randomnum.nextInt());
		r = ( r % (max - min + 1)) + min;
		return r;
	}

}
