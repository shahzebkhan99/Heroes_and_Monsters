package ham;

public class Troll extends Monster {
	
	private double chance_to_roll;

	public Troll() {
		super();
		this.chance_to_roll = 1;
	}

	public Troll(String name, int max_hp, int y_coord, int x_coord, double base_chance, int do_damage_min,
			int do_damage_max, int direction, double chance_to_regen, int min_regen, int max_regen, double chance_to_roll) {
		super(name, max_hp, y_coord, x_coord, base_chance, do_damage_min, do_damage_max, direction, chance_to_regen,
				min_regen, max_regen);
		
		this.chance_to_roll = chance_to_roll;
		
	}

	@Override
	public String get_data() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int[] get_new_solution(int[] solution) {
		int[] new_solution = new int[5];
		new_solution[0] = solution [0];
		new_solution[1] = solution [1];
		new_solution[4] = solution [2];
		int facing = super.get_direction();
		switch(facing) {
		case 1:
			new_solution[2] = super.set_vars(0, 9, new_solution[0] - 1);
			new_solution[3] = new_solution[1];
			break;
		case 2:
			new_solution[2] = new_solution[0];
			new_solution[3] = super.set_vars(0, 9, new_solution[1] + 1);
			break;
		case 3:
			new_solution[2] = super.set_vars(0, 9, new_solution[0] + 1);
			new_solution[3] = new_solution[1];
			break;			
		case 4:
			new_solution[2] = new_solution[0];
			new_solution[3] = super.set_vars(0, 9, new_solution[1] - 1);
			break;			
		}
		return new_solution;
	}
	
	@Override
	public int[] attack() {
		int[] solution = super.attack();
		if(super.successful_action(chance_to_roll)) {
			System.out.println(super.get_name() + " rolls a boulder!");
			int[] new_solution = this.get_new_solution(solution);
			return new_solution;
		}else {
			return solution;
		}
	}

}
