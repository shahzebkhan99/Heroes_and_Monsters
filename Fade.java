package ham;

public class Fade extends Monster {

	public double chance_to_counter;

	public Fade() {

		chance_to_counter = 1;

	}

	public Fade(String name, int max_hp, int y_coord, int x_coord, double base_chance, 
			int do_damage_min, int do_damage_max, int direction, double chance_to_regen, 
			int min_regen, int max_regen, double chance_to_counter) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_regen,
				min_regen, max_regen);

		this.chance_to_counter = chance_to_counter;

	}

	@Override
	public String get_data() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hit(int damage) {
		if (damage > 0) {			
			if (super.successful_action(chance_to_counter)) {				
				System.out.println("Fade countered the attack");
				damage = 0;
			}
		}
	}
}
