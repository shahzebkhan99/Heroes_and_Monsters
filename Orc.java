package ham;

public class Orc extends Monster {
	
	private double combo_chance;
	
	public Orc() {
		this.combo_chance = 1;
	}

	public Orc(String name, int max_hp, int y_coord, int x_coord, double base_chance, int do_damage_min,
			int do_damage_max, int direction, double chance_to_regen, 
			int min_regen, int max_regen, double combo_chance) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_regen,
				min_regen, max_regen);
		this.combo_chance = combo_chance;
	}

	@Override
	public String get_data() {
		return null;
	}
	
	@Override
	public int[] attack() {
		double current_combo_chance = this.combo_chance;
		int[] solution = super.attack();
		int damage = solution[2];
		while (super.successful_action(current_combo_chance)) {			
			System.out.println("Why are you hitting yourself?");
			solution = super.attack();			
			solution[2] += damage;
			current_combo_chance /= 2;
		}
		return solution;		
	}

}
