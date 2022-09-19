package ham;

public class Cell extends Monster {
	
	private int revive_left;

	public Cell() {		
		
		revive_left = 1;
		
	}

	public Cell(String name, int max_hp, int y_coord, int x_coord, double base_chance, 
			int do_damage_min,int do_damage_max, int direction, double chance_to_regen, 
			int min_regen, int max_regen, int revive_left) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_regen,
				min_regen, max_regen);

		this.revive_left = revive_left;
		
	}

	@Override
	public String get_data() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int get_current_hp() {
		
		int solution = super.get_current_hp();
		
		if (solution <= 10 && revive_left > 0) {
			System.out.println(super.get_name() + " is revived and emerges from his grave");
			revive_left -= 1;
			solution = 40;
		}
		
		return solution;
	}
}
