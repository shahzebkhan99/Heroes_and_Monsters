package ham;

public class Warrior extends Hero {
	
	private double chance_to_crit;
	private int max_cool_down, current_cool_down;

	public Warrior() {
		this.chance_to_crit = 1;
		this.max_cool_down = 1;
		this.current_cool_down = 0;
	}

	public Warrior(String name, int max_hp, int y_coord, int x_coord, double base_chance, int do_damage_min,
			int do_damage_max, int direction, double chance_to_block, double chance_to_crit, int max_cool_down) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_block);
		this.max_cool_down = max_cool_down;
		this.current_cool_down = this.max_cool_down;		
		this.chance_to_crit = chance_to_crit;
	}

	@Override
	public String get_data() {
		return null;
	}
	
	@Override
	public int[] attack() {
		int[] solution = super.attack();
		int damage = solution[2];
		if (damage > 0 && current_cool_down <= 0 && super.successful_action(this.chance_to_crit)) {
			System.out.println(super.get_name() + " swings and critical damage's for");
			damage += damage * this.chance_to_crit;
			this.current_cool_down = this.max_cool_down;
		}else {
			this.current_cool_down --;
		}
		solution[2] = damage;
		return solution;
	} 

}
