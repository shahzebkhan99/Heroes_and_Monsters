package ham;

public class Chamber extends Monster {

	public double chance_to_absorb;

	public Chamber() {

		chance_to_absorb = 1;

	}

	public Chamber(String name, int max_hp, int y_coord, int x_coord, double base_chance, 
			int do_damage_min, int do_damage_max, int direction, double chance_to_regen, 
			int min_regen, int max_regen, double chance_to_absorb) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_regen,
				min_regen, max_regen);

		this.chance_to_absorb = chance_to_absorb;

	}

	@Override
	public String get_data() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void hit(int damage) {		
		if(damage > 0) {
			super.hit(damage);
			if(this.is_alive() && super.successful_action(chance_to_absorb)) {
				int regen = damage;
				super.hit(damage*-1);
				System.out.println(super.get_name() + " regenerates for " + regen );
			}
		}
	}

}
