package ham;

import java.util.Random;

public abstract class Monster extends Dungeon_Chracter {

	private double chance_to_regen;
	private int min_regen, max_regen;

	public Monster() {

		this.chance_to_regen = 0.5;
		this.min_regen = 1;
		this.max_regen = 5;

	}

	public Monster(String name, int max_hp, int y_coord, int x_coord, double base_chance, int do_damage_min,
			int do_damage_max, int direction,double chance_to_regen, int min_regen, int max_regen) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction);
		this.chance_to_regen = chance_to_regen;
		this.min_regen = min_regen;
		this.max_regen = max_regen;
	}

	@Override
	public void hit(int damage) {
		if(damage > 0) {
			super.hit(damage);
			if (this.is_alive() && super.successful_action(chance_to_regen)) {
				int regen = get_random_int(min_regen, max_regen);
				super.hit(regen*-1);
				System.out.println(super.get_name() + " regenerates for " + regen);
				
			}
		}
	}
	
	public static int get_random_int(int min, int max) {
		Random randomnum = new Random();
		int r = Math.abs(randomnum.nextInt());
		r = ( r % (max - min + 1)) + min;
		return r;
		
}

}
