package ham;

public class SuperChicken extends Hero {

	private double chance_to_super_punch;
	
	public SuperChicken() {
		
		this.chance_to_super_punch = 1;
		
	}

	public SuperChicken(String name, int max_hp, int y_coord, int x_coord, double base_chance, 
			int do_damage_min,int do_damage_max, int direction, double chance_to_block, 
			double chance_to_super_punch) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_block);
		
		this.chance_to_super_punch = chance_to_super_punch;
		
	}
	
	@Override
	public int[] attack() {
		int[] solution = super.attack();
		int damage = solution [2];
		if(super.successful_action(this.chance_to_super_punch)) {
			System.out.println(super.get_name() + " super punches the opponent");
			damage = 25;
		}

		solution[2] = damage;
		return solution;
	}

	@Override
	public String get_data() {
		// TODO Auto-generated method stub
		return null;
	}

}
